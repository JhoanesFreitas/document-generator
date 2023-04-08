package com.cajusoftware.fakedocumentgenerator.generators.cpf

import com.cajusoftware.fakedocumentgenerator.generators.FederationUnit
import com.cajusoftware.fakedocumentgenerator.generators.FederationUnitGroup
import com.cajusoftware.fakedocumentgenerator.generators.base.BaseGenerator
import com.cajusoftware.fakedocumentgenerator.masks.Mask
import com.cajusoftware.fakedocumentgenerator.masks.MaskEnum
import com.cajusoftware.fakedocumentgenerator.utils.space
import com.cajusoftware.fakedocumentgenerator.utils.spaceBeforeThat

internal class CpfGeneratorImpl internal constructor() : CpfGenerator, BaseGenerator,
    FederationUnitGroup {

    override var mask: Mask? = null
    override var prefix: String? = null
    override var suffix: String? = null
    override var federationUnit: FederationUnit? = null
    override val documentType: MaskEnum
        get() = MaskEnum.CPF

    override fun generateCpf(): String {
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

        cpf = mask?.addMask(cpf) ?: cpf

        return (prefix?.trim()?.space() ?: "")
            .plus(cpf)
            .plus((suffix?.trim()?.spaceBeforeThat() ?: ""))
    }

    override suspend fun generateCpfSet(quantity: Int): Set<String> {
        val cpfSet = mutableSetOf<String>()

        repeat(quantity) {
            cpfSet.add(generateCpf())
        }

        return cpfSet
    }

    private fun getNumberChecker(sumSequence: Int): Int =
        if (sumSequence % 11 < 2) 0 else 11 - (sumSequence % 11)
}