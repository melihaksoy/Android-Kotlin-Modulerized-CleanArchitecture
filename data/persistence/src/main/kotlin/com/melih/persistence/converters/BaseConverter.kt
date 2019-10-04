package com.melih.persistence.converters

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * Base converter for reduced boilerplate code
 */
abstract class BaseConverter<T> {

    //region Abstractions

    abstract fun getAdapter(moshi: Moshi): JsonAdapter<T>
    //endregion

    //region Properties

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    //endregion

    //region Functions

    @TypeConverter
    fun convertFrom(item: T) =
        getAdapter(moshi).toJson(item)

    @TypeConverter
    fun convertTo(string: String) =
        getAdapter(moshi).fromJson(string)
    //endregion
}
