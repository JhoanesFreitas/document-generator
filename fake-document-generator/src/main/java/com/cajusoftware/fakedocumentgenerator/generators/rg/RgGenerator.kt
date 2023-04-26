package com.cajusoftware.fakedocumentgenerator.generators.rg

import com.cajusoftware.fakedocumentgenerator.generators.base.BaseGeneratorBuilder
import com.cajusoftware.fakedocumentgenerator.generators.base.Generator

interface RgGenerator : Generator {
    fun generateRg(): String
    suspend fun generateRgSet(quantity: Int): Set<String>

    class Builder : BaseGeneratorBuilder<RgGenerator> {
        private var _documentGenerator: RgGenerator? = null

        override val documentGenerator: RgGenerator
            get() = _documentGenerator ?: createGenerator()

        private fun createGenerator(): RgGenerator {
            _documentGenerator = RgGeneratorImpl()
            return _documentGenerator!!
        }
    }
}