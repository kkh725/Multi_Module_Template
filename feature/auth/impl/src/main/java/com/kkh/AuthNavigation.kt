package com.kkh

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.kkh.multimodule.navigation.AuthRoute
import com.kkh.multimodule.navigation.AuthGraph
import com.kkh.multimodule.navigation.AuthGraphBaseRoute

fun EntryProviderScope<NavKey>.authEntry() {
    entry<AuthRoute> { AuthRoute() }
    entry<AuthGraphBaseRoute> { AuthRoute() }
    entry<AuthGraph.LoginRoute> { backStackEntry ->
        // LoginRoute with parameter
        AuthRoute()
    }
    entry<AuthGraph.VerificationRoute> { AuthRoute() }
    entry<AuthGraph.SignUpRoute> { AuthRoute() }
}