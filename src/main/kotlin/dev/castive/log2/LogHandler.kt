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

import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.ConsoleHandler
import java.util.logging.Formatter
import java.util.logging.LogRecord
import java.util.logging.SimpleFormatter

class LogHandler: ConsoleHandler() {
    override fun setOutputStream(out: OutputStream?) {
        // ensures that logs aren't written to stderr
        super.setOutputStream(System.out)
    }

    override fun setFormatter(newFormatter: Formatter?) {
        super.setFormatter(LogFormat())
    }
}
class LogFormat: SimpleFormatter() {
    private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS", Locale.ENGLISH)

    override fun format(record: LogRecord?): String {
        if(record == null) return ""
        val colour = if(record.parameters.isNotEmpty() && record.parameters[0] != null) record.parameters[0] else ""
        return "${sdf.format(record.millis)} | [${record.threadID}] |-$colour${record.level.localizedName}${Log.ANSI_RESET} in ${record.sourceClassName} - ${record.message}${Log.ANSI_RESET}\n"
    }
}