package com.cajusoftware.fakedocumentgenerator.generators.rg

import com.cajusoftware.fakedocumentgenerator.masks.MaskEnum
import com.cajusoftware.fakedocumentgenerator.masks.Mask

interface RgGenerator {
    fun generateRg(): String
    suspend fun generateRgSet(quantity: Int): Set<String>

    class Builder {
        private val rgGenerator = RgGeneratorImpl()

        fun withSymbols(value: Boolean) = apply {
            if (value)
                rgGenerator.mask = Mask(MaskEnum.RG)
        }

        fun setPrefix(prefix: String) = apply {
            rgGenerator.prefix = prefix
        }

        fun setSuffix(suffix: String) = apply {
            rgGenerator.suffix = suffix
        }

        fun build() = rgGenerator as RgGenerator
    }
}