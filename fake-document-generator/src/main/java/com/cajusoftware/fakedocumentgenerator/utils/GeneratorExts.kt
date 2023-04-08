package com.cajusoftware.fakedocumentgenerator.utils

import com.cajusoftware.fakedocumentgenerator.generators.FederationUnit
import com.cajusoftware.fakedocumentgenerator.generators.FederationUnitGroup
import com.cajusoftware.fakedocumentgenerator.generators.base.BaseGenerator
import com.cajusoftware.fakedocumentgenerator.generators.base.BaseGeneratorBuilder
import com.cajusoftware.fakedocumentgenerator.generators.base.Generator
import com.cajusoftware.fakedocumentgenerator.generators.cpf.CpfGenerator
import com.cajusoftware.fakedocumentgenerator.masks.Mask

internal fun <T : Generator> BaseGeneratorBuilder<T>.setMask() {
    val documentGeneratorAsBaseGenerator = convertGeneratorToBaseGenerator()
    documentGeneratorAsBaseGenerator.mask = Mask(documentGeneratorAsBaseGenerator.documentType)
}

internal fun <T : Generator> BaseGeneratorBuilder<T>.setInternalPrefix(prefix: String) {
    convertGeneratorToBaseGenerator().prefix = prefix
}

internal fun <T : Generator> BaseGeneratorBuilder<T>.setInternalSuffix(suffix: String) {
    convertGeneratorToBaseGenerator().suffix = suffix
}

fun BaseGeneratorBuilder<CpfGenerator>.setFederationUnit(federationUnit: FederationUnit) = apply {
    (documentGenerator as? FederationUnitGroup)?.federationUnit = federationUnit
}

private fun <T : Generator> BaseGeneratorBuilder<T>.convertGeneratorToBaseGenerator() =
    documentGenerator as BaseGenerator