package com.kkh.multimodule.moduletest.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.kkh.multimodule.designsystem.component.TestBottomSheet
import com.kkh.multimodule.ui.TestBottomSheetScaffoldState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestApp(
    navHostController: NavHostController,
    bottomSheetState: TestBottomSheetScaffoldState,
) {
    TestBottomSheet(
        bottomSheetState = bottomSheetState
    ){ paddingValues ->
        TestAppNavHost(
            navController = navHostController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}