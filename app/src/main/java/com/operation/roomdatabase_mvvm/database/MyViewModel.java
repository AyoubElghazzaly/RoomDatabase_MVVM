package com.operation.roomdatabase_mvvm.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

public class MyViewModel extends AndroidViewModel {

    Repository repository ;
    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    // methods employee dao
    public void insertEmployee(Employee employee){
        repository.insertEmployee(employee);
    }

    public void updateEmployee(Employee employee){
        repository.updateEmployee(employee);
    }
    public void deleteEmployee(Employee employee){
        repository.deleteEmployee(employee);
    }
    public void deleteEmployeeByEmail(String email){
        repository.deleteEmployeeByEmail(email);
    }
    public LiveData<List<Employee>> getAllEmployees(){
        return repository.getAllEmployees();
    }

    public LiveData<List<Employee>> getAllEmployeesByEmail(String email){
        return repository.getAllEmployeesByEmail(email);
    }

    public LiveData<List<Employee>> getEmployeesByName(String name){
        return repository.getEmployeesByName(name);
    }

    // methods Salary dao


    public void insertSalary(Salary salary){
        repository.insertSalary(salary);
    }
    public void updateSalary(Salary salary){
        repository.updateSalary(salary);
    }
    public void deleteSalary(Salary salary){
        repository.deleteSalary(salary);
    }
    public LiveData<List<Salary>> getEmployeeSalaries(long emp_id){
        return repository.getEmployeeSalaries(emp_id);
    }
    public LiveData<List<Salary>> getEmployeeSalaries(long emp_id , Date from , Date to){
        return repository.getEmployeeSalaries(emp_id , from,to);
    }
    public LiveData<List<Salary>> getEmployeeSalaries(Date from , Date to){
        return repository.getEmployeeSalaries(from , to);
    }

    public void getSalariesSum(long emp_id , DoubleValueListener listener){
        repository.getSalariesSum(emp_id,listener);
    }
}
