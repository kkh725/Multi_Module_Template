package com.kkh.feature.auth.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.kkh.feature.auth.AuthRoute
import com.kkh.navigation.AuthGraph
import com.kkh.navigation.AuthGraphBaseRoute
import com.kkh.navigation.AuthRoute as AuthRouteKey

fun EntryProviderScope<NavKey>.authEntry() {
    entry<AuthRouteKey> { AuthRoute() }
    entry<AuthGraphBaseRoute> { AuthRoute() }
    entry<AuthGraph.LoginRoute> { backStackEntry ->
        // LoginRoute with parameter
        AuthRoute()
    }
    entry<AuthGraph.VerificationRoute> { AuthRoute() }
    entry<AuthGraph.SignUpRoute> { AuthRoute() }
}