package com.cajusoftware.fakedocumentgenerator.masks

import com.cajusoftware.fakedocumentgenerator.utils.withoutDocumentSymbols

private const val MASK = "###.###.###-##"

class CpfMask {

    fun addMask(cpfString: String): String {
        val str = cpfString.withoutDocumentSymbols
        var cpfWithMask = ""

        var i = 0
        for (m: Char in MASK.toCharArray()) {
            if (m != '#') {
                cpfWithMask += m
                continue
            }
            try {
                cpfWithMask += str.get(i)
            } catch (e: Exception) {
                break
            }
            i++
        }

        return cpfWithMask
    }
}