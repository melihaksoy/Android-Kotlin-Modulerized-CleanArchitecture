package com.melih.repository.interactors.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/melih/repository/interactors/base/GenericError;", "Lcom/melih/repository/interactors/base/InteractionErrorReason;", "messageRes", "", "(I)V", "getMessageRes", "()I", "interactors_debug"})
public final class GenericError extends com.melih.repository.interactors.base.InteractionErrorReason {
    private final int messageRes = 0;
    
    @java.lang.Override()
    public int getMessageRes() {
        return 0;
    }
    
    public GenericError(@androidx.annotation.StringRes()
    int messageRes) {
    }
    
    public GenericError() {
    }
}