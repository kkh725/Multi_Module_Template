package com.kkh.multimodule.moduletest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kkh.multimodule.feature.test.TestRoute
import com.kkh.multimodule.feature.test.navigation.testNavigation
import com.kkh.multimodule.navigaiton.AuthGraphBaseRoute

@Composable
fun TestAppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = AuthGraphBaseRoute,
        modifier = modifier,
    ) {
        testNavigation()
    }
}