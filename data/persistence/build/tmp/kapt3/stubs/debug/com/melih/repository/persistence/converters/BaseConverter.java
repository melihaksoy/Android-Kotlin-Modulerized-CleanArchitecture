package com.melih.repository.persistence.converters;

import java.lang.System;

/**
 * Base converter for reduced boilerplate code
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u001d\u0010\u0007\u001a\n \u0006*\u0004\u0018\u00010\b0\b2\u0006\u0010\t\u001a\u00028\u0000H\u0007\u00a2\u0006\u0002\u0010\nJ\u0017\u0010\u000b\u001a\u0004\u0018\u00018\u00002\u0006\u0010\f\u001a\u00020\bH\u0007\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H&R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/melih/repository/persistence/converters/BaseConverter;", "T", "", "()V", "moshi", "Lcom/squareup/moshi/Moshi;", "kotlin.jvm.PlatformType", "convertFrom", "", "item", "(Ljava/lang/Object;)Ljava/lang/String;", "convertTo", "string", "(Ljava/lang/String;)Ljava/lang/Object;", "getAdapter", "Lcom/squareup/moshi/JsonAdapter;", "persistence_debug"})
public abstract class BaseConverter<T extends java.lang.Object> {
    private final com.squareup.moshi.Moshi moshi = null;
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.squareup.moshi.JsonAdapter<T> getAdapter(@org.jetbrains.annotations.NotNull()
    com.squareup.moshi.Moshi moshi);
    
    @androidx.room.TypeConverter()
    public final java.lang.String convertFrom(T item) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.TypeConverter()
    public final T convertTo(@org.jetbrains.annotations.NotNull()
    java.lang.String string) {
        return null;
    }
    
    public BaseConverter() {
        super();
    }
}