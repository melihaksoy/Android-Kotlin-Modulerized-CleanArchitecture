package com.melih.repository.persistence.dao;

import java.lang.System;

/**
 * DAO for list of [launches][LaunchEntity]
 */
@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\'\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0011\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u0019\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u001f\u0010\u0014\u001a\u00020\u000f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lcom/melih/repository/persistence/dao/LaunchesDao;", "", "()V", "getLaunchById", "Lcom/melih/repository/entities/LaunchEntity;", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLaunches", "", "count", "", "page", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "nukeLaunches", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveLaunch", "launch", "(Lcom/melih/repository/entities/LaunchEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveLaunches", "launches", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "persistence_debug"})
public abstract class LaunchesDao {
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "SELECT * FROM Launches ORDER BY launchStartTime DESC LIMIT :count OFFSET :page*:count")
    public abstract java.lang.Object getLaunches(int count, int page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.melih.repository.entities.LaunchEntity>> p2);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "SELECT * FROM Launches WHERE id=:id LIMIT 1")
    public abstract java.lang.Object getLaunchById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.melih.repository.entities.LaunchEntity> p1);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "DELETE FROM Launches")
    public abstract java.lang.Object nukeLaunches(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p0);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object saveLaunches(@org.jetbrains.annotations.NotNull()
    java.util.List<com.melih.repository.entities.LaunchEntity> launches, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object saveLaunch(@org.jetbrains.annotations.NotNull()
    com.melih.repository.entities.LaunchEntity launch, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1);
    
    public LaunchesDao() {
        super();
    }
}