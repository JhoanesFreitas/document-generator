package com.cajusoftware.fakedocumentgenerator.generators.base

import com.cajusoftware.fakedocumentgenerator.masks.Mask
import com.cajusoftware.fakedocumentgenerator.masks.MaskEnum

interface Generator

internal interface BaseGenerator {
    var mask: Mask?
    var prefix: String?
    var suffix: String?
    val documentType: MaskEnum
}