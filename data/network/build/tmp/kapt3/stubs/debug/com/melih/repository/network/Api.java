package com.melih.repository.network;

import java.lang.System;

/**
 * Retrofit interface for networking
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J+\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\f\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"Lcom/melih/repository/network/Api;", "", "getLaunchById", "Lretrofit2/Response;", "Lcom/melih/repository/entities/LaunchEntity;", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNextLaunches", "Lcom/melih/repository/entities/LaunchesEntity;", "count", "", "offset", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "network_debug"})
public abstract interface Api {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "launch/next/{count}")
    public abstract java.lang.Object getNextLaunches(@retrofit2.http.Path(value = "count")
    int count, @retrofit2.http.Query(value = "offset")
    int offset, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.melih.repository.entities.LaunchesEntity>> p2);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "launch/{id}")
    public abstract java.lang.Object getLaunchById(@retrofit2.http.Path(value = "id")
    long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.melih.repository.entities.LaunchEntity>> p1);
}