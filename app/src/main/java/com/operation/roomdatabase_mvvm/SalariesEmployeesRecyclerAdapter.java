package com.operation.roomdatabase_mvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.operation.roomdatabase_mvvm.database.DoubleValueListener;
import com.operation.roomdatabase_mvvm.database.Employee;
import com.operation.roomdatabase_mvvm.database.MyViewModel;
import com.operation.roomdatabase_mvvm.databinding.CustomSalaryEmployeeItemBinding;

import java.util.List;

public class SalariesEmployeesRecyclerAdapter extends RecyclerView.Adapter<SalariesEmployeesRecyclerAdapter.ViewEmpHolder> {
    private List<Employee> employees ;
    private MyViewModel mvm ;

    public SalariesEmployeesRecyclerAdapter(List<Employee> employees, MyViewModel mvm) {
        this.employees = employees;
        this.mvm = mvm;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewEmpHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewEmpHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_salary_employee_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewEmpHolder holder, int position) {
        Employee e = employees.get(position);
        holder.bind(e,mvm);
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class ViewEmpHolder extends RecyclerView.ViewHolder {
        CustomSalaryEmployeeItemBinding binding ;
        Employee employee ;
        public ViewEmpHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomSalaryEmployeeItemBinding.bind(itemView);
        }
        public void bind(Employee employee , MyViewModel mvm){
            this.employee = employee ;
            binding.tvName.setText(employee.getName());
            mvm.getSalariesSum(employee.getId(), new DoubleValueListener() {
                @Override
                public void onValueSubmit(Double value) {
                    binding.tvSalary.setText(String.valueOf(value));
                }
            });
        }
    }
}
