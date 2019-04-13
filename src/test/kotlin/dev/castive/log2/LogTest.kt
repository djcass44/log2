package dev.castive.log2

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LogTest {
    @Test
    fun manualColours() {
        Log.USE_SHORT_COLOURS = true
        Log.i(javaClass, "Info test")
        Log.ok(javaClass, "Ok test")
        Log.good(javaClass, "Good test")
        Log.w(javaClass, "Warning test")
        Log.e(javaClass, "Error test")
        Log.f(javaClass, "Fatal error test")
        Log.s(javaClass, "Silent test")
    }
    @Test
    fun manualLongColours() {
        Log.USE_SHORT_COLOURS = false
        Log.i(javaClass, "Info test")
        Log.ok(javaClass, "Okay test")
        Log.good(javaClass, "Good test")
        Log.w(javaClass, "Warning test")
        Log.e(javaClass, "Error test")
        Log.f(javaClass, "Fatal error test")
        Log.s(javaClass, "Silent test")
    }
    @ParameterizedTest
    @ValueSource(strings = [
        "INFO",
        "VERBOSE",
        "ERROR",
        "SILENT",
        "DEBUG",
        "FATAL",
        "OK",
        "GOOD",
        "WARNING"
    ])
    fun testNamedSetter(name: String) {
        Log.setPriority(name)
        Assertions.assertEquals(name, Log.getPriority())
    }
}