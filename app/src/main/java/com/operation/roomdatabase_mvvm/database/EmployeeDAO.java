package com.operation.roomdatabase_mvvm.database;

import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.List;

@Dao
@TypeConverters({DateConverter.class})
public interface EmployeeDAO {

    @Insert
    void insertEmployee(Employee employee);
    @Update
    void updateEmployee(Employee employee);
    @Delete
    void deleteEmployee(Employee employee);
    @Query("delete from Employee_table where email=:email")
    void deleteEmployeeByEmail(String email);
    @Query("select * from Employee_table order by name asc")
    LiveData<List<Employee>> getAllEmployees();

    @Query("select * from Employee_table where email=:email")
    LiveData<List<Employee>> getAllEmployeesByEmail(String email);

    @Query("select * from Employee_table where name like '%'||:name||'%' ")
    LiveData<List<Employee>> getEmployeesByName(String name);
}
