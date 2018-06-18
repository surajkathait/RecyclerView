package com.brillicaservices.databaseapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ListIntentActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_intent);

        tv=(TextView)findViewById(R.id.result);

        Intent i=getIntent();

        Integer id=i.getIntExtra("id",0);
        String name= i.getStringExtra("name");
        String address=i.getStringExtra("Address");
        String college=i.getStringExtra("College");
        long phone=i.getLongExtra("Phone",0);

        tv.setText(tv.getText()+"Id is: "+id+"\n");
        tv.setText(tv.getText()+"Name is :"+name+"\n");
        tv.setText(tv.getText()+"Address is :"+address+"\n");
        tv.setText(tv.getText()+"College is :"+college+"\n");
        tv.setText(tv.getText()+"Phone is :"+phone);



    }
}
