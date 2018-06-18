package com.brillicaservices.databaseapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button addstudent;
    Button displaystudent;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addstudent=(Button)findViewById(R.id.addstudentbtn);
        displaystudent=(Button)findViewById(R.id.displaystudentbtn);

        addstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,AddStudent_activity.class);
                startActivity(i);
            }
        });
        displaystudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i=new Intent(MainActivity.this,DisplayStudent_activity.class);
                startActivity(i);

            }
        });
    }
}
