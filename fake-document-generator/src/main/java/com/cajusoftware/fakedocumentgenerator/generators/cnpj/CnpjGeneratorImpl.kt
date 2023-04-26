package com.cajusoftware.fakedocumentgenerator.generators.cnpj

import com.cajusoftware.fakedocumentgenerator.generators.base.BaseGenerator
import com.cajusoftware.fakedocumentgenerator.masks.Mask
import com.cajusoftware.fakedocumentgenerator.masks.MaskEnum
import com.cajusoftware.fakedocumentgenerator.utils.concatenateSuffix
import com.cajusoftware.fakedocumentgenerator.utils.space

internal class CnpjGeneratorImpl internal constructor() : CnpjGenerator, BaseGenerator {

    override var mask: Mask? = null
    override var prefix: String? = null
    override var suffix: String? = null
    override val documentType: MaskEnum
        get() = MaskEnum.CNPJ

    private val cnpjNumbers = arrayListOf<Int>()
    private var sumFirstSequenceDigitalChecker = 0
    private var sumSecondSequenceDigitalChecker = 0
    private var firstDigitalChecker: Int = 0
    private var secondDigitalChecker: Int = 0
    private var index = 5

    private var returnedCnpj = ""

    override fun generateCnpj(): String {
        generateTopTwelveNumbers()

        calculateTheFirstSequenceSumToDigitalChecker()
        getFirstDigitalChecker()

        calculateTheSecondSequenceSumToDigitalChecker()
        getSecondDigitalChecker()

        formatCnpjNumbers()
        addMaskToCnpjNumbers()

        return getFinalCnpjNumbers()
    }

    private fun generateTopTwelveNumbers() {
        repeat((1..12).count()) {
            val number = when (it) {
                8, 9, 10 -> 0
                11 -> 1
                else -> (0..9).random()
            }
            cnpjNumbers.add(number)
        }
    }

    private fun calculateTheFirstSequenceSumToDigitalChecker() {
        repeat((0..11).count()) {
            sumFirstSequenceDigitalChecker += (cnpjNumbers[it] * index)
            if (index == 2) index = 9 else index--
        }
    }

    private fun getFirstDigitalChecker() {
        firstDigitalChecker = getNumberChecker(sumFirstSequenceDigitalChecker)
    }

    private fun calculateTheSecondSequenceSumToDigitalChecker() {
        index = 6
        repeat((1..13).count()) {
            sumSecondSequenceDigitalChecker += when {
                it < 12 -> (cnpjNumbers[it] * index)
                else -> (firstDigitalChecker * index)
            }
            if (index == 2) index = 9 else index--
        }
    }

    private fun getSecondDigitalChecker() {
        secondDigitalChecker = getNumberChecker(sumSecondSequenceDigitalChecker)
    }

    private fun getNumberChecker(sumSequence: Int): Int =
        if (sumSequence % 11 < 2) 0 else 11 - (sumSequence % 11)

    private fun formatCnpjNumbers() {
        returnedCnpj = (cnpjNumbers.joinToString("")) + firstDigitalChecker + secondDigitalChecker
    }

    private fun addMaskToCnpjNumbers() {
        returnedCnpj = mask?.addMask(returnedCnpj) ?: returnedCnpj
    }

    private fun getFinalCnpjNumbers(): String {
        return concatenatePrefixToReturnedCnpj().concatenateSuffix(suffix)
    }

    private fun concatenatePrefixToReturnedCnpj(): String {
        return (prefix?.trim()?.space() ?: "")
            .plus(returnedCnpj)
    }

    override suspend fun generateCnpjSet(quantity: Int): Set<String> {
        val cnpjSet = mutableSetOf<String>()

        repeat(quantity) {
            cnpjSet.add(generateCnpj())
        }

        return cnpjSet
    }
}