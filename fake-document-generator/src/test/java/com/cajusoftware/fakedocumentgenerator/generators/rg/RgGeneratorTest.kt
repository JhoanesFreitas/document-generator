package com.cajusoftware.fakedocumentgenerator.generators.rg

import org.junit.Assert.assertTrue
import org.junit.Test

class RgGeneratorTest {

    @Test
    fun rgGenerator_generateRg_verifyRgWithSymbols() {
        val rgGenerator = RgGenerator.Builder()
            .withSymbols()
            .build()

        val rg = rgGenerator.generateRg()
        assertTrue(rg.contains("."))
        assertTrue(rg.contains("-"))
    }

    @Test
    fun rgGenerator_generateRg_verifyRgWithPrefix() {
        val rgGenerator = RgGenerator.Builder()
            .setPrefix("cnpj is:")
            .build()

        val rg = rgGenerator.generateRg()
        assertTrue(rg.startsWith("cnpj is:"))
    }

    @Test
    fun rgGenerator_generateRg_verifyRgWithSuffix() {
        val rgGenerator = RgGenerator.Builder()
            .setSuffix("<<<")
            .build()

        val rg = rgGenerator.generateRg()
        assertTrue(rg.endsWith("<<<"))
    }
}