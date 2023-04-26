package com.cajusoftware.fakedocumentgenerator.generators.cpf

import com.cajusoftware.fakedocumentgenerator.generators.FederationUnit
import com.cajusoftware.fakedocumentgenerator.generators.FederationUnitGroup
import com.cajusoftware.fakedocumentgenerator.generators.base.BaseGenerator
import com.cajusoftware.fakedocumentgenerator.masks.Mask
import com.cajusoftware.fakedocumentgenerator.masks.MaskEnum
import com.cajusoftware.fakedocumentgenerator.utils.concatenateSuffix
import com.cajusoftware.fakedocumentgenerator.utils.getRandomNumberFromSequence
import com.cajusoftware.fakedocumentgenerator.utils.isFederationNumberKey
import com.cajusoftware.fakedocumentgenerator.utils.space

/***
 *
 * A CpfGeneratorImpl allows generate CPF numbers according to
 * CPF generate algorithm. This one needs generate, firstly, a sequence
 * with 8 numbers from 0 to 9. Then, it calculate the first sequence sum to might get the
 * first digital checker (the last two CPF numbers are checker to determine CPF valid).
 * Next, the algorithm calculate the second sequence sum and get the second digital checker.
 * Thereby, we can return a CPF successfully.
 *
 */
internal class CpfGeneratorImpl internal constructor() : CpfGenerator, BaseGenerator,
    FederationUnitGroup {

    override var mask: Mask? = null
    override var prefix: String? = null
    override var suffix: String? = null
    override var federationUnit: FederationUnit? = null
    override val documentType: MaskEnum
        get() = MaskEnum.CPF

    private val cpfNumbers = arrayListOf<Int>()
    private var sumFirstSequenceDigitalChecker = 0
    private var sumSecondSequenceDigitalChecker = 0
    private var firstDigitalChecker: Int = 0
    private var secondDigitalChecker: Int = 0
    private var index = 10

    private var returnedCpf = ""

    override fun generateCpf(): String {
        generateCpfTopEightNumbers()

        calculateTheFirstSequenceSumToDigitalChecker()
        getFirstDigitalChecker()

        calculateTheSecondSequenceSumToDigitalChecker()
        getSecondDigitalChecker()

        formatCpfNumbers()
        addMaskToCpfNumbers()

        return getFinalCpfStyle()
    }

    private fun generateCpfTopEightNumbers() {
        repeat((1..9).count()) {
            val number = getRandomNumberOrFederationUnitNumber(it.isFederationNumberKey)
            cpfNumbers.add(number)
        }
    }

    private fun getRandomNumberOrFederationUnitNumber(isFederationNumberKey: Boolean): Int {
        return if (isFederationNumberKey)
            federationUnit?.digit ?: getRandomNumberFromSequence()
        else
            getRandomNumberFromSequence()

    }

    private fun calculateTheFirstSequenceSumToDigitalChecker() {
        repeat((0..8).count()) {
            sumFirstSequenceDigitalChecker += (cpfNumbers[it] * index--)
        }
    }

    private fun getFirstDigitalChecker() {
        firstDigitalChecker = getNumberChecker(sumFirstSequenceDigitalChecker)
    }

    private fun calculateTheSecondSequenceSumToDigitalChecker() {
        index = 11
        repeat((0..8).count()) {
            sumSecondSequenceDigitalChecker += (cpfNumbers[it] * index--)
        }
        sumSecondSequenceDigitalChecker += firstDigitalChecker * index--
    }

    private fun getSecondDigitalChecker() {
        secondDigitalChecker = getNumberChecker(sumSecondSequenceDigitalChecker)
    }

    private fun getNumberChecker(sumSequence: Int): Int {
        return if (sumSequence % 11 < 2)
            0
        else
            11 - (sumSequence % 11)
    }

    private fun formatCpfNumbers() {
        returnedCpf =
            (cpfNumbers.joinToString("") + firstDigitalChecker + secondDigitalChecker)
    }

    private fun addMaskToCpfNumbers() {
        returnedCpf = mask?.addMask(returnedCpf) ?: returnedCpf
    }

    private fun getFinalCpfStyle(): String {
        return concatenatePrefixToReturnedCpf().concatenateSuffix(suffix)
    }

    private fun concatenatePrefixToReturnedCpf(): String {
        return (prefix?.trim()?.space() ?: "")
            .plus(returnedCpf)
    }

    override suspend fun generateCpfSet(quantity: Int): Set<String> {
        val cpfSet = mutableSetOf<String>()

        repeat(quantity) {
            cpfSet.add(generateCpf())
        }

        return cpfSet
    }
}