package com.kk.jet2articalassignment.utils

import org.junit.Test

import org.junit.Assert.*

class FormatorUtilsTest {

    @Test
    fun getFormatDuration() {
    }

    @Test
    fun convertNumnerIntoInternationalFormat_T() {
        var number_1 : Long  = 5214
        assertEquals(FormatorUtils.convertNumnerIntoInternationalFormat(number_1),"5.21K")
    }
    @Test
    fun convertNumnerIntoInternationalFormat_10T() {
        var number_1 : Long = 52140
        assertEquals(FormatorUtils.convertNumnerIntoInternationalFormat(number_1),"52.14K")

    }
    @Test
    fun convertNumnerIntoInternationalFormat_100T() {
        var number_1 : Long  = 521400
        assertEquals(FormatorUtils.convertNumnerIntoInternationalFormat(number_1),"521.4K")

    }
    @Test
    fun convertNumnerIntoInternationalFormat_M() {
        var number_1 : Long  = 5214000
        assertEquals(FormatorUtils.convertNumnerIntoInternationalFormat(number_1),"5.21M")

    }
}