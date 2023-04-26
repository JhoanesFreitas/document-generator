package com.cajusoftware.fakedocumentgenerator.utils

private const val FEDERATION_UNIT_KEY = 8
val Int.isFederationNumberKey: Boolean
    get() = this == FEDERATION_UNIT_KEY

fun getRandomNumberFromSequence(startNumber: Int = 0, endNumber: Int = 9) =
    (startNumber..endNumber).random()