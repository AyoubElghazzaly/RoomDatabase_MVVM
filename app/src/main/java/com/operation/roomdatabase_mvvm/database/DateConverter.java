package com.operation.roomdatabase_mvvm.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long milliseconds){
        return milliseconds == null? null:new Date(milliseconds);
    }

    @TypeConverter
    public static Long fromDate(Date date){
        return date == null? null: date.getTime();
    }
}
