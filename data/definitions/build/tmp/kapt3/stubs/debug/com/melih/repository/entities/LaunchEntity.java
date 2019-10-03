package com.melih.repository.entities;

import java.lang.System;

@androidx.room.Entity(tableName = "Launches")
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BQ\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\tH\u00c6\u0003J\t\u0010!\u001a\u00020\u000bH\u00c6\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u00c6\u0003JU\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u00c6\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\'\u001a\u00020(H\u00d6\u0001J\t\u0010)\u001a\u00020\u0005H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006*"}, d2 = {"Lcom/melih/repository/entities/LaunchEntity;", "", "id", "", "name", "", "launchStartTime", "launchEndTime", "location", "Lcom/melih/repository/entities/LocationEntity;", "rocket", "Lcom/melih/repository/entities/RocketEntity;", "missions", "", "Lcom/melih/repository/entities/MissionEntity;", "(JLjava/lang/String;JJLcom/melih/repository/entities/LocationEntity;Lcom/melih/repository/entities/RocketEntity;Ljava/util/List;)V", "getId", "()J", "getLaunchEndTime", "getLaunchStartTime", "getLocation", "()Lcom/melih/repository/entities/LocationEntity;", "getMissions", "()Ljava/util/List;", "getName", "()Ljava/lang/String;", "getRocket", "()Lcom/melih/repository/entities/RocketEntity;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "definitions_debug"})
@com.squareup.moshi.JsonClass(generateAdapter = true)
public final class LaunchEntity {
    @androidx.room.PrimaryKey()
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @com.squareup.moshi.Json(name = "wsstamp")
    private final long launchStartTime = 0L;
    @com.squareup.moshi.Json(name = "westamp")
    private final long launchEndTime = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.melih.repository.entities.LocationEntity location = null;
    @org.jetbrains.annotations.NotNull()
    private final com.melih.repository.entities.RocketEntity rocket = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.melih.repository.entities.MissionEntity> missions = null;
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    public final long getLaunchStartTime() {
        return 0L;
    }
    
    public final long getLaunchEndTime() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.melih.repository.entities.LocationEntity getLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.melih.repository.entities.RocketEntity getRocket() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.melih.repository.entities.MissionEntity> getMissions() {
        return null;
    }
    
    public LaunchEntity(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, long launchStartTime, long launchEndTime, @org.jetbrains.annotations.NotNull()
    com.melih.repository.entities.LocationEntity location, @org.jetbrains.annotations.NotNull()
    com.melih.repository.entities.RocketEntity rocket, @org.jetbrains.annotations.NotNull()
    java.util.List<com.melih.repository.entities.MissionEntity> missions) {
        super();
    }
    
    public LaunchEntity() {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final long component4() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.melih.repository.entities.LocationEntity component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.melih.repository.entities.RocketEntity component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.melih.repository.entities.MissionEntity> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.melih.repository.entities.LaunchEntity copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, long launchStartTime, long launchEndTime, @org.jetbrains.annotations.NotNull()
    com.melih.repository.entities.LocationEntity location, @org.jetbrains.annotations.NotNull()
    com.melih.repository.entities.RocketEntity rocket, @org.jetbrains.annotations.NotNull()
    java.util.List<com.melih.repository.entities.MissionEntity> missions) {
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