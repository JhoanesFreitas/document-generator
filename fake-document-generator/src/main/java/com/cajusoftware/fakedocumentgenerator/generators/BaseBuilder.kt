package com.cajusoftware.fakedocumentgenerator.generators

internal interface BaseBuilder<T, V> {
    fun withSymbols(value: Boolean): T
    fun setPrefix(prefix: String): T
    fun setSuffix(suffix: String): T
    fun build(): V
}