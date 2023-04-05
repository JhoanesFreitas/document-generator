package com.cajusoftware.fakedocumentgenerator.generators.rg

import com.cajusoftware.fakedocumentgenerator.masks.Mask
import com.cajusoftware.fakedocumentgenerator.utils.space
import com.cajusoftware.fakedocumentgenerator.utils.spaceBeforeThat

internal class RgGeneratorImpl internal constructor() : RgGenerator {

    override var mask: Mask? = null
    override var prefix: String? = null
    override var suffix: String? = null

    override fun generateRg(): String {
        val numbers = arrayListOf<Int>()
        var sumFirstSequence = 0
        var index = 2

        repeat((1..8).count()) {
            val number = (0..9).random()
            numbers.add(number)
            sumFirstSequence += (number * index++)
        }

        val verifyDigit = getNumberChecker(sumFirstSequence)

        var rg = numbers.joinToString("") + verifyDigit

        rg = mask?.addMask(rg) ?: rg

        return (prefix?.trim()?.space() ?: "")
            .plus(rg)
            .plus((suffix?.trim()?.spaceBeforeThat() ?: ""))
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