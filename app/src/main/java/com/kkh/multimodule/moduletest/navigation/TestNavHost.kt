package com.kkh.multimodule.moduletest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.kkh.authEntry
import com.kkh.multimodule.feature.test.navigation.testEntry

@Composable
internal fun TestAppNavHost(
    navBackStack: NavBackStack<NavKey>,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavDisplay(
        backStack = navBackStack,
        onBack = onBack,
        modifier = modifier,
        entryProvider = entryProvider {
            testEntry()
            authEntry()
        }
    )
}