package com.cajusoftware.fakedocumentgenerator.generators.rg

import com.cajusoftware.fakedocumentgenerator.generators.base.BaseGenerator
import com.cajusoftware.fakedocumentgenerator.masks.Mask
import com.cajusoftware.fakedocumentgenerator.masks.MaskEnum
import com.cajusoftware.fakedocumentgenerator.utils.concatenateSuffix
import com.cajusoftware.fakedocumentgenerator.utils.space

/***
 *
 * An RgGenerationImpl allows to generate RG numbers according to the RG algorithm.
 * This algorithm conforms to the SSP-SP. Thus, it generates numbers following the rules
 * established by the SSP-SP.
 *
 */
internal class RgGeneratorImpl internal constructor() : RgGenerator, BaseGenerator {

    override var mask: Mask? = null
    override var prefix: String? = null
    override var suffix: String? = null
    override val documentType: MaskEnum
        get() = MaskEnum.RG

    private val rgNumbers = arrayListOf<Int>()
    private var sumSequenceDigitalChecker = 0
    private var digitalChecker = ""
    private var index = 2

    private var returnedRg = ""

    override fun generateRg(): String {
        generateRgTopEightNumbers()

        calculateSequenceSumToDigitalChecker()
        getDigitalChecker()

        formatRgNumbers()
        addMaskToRgNumbers()

        return getFinalRgStyle()
    }

    private fun generateRgTopEightNumbers() {
        repeat((1..8).count()) {
            val number = (0..9).random()
            rgNumbers.add(number)
        }
    }

    private fun calculateSequenceSumToDigitalChecker() {
        repeat((0..7).count()) {
            sumSequenceDigitalChecker += (rgNumbers[it] * index++)
        }
    }

    private fun getDigitalChecker() {
        digitalChecker = getNumberChecker(sumSequenceDigitalChecker)
    }

    private fun formatRgNumbers() {
        returnedRg = rgNumbers.joinToString("") + digitalChecker
    }

    private fun addMaskToRgNumbers() {
        returnedRg = mask?.addMask(returnedRg) ?: returnedRg
    }

    private fun getFinalRgStyle(): String {
        return concatenatePrefixToReturnedRg().concatenateSuffix(suffix)
    }

    private fun concatenatePrefixToReturnedRg(): String {
        return (prefix?.trim()?.space() ?: "")
            .plus(returnedRg)
    }

    override suspend fun generateRgSet(quantity: Int): Set<String> {
        val rgSet = mutableSetOf<String>()

        repeat(quantity) {
            rgSet.add(generateRg())
        }

        return rgSet
    }

    private fun getNumberChecker(sumSequence: Int): String =
        when (sumSequence % 11) {
            1 -> "X"
            0 -> 0.toString()
            else -> (11 - (sumSequence % 11)).toString()
        }
}