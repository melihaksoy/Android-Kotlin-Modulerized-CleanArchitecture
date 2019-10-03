package com.melih.repository.persistence.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.melih.repository.entities.LaunchEntity;
import com.melih.repository.entities.LocationEntity;
import com.melih.repository.entities.MissionEntity;
import com.melih.repository.entities.RocketEntity;
import com.melih.repository.persistence.converters.LocationConverter;
import com.melih.repository.persistence.converters.MissionConverter;
import com.melih.repository.persistence.converters.RocketConverter;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class LaunchesDao_Impl extends LaunchesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LaunchEntity> __insertionAdapterOfLaunchEntity;

  private final LocationConverter __locationConverter = new LocationConverter();

  private final RocketConverter __rocketConverter = new RocketConverter();

  private final MissionConverter __missionConverter = new MissionConverter();

  private final SharedSQLiteStatement __preparedStmtOfNukeLaunches;

  public LaunchesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLaunchEntity = new EntityInsertionAdapter<LaunchEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Launches` (`id`,`name`,`launchStartTime`,`launchEndTime`,`location`,`rocket`,`missions`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LaunchEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getLaunchStartTime());
        stmt.bindLong(4, value.getLaunchEndTime());
        final String _tmp;
        _tmp = __locationConverter.convertFrom(value.getLocation());
        if (_tmp == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, _tmp);
        }
        final String _tmp_1;
        _tmp_1 = __rocketConverter.convertFrom(value.getRocket());
        if (_tmp_1 == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, _tmp_1);
        }
        final String _tmp_2;
        _tmp_2 = __missionConverter.convertFrom(value.getMissions());
        if (_tmp_2 == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, _tmp_2);
        }
      }
    };
    this.__preparedStmtOfNukeLaunches = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Launches";
        return _query;
      }
    };
  }

  @Override
  public Object saveLaunches(final List<LaunchEntity> launches,
      final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLaunchEntity.insert(launches);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object saveLaunch(final LaunchEntity launch, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLaunchEntity.insert(launch);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object nukeLaunches(final Continuation<? super Unit> p0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfNukeLaunches.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfNukeLaunches.release(_stmt);
        }
      }
    }, p0);
  }

  @Override
  public Object getLaunches(final int count, final int page,
      final Continuation<? super List<LaunchEntity>> p2) {
    final String _sql = "SELECT * FROM Launches ORDER BY launchStartTime DESC LIMIT ? OFFSET ?*?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, count);
    _argIndex = 2;
    _statement.bindLong(_argIndex, page);
    _argIndex = 3;
    _statement.bindLong(_argIndex, count);
    return CoroutinesRoom.execute(__db, false, new Callable<List<LaunchEntity>>() {
      @Override
      public List<LaunchEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfLaunchStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "launchStartTime");
          final int _cursorIndexOfLaunchEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "launchEndTime");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfRocket = CursorUtil.getColumnIndexOrThrow(_cursor, "rocket");
          final int _cursorIndexOfMissions = CursorUtil.getColumnIndexOrThrow(_cursor, "missions");
          final List<LaunchEntity> _result = new ArrayList<LaunchEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final LaunchEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final long _tmpLaunchStartTime;
            _tmpLaunchStartTime = _cursor.getLong(_cursorIndexOfLaunchStartTime);
            final long _tmpLaunchEndTime;
            _tmpLaunchEndTime = _cursor.getLong(_cursorIndexOfLaunchEndTime);
            final LocationEntity _tmpLocation;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfLocation);
            _tmpLocation = __locationConverter.convertTo(_tmp);
            final RocketEntity _tmpRocket;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfRocket);
            _tmpRocket = __rocketConverter.convertTo(_tmp_1);
            final List<MissionEntity> _tmpMissions;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfMissions);
            _tmpMissions = __missionConverter.convertTo(_tmp_2);
            _item = new LaunchEntity(_tmpId,_tmpName,_tmpLaunchStartTime,_tmpLaunchEndTime,_tmpLocation,_tmpRocket,_tmpMissions);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p2);
  }

  @Override
  public Object getLaunchById(final long id, final Continuation<? super LaunchEntity> p1) {
    final String _sql = "SELECT * FROM Launches WHERE id=? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.execute(__db, false, new Callable<LaunchEntity>() {
      @Override
      public LaunchEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfLaunchStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "launchStartTime");
          final int _cursorIndexOfLaunchEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "launchEndTime");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfRocket = CursorUtil.getColumnIndexOrThrow(_cursor, "rocket");
          final int _cursorIndexOfMissions = CursorUtil.getColumnIndexOrThrow(_cursor, "missions");
          final LaunchEntity _result;
          if(_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final long _tmpLaunchStartTime;
            _tmpLaunchStartTime = _cursor.getLong(_cursorIndexOfLaunchStartTime);
            final long _tmpLaunchEndTime;
            _tmpLaunchEndTime = _cursor.getLong(_cursorIndexOfLaunchEndTime);
            final LocationEntity _tmpLocation;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfLocation);
            _tmpLocation = __locationConverter.convertTo(_tmp);
            final RocketEntity _tmpRocket;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfRocket);
            _tmpRocket = __rocketConverter.convertTo(_tmp_1);
            final List<MissionEntity> _tmpMissions;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfMissions);
            _tmpMissions = __missionConverter.convertTo(_tmp_2);
            _result = new LaunchEntity(_tmpId,_tmpName,_tmpLaunchStartTime,_tmpLaunchEndTime,_tmpLocation,_tmpRocket,_tmpMissions);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p1);
  }
}
