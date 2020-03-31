package com.melih.abstractions.mapper

import com.melih.abstractions.data.ViewEntity

interface Mapper<in T, out R : ViewEntity> {

    fun convert(t: T): R
}
