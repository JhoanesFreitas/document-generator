package com.cajusoftware.fakedocumentgenerator.generators.rg

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class RgGeneratorTest {

    @Test
    fun rgGenerator_generateRg_verifyRgIsValid() {
        val rgGenerator = RgGenerator.Builder()
            .build()

        val rg = rgGenerator.generateRg()

        var digitalCheckerSum = 0

        var index = 2
        val rgArray = rg.toCharArray()

        repeat((0..7).count()) {
            digitalCheckerSum += (rgArray[it].digitToInt() * index++)
        }

        assertEquals(getNumberChecker(digitalCheckerSum), rg.last().toString())
    }

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

    private fun getNumberChecker(sumSequence: Int): String =
        when (sumSequence % 11) {
            1 -> "X"
            0 -> 0.toString()
            else -> (11 - (sumSequence % 11)).toString()
        }
}