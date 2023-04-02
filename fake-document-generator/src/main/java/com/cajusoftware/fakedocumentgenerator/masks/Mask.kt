package com.cajusoftware.fakedocumentgenerator.masks

import com.cajusoftware.fakedocumentgenerator.utils.withoutDocumentSymbols

class Mask(private val maskEnum: MaskEnum) {

    fun addMask(cpfString: String): String {
        val str = cpfString.withoutDocumentSymbols
        var stringWithMask = ""

        var i = 0
        for (m: Char in maskEnum.value.toCharArray()) {
            if (m != '#') {
                stringWithMask += m
                continue
            }
            try {
                stringWithMask += str.get(i)
            } catch (e: Exception) {
                break
            }
            i++
        }

        return stringWithMask
    }
}