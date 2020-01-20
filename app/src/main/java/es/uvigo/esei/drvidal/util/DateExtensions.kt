package es.uvigo.esei.drvidal.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Denís Requejo on 2020-01-20.
 */
fun Long.transformToHoursAndMinutes() : String {
    return try {
        return String.format("%d horas, %d minutos",
            TimeUnit.MILLISECONDS.toHours(this),
            TimeUnit.MILLISECONDS.toMinutes(this) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(this))
        )
    } catch (e: Exception) {
        Log.e("getHoursAndMinutes", e.localizedMessage + "")
        "--"
    }
}

fun Long.transformToYearMonthDay() : String {
    return try {
        val sdf = SimpleDateFormat("dd/M/yyyy", Locale.FRANCE)
        return sdf.format(Date(this))
    } catch (e: Exception) {
        Log.e("transformToYearMonthDay", e.localizedMessage + "")
        "--"
    }
}