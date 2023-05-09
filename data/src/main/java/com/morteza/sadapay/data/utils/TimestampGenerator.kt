package com.morteza.sadapay.data.utils

interface TimestampGenerator {
    fun generateTimestamp(): Long
}

class SystemTimestampGenerator : TimestampGenerator {
    override fun generateTimestamp(): Long {
        return System.currentTimeMillis()
    }
}