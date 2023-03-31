package com.cajusoftware.fakedocumentgenerator.generators

import com.cajusoftware.fakedocumentgenerator.masks.CpfMask
import com.cajusoftware.fakedocumentgenerator.utils.space
import com.cajusoftware.fakedocumentgenerator.utils.spaceBeforeThat

class CpfGenerator private constructor() {

    private var cpfMask: CpfMask? = null
    private var prefix: String? = null
    private var suffix: String? = null
    private var federationUnit: FederationUnit? = null

    fun getNewCpf(): String {
        val numbers = arrayListOf<Int>()
        var sumFirstSequence = 0
        var sumSecondSequence = 0
        var index = 10

        repeat((1..9).count()) {
            val number =
                if (it == 8) {
                    federationUnit?.digit ?: (0..9).random()
                } else {
                    (0..9).random()
                }
            numbers.add(number)
            sumFirstSequence += (number * index--)
        }

        val firstChecker: Int = getNumberChecker(sumFirstSequence)

        index = 11

        repeat((0..8).count()) {
            sumSecondSequence += (numbers[it] * index--)
        }

        sumSecondSequence += firstChecker * index--

        var cpf = (numbers.joinToString("") + firstChecker + getNumberChecker(sumSecondSequence))

        cpf = cpfMask?.addMask(cpf) ?: cpf

        return (prefix?.trim()?.space() ?: "")
            .plus(cpf)
            .plus((suffix?.trim()?.spaceBeforeThat() ?: ""))
    }

    private fun getNumberChecker(sumSequence: Int): Int =
        if (sumSequence % 11 < 2) 0 else 11 - (sumSequence % 11)

    class Builder {
        private val cpfGenerator = CpfGenerator()

        fun withSymbols(value: Boolean) = apply {
            if (value)
                cpfGenerator.cpfMask = CpfMask()
        }

        fun setPrefix(prefix: String) = apply {
            cpfGenerator.prefix = prefix
        }

        fun setSuffix(suffix: String) = apply {
            cpfGenerator.suffix = suffix
        }

        fun setFederationUnit(federationUnit: FederationUnit) = apply {
            cpfGenerator.federationUnit = federationUnit
        }

        fun build() = cpfGenerator
    }
}