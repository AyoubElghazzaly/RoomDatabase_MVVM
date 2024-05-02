package com.operation.roomdatabase_mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.operation.roomdatabase_mvvm.database.Employee;
import com.operation.roomdatabase_mvvm.databinding.ActivityAddEmployeeBinding;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class AddEmployeeActivity extends AppCompatActivity {

    ActivityAddEmployeeBinding binding ;
    Calendar selectedDate ;

    public static final String EMPLOYEE_KEY = "employee";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEmployeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSave.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String name = binding.editName.getText().toString();
                String email = binding.editEmail.getText().toString();
                String strEmpId = binding.editIdNumber.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(strEmpId)
                || selectedDate  == null){
                    Toast.makeText(AddEmployeeActivity.this, "Pleas enter a valid data", Toast.LENGTH_SHORT).show();
                    return;
                }
                long id = Long.parseLong(strEmpId);

                Employee employee = new Employee(id,name,email,selectedDate.getTime());
                Intent intent = new Intent();
                intent.putExtra(EMPLOYEE_KEY,employee);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        binding.datePickerBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        binding.datePickerBirthdate.setText(dayOfMonth+" / "+monthOfYear+" / "+year);
                        selectedDate = Calendar.getInstance();
                        selectedDate.set(Calendar.YEAR,year);
                        selectedDate.set(Calendar.MONTH,monthOfYear);
                        selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                    }
                }, Calendar.getInstance());
                dialog.show(getSupportFragmentManager(),null);
            }
        });

    }
}