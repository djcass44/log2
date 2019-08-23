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

import java.util.logging.Level
import java.util.logging.Logger

@Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate")
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
    private const val ANSI_RED = "\u001B[31m"
    private const val ANSI_GREEN = "\u001B[32m"
    private const val ANSI_YELLOW = "\u001B[33m"

    // Background colours
    private const val ANSI_WHITE_BACKGROUND = "\u001B[47m"

    /**
     * Toggle whether the console colours should be limited only to the level name
     * E.g. 1970-01-01 **WARNING** An error occurred! if enabled versus
     *      **1970-01-01 WARNING An error occurred!** when disabled
     */
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
    // Stores active log handlers
    private val handlers = hashMapOf<String, Logger>()

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
    public fun v(src: String, msg: String) = log(src, msg, Level.FINER)
    /**
     * Show a debug message
     * Indicates that something is working
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun d(src: String, msg: String) = log(src, msg, Level.FINEST)
    /**
     * Show an information message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun i(src: String, msg: String) = log(src, msg, Level.INFO)
    /**
     * Show an ok message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun ok(src: String, msg: String) = log(src, msg, Level.INFO, ANSI_GREEN)
    /**
     * Show a good message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun good(src: String, msg: String) = log(src, msg, Level.INFO, ANSI_GREEN)
    /**
     * Show an alert message
     * Useful for highlighting something to the user
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun a(src: String, msg: String) = log(src, msg, Level.WARNING, ANSI_YELLOW)
    /**
     * Show a warning message
     * Indicates that a task completed correctly or something went as expected
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun w(src: String, msg: String) = log(src, msg, Level.WARNING, ANSI_YELLOW)
    /**
     * Show an error message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun e(src: String, msg: String) = log(src, msg, Level.WARNING, ANSI_RED)
    /**
     * Show a fatal message
     * Fatal messages indicate critical failure that cannot be recovered from
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun f(src: String, msg: String) = log(src, msg, Level.SEVERE, ANSI_RED)
    /**
     * Show a silent message
     * Silent messages are always shown and cannot be hidden.
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    public fun s(src: String, msg: String) = log(src, msg, Level.SEVERE, ANSI_WHITE_BACKGROUND)

    internal fun log(name: String, msg: String, level: Level, colour: String? = null) {
        val logger = handlers[name] ?: run {
            // We couldn't find an existing logger, so lets make a new one
            val newLogger = Logger.getLogger(name).apply {
                // Remove the default handlers
                this.handlers.forEach { h -> this.removeHandler(h) }
                // Add our custom handler
                addHandler(LogHandler())
                useParentHandlers = false
            }
            handlers[name] = newLogger
            return@run newLogger
        }
        logger.log(level, msg, colour)
    }

    /**
     * Get the names of all log levels
     *
     * @return Array of String's containing each level
     */
    public fun getNames(): Array<String> = priorities

}