package com.operation.roomdatabase_mvvm.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Employee.class, Salary.class},version = 1,exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {

    public abstract EmployeeDAO employeeDAO();
    public abstract SalaryDAO salaryDAO();
    private static volatile MyRoomDatabase INSTANCE ;
    private static final int NUMBER_OF_THREADS = 4 ;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static MyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null){
            synchronized (MyRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyRoomDatabase.class,"employee_db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE ;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate (@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);

            databaseWriteExecutor.execute(()->{

            });
        }
    };
}
