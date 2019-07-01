package com.melih.repository.persistence.converters

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * Base converter for reduced boilerplate code
 */
abstract class BaseListConverter<T> {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    abstract fun getAdapter(moshi: Moshi): JsonAdapter<List<T>>

    // region Functions

    @TypeConverter
    fun convertFrom(items: List<T>) =
        getAdapter(moshi).toJson(items)

    @TypeConverter
    fun convertTo(string: String): List<T>? =
        getAdapter(moshi).fromJson(string)
    // endregion
}
