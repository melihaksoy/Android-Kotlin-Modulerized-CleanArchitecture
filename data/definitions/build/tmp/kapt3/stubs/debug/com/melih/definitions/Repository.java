package com.melih.definitions;

import java.lang.System;

/**
 * Contract for sources to seperate low level business logic from build and return type
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J9\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u0002H\u00040\bH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJG\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\f0\u0003\"\u0004\b\u0000\u0010\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u0002H\u00040\bH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0011"}, d2 = {"Lcom/melih/definitions/Repository;", "", "getLaunchById", "Lcom/melih/abstractions/deliverable/Result;", "T", "id", "", "mapper", "Lcom/melih/abstractions/mapper/Mapper;", "Lcom/melih/repository/entities/LaunchEntity;", "(JLcom/melih/abstractions/mapper/Mapper;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNextLaunches", "", "count", "", "page", "(IILcom/melih/abstractions/mapper/Mapper;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "definitions_debug"})
public abstract interface Repository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract <T extends java.lang.Object>java.lang.Object getNextLaunches(int count, int page, @org.jetbrains.annotations.NotNull()
    com.melih.abstractions.mapper.Mapper<? super com.melih.repository.entities.LaunchEntity, ? extends T> mapper, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.melih.abstractions.deliverable.Result<? extends java.util.List<? extends T>>> p3);
    
    @org.jetbrains.annotations.Nullable()
    public abstract <T extends java.lang.Object>java.lang.Object getLaunchById(long id, @org.jetbrains.annotations.NotNull()
    com.melih.abstractions.mapper.Mapper<? super com.melih.repository.entities.LaunchEntity, ? extends T> mapper, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.melih.abstractions.deliverable.Result<? extends T>> p2);
}