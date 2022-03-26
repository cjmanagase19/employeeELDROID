package com.example.employeedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button createBtn,retrieveBtn,deleteBtn,updateBtn;
    EditText empName,empAddress,empGender,empPosition,empID;
    RecyclerView recyclerView;
    DatabaseSql databaseSql;
    Boolean isFound=false;
    List<employeeModel> employeeArray = new ArrayList<employeeModel>();
    mainAdopter mainAdopter;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        createBtn = (Button) findViewById(R.id.createBtn);
        retrieveBtn = (Button)findViewById(R.id.retrieveBtn);
        deleteBtn = (Button)findViewById(R.id.deleteBtn);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        empAddress = (EditText)findViewById(R.id.addressEditText);
        empGender = (EditText)findViewById(R.id.genderEditView);
        empName = (EditText)findViewById(R.id.nameEditText);
        empPosition = (EditText)findViewById(R.id.positionEditText);
        empID = (EditText)findViewById(R.id.empIDEditText);

        Random rnd = new Random();
        databaseSql = new DatabaseSql(this);
        retrieveAll();
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrieveAll();
                if (empID.getText().toString().equals(""))
                {
                    int rEmpID = rnd.nextInt(1000000000);
                    empID.setText(""+rEmpID);
                    empID.setEnabled(true);
                    empAddress.setEnabled(true);
                    empGender.setEnabled(true);
                    empName.setEnabled(true);
                    empPosition.setEnabled(true);
                }
                 else
                {
                    if(checker()==false) {
                        empID.setEnabled(true);
                        empAddress.setEnabled(true);
                        empGender.setEnabled(true);
                        empName.setEnabled(true);
                        empPosition.setEnabled(true);
                        String id = empID.getText().toString();
                        String name = empName.getText().toString();
                        String position = empPosition.getText().toString();
                        String address = empAddress.getText().toString();
                        String genders = empGender.getText().toString();
                        Boolean createData = databaseSql.create(id, name, position, address, genders);
                        if (createData == true) {
                            Toast.makeText(MainActivity.this, "Create Data Successfully", Toast.LENGTH_SHORT).show();
                            Log.d("dasda",id+ name+ position+ address +genders);
                            reset();
                        } else {
                            Toast.makeText(MainActivity.this, "Create Data Fail", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                retrieveAll();
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrieveAll();
                if(isFound==false)
                {
                    Toast.makeText(MainActivity.this, "Please Retrieve data first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean updateData = databaseSql.update(empID.getText().toString(),empName.getText().toString(),empPosition.getText().toString(), empAddress.getText().toString(),empGender.getText().toString());
                    if(updateData == true){
                        Toast.makeText(MainActivity.this,"Entry Successfully Updated", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this,"Entry Not Updated", Toast.LENGTH_SHORT).show(); } ;
                }
                retrieveAll();

            }

        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrieveAll();
                if(isFound==true)
                {
                    empID.setEnabled(false);
                    empAddress.setEnabled(false);
                    empGender.setEnabled(false);
                    empName.setEnabled(false);
                    empPosition.setEnabled(false);
                    String id = empID.getText().toString();
                    Boolean deleteData = databaseSql.delete(id);
                    if(deleteData=true){
                        Toast.makeText(MainActivity.this,"Successfully Deleted!", Toast.LENGTH_SHORT).show();
                    }
                    else
                        {
                        Toast.makeText(MainActivity.this,"Not Deleted!", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please Retrieve data first", Toast.LENGTH_SHORT).show();
                }
                retrieveAll();
            }
        });
        retrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor result = databaseSql.retrieve(empID.getText().toString());
                if (result.getCount() == 0)
                {
                    isFound = false;
                    Toast.makeText(MainActivity.this, "Employee not exist! Try again", Toast.LENGTH_SHORT).show();
                    Log.d("we naa man gud",empID.getText().toString());
                    reset();
                    return;
                }
                else
                {
                    isFound = true;
                    while(result.moveToNext())
                    {
                        empID.setText(result.getString(0));
                        empName.setText(result.getString(1));
                        empPosition.setText(result.getString(2));
                        empAddress.setText(result.getString(3));
                        empGender.setText(result.getString(4));
                        empID.setEnabled(false);
                        empPosition.setEnabled(true);
                        empName.setEnabled(true);
                        empGender.setEnabled(true);
                        empAddress.setEnabled(true);
                    }
                }
            }
        });
    }

    public void retrieveAll()
    {

        if(employeeArray.size()!=0)
        {
            employeeArray.clear();
            mainAdopter.notifyDataSetChanged();
        }
        Cursor result = databaseSql.getdata();
        if (result.getCount() == 0) {
            Toast.makeText(MainActivity.this, "Table is Empty", Toast.LENGTH_SHORT).show();
            return; }
        while (result.moveToNext())
        {
            employeeModel employeeModels = new employeeModel(result.getString(0),result.getString(1),result.getString(2),result.getString(3),result.getString(4));
            employeeArray.add(employeeModels);

        }
        layoutManager = new LinearLayoutManager(this);
        mainAdopter = new mainAdopter(employeeArray);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdopter);
    }

    public boolean checker(){
        if(empID.getText().toString().equals(""))
        {
            empID.setError("Enter id");
            return true;
        }
        if(empAddress.getText().toString().equals(""))
        {
            empAddress.setError("Enter address");
            return true;
        }
        if(empGender.getText().toString().equals(""))
        {
            empGender.setError("Enter gender");
            return  true;
        }
        if(empName.getText().toString().equals(""))
        {
            empName.setError("Enter name");
            return true;
        }
        if(empPosition.getText().toString().equals(""))
        {
            empPosition.setError("Enter Position");
            return true;
        }
        else
        {

            return  false;
        }
    }
    public void reset(){
        empID.setEnabled(true);
        empPosition.setEnabled(false);
        empName.setEnabled(false);
        empGender.setEnabled(false);
        empAddress.setEnabled(false);
        empID.setText("");
        empPosition.setText("");
        empName.setText("");
        empGender.setText("");
        empAddress.setText("");
    }

}
