package com.operation.roomdatabase_mvvm;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.operation.roomdatabase_mvvm.database.Employee;
import com.operation.roomdatabase_mvvm.database.MyViewModel;
import com.operation.roomdatabase_mvvm.database.Salary;
import com.operation.roomdatabase_mvvm.databinding.ActivityAddSalaryBinding;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddSalaryActivity extends AppCompatActivity {

    ActivityAddSalaryBinding binding ;
    Calendar selectedDate ;
    MyViewModel myViewModel ;

    EmployeeSpinnerAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddSalaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding.btnSalaryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        binding.btnSalaryDate.setText(dayOfMonth+" / "+monthOfYear+" / "+year);
                        selectedDate = Calendar.getInstance();
                        selectedDate.set(Calendar.YEAR,year);
                        selectedDate.set(Calendar.MONTH,monthOfYear);
                        selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                    }
                }, Calendar.getInstance());
                dialog.show(getSupportFragmentManager(),null);
            }
        });

        adapter = new EmployeeSpinnerAdapter(new ArrayList<>());
        binding.mySpinner.setAdapter(adapter);
        myViewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.setEmployees(employees);
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strAmount = binding.editAmount.getText().toString();
                long empId = binding.mySpinner.getSelectedItemId();
                if (TextUtils.isEmpty(strAmount) || selectedDate == null){
                    Toast.makeText(AddSalaryActivity.this, "Please enter a valid data", Toast.LENGTH_SHORT).show();
                    return;
                }
                double amount = Double.parseDouble(strAmount);
                Salary salary = new Salary(amount,selectedDate.getTime(),empId);
                myViewModel.insertSalary(salary);
                finish();
            }
        });


    }
}