package com.oveln.ovteam.utils

object CU {
    fun String.colorful():String {
        return this.replace("&" , "§")
    }
}