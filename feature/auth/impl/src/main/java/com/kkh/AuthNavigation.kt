package com.kkh

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.kkh.multimodule.navigation.AuthRoute

fun EntryProviderScope<NavKey>.authEntry() {
    entry<AuthRoute> { AuthRoute() }
}