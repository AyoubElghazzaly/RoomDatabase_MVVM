package com.operation.roomdatabase_mvvm.database;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

public class Repository {
    EmployeeDAO employeeDAO ;
    SalaryDAO salaryDAO ;

    public Repository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        employeeDAO = db.employeeDAO();
        salaryDAO = db.salaryDAO();
    }

    // methods employee dao
    public void insertEmployee(Employee employee){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDAO.insertEmployee(employee);
            }
        });
    }

    public void updateEmployee(Employee employee){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDAO.updateEmployee(employee);
            }
        });
    }
    public void deleteEmployee(Employee employee){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDAO.deleteEmployee(employee);
            }
        });
    }
    public void deleteEmployeeByEmail(String email){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDAO.deleteEmployeeByEmail(email);
            }
        });
    }
    public LiveData<List<Employee>> getAllEmployees(){
        return employeeDAO.getAllEmployees();
    }

    public LiveData<List<Employee>> getAllEmployeesByEmail(String email){
        return employeeDAO.getAllEmployeesByEmail(email);
    }

    public LiveData<List<Employee>> getEmployeesByName(String name){
        return employeeDAO.getEmployeesByName(name);
    }

    // methods Salary dao


    public void insertSalary(Salary salary){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                salaryDAO.insertSalary(salary);
            }
        });
    }
    public void updateSalary(Salary salary){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                salaryDAO.updateSalary(salary);
            }
        });
    }
    public void deleteSalary(Salary salary){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                salaryDAO.deleteSalary(salary);
            }
        });
    }
    public LiveData<List<Salary>> getEmployeeSalaries(long emp_id){
        return salaryDAO.getEmployeeSalaries(emp_id);
    }
    public LiveData<List<Salary>> getEmployeeSalaries(long emp_id , Date from , Date to){
        return salaryDAO.getEmployeeSalaries(emp_id , from,to);
    }
    public LiveData<List<Salary>> getEmployeeSalaries(Date from , Date to){
        return salaryDAO.getEmployeeSalaries(from , to);
    }

    public void getSalariesSum(long emp_id , DoubleValueListener listener){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Double value = salaryDAO.getSalariesSum(emp_id);
                listener.onValueSubmit(value);
            }
        });
    }
}
