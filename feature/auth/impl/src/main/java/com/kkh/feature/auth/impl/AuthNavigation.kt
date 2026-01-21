package com.kkh.feature.auth.impl

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.kkh.navigation.AuthRoute
import com.kkh.navigation.AuthGraph
import com.kkh.navigation.AuthGraphBaseRoute

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