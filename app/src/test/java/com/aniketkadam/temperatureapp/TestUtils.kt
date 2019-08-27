package com.aniketkadam.temperatureapp

fun getTextInFile(fileName: String): String? =
    ClassLoader.getSystemResource(fileName).readText()
