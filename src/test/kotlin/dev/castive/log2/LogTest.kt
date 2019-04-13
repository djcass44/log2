/*
 *    Copyright 2019 Django Cass
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package dev.castive.log2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.text.SimpleDateFormat

class LogTest {
    @BeforeEach
    internal fun setUp() {
        Log.setPriorityLevel(0)
    }

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
        Log.ANSI_GREEN,
        Log.ANSI_RED,
        Log.ANSI_WHITE_BACKGROUND,
        Log.ANSI_YELLOW
    ])
    fun testColourChange(colour: String) {
        Log.USE_SHORT_COLOURS = false
        val log = Log.getMessage(javaClass.name, "Test message", 0, colour)
        println(log)
        assert(log.startsWith(colour))
        assert(log.endsWith(Log.ANSI_RESET))
    }
    @Test
    fun testNullColour() {
        Log.USE_SHORT_COLOURS = false
        val log = Log.getMessage(javaClass.name, "Test message", 0, null)
        println(log)
        // Non-coloured log should start with the year
        assertTrue(log.startsWith(SimpleDateFormat("yyyy").format(System.currentTimeMillis())))
        assertFalse(log.endsWith(Log.ANSI_RESET))
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
        assertEquals(name, Log.getPriority())
    }
}