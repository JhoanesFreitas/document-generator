package com.cajusoftware.fakedocumentgenerator.generators.cnpj

import com.cajusoftware.fakedocumentgenerator.generators.BaseBuilder
import com.cajusoftware.fakedocumentgenerator.masks.Mask
import com.cajusoftware.fakedocumentgenerator.masks.MaskEnum

interface CnpjGenerator {
    fun generateCnpj(): String
    suspend fun generateCnpjSet(quantity: Int): Set<String>

    class Builder : BaseBuilder<Builder, CnpjGenerator> {
        private val cnpjGenerator = CnpjGeneratorImpl()

        override fun withSymbols(value: Boolean) = apply {
            if (value)
                cnpjGenerator.mask = Mask(MaskEnum.CNPJ)
        }

        override fun setPrefix(prefix: String) = apply {
            cnpjGenerator.prefix = prefix
        }

        override fun setSuffix(suffix: String) = apply {
            cnpjGenerator.suffix = suffix
        }

        override fun build() = cnpjGenerator as CnpjGenerator
    }
}