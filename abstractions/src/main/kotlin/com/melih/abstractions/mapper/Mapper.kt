package com.melih.abstractions.mapper

import com.melih.abstractions.data.DataEntity
import com.melih.abstractions.data.ViewEntity

abstract class Mapper<in T : DataEntity, out R : ViewEntity> {

    abstract fun convert(t: T): R
}
