package com.cajusoftware.fakedocumentgenerator.generators.cnpj

import com.cajusoftware.fakedocumentgenerator.generators.FederationUnit
import com.cajusoftware.fakedocumentgenerator.masks.Mask
import com.cajusoftware.fakedocumentgenerator.masks.MaskEnum

interface CnpjGenerator {
    fun generateCnpj(): String
    suspend fun generateCnpjSet(quantity: Int): Set<String>

    class Builder {
        private val cnpjGenerator = CnpjGeneratorImpl()

        fun withSymbols(value: Boolean) = apply {
            if (value)
                cnpjGenerator.mask = Mask(MaskEnum.CNPJ)
        }

        fun setPrefix(prefix: String) = apply {
            cnpjGenerator.prefix = prefix
        }

        fun setSuffix(suffix: String) = apply {
            cnpjGenerator.suffix = suffix
        }

        fun setFederationUnit(federationUnit: FederationUnit) = apply {
            cnpjGenerator.federationUnit = federationUnit
        }

        fun build() = cnpjGenerator as CnpjGenerator
    }
}