package com.cajusoftware.fakedocumentgenerator.generators.cpf

import com.cajusoftware.fakedocumentgenerator.generators.FederationUnit
import com.cajusoftware.fakedocumentgenerator.utils.setFederationUnit
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CpfGeneratorTest {

    @Test
    fun cpfGenerator_generateCpf_verifyCpfIsValid() {
        val cpfGenerator = CpfGenerator.Builder()
            .build()
        val cpf = cpfGenerator.generateCpf()

        var firstDigitCheck = 0

        var index = 10

        for (number: Char in cpf.toCharArray()) {
            firstDigitCheck += (number.digitToInt() * index--)
            if (index < 2) break
        }

        var division = (firstDigitCheck * 10) % 11

        var check = if (division == 10) 0 else division

        assertEquals(cpf[9].digitToInt(), check)

        var secondDigitCheck = 0

        index = 11

        for (number: Char in cpf.toCharArray()) {
            secondDigitCheck += (number.digitToInt() * index--)
            if (index < 2) break
        }

        division = (secondDigitCheck * 10) % 11

        check = if (division == 10) 0 else division

        assertEquals(cpf[10].digitToInt(), check)
    }

    @Test
    fun cpfGenerator_generateCpf_verifyCpfStateDigit() {
        val cpfGenerator = CpfGenerator.Builder()
            .setFederationUnit(FederationUnit.RN)
            .build()

        val cpf = cpfGenerator.generateCpf()
        assertEquals(4, cpf[8].digitToInt())
    }

    @Test
    fun cpfGenerator_generateCpf_verifyCpfWithSymbols() {
        val cpfGenerator = CpfGenerator.Builder()
            .withSymbols()
            .build()

        val cpf = cpfGenerator.generateCpf()
        assertTrue(cpf.contains("."))
        assertTrue(cpf.contains("-"))
    }

    @Test
    fun cpfGenerator_generateCpf_verifyCpfWithPrefix() {
        val cpfGenerator = CpfGenerator.Builder()
            .setPrefix("cpf is:")
            .build()

        val cpf = cpfGenerator.generateCpf()
        assertTrue(cpf.startsWith("cpf is:"))
    }

    @Test
    fun cpfGenerator_generateCpf_verifyCpfWithSuffix() {
        val cpfGenerator = CpfGenerator.Builder()
            .setSuffix("<<<")
            .build()

        val cpf = cpfGenerator.generateCpf()
        assertTrue(cpf.endsWith("<<<"))
    }

    @Test
    fun cpfGenerator_generateCpfSet_verifyCpfSetSize() = runBlocking {
        val cpfGenerator = CpfGenerator.Builder()
            .build()

        val cpfSet = cpfGenerator.generateCpfSet(2)

        assertTrue(cpfSet.size == 2)
    }
}