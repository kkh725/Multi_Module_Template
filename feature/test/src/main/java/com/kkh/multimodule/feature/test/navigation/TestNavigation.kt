package com.kkh.multimodule.feature.test.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.kkh.multimodule.domain.model.history.HistoryModel
import com.kkh.multimodule.feature.test.TestRoute
import com.kkh.multimodule.navigaiton.AuthGraph
import com.kkh.multimodule.navigaiton.TestRoute
import com.kkh.multimodule.navigaiton.serializableType
import kotlin.reflect.typeOf

fun NavGraphBuilder.testNavigation() {

    composable<TestRoute> { TestRoute {} }
}