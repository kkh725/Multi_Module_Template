package com.kkh.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.kkh.feature.auth.navigation.authEntry
import com.kkh.feature.test.navigation.testEntry

@Composable
internal fun TestAppNavHost(
    navBackStack: NavBackStack<NavKey>,
    modifier: Modifier = Modifier,
) {
    NavDisplay(
        backStack = navBackStack,
        modifier = modifier,
        entryProvider = entryProvider {
            testEntry()
            authEntry()
        }
    )
}