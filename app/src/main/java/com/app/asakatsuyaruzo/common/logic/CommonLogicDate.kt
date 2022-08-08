package com.app.asakatsuyaruzo.common.logic

fun commonTranslateTimeIntToString(hour:Int?, minute:Int?):String{

    return if(hour==null||minute==null){
        ""
    }else{
        commonTranslateNumberIntTo2DigitString(hour)+":"+commonTranslateNumberIntTo2DigitString(minute)
    }
}

fun commonTranslateNumberIntTo2DigitString(number:Int):String{
    return "0"+number.toString().substring(("0$number").length - 2)
}