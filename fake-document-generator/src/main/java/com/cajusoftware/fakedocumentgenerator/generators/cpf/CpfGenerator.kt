package com.cajusoftware.fakedocumentgenerator.generators.cpf

import com.cajusoftware.fakedocumentgenerator.generators.base.BaseGeneratorBuilder
import com.cajusoftware.fakedocumentgenerator.generators.base.Generator

interface CpfGenerator : Generator {
    fun generateCpf(): String
    suspend fun generateCpfSet(quantity: Int): Set<String>

    class Builder : BaseGeneratorBuilder<CpfGenerator> {
        private var _documentGenerator: CpfGenerator? = null

        override val documentGenerator: CpfGenerator
            get() = _documentGenerator ?: createGenerator()

        private fun createGenerator(): CpfGenerator {
            _documentGenerator = CpfGeneratorImpl()
            return _documentGenerator!!
        }
    }
}