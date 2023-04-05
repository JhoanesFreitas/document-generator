package com.cajusoftware.fakedocumentgenerator.utils

import com.cajusoftware.fakedocumentgenerator.generators.FederationUnit
import com.cajusoftware.fakedocumentgenerator.generators.base.BaseGenerator
import com.cajusoftware.fakedocumentgenerator.generators.base.BaseGeneratorBuilder
import com.cajusoftware.fakedocumentgenerator.generators.cnpj.CnpjGenerator
import com.cajusoftware.fakedocumentgenerator.generators.cnpj.CnpjGeneratorImpl
import com.cajusoftware.fakedocumentgenerator.generators.cpf.CpfGenerator
import com.cajusoftware.fakedocumentgenerator.generators.cpf.CpfGeneratorImpl
import com.cajusoftware.fakedocumentgenerator.generators.rg.RgGenerator
import com.cajusoftware.fakedocumentgenerator.generators.rg.RgGeneratorImpl
import com.cajusoftware.fakedocumentgenerator.masks.Mask
import com.cajusoftware.fakedocumentgenerator.masks.MaskEnum
import java.io.InvalidClassException

private fun BaseGenerator.getMask(): MaskEnum {
    return when (this) {
        is RgGenerator -> MaskEnum.RG
        is CpfGenerator -> MaskEnum.CPF
        is CnpjGenerator -> MaskEnum.CNPJ
        else -> throw InvalidClassException("Invalid BaseGenerator class")
    }
}

internal fun <V : BaseGenerator> BaseGeneratorBuilder<V>.setMask() {
    val mask = Mask(documentGenerator.getMask())
    when (this) {
        is RgGenerator.Builder -> (documentGenerator as RgGeneratorImpl).mask = mask
        is CpfGenerator.Builder -> (documentGenerator as CpfGeneratorImpl).mask = mask
        is CnpjGenerator.Builder -> (documentGenerator as CnpjGeneratorImpl).mask = mask
        else -> throw InvalidClassException("Invalid BaseGenerator class")
    }
}

internal fun <V : BaseGenerator> BaseGeneratorBuilder<V>.setInternalPrefix(prefix: String) {
    when (this) {
        is RgGenerator.Builder -> (documentGenerator as RgGeneratorImpl).prefix = prefix
        is CpfGenerator.Builder -> (documentGenerator as CpfGeneratorImpl).prefix = prefix
        is CnpjGenerator.Builder -> (documentGenerator as CnpjGeneratorImpl).prefix = prefix
        else -> throw InvalidClassException("Invalid BaseGenerator class")
    }
}

internal fun <V : BaseGenerator> BaseGeneratorBuilder<V>.setInternalSuffix(suffix: String) {
    when (this) {
        is RgGenerator.Builder -> (documentGenerator as RgGeneratorImpl).suffix = suffix
        is CpfGenerator.Builder -> (documentGenerator as CpfGeneratorImpl).suffix = suffix
        is CnpjGenerator.Builder -> (documentGenerator as CnpjGeneratorImpl).suffix = suffix
        else -> throw InvalidClassException("Invalid BaseGenerator class")
    }
}

fun BaseGeneratorBuilder<CpfGenerator>.setFederationUnit(federationUnit: FederationUnit) = apply {
    (documentGenerator as CpfGeneratorImpl).federationUnit = federationUnit
}