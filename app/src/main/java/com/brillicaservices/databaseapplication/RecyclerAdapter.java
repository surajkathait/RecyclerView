package com.brillicaservices.databaseapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by win-8 on 12-06-2018.
 */

public class RecyclerAdapter  extends
        RecyclerView.Adapter<RecyclerAdapter.StudentViewHolder> {

    private static final String TAG = RecyclerAdapter.class.getName();

    private final ListItemClickListener itemClickListener;

    private static int viewHolderCount;

    private ArrayList<StudentData> studentArrayList;

    public interface ListItemClickListener {
        void onListItemClickListener(int clickedItemIndex);
    }

    public RecyclerAdapter(ArrayList<StudentData> studentArrayList,
                           ListItemClickListener itemClickListener) {
        this.studentArrayList = studentArrayList;
        this.itemClickListener = itemClickListener;
        //viewHolderCount = 0;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout, parent, false);

        StudentViewHolder studentViewHolder = new StudentViewHolder(view);
        //studentViewHolder.recyclerNumber.setText(""+viewHolderCount);

        //viewHolderCount = viewHolderCount + 1;

        return studentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.StudentViewHolder holder,
                                 int position) {
        StudentData student = studentArrayList.get(position);

        holder.studentId.setText("Id is: "+ student.getId());
        holder.studentName.setText("Name is: " + student.getName());
        holder.studentAddress.setText("Address is: " + student.getAddress());
        holder.studentCollege.setText("College is: " + student.getCollegeName());
        holder.studentPhone.setText("PhoneNumber is: " + student.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }

    public class StudentViewHolder extends
            RecyclerView.ViewHolder
            implements View.OnClickListener{

        TextView studentId;
        TextView studentName;
        TextView studentAddress;
        TextView studentCollege;
        TextView studentPhone;

       // TextView recyclerNumber;


        public StudentViewHolder(View itemView) {
            super(itemView);
            studentId=itemView.findViewById(R.id.student_id);
            studentName = itemView.findViewById(R.id.student_name);
            studentAddress = itemView.findViewById(R.id.student_address);
            studentCollege = itemView.findViewById(R.id.student_college);
            studentPhone = itemView.findViewById(R.id.student_phone);
           // recyclerNumber = itemView.findViewById(R.id.recycler_number);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            itemClickListener.onListItemClickListener(clickedPosition);
        }
    }
}


