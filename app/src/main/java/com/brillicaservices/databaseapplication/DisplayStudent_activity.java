package com.brillicaservices.databaseapplication;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayStudent_activity extends AppCompatActivity  implements RecyclerAdapter.ListItemClickListener {

    DatabaseHelper db;
    RecyclerView rv;
    RecyclerAdapter recyclerAdapter;
    ArrayList<StudentData> studentArrayList=new ArrayList<StudentData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_student_activity);

        rv=(RecyclerView) findViewById(R.id.recycler_view_result);
        db = new DatabaseHelper(this);

        studentArrayList.addAll(db.allStudentsDetails());

        //studentArrayList.add(new StudentData(name, collegeName, address, phone));
        /*for (int i=0; i<studentArrayList.size(); i++) {


            result.setText(result.getText() +
                    "Student ID is: " + studentArrayList.get(i).getId() + "\n");
            result.setText(result.getText() +
                    "Student Name is: " + studentArrayList.get(i).getName() + "\n");
            result.setText(result.getText() +
                    "Student College is: " + studentArrayList.get(i).getCollegeName() + "\n");
            result.setText(result.getText() +
                    "Student Phone Number is: " + studentArrayList.get(i).getPhoneNumber() + "\n");
            result.setText(result.getText() +
                    "Student Address is: " + studentArrayList.get(i).getAddress() + "\n");
            result.setText(result.getText() + "****************\n\n");*/


            LinearLayoutManager linearLayoutManager =
                    new LinearLayoutManager(this);
            rv.setLayoutManager(linearLayoutManager);

            rv.setHasFixedSize(true);
            recyclerAdapter = new RecyclerAdapter(studentArrayList, this);
            rv.setAdapter(recyclerAdapter);

        }


    @Override
    public void onListItemClickListener(int clickedItemIndex) {
        Intent i=new Intent(getApplicationContext(),ListIntentActivity.class);
        i.putExtra("id",studentArrayList.get(clickedItemIndex).getId());
        i.putExtra("name",studentArrayList.get(clickedItemIndex).getName());
        i.putExtra("Address",studentArrayList.get(clickedItemIndex).getAddress());
        i.putExtra("College",studentArrayList.get(clickedItemIndex).getCollegeName());
        i.putExtra("Phone",studentArrayList.get(clickedItemIndex).getPhoneNumber());
        startActivity(i);

    }
}
