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

import java.text.SimpleDateFormat
import java.util.*

object Log {
    const val ANSI_RESET = "\u001B[0m"

    // Text colours
    const val ANSI_BLACK = "\u001B[30m"
    const val ANSI_RED = "\u001B[31m"
    const val ANSI_GREEN = "\u001B[32m"
    const val ANSI_YELLOW = "\u001B[33m"
    const val ANSI_BLUE = "\u001B[34m"
    const val ANSI_PURPLE = "\u001B[35m"
    const val ANSI_CYAN = "\u001B[36m"
    const val ANSI_WHITE = "\u001B[37m"

    // Background colours
    const val ANSI_BLACK_BACKGROUND = "\u001B[40m"
    const val ANSI_RED_BACKGROUND = "\u001B[41m"
    const val ANSI_GREEN_BACKGROUND = "\u001B[42m"
    const val ANSI_YELLOW_BACKGROUND = "\u001B[43m"
    const val ANSI_BLUE_BACKGROUND = "\u001B[44m"
    const val ANSI_PURPLE_BACKGROUND = "\u001B[45m"
    const val ANSI_CYAN_BACKGROUND = "\u001B[46m"
    const val ANSI_WHITE_BACKGROUND = "\u001B[47m"

    public var USE_SHORT_COLOURS = true
    private var priorityLevel = 0
    private val priorities = arrayOf(
        "VERBOSE",
        "DEBUG",
        "INFO",
        "OK",
        "GOOD",
        "WARNING",
        "ERROR",
        "FATAL",
        "SILENT"
    )
    private val timeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS", Locale.getDefault())

    public fun v(src: Class<Any>, msg: String) = log(src.name, msg, 0)
    public fun d(src: Class<Any>, msg: String) = log(src.name, msg, 1)
    public fun i(src: Class<Any>, msg: String) = log(src.name, msg, 2)
    public fun ok(src: Class<Any>, msg: String) = log(src.name, msg, 3, ANSI_GREEN)
    public fun good(src: Class<Any>, msg: String) = log(src.name, msg, 4, ANSI_GREEN_BACKGROUND)
    public fun w(src: Class<Any>, msg: String) = log(src.name, msg, 5, ANSI_YELLOW)
    public fun e(src: Class<Any>, msg: String) = log(src.name, msg, 6, ANSI_RED)
    public fun f(src: Class<Any>, msg: String) = log(src.name, msg, 7, ANSI_RED_BACKGROUND)
    public fun s(src: Class<Any>, msg: String) = log(src.name, msg, 8, ANSI_WHITE_BACKGROUND)

    private fun log(name: String, msg: String, priority: Int, colour: String? = null) {
        if(priorityLevel > priority) return
        println(getMessage(name, msg, priority, colour))
    }

    internal fun getMessage(name: String, msg: String, priority: Int, colour: String? = null): String {
        if(colour == null) return "${timeFormat.format(System.currentTimeMillis())} | [${Thread.currentThread().name}] |-${priorities[priority]} in $name - $msg"
        // Display with pretty colours
        return if(USE_SHORT_COLOURS) "${timeFormat.format(System.currentTimeMillis())} | [${Thread.currentThread().name}] |-$colour${priorities[priority]}$ANSI_RESET in $name - $msg"
        else "$colour${timeFormat.format(System.currentTimeMillis())} | [${Thread.currentThread().name}] |-${priorities[priority]} in $name - $msg$ANSI_RESET"
    }

    public fun setPriorityLevel(level: Int) {
        priorityLevel = level
        if(priorityLevel > 8) priorityLevel = 8
        else if(priorityLevel < 0) priorityLevel = 0
        i(javaClass, "Log priority cutoff set to $priorityLevel")
    }
    public fun getPriorityLevel(): Int {
        return priorityLevel
    }
    public fun setPriority(name: String) {
        for ((i, p) in priorities.withIndex()) {
            if(p != name) continue
            setPriorityLevel(i)
            break // We've found what we're looking for, no need to finish the loop
        }
    }
    public fun getPriority(): String {
        return priorities[priorityLevel]
    }
    public fun getNames(): Array<String> = priorities

}