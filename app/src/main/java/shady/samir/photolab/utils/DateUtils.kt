package shady.samir.photolab.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    companion object{
        fun convertISOTimeToDate(isoTime: String): Date? {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
            //2021-01-10T11:40:27.000000Z
            //2021-02-28T07:28:38.000000Z
            //2021-08-22T10:25:26.000000Z
            var convertedDate: Date? = null
            try {
                convertedDate = sdf.parse(isoTime)
                convertedDate.hours += 2
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return convertedDate
        }


        fun convertTimeToDate(isoTime: String): Date? {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            //2021-03-24 15:11:43
            var convertedDate: Date? = null
            try {
                convertedDate = sdf.parse(isoTime)
                convertedDate.hours += 2
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return convertedDate
        }

        fun convertStringToDate(isoTime: String): Date? {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            //2021-03-24 15:11:43
            var convertedDate: Date? = null
            try {
                convertedDate = sdf.parse(isoTime)
                convertedDate.hours += 2
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return convertedDate
        }

        fun showDateTime(date: Date):String{
            val sdf = SimpleDateFormat("HH:mm aa dd MMM, yyyy")
            return sdf.format(date)
        }

        fun showTime(date: Date):String{
            val sdf = SimpleDateFormat("HH:mm aa")
            return sdf.format(date)
        }

        fun showTime2(date: Date):String{
            val sdf = SimpleDateFormat("HH:mm")
            return sdf.format(date)
        }

        fun showDate(date: Date):String{
            val sdf = SimpleDateFormat("dd MMM, yyyy")
            return sdf.format(date)
        }

        fun showDate2(date: Date):String{
            val sdf = SimpleDateFormat("dd/mm/yyyy")
            return sdf.format(date)
        }

    }
}
