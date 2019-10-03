package com.melih.repository.interactors.base;

import java.lang.System;

/**
 * Base use case that wraps [suspending][suspend] [run] function with [flow][Flow] and returns it for later usage.
 */
@kotlin.UseExperimental(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\n\b\u0001\u0010\u0002 \u0000*\u00020\u00032\u00020\u0004B\u0005\u00a2\u0006\u0002\u0010\u0005J\"\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u00072\u0006\u0010\t\u001a\u00028\u0001H\u0086\u0002\u00a2\u0006\u0002\u0010\nJ)\u0010\u000b\u001a\u00020\f*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\r2\u0006\u0010\t\u001a\u00028\u0001H\u00a4@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/melih/repository/interactors/base/BaseInteractor;", "T", "P", "Lcom/melih/repository/interactors/base/InteractorParameters;", "", "()V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "Lcom/melih/abstractions/deliverable/Result;", "params", "(Lcom/melih/repository/interactors/base/InteractorParameters;)Lkotlinx/coroutines/flow/Flow;", "run", "", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lcom/melih/repository/interactors/base/InteractorParameters;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "interactors_debug"})
public abstract class BaseInteractor<T extends java.lang.Object, P extends com.melih.repository.interactors.base.InteractorParameters> {
    
    @org.jetbrains.annotations.Nullable()
    protected abstract java.lang.Object run(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.flow.FlowCollector<? super com.melih.abstractions.deliverable.Result<? extends T>> $this$run, @org.jetbrains.annotations.NotNull()
    P params, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p2);
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.melih.abstractions.deliverable.Result<T>> invoke(@org.jetbrains.annotations.NotNull()
    P params) {
        return null;
    }
    
    public BaseInteractor() {
        super();
    }
}