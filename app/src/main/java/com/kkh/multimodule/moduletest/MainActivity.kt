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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kkh.multimodule.effect.CommonEffect
import com.kkh.multimodule.effect.EffectHelper
import com.kkh.multimodule.moduletest.navigation.TestApp
import com.kkh.multimodule.moduletest.ui.theme.TestModuleTheme
import com.kkh.multimodule.navigation.AuthRoute
import com.kkh.multimodule.navigation.NavigationEvent
import com.kkh.multimodule.navigation.NavigationHelper
import com.kkh.multimodule.navigation.PauseRoute
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

            val navHostController = rememberNavController()
            val sheetState = rememberTestBottomSheetScaffoldState()
            val startRoute = if (autoLoginState == true) PauseRoute else AuthRoute

            LaunchedEffect(viewModel) {
                processSideEffect(sheetState)
                processNavigationEvent(navHostController)
            }

            TestModuleTheme {
                TestApp(
                    navHostController = navHostController,
                    startDestination = startRoute,
                    bottomSheetState = sheetState
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private suspend fun processSideEffect(bottomSheetState: TestBottomSheetScaffoldState) {
        effectHelper.effectFlow.collect { event ->
            when (event) {
                is CommonEffect.ShowSnackBar ->
                    bottomSheetState.sheetState.snackbarHostState.showSnackbar(event.text)

                CommonEffect.HideSnackBar ->
                    bottomSheetState.sheetState.snackbarHostState.currentSnackbarData?.dismiss()

                is CommonEffect.ShowBottomSheet -> {
                    bottomSheetState.updateContent(event.content)
                    bottomSheetState.sheetState.bottomSheetState.expand()
                }

                CommonEffect.HideBottomSheet -> bottomSheetState.sheetState.bottomSheetState.hide()
            }
        }
    }

    private suspend fun processNavigationEvent(navController: NavHostController) {
        navigationHelper.navigationFlow.collect { event ->

            when (event) {
                is NavigationEvent.To -> {
                    navController.navigate(event.route) {
                        if (event.popUpTo) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = false
                            }
                        }
                    }
                }

                NavigationEvent.Up -> {
                    val hasBackStack = navController.previousBackStackEntry != null

                    if (hasBackStack) {
                        navController.popBackStack()
                    } else {
                        val currentTime = System.currentTimeMillis()

                        if (currentTime - lastUpPressTime < 3000) {
                            this@MainActivity.finishAffinity()
                        } else {
                            lastUpPressTime = currentTime
                            effectHelper.sendEffect(CommonEffect.ShowSnackBar("한 번 더 누르면 종료됩니다."))
                        }
                    }
                }

                is NavigationEvent.TopLevelTo -> {
                    navController.navigate(event.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }

                is NavigationEvent.BottomBarTo -> {
                    navController.navigate(event.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = false
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    }
}
