package com.cajusoftware.fakedocumentgenerator.generators.rg

import com.cajusoftware.fakedocumentgenerator.generators.BaseBuilder
import com.cajusoftware.fakedocumentgenerator.masks.Mask
import com.cajusoftware.fakedocumentgenerator.masks.MaskEnum

interface RgGenerator {
    fun generateRg(): String
    suspend fun generateRgSet(quantity: Int): Set<String>

    class Builder : BaseBuilder<Builder, RgGenerator> {
        private val rgGenerator = RgGeneratorImpl()

        override fun withSymbols(value: Boolean) = apply {
            if (value)
                rgGenerator.mask = Mask(MaskEnum.RG)
        }

        override fun setPrefix(prefix: String) = apply {
            rgGenerator.prefix = prefix
        }

        override fun setSuffix(suffix: String) = apply {
            rgGenerator.suffix = suffix
        }

        override fun build() = rgGenerator as RgGenerator
    }
}