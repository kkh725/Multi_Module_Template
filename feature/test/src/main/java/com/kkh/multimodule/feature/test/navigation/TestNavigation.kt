package com.kkh.multimodule.feature.test.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.kkh.multimodule.feature.test.TestRoute as TestScreen
import com.kkh.multimodule.navigation.TestRoute
import com.kkh.multimodule.navigation.PauseRoute

fun EntryProviderScope<NavKey>.testEntry() {
    entry<TestRoute> { TestScreen {} }
    entry<PauseRoute> { TestScreen {} }
}