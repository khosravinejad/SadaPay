package com.morteza.sadapay.data.mapper

abstract class Mapper<INPUT, OUTPUT> {
    abstract fun mapFrom(input: INPUT): OUTPUT
    abstract fun mapFromList(input: List<INPUT>): List<OUTPUT>
}