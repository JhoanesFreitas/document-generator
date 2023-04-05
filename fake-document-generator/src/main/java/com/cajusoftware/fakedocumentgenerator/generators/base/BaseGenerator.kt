package com.cajusoftware.fakedocumentgenerator.generators.base

import com.cajusoftware.fakedocumentgenerator.masks.Mask

interface BaseGenerator {
    val mask: Mask?
    val prefix: String?
    val suffix: String?
}