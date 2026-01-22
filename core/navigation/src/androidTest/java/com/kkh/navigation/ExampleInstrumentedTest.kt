package com.kkh.navigation

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.kkh.common.navigaiton.test", appContext.packageName)
    }
}