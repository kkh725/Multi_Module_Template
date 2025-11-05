package com.kkh.multimodule.moduletest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.kkh.multimodule.moduletest.navigation.TestAppNavHost
import com.kkh.multimodule.moduletest.ui.theme.TestModuleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TestModuleTheme {
                TestApp()
            }
        }
    }
}

@Composable
fun TestApp(){
    val navHostController = rememberNavController()
    TestAppNavHost(navHostController)
}
