package shady.samir.photolab.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class Methods {
    companion object{

        fun     printMSG(msg:String){
            Log.d(title,msg)
        }
        val title= "DataTest"
        private val sdfDate = SimpleDateFormat("dd MMM yyyy")
        private val sdfTime = SimpleDateFormat("hh:mm")
      fun  getMessageId(id1:String,id2:String):String{
            return  if (id1>id2){
                "$id1-$id2"
            }else{
                "$id2-$id1"
            }
        }

        fun getDateString(date: Date): String {
            return sdfDate.format(date)
        }
        fun getTimeString(date: Date): String {
            return sdfTime.format(date)
        }
    }
}
