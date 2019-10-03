package com.melih.abstractions.mapper

import com.melih.abstractions.data.ViewEntity

abstract class Mapper<in T, out R : ViewEntity> {

    abstract fun convert(t: T): R
}
