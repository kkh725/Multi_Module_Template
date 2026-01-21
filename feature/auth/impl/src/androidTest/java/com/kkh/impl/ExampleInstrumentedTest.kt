package com.kkh.feature.auth.impl

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.kkh.impl.test", appContext.packageName)
    }
}