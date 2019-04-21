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
    object Priority {
        const val VERBOSE = "VERBOSE"
        const val DEBUG = "DEBUG"
        const val INFO = "INFO"
        const val OK = "OK"
        const val GOOD = "GOOD"
        const val ALERT = "ALERT"
        const val WARNING = "WARNING"
        const val ERROR = "ERROR"
        const val FATAL = "FATAL"
        const val SILENT = "SILENT"
    }

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

    /**
     * Toggle whether the console colours should be limited only to the level name
     * E.g. 1970-01-01 **WARNING** An error occurred! if enabled versus
     *      **1970-01-01 WARNING An error occurred!** when disabled
     */
    public var USE_SHORT_COLOURS = true
    private var priorityLevel = 0
    private val priorities = arrayOf(
        Priority.VERBOSE,
        Priority.DEBUG,
        Priority.INFO,
        Priority.OK,
        Priority.GOOD,
        Priority.ALERT,
        Priority.WARNING,
        Priority.ERROR,
        Priority.FATAL,
        Priority.SILENT
    )
    private val timeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS", Locale.getDefault())

    /**
     * Show a verbose message
     *
     * @param src Calling class
     * @param msg Message to show
     */
    public fun v(src: Class<out Any>, msg: String) = v(src.name, msg)
    /**
     * Show a debug message
     *
     * @param src Calling class
     * @param msg Message to show
     */
    public fun d(src: Class<out Any>, msg: String) = d(src.name, msg)
    /**
     * Show an information message
     *
     * @param src Calling class
     * @param msg Message to show
     */
    public fun i(src: Class<out Any>, msg: String) = i(src.name, msg)
    /**
     * Show an ok message
     * Indicates that something is working
     *
     * @param src Calling class
     * @param msg Message to show
     */
    public fun ok(src: Class<out Any>, msg: String) = ok(src.name, msg)
    /**
     * Show a good message
     * Indicates that a task completed correctly or something went as expected
     *
     * @param src Calling class
     * @param msg Message to show
     */
    public fun good(src: Class<out Any>, msg: String) = good(src.name, msg)
    /**
     * Show an alert message
     * Useful for highlighting something to the user
     *
     * @param src Calling class
     * @param msg Message to show
     */
    public fun a(src: Class<out Any>, msg: String) = a(src.name, msg)
    /**
     * Show a warning message
     *
     * @param src Calling class
     * @param msg Message to show
     */
    public fun w(src: Class<out Any>, msg: String) = w(src.name, msg)
    /**
     * Show an error message
     *
     * @param src Calling class
     * @param msg Message to show
     */
    public fun e(src: Class<out Any>, msg: String) = e(src.name, msg)
    /**
     * Show a fatal message
     * Fatal messages indicate critical failure that cannot be recovered from
     *
     * @param src Calling class
     * @param msg Message to show
     */
    public fun f(src: Class<out Any>, msg: String) = f(src.name, msg)
    /**
     * Show a silent message
     * Silent messages are always shown and cannot be hidden.
     *
     * @param src Calling class
     * @param msg Message to show
     */
    public fun s(src: Class<out Any>, msg: String) = s(src.name, msg)

    /**
     * Show a verbose message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun v(src: String, msg: String) = log(src, msg, 0)
    /**
     * Show a debug message
     * Indicates that something is working
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun d(src: String, msg: String) = log(src, msg, 1)
    /**
     * Show an information message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun i(src: String, msg: String) = log(src, msg, 2)
    /**
     * Show an ok message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun ok(src: String, msg: String) = log(src, msg, 3, ANSI_GREEN)
    /**
     * Show a good message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun good(src: String, msg: String) = log(src, msg, 4, ANSI_GREEN_BACKGROUND)
    /**
     * Show an alert message
     * Useful for highlighting something to the user
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun a(src: String, msg: String) = log(src, msg, 5, ANSI_YELLOW_BACKGROUND)
    /**
     * Show a warning message
     * Indicates that a task completed correctly or something went as expected
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun w(src: String, msg: String) = log(src, msg, 6, ANSI_YELLOW)
    /**
     * Show an error message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun e(src: String, msg: String) = log(src, msg, 7, ANSI_RED)
    /**
     * Show a fatal message
     * Fatal messages indicate critical failure that cannot be recovered from
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun f(src: String, msg: String) = log(src, msg, 8, ANSI_RED_BACKGROUND)
    /**
     * Show a silent message
     * Silent messages are always shown and cannot be hidden.
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun s(src: String, msg: String) = log(src, msg, 9, ANSI_WHITE_BACKGROUND)

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

    /**
     * Set the level which to cutoff log messages
     *
     * @param level level of which logs below are cut off. Lower is more detailed logging
     */
    public fun setPriorityLevel(level: Int) {
        priorityLevel = level
        if(priorityLevel >= priorities.size) priorityLevel = priorities.size - 1
        else if(priorityLevel < 0) priorityLevel = 0
        i(javaClass.name, "Log priority cutoff set to $priorityLevel")
    }

    /**
     * Get the current log cutoff
     *
     * @return Int value of the cutoff level
     */
    public fun getPriorityLevel(): Int {
        return priorityLevel
    }

    /**
     * Set the level which to cutoff log messages by name
     *
     * @param name the name of the log level to set as the minimum
     */
    public fun setPriority(name: String) {
        for ((i, p) in priorities.withIndex()) {
            if(p != name) continue
            setPriorityLevel(i)
            break // We've found what we're looking for, no need to finish the loop
        }
    }

    /**
     * Get the name of the current log cutoff
     *
     * @return String containing the name
     */
    public fun getPriority(): String {
        return priorities[priorityLevel]
    }

    /**
     * Get the names of all log levels
     *
     * @return Array of String's containing each level
     */
    public fun getNames(): Array<String> = priorities

}