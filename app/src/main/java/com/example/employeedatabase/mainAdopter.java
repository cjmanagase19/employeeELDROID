package com.example.employeedatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class mainAdopter extends RecyclerView.Adapter<mainAdopter.ViewHolder> {
   List<employeeModel> employee;

   public mainAdopter(List<employeeModel> employee)
   {

       this.employee=employee;
   }
    @NonNull
    @Override
    public mainAdopter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new mainAdopter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull mainAdopter.ViewHolder holder, int position) {
       holder.empName.setText(employee.get(position).getEmpName());
       holder.empID.setText(employee.get(position).getEmpID());
       holder.empPosition.setText(employee.get(position).getEmpPosition());
       holder.empAddress.setText(employee.get(position).getEmpAddress());
       holder.empGender.setText(employee.get(position).getEmpGender());


    }

    @Override
    public int getItemCount() {
        return employee.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView empID,empPosition,empName,empGender,empAddress;
       Button selectBtn,deleteBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            empID = (TextView) itemView.findViewById(R.id.emp_id_tv);
            empPosition = (TextView) itemView.findViewById(R.id.emp_position_tv);
            empAddress = (TextView) itemView.findViewById(R.id.emp_address_tv);
            empName = (TextView) itemView.findViewById(R.id.emp_name_tv);
            empGender = (TextView) itemView.findViewById(R.id.emp_gender_tv);

        }

    }
}
