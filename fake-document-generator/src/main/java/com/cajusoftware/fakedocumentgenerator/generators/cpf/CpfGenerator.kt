package com.cajusoftware.fakedocumentgenerator.generators.cpf

import com.cajusoftware.fakedocumentgenerator.generators.BaseBuilder
import com.cajusoftware.fakedocumentgenerator.generators.FederationUnit
import com.cajusoftware.fakedocumentgenerator.masks.Mask
import com.cajusoftware.fakedocumentgenerator.masks.MaskEnum

interface CpfGenerator {
    fun generateCpf(): String
    suspend fun generateCpfSet(quantity: Int): Set<String>

    class Builder : BaseBuilder<Builder, CpfGenerator> {
        private val cpfGenerator = CpfGeneratorImpl()

        fun setFederationUnit(federationUnit: FederationUnit) = apply {
            cpfGenerator.federationUnit = federationUnit
        }

        override fun withSymbols(value: Boolean) = apply {
            if (value)
                cpfGenerator.mask = Mask(MaskEnum.CPF)
        }

        override fun setPrefix(prefix: String) = apply {
            cpfGenerator.prefix = prefix
        }

        override fun setSuffix(suffix: String) = apply {
            cpfGenerator.suffix = suffix
        }

        override fun build() = cpfGenerator as CpfGenerator
    }
}