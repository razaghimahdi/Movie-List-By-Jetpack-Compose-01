package com.example.core.constants


/**
 * remember, loging doesn't work when we use Kotlin library, because log needs Android library
 * */

class Logger(
    private val tag:String,
    private val isDebug:Boolean=true,
) {
    fun log(msg:String){
        if (!isDebug){

        }else{
            printLogD(tag,msg)
        }
    }

    companion object Factory{
        fun buildDebug(tag:String): Logger {
            return Logger(
                tag = tag,
                isDebug = true
            )
        }
        fun buildRelease(tag:String): Logger {
            return Logger(
                tag = tag,
                isDebug = false
            )
        }
    }

}

fun printLogD(tag: String?,msg: String?){
    println("$tag: $msg")
}