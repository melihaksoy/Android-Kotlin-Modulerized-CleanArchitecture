package com.melih.repository.network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u001f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\'\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014R#\u0010\u0003\u001a\n \u0004*\u0004\u0018\u00010\u00010\u00018BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0015"}, d2 = {"Lcom/melih/repository/network/ApiImpl;", "Lcom/melih/repository/network/Api;", "()V", "service", "kotlin.jvm.PlatformType", "getService", "()Lcom/melih/repository/network/Api;", "service$delegate", "Lkotlin/Lazy;", "getLaunchById", "Lretrofit2/Response;", "Lcom/melih/repository/entities/LaunchEntity;", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNextLaunches", "Lcom/melih/repository/entities/LaunchesEntity;", "count", "", "offset", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "network_debug"})
public final class ApiImpl implements com.melih.repository.network.Api {
    private final kotlin.Lazy service$delegate = null;
    
    private final com.melih.repository.network.Api getService() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getNextLaunches(int count, int offset, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.melih.repository.entities.LaunchesEntity>> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getLaunchById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.melih.repository.entities.LaunchEntity>> p1) {
        return null;
    }
    
    @javax.inject.Inject()
    public ApiImpl() {
        super();
    }
}