package com.cajusoftware.fakedocumentgenerator.generators.cnpj

import org.junit.Assert.assertTrue
import org.junit.Test

class CnpjGeneratorTest {

    @Test
    fun cnpjGenerator_generateCnpj_verifyCnpjWithSymbols() {
        val cnpjGenerator = CnpjGenerator.Builder()
            .withSymbols(true)
            .build()

        val cnpj = cnpjGenerator.generateCnpj()
        assertTrue(cnpj.contains("."))
        assertTrue(cnpj.contains("/"))
        assertTrue(cnpj.contains("-"))
    }

    @Test
    fun cnpjGenerator_generateCnpj_verifyCnpjWithPrefix() {
        val cnpjGenerator = CnpjGenerator.Builder()
            .setPrefix("cnpj is:")
            .build()

        val cnpj = cnpjGenerator.generateCnpj()
        assertTrue(cnpj.startsWith("cnpj is:"))
    }

    @Test
    fun cnpjGenerator_generateCnpj_verifyCnpjWithSuffix() {
        val cnpjGenerator = CnpjGenerator.Builder()
            .setSuffix("<<<")
            .build()

        val cnpj = cnpjGenerator.generateCnpj()
        assertTrue(cnpj.endsWith("<<<"))
    }
}