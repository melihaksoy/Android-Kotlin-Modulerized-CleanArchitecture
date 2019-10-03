package com.melih.repository.interactors;

import java.lang.System;

/**
 * Gets next given number of launches
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u0003:\u0001\u0017B \b\u0007\u0012\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007\u00a2\u0006\u0002\b\t\u00a2\u0006\u0002\u0010\nJ/\u0010\u0011\u001a\u00020\u0012*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0005H\u0094@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016R\u001e\u0010\u000b\u001a\u00020\f8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001f\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007\u00a2\u0006\u0002\b\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Lcom/melih/repository/interactors/GetLaunches;", "T", "Lcom/melih/abstractions/data/ViewEntity;", "Lcom/melih/repository/interactors/base/BaseInteractor;", "", "Lcom/melih/repository/interactors/GetLaunches$Params;", "mapper", "Lcom/melih/abstractions/mapper/Mapper;", "Lcom/melih/repository/entities/LaunchEntity;", "Lkotlin/jvm/JvmSuppressWildcards;", "(Lcom/melih/abstractions/mapper/Mapper;)V", "launchesSource", "Lcom/melih/interactors/sources/LaunchesSource;", "getLaunchesSource$interactors_debug", "()Lcom/melih/interactors/sources/LaunchesSource;", "setLaunchesSource$interactors_debug", "(Lcom/melih/interactors/sources/LaunchesSource;)V", "run", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/melih/abstractions/deliverable/Result;", "params", "(Lkotlinx/coroutines/flow/FlowCollector;Lcom/melih/repository/interactors/GetLaunches$Params;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Params", "interactors_debug"})
public final class GetLaunches<T extends com.melih.abstractions.data.ViewEntity> extends com.melih.repository.interactors.base.BaseInteractor<java.util.List<? extends T>, com.melih.repository.interactors.GetLaunches.Params> {
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
    kotlinx.coroutines.flow.FlowCollector<? super com.melih.abstractions.deliverable.Result<? extends java.util.List<? extends T>>> $this$run, @org.jetbrains.annotations.NotNull()
    com.melih.repository.interactors.GetLaunches.Params params, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p2) {
        return null;
    }
    
    @javax.inject.Inject()
    public GetLaunches(@org.jetbrains.annotations.NotNull()
    com.melih.abstractions.mapper.Mapper<com.melih.repository.entities.LaunchEntity, T> mapper) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lcom/melih/repository/interactors/GetLaunches$Params;", "Lcom/melih/repository/interactors/base/InteractorParameters;", "count", "", "page", "(II)V", "getCount", "()I", "getPage", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "interactors_debug"})
    public static final class Params implements com.melih.repository.interactors.base.InteractorParameters {
        private final int count = 0;
        private final int page = 0;
        
        public final int getCount() {
            return 0;
        }
        
        public final int getPage() {
            return 0;
        }
        
        public Params(int count, int page) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int component2() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.melih.repository.interactors.GetLaunches.Params copy(int count, int page) {
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