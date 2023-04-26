package com.cajusoftware.fakedocumentgenerator.generators.cnpj

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CnpjGeneratorTest {

    @Test
    fun cnpjGenerator_generateCnpj_verifyCnpjIsValid() {
        val cnpjGenerator = CnpjGenerator.Builder()
            .build()

        val cnpj = cnpjGenerator.generateCnpj()
        val cnpjNumbers = cnpj.toCharArray()

        var firstDigitalCheckerSum = 0
        var secondDigitalCheckerSum = 0

        var index = 5

        repeat((0..11).count()) {
            firstDigitalCheckerSum += ((cnpjNumbers[it]).digitToInt() * index)
            if (index == 2) index = 9 else index--
        }

        val firstDigitalChecker: Int = getNumberChecker(firstDigitalCheckerSum)

        index = 6
        repeat((1..13).count()) {
            secondDigitalCheckerSum += when {
                it < 12 -> ((cnpjNumbers[it]).digitToInt() * index)
                else -> (firstDigitalChecker * index)
            }
            if (index == 2) index = 9 else index--
        }

        val secondDigitalChecker = getNumberChecker(secondDigitalCheckerSum)

        assertEquals(firstDigitalChecker, cnpjNumbers[cnpjNumbers.size - 2].digitToInt())
        assertEquals(secondDigitalChecker, cnpjNumbers.last().digitToInt())
    }

    @Test
    fun cnpjGenerator_generateCnpj_verifyCnpjWithSymbols() {
        val cnpjGenerator = CnpjGenerator.Builder()
            .withSymbols()
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

    @Test
    fun cnpjGenerator_generateCnpjSet_verifyCnpjSetSize() = runBlocking {
        val cnpjGenerator = CnpjGenerator.Builder()
            .build()

        val cnpjSet = cnpjGenerator.generateCnpjSet(2)

        assertTrue(cnpjSet.size == 2)
    }

    private fun getNumberChecker(sumSequence: Int): Int =
        if (sumSequence % 11 < 2) 0 else 11 - (sumSequence % 11)
}