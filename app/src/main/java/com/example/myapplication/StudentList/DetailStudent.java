package com.example.myapplication.StudentList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

import org.w3c.dom.Text;

public class DetailStudent extends Fragment {
    TextView namee,ernoe;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
       View root= inflater.inflate(R.layout.detail_student,container,false);
        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        namee=getView().findViewById(R.id.detailname);
        ernoe=getView().findViewById(R.id.detailerno);
        String name=DetailStudentArgs.fromBundle(getArguments()).getName();
        String erno=DetailStudentArgs.fromBundle(getArguments()).getErno();
        Log.i("surendra",name+erno);
        namee.setText(name);
        ernoe.setText(erno);
    }
}
