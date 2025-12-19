package com.kkh.multimodule.moduletest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kkh.multimodule.feature.test.navigation.testNavigation
import com.kkh.multimodule.navigation.Route

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