package com.operation.roomdatabase_mvvm.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(foreignKeys = {@ForeignKey(entity = Employee.class,
        parentColumns = {"id"},
        childColumns = {"empId"},
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE),})
@TypeConverters({DateConverter.class})
public class Salary {

    @PrimaryKey(autoGenerate = true)
    private int id ;
    private double amount ;
    private Date date ;
    private long empId ;

    public Salary() {
    }

    public Salary(int id, double amount, Date date, long empId) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.empId = empId;
    }

    public Salary(double amount, Date date, long empId) {
        this.amount = amount;
        this.date = date;
        this.empId = empId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }
}
