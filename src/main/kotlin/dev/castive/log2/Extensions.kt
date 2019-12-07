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

@file:Suppress("RedundantVisibilityModifier", "unused", "SpellCheckingInspection")

package dev.castive.log2

/**
 * Contains Kotlin extensions
 */

/**
 * Show a verbose message
 * @param src Calling class
 * @param t the exception (throwable) to log
 */
public fun String.logv(src: Class<out Any>, t: Throwable? = null) = Log.v(src.name, this, t)

/**
 * Show a debug message
 * @param src Calling class
 * @param t the exception (throwable) to log
 */
public fun String.logd(src: Class<out Any>, t: Throwable? = null) = Log.d(src.name, this, t)
/**
 * Show an information message
 * @param src Calling class
 * @param t the exception (throwable) to log
 */
public fun String.logi(src: Class<out Any>, t: Throwable? = null) = Log.i(src.name, this, t)
/**
 * Show an ok message
 * Indicates that something is working
 * @param src Calling class
 * @param t the exception (throwable) to log
 */
public fun String.logok(src: Class<out Any>, t: Throwable? = null) = Log.ok(src.name, this, t)
/**
 * Show a good message
 * Indicates that a task completed correctly or something went as expected
 * @param src Calling class
 * @param t the exception (throwable) to log
 */
public fun String.logg(src: Class<out Any>, t: Throwable? = null) = Log.good(src.name, this, t)
/**
 * Show an alert message
 * Useful for highlighting something to the user
 * @param src Calling class
 * @param t the exception (throwable) to log
 */
public fun String.loga(src: Class<out Any>, t: Throwable? = null) = Log.a(src.name, this, t)
/**
 * Show a warning message
 * @param src Calling class
 * @param t the exception (throwable) to log
 */
public fun String.logw(src: Class<out Any>, t: Throwable? = null) = Log.w(src.name, this, t)
/**
 * Show an error message
 * @param src Calling class
 * @param t the exception (throwable) to log
 */
public fun String.loge(src: Class<out Any>, t: Throwable? = null) = Log.e(src.name, this, t)
/**
 * Show a fatal message
 * Fatal messages indicate critical failure that cannot be recovered from
 * @param src Calling class
 * @param t the exception (throwable) to log
 */
public fun String.logf(src: Class<out Any>, t: Throwable? = null) = Log.f(src.name, this, t)
/**
 * Show a silent message
 * Silent messages are always shown and cannot be hidden
 * @param src Calling class
 * @param t the exception (throwable) to log
 */
public fun String.logs(src: Class<out Any>, t: Throwable? = null) = Log.s(src.name, this, t)