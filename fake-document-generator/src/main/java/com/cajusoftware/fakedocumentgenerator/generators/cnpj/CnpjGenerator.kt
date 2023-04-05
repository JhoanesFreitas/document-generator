package com.cajusoftware.fakedocumentgenerator.generators.cnpj

import com.cajusoftware.fakedocumentgenerator.generators.base.BaseGeneratorBuilder
import com.cajusoftware.fakedocumentgenerator.generators.base.BaseGenerator

interface CnpjGenerator : BaseGenerator {
    fun generateCnpj(): String
    suspend fun generateCnpjSet(quantity: Int): Set<String>

    class Builder : BaseGeneratorBuilder<CnpjGenerator> {
        private var _documentGenerator: CnpjGenerator? = null

        override val documentGenerator: CnpjGenerator
            get() = _documentGenerator ?: createGenerator()

        private fun createGenerator(): CnpjGenerator {
            _documentGenerator = CnpjGeneratorImpl()
            return _documentGenerator!!
        }
    }
}