package com.kkh.multimodule.moduletest.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.kkh.multimodule.designsystem.component.TestBottomSheet
import com.kkh.multimodule.navigaiton.Route
import com.kkh.multimodule.ui.TestBottomSheetScaffoldState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TestApp(
    navHostController: NavHostController,
    startDestination: Route,
    bottomSheetState: TestBottomSheetScaffoldState,
) {
    TestBottomSheet(
        bottomSheetState = bottomSheetState
    ){ paddingValues ->
        TestAppNavHost(
            navController = navHostController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues)
        )
    }
}