package com.melih.repository.persistence;

import java.lang.System;

/**
 * DB that manages launches
 */
@androidx.room.TypeConverters(value = {com.melih.repository.persistence.converters.LocationConverter.class, com.melih.repository.persistence.converters.RocketConverter.class, com.melih.repository.persistence.converters.MissionConverter.class})
@androidx.room.Database(entities = {com.melih.repository.entities.LaunchEntity.class}, exportSchema = true, version = 1)
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\b"}, d2 = {"Lcom/melih/repository/persistence/LaunchesDatabase;", "Landroidx/room/RoomDatabase;", "()V", "launchesDao", "Lcom/melih/repository/persistence/dao/LaunchesDao;", "getLaunchesDao", "()Lcom/melih/repository/persistence/dao/LaunchesDao;", "Companion", "persistence_debug"})
public abstract class LaunchesDatabase extends androidx.room.RoomDatabase {
    private static com.melih.repository.persistence.LaunchesDatabase instance;
    public static final com.melih.repository.persistence.LaunchesDatabase.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.melih.repository.persistence.dao.LaunchesDao getLaunchesDao();
    
    public LaunchesDatabase() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/melih/repository/persistence/LaunchesDatabase$Companion;", "", "()V", "instance", "Lcom/melih/repository/persistence/LaunchesDatabase;", "getInstance", "ctx", "Landroid/content/Context;", "persistence_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.melih.repository.persistence.LaunchesDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context ctx) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}