package com.melih.abstractions.mapper

abstract class Mapper<in T, out R> {

    abstract fun convert(t: T): R
}
