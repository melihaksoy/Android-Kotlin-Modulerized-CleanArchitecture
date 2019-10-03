package com.melih.repository.interactors;

import java.lang.System;

/**
 * Gets next given number of launches
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0015B \b\u0007\u0012\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000\u0005\u00a2\u0006\u0002\b\u0007\u00a2\u0006\u0002\u0010\bJ)\u0010\u000f\u001a\u00020\u0010*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0003H\u0094@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014R\u001e\u0010\t\u001a\u00020\n8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001f\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000\u0005\u00a2\u0006\u0002\b\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0016"}, d2 = {"Lcom/melih/repository/interactors/GetLaunchDetails;", "T", "Lcom/melih/repository/interactors/base/BaseInteractor;", "Lcom/melih/repository/interactors/GetLaunchDetails$Params;", "mapper", "Lcom/melih/abstractions/mapper/Mapper;", "Lcom/melih/repository/entities/LaunchEntity;", "Lkotlin/jvm/JvmSuppressWildcards;", "(Lcom/melih/abstractions/mapper/Mapper;)V", "launchesSource", "Lcom/melih/interactors/sources/LaunchesSource;", "getLaunchesSource$interactors_debug", "()Lcom/melih/interactors/sources/LaunchesSource;", "setLaunchesSource$interactors_debug", "(Lcom/melih/interactors/sources/LaunchesSource;)V", "run", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/melih/abstractions/deliverable/Result;", "params", "(Lkotlinx/coroutines/flow/FlowCollector;Lcom/melih/repository/interactors/GetLaunchDetails$Params;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Params", "interactors_debug"})
public final class GetLaunchDetails<T extends java.lang.Object> extends com.melih.repository.interactors.base.BaseInteractor<T, com.melih.repository.interactors.GetLaunchDetails.Params> {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.melih.interactors.sources.LaunchesSource launchesSource;
    private final com.melih.abstractions.mapper.Mapper<com.melih.repository.entities.LaunchEntity, T> mapper = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.melih.interactors.sources.LaunchesSource getLaunchesSource$interactors_debug() {
        return null;
    }
    
    public final void setLaunchesSource$interactors_debug(@org.jetbrains.annotations.NotNull()
    com.melih.interactors.sources.LaunchesSource p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected java.lang.Object run(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.flow.FlowCollector<? super com.melih.abstractions.deliverable.Result<? extends T>> $this$run, @org.jetbrains.annotations.NotNull()
    com.melih.repository.interactors.GetLaunchDetails.Params params, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p2) {
        return null;
    }
    
    @javax.inject.Inject()
    public GetLaunchDetails(@org.jetbrains.annotations.NotNull()
    com.melih.abstractions.mapper.Mapper<com.melih.repository.entities.LaunchEntity, T> mapper) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/melih/repository/interactors/GetLaunchDetails$Params;", "Lcom/melih/repository/interactors/base/InteractorParameters;", "id", "", "(J)V", "getId", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "interactors_debug"})
    public static final class Params implements com.melih.repository.interactors.base.InteractorParameters {
        private final long id = 0L;
        
        public final long getId() {
            return 0L;
        }
        
        public Params(long id) {
            super();
        }
        
        public final long component1() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.melih.repository.interactors.GetLaunchDetails.Params copy(long id) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object p0) {
            return false;
        }
    }
}