package com.cajusoftware.fakedocumentgenerator.generators.base

import com.cajusoftware.fakedocumentgenerator.utils.setInternalPrefix
import com.cajusoftware.fakedocumentgenerator.utils.setInternalSuffix
import com.cajusoftware.fakedocumentgenerator.utils.setMask

interface BaseGeneratorBuilder<V : BaseGenerator> {

    val documentGenerator: V

    fun withSymbols(value: Boolean) = apply {
        if (value) setMask()
    }

    fun setPrefix(prefix: String) = apply {
        setInternalPrefix(prefix)
    }

    fun setSuffix(suffix: String) = apply {
        setInternalSuffix(suffix)
    }

    fun build() = documentGenerator
}