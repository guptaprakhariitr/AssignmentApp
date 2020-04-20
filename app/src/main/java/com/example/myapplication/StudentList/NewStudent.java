package com.example.myapplication.StudentList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;

public class NewStudent extends Fragment {
    private StudentListViewModel studentListViewModel;
    LiveData<DataSnapshot> liveData;
    EditText name,erno;
    Button submit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        studentListViewModel=  ViewModelProviders.of(this).get(StudentListViewModel.class);
        View root = inflater.inflate(R.layout.new_student, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name=getView().findViewById(R.id.newName);
        erno=getView().findViewById(R.id.newerno);
        submit=getView().findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ernoing=erno.getText().toString();
                final String naming=name.getText().toString();
                Runnable runnable=new Runnable() {
                    @Override
                    public void run() {

                        StudentListViewModel.setData(naming,ernoing);
                    }
                };
                runnable.run();
                name.setText("");
                erno.setText("");
                Toast.makeText(getContext(),"Done",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
