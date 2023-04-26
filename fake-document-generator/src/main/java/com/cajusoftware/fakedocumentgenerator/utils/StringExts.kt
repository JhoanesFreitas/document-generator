package com.cajusoftware.fakedocumentgenerator.utils

val String.withoutMask: String
    get() = this
        .replace(".", "").replace("-", "")
        .replace("(", "").replace(")", "")
        .replace("/", "").replace(" ", "")
        .replace("*", "")

fun String?.space(): String = "$this "
fun String?.spaceBeforeThat(): String = " $this"

fun String.concatenateSuffix(suffix: String?): String {
    return this
        .plus((suffix?.trim()?.spaceBeforeThat() ?: ""))
}