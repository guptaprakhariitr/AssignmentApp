package com.example.myapplication.View;

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
import com.example.myapplication.ViewModels.otherViewModel;
import com.google.firebase.database.DataSnapshot;

public class NewStudent extends Fragment {
    private otherViewModel studentListViewModel;
    LiveData<DataSnapshot> liveData;
    EditText name,erno;
    Button submit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        studentListViewModel=  ViewModelProviders.of(this).get(otherViewModel.class);
        View root = inflater.inflate(R.layout.new_student, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        name=getView().findViewById(R.id.newName);
        erno=getView().findViewById(R.id.newerno);
        submit=getView().findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ernoing=erno.getText().toString();
                final String naming=name.getText().toString();
                otherViewModel.setData(naming,ernoing);
                name.setText("");
                erno.setText("");
                Toast.makeText(getContext(),"Done",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
