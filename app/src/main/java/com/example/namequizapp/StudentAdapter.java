package com.example.namequizapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.namequizapp.database.Person;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    List<Person> students;
    Context context;

    public StudentAdapter(Context ct, List<Person> students) {
        context = ct;
        this.students = students;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_custom_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.getTextView().setText(students.get(position).getName());
        holder.getImageView().setImageURI(Uri.parse(students.get(position).getPicture()));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView myImage;
        private TextView studentName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myImage = itemView.findViewById(R.id.studentImage);
            studentName = itemView.findViewById(R.id.studentText);
        }

        public TextView getTextView() {
            return studentName;
        }

        public ImageView getImageView() {
            return myImage;
        }
    }
}
