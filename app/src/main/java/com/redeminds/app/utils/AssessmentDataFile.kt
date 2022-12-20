package com.redeminds.app.utils

data class Assessment31(val question: String,val value: Int)

data class Assessment21(var id:Int =0, var question: String ="", var checkedId: Int = -1, var selectedIndex : Int =-1, var isAnswered : Boolean = false)