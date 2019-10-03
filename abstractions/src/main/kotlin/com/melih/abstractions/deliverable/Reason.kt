package com.melih.abstractions.deliverable

abstract class Reason : Throwable() {

    abstract val messageRes: Int
}
