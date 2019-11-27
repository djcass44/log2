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

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.event.Level
import java.util.concurrent.ConcurrentHashMap

object Log {
    // Stores active log handlers
    private val handlers = ConcurrentHashMap<String, Logger>()

    /**
     * Show a verbose message
     *
     * @param src Calling class
     * @param msg Message to show
     */
    fun v(src: Class<out Any>, msg: String) = v(src.name, msg)
    /**
     * Show a debug message
     *
     * @param src Calling class
     * @param msg Message to show
     */
    fun d(src: Class<out Any>, msg: String) = d(src.name, msg)
    /**
     * Show an information message
     *
     * @param src Calling class
     * @param msg Message to show
     */
    fun i(src: Class<out Any>, msg: String) = i(src.name, msg)
    /**
     * Show an ok message
     * Indicates that something is working
     *
     * @param src Calling class
     * @param msg Message to show
     */
    fun ok(src: Class<out Any>, msg: String) = ok(src.name, msg)
    /**
     * Show a good message
     * Indicates that a task completed correctly or something went as expected
     *
     * @param src Calling class
     * @param msg Message to show
     */
    fun good(src: Class<out Any>, msg: String) = good(src.name, msg)
    /**
     * Show an alert message
     * Useful for highlighting something to the user
     *
     * @param src Calling class
     * @param msg Message to show
     */
    fun a(src: Class<out Any>, msg: String) = a(src.name, msg)
    /**
     * Show a warning message
     *
     * @param src Calling class
     * @param msg Message to show
     */
    fun w(src: Class<out Any>, msg: String) = w(src.name, msg)
    /**
     * Show an error message
     *
     * @param src Calling class
     * @param msg Message to show
     */
    fun e(src: Class<out Any>, msg: String) = e(src.name, msg)
    /**
     * Show a fatal message
     * Fatal messages indicate critical failure that cannot be recovered from
     *
     * @param src Calling class
     * @param msg Message to show
     */
    fun f(src: Class<out Any>, msg: String) = f(src.name, msg)
    /**
     * Show a silent message
     * Silent messages are always shown and cannot be hidden.
     *
     * @param src Calling class
     * @param msg Message to show
     */
    fun s(src: Class<out Any>, msg: String) = s(src.name, msg)

    /**
     * Show a verbose message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    fun v(src: String, msg: String) = log(src, msg, Level.TRACE)
    /**
     * Show a debug message
     * Indicates that something is working
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    fun d(src: String, msg: String) = log(src, msg, Level.DEBUG)
    /**
     * Show an information message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    fun i(src: String, msg: String) = log(src, msg, Level.INFO)
    /**
     * Show an ok message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    fun ok(src: String, msg: String) = log(src, msg, Level.INFO)
    /**
     * Show a good message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    fun good(src: String, msg: String) = log(src, msg, Level.INFO)
    /**
     * Show an alert message
     * Useful for highlighting something to the user
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    fun a(src: String, msg: String) = log(src, msg, Level.WARN)
    /**
     * Show a warning message
     * Indicates that a task completed correctly or something went as expected
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    fun w(src: String, msg: String) = log(src, msg, Level.WARN)
    /**
     * Show an error message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    fun e(src: String, msg: String) = log(src, msg, Level.WARN)
    /**
     * Show a fatal message
     * Fatal messages indicate critical failure that cannot be recovered from
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    fun f(src: String, msg: String) = log(src, msg, Level.ERROR)
    /**
     * Show a silent message
     * Silent messages are always shown and cannot be hidden.
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     */
    fun s(src: String, msg: String) = log(src, msg, Level.ERROR)

    private fun log(name: String, msg: String, level: Level) {
        val logger = handlers[name] ?: run {
            // We couldn't find an existing logger, so lets make a new one
            val newLogger = LoggerFactory.getLogger(name)
            handlers[name] = newLogger
            return@run newLogger
        }
        when(level) {
            Level.TRACE -> logger.trace(msg)
            Level.DEBUG -> logger.debug(msg)
            Level.INFO -> logger.info(msg)
            Level.WARN -> logger.warn(msg)
            Level.ERROR -> logger.error(msg)
            // assume info if we don't recognise the level
            else -> logger.info(msg)
        }
    }

}