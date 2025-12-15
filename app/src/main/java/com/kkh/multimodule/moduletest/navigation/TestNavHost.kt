package com.kkh.multimodule.moduletest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.kkh.AuthRoute
import com.kkh.authEntry
import com.kkh.multimodule.feature.test.TestRoute
import com.kkh.multimodule.feature.test.navigation.testNavigation
import com.kkh.multimodule.navigaiton.AuthGraphBaseRoute
import com.kkh.multimodule.navigaiton.Route

@Composable
internal fun TestAppNavHost(
    navController: NavHostController,
    startDestination: Route,
    modifier: Modifier = Modifier,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        testNavigation()
    }

//    val backStack = rememberNavBackStack()
//
//    backStack.add(com.kkh.multimodule.navigaiton.AuthRoute)
//
//    NavDisplay(
//        backStack = backStack,
//        entryProvider = entryProvider {
//            authEntry()
//        }
//    )
}