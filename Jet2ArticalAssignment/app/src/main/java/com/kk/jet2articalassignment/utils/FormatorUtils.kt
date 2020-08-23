package com.kk.jet2articalassignment.utils

import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object FormatorUtils {

    val suffix = arrayOf("yr", "mon", "days", "hr", "min", "sec", "sec")
    val FORMAT_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'"

    fun getFormatDuration(dateString: String): String {
        val targetFormat = SimpleDateFormat(FORMAT_DATE_PATTERN)
        targetFormat.setTimeZone(Calendar.getInstance().timeZone)
        try {
            val createdDate = targetFormat.parse(dateString)
            var count = 0
            var value = 0
            val calendar = Calendar.getInstance()
            calendar.time = createdDate
            val createDateData = getDateData(calendar)

            calendar.time = Date()
            val currentDateData = getDateData(calendar)

            count = 0
            while (count < createDateData.size) {
                if (createDateData[count] != currentDateData[count]) {
                    value = currentDateData[count] - createDateData[count]
                    break
                }
                count++
            }

            return value.toString() + " " + suffix[count]

        } catch (e: ParseException) {
            System.out.println(e)
        }

        return ""
    }

    private fun getDateData(calendar: Calendar): IntArray {
        val data = IntArray(6)
        data[0] = calendar.get(Calendar.YEAR)
        data[1] = calendar.get(Calendar.MONTH)
        data[2] = calendar.get(Calendar.DAY_OF_MONTH)
        data[3] = calendar.get(Calendar.HOUR_OF_DAY)
        data[4] = calendar.get(Calendar.MINUTE)
        data[5] = calendar.get(Calendar.SECOND)
        return data
    }

    fun convertNumnerIntoInternationalFormat(count: Long): String {
        if (count < 1000)
            return "" + count
        val exp = (Math.log(count.toDouble()) / Math.log(1000.0)).toInt()
        val format = DecimalFormat("0.##")
        val value = format.format(count / Math.pow(1000.0, exp.toDouble()))
        return String.format("%s%c", value, "KMB"[exp - 1])

    }
}