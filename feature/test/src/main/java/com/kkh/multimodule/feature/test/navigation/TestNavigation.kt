package com.kkh.multimodule.feature.test.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kkh.multimodule.feature.test.TestRoute
import com.kkh.multimodule.navigation.TestRoute

fun NavGraphBuilder.testNavigation() {

    composable<TestRoute> { TestRoute {} }
}