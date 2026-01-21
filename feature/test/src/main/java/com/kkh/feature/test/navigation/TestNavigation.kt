package com.kkh.feature.test.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.kkh.feature.test.TestRoute as TestScreen
import com.kkh.navigation.TestRoute
import com.kkh.navigation.PauseRoute

fun EntryProviderScope<NavKey>.testEntry() {
    entry<TestRoute> { TestScreen() }
    entry<PauseRoute> { TestScreen() }
}