package com.example.symptomtracker

import kotlin.test.DefaultAsserter.assertTrue
import kotlin.test.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", Greeting().greet().contains("Android"))
    }
}
