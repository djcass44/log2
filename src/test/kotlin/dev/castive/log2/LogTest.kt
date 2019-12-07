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

import org.junit.jupiter.api.Test

class LogTest {
    @Test
    fun manualColours() {
        Log.i(javaClass, "Info test")
        Log.ok(javaClass, "Ok test")
        Log.good(javaClass, "Good test")
        Log.a(javaClass, "Alert test")
        Log.w(javaClass, "Warning test")
        Log.e(javaClass, "Error test")
        Log.f(javaClass, "Fatal error test")
        Log.s(javaClass, "Silent test")
    }

    @Test
    fun `test exceptions are handled correctly`() {
        val e = Exception("Test exception")

        Log.i(javaClass, "Info test", e)
        "Info test 2".logi(javaClass, e)
    }
}