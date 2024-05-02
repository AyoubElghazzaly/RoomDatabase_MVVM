package com.operation.roomdatabase_mvvm.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
@TypeConverters({DateConverter.class})
public interface SalaryDAO {

    @Insert
    void insertSalary(Salary salary);
    @Update
    void updateSalary(Salary salary);
    @Delete
    void deleteSalary(Salary salary);
    @Query("select * from salary where empId=:emp_id order by date desc")
    LiveData<List<Salary>> getEmployeeSalaries(long emp_id);
    @Query("select * from salary where empId =:emp_id AND date>=:from AND date<=:to order by date desc")
    LiveData<List<Salary>> getEmployeeSalaries(long emp_id , Date from , Date to);
    @Query("select * from salary where date>=:from AND date<=:to order by date desc")
    LiveData<List<Salary>> getEmployeeSalaries(Date from , Date to);

    @Query("select sum(amount) from salary where empId =:emp_id")
    double getSalariesSum(long emp_id);
}
