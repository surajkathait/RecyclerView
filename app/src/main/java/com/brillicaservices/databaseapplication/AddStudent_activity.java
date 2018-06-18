package com.brillicaservices.databaseapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddStudent_activity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {

    static final String TAG = MainActivity.class.getName();

    EditText studentName, studentPhone, studentAddress;
    Button submitBtn;
    String collegeName ;

    ArrayList<StudentData> studentArrayList = new ArrayList<StudentData>();

    Spinner spinnerCollegeNames;

    String collegeNames[] = {"Select college name", "DIT", "Graphic Era", "HNB"};

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_activity);

        studentName = findViewById(R.id.name);
        studentPhone = findViewById(R.id.phoneno);
        studentAddress = findViewById(R.id.address);
        submitBtn = findViewById(R.id.submit);

        databaseHelper = new DatabaseHelper(this);

        spinnerCollegeNames = findViewById(R.id.spinner);

        spinnerCollegeNames.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, collegeNames);

        spinnerCollegeNames.setAdapter(arrayAdapter);
        spinnerCollegeNames.setPrompt(collegeNames[0]);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String name = studentName.getText().toString();
                long phone = Long.parseLong(studentPhone.getText().toString());
                String address = studentAddress.getText().toString();

                databaseHelper.addNewStudent(new StudentData(name, collegeName, address, phone));
                studentArrayList.addAll(databaseHelper.allStudentsDetails());

                Toast.makeText(getApplicationContext(), "Details Entered Successfully", Toast.LENGTH_LONG).show();


            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        collegeName = collegeNames[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


