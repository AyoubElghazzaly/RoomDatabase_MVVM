package com.operation.roomdatabase_mvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.operation.roomdatabase_mvvm.database.Employee;

import java.util.List;

public class EmployeeSpinnerAdapter extends BaseAdapter {

    private List<Employee> employees ;

    public EmployeeSpinnerAdapter(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Employee getItem(int position) {
        return employees.get(position);
    }

    @Override
    public long getItemId(int position) {
        return employees.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView ;
        if (v == null){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_spinner_item,null,false);
        }
        TextView tv = v.findViewById(R.id.item_spinner);
        Employee e = getItem(position);
        tv.setText(e.getName());
        return v;
    }
}
