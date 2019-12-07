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
     * @param t the exception (throwable) to log
     */
    fun v(src: Class<out Any>, msg: String, t: Throwable? = null) = v(src.name, msg, t)
    /**
     * Show a debug message
     *
     * @param src Calling class
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun d(src: Class<out Any>, msg: String, t: Throwable? = null) = d(src.name, msg, t)
    /**
     * Show an information message
     *
     * @param src Calling class
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun i(src: Class<out Any>, msg: String, t: Throwable? = null) = i(src.name, msg, t)
    /**
     * Show an ok message
     * Indicates that something is working
     *
     * @param src Calling class
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun ok(src: Class<out Any>, msg: String, t: Throwable? = null) = ok(src.name, msg, t)
    /**
     * Show a good message
     * Indicates that a task completed correctly or something went as expected
     *
     * @param src Calling class
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun good(src: Class<out Any>, msg: String, t: Throwable? = null) = good(src.name, msg, t)
    /**
     * Show an alert message
     * Useful for highlighting something to the user
     *
     * @param src Calling class
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun a(src: Class<out Any>, msg: String, t: Throwable? = null) = a(src.name, msg, t)
    /**
     * Show a warning message
     *
     * @param src Calling class
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun w(src: Class<out Any>, msg: String, t: Throwable? = null) = w(src.name, msg, t)
    /**
     * Show an error message
     *
     * @param src Calling class
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun e(src: Class<out Any>, msg: String, t: Throwable? = null) = e(src.name, msg, t)
    /**
     * Show a fatal message
     * Fatal messages indicate critical failure that cannot be recovered from
     *
     * @param src Calling class
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun f(src: Class<out Any>, msg: String, t: Throwable? = null) = f(src.name, msg, t)
    /**
     * Show a silent message
     * Silent messages are always shown and cannot be hidden.
     *
     * @param src Calling class
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun s(src: Class<out Any>, msg: String, t: Throwable? = null) = s(src.name, msg, t)

    /**
     * Show a verbose message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun v(src: String, msg: String, t: Throwable? = null) = log(src, msg, Level.TRACE, t)
    /**
     * Show a debug message
     * Indicates that something is working
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun d(src: String, msg: String, t: Throwable? = null) = log(src, msg, Level.DEBUG, t)
    /**
     * Show an information message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun i(src: String, msg: String, t: Throwable? = null) = log(src, msg, Level.INFO, t)
    /**
     * Show an ok message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun ok(src: String, msg: String, t: Throwable? = null) = log(src, msg, Level.INFO, t)
    /**
     * Show a good message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun good(src: String, msg: String, t: Throwable? = null) = log(src, msg, Level.INFO, t)
    /**
     * Show an alert message
     * Useful for highlighting something to the user
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun a(src: String, msg: String, t: Throwable? = null) = log(src, msg, Level.WARN, t)
    /**
     * Show a warning message
     * Indicates that a task completed correctly or something went as expected
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun w(src: String, msg: String, t: Throwable? = null) = log(src, msg, Level.WARN, t)
    /**
     * Show an error message
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun e(src: String, msg: String, t: Throwable? = null) = log(src, msg, Level.WARN, t)
    /**
     * Show a fatal message
     * Fatal messages indicate critical failure that cannot be recovered from
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun f(src: String, msg: String, t: Throwable? = null) = log(src, msg, Level.ERROR, t)
    /**
     * Show a silent message
     * Silent messages are always shown and cannot be hidden.
     *
     * @param src Calling classname or other indication of source
     * @param msg Message to show
     * @param t the exception (throwable) to log
     */
    fun s(src: String, msg: String, t: Throwable? = null) = log(src, msg, Level.ERROR, t)

    private fun log(name: String, msg: String, level: Level, t: Throwable? = null) {
        val logger = handlers[name] ?: run {
            // We couldn't find an existing logger, so lets make a new one
            val newLogger = LoggerFactory.getLogger(name)
            handlers[name] = newLogger
            return@run newLogger
        }
        when(level) {
            Level.TRACE -> logger.trace(msg, t)
            Level.DEBUG -> logger.debug(msg, t)
            Level.INFO -> logger.info(msg, t)
            Level.WARN -> logger.warn(msg, t)
            Level.ERROR -> logger.error(msg, t)
            // assume info if we don't recognise the level
            else -> logger.info(msg, t)
        }
    }

}