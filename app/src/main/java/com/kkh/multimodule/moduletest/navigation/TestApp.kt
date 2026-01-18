package com.kkh.multimodule.moduletest.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.kkh.multimodule.designsystem.component.TestBottomSheet
import com.kkh.multimodule.navigation.Route
import com.kkh.multimodule.ui.TestBottomSheetScaffoldState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TestApp(
    navBackStack: NavBackStack<NavKey>,
    bottomSheetState: TestBottomSheetScaffoldState,
) {
    TestBottomSheet(
        bottomSheetState = bottomSheetState
    ){ paddingValues ->
        TestAppNavHost(
            navBackStack = navBackStack,
            modifier = Modifier.padding(paddingValues)
        )
    }
}