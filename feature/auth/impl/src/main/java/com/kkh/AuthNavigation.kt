package com.kkh

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.kkh.multimodule.navigaiton.AuthRoute

fun EntryProviderScope<NavKey>.authEntry() {
    entry<AuthRoute> { AuthRoute() }
}