package com.cajusoftware.fakedocumentgenerator.generators.base

import com.cajusoftware.fakedocumentgenerator.utils.setInternalPrefix
import com.cajusoftware.fakedocumentgenerator.utils.setInternalSuffix
import com.cajusoftware.fakedocumentgenerator.utils.setMask

interface BaseGeneratorBuilder<T : Generator> {

    val documentGenerator: T

    fun withSymbols() = apply {
        setMask()
    }

    fun setPrefix(prefix: String) = apply {
        setInternalPrefix(prefix)
    }

    fun setSuffix(suffix: String) = apply {
        setInternalSuffix(suffix)
    }

    fun build() = documentGenerator
}