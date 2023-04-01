package com.cajusoftware.fakedocumentgenerator.generators.cnpj

import android.util.Log
import com.cajusoftware.fakedocumentgenerator.generators.FederationUnit
import com.cajusoftware.fakedocumentgenerator.masks.Mask

internal class CnpjGeneratorImpl internal constructor() : CnpjGenerator {

    internal var mask: Mask? = null
    internal var prefix: String? = null
    internal var suffix: String? = null
    internal var federationUnit: FederationUnit? = null

    override fun generateCnpj(): String {
        val numbers = arrayListOf<Int>()
        var sumFirstSequence = 0
        var sumSecondSequence = 0
        var index = 5

        repeat((1..12).count()) {
            val number = when (it) {
                8, 9, 10 -> 0
                11 -> 1
                else -> (0..9).random()
            }
            numbers.add(number)
            sumFirstSequence += (number * index)
            if (index == 2) index = 9 else index--
        }

        val firstChecker: Int = getNumberChecker(sumFirstSequence)

        index = 6

        repeat((1..13).count()) {
            sumSecondSequence += when {
                it < 12 -> (numbers[it] * index)
                else -> (firstChecker * index)
            }
            if (index == 2) index = 9 else index--
        }

        val secondChecker: Int = getNumberChecker(sumSecondSequence)

        var cnpj = (numbers.joinToString("")) + firstChecker + secondChecker

        cnpj = mask?.addMask(cnpj) ?: cnpj

        Log.d("CNPJ", cnpj)

        return cnpj
    }

    override suspend fun generateCnpjSet(quantity: Int): Set<String> {
        val cnpjSet = mutableSetOf<String>()

        repeat(quantity) {
            cnpjSet.add(generateCnpj())
        }

        return cnpjSet
    }

    private fun getNumberChecker(sumSequence: Int): Int =
        if (sumSequence % 11 < 2) 0 else 11 - (sumSequence % 11)
}