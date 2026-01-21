package com.kkh.multimodule.moduletest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.kkh.multimodule.effect.CommonEffect
import com.kkh.multimodule.effect.EffectHelper
import com.kkh.multimodule.moduletest.navigation.TestApp
import com.kkh.multimodule.moduletest.ui.theme.TestModuleTheme
import com.kkh.multimodule.navigation.AuthRoute
import com.kkh.multimodule.navigation.NavigationEvent
import com.kkh.multimodule.navigation.NavigationHelper
import com.kkh.multimodule.navigation.PauseRoute
import com.kkh.multimodule.ui.SnackBarState
import com.kkh.multimodule.ui.TestBottomSheetScaffoldState
import com.kkh.multimodule.ui.rememberTestBottomSheetScaffoldState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var effectHelper: EffectHelper
    @Inject
    lateinit var navigationHelper: NavigationHelper

    private val viewModel: MainViewModel by viewModels()

    private var lastUpPressTime = 0L
    private var isInitialized = false

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { !isInitialized }

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        lifecycleScope.launch {
            viewModel.autoLogin.collect {
                viewModel.initConfig()

                if (it != null) {
                    delay(300)
                    if (intent.extras != null) Log.d("intent", "onCreate: ${intent.extras}")

                    isInitialized = true
                }
            }
        }

        setContent {
            val autoLoginState by viewModel.autoLogin.collectAsStateWithLifecycle()

            val startRoute = if (autoLoginState == true) PauseRoute else AuthRoute
            val navBackStack = rememberNavBackStack(startRoute)
            val sheetState = rememberTestBottomSheetScaffoldState()

            LaunchedEffect(autoLoginState) {
                if (autoLoginState != null && navBackStack.isEmpty()) {
                    navBackStack.add(startRoute)
                }
            }

            LaunchedEffect(viewModel) {
                processSideEffect(sheetState)
                processNavigationEvent(navBackStack)
            }

            TestModuleTheme {
                TestApp(
                    navBackStack = navBackStack,
                    bottomSheetState = sheetState
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private suspend fun processSideEffect(bottomSheetState: TestBottomSheetScaffoldState) {
        effectHelper.effectFlow.collect { event ->
            val snackBarHostState = bottomSheetState.sheetState.snackbarHostState

            when (event) {
                is CommonEffect.ShowSnackBar ->{
                    snackBarHostState.currentSnackbarData?.dismiss()
                    val raw = SnackBarState.toRaw(event.snackBarState)
                    snackBarHostState.showSnackbar(raw)
                }

                CommonEffect.HideSnackBar ->
                    snackBarHostState.currentSnackbarData?.dismiss()

                is CommonEffect.ShowBottomSheet -> {
                    bottomSheetState.updateContent(event.content)
                    bottomSheetState.sheetState.bottomSheetState.expand()
                }

                CommonEffect.HideBottomSheet -> bottomSheetState.sheetState.bottomSheetState.hide()
            }
        }
    }

    private suspend fun processNavigationEvent(navBackStack: NavBackStack<NavKey>) {
        navigationHelper.navigationFlow.collect { event ->
            when (event) {
                is NavigationEvent.To -> {
                    if (event.popUpTo) {
                        // popUpTo를 사용하는 경우, 백스택의 첫 번째 항목까지 제거하고 새로운 route 추가
                        val firstRoute = if (navBackStack.isNotEmpty()) navBackStack[0] else null
                        navBackStack.clear()
                        if (firstRoute != null) {
                            navBackStack.add(firstRoute)
                        }
                    }
                    navBackStack.add(event.route)
                }

                NavigationEvent.Up -> {
                    val hasBackStack = navBackStack.size > 1

                    if (hasBackStack) {
                        navBackStack.removeAt(navBackStack.lastIndex)
                    } else {
                        val currentTime = System.currentTimeMillis()

                        if (currentTime - lastUpPressTime < 3000) {
                            this@MainActivity.finishAffinity()
                        } else {
                            lastUpPressTime = currentTime
                            effectHelper.sendEffect(CommonEffect.ShowSnackBar(SnackBarState.Info("한 번 더 누르면 종료됩니다.")))
                        }
                    }
                }

                is NavigationEvent.TopLevelTo -> {
                    // Top level navigation: 백스택을 완전히 교체
                    navBackStack.clear()
                    navBackStack.add(event.route)
                }

                is NavigationEvent.BottomBarTo -> {
                    // Bottom bar navigation: 백스택의 첫 번째 항목은 유지하고 나머지를 제거한 후 새로운 route 추가
                    val firstRoute = if (navBackStack.isNotEmpty()) navBackStack[0] else null

                    if (firstRoute != null && firstRoute != event.route) {
                        // 첫 번째 항목을 유지하고 나머지 제거
                        while (navBackStack.size > 1) {
                            navBackStack.removeAt(navBackStack.lastIndex)
                        }
                        // 새로운 route 추가
                        navBackStack.add(event.route)
                    } else if (firstRoute == null) {
                        // 백스택이 비어있으면 새로운 route 추가
                        navBackStack.add(event.route)
                    }
                    // 이미 같은 route면 아무것도 하지 않음
                }
            }
        }
    }
}
