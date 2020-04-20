package com.example.myapplication.Models;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.myapplication.R;
import com.example.myapplication.ViewModels.StudentListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class StudentListModel extends Fragment {
    private ArrayAdapter<String> listAdapter ;
    private TextView text;
    ArrayList<String> studentsList = new ArrayList<String>();
    ArrayList<String> studentsEr = new ArrayList<String>();
    private ListView mainListView ;
    private StudentListViewModel studentListViewModel;
    LiveData<DataSnapshot> liveData;
    FloatingActionButton add;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        studentListViewModel=  ViewModelProviders.of(this).get(StudentListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_first, container, false);
        liveData = studentListViewModel.getDataSnapshotLiveData();
        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainListView = (ListView) getView().findViewById(R.id.list);
        add=getView().findViewById(R.id.add);
        liveData.observe(Objects.requireNonNull(getActivity()), new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot){
                if (dataSnapshot != null) {
                    studentsList.clear();
                    studentsEr.clear();
                    for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if(snapshot.hasChild("name") && snapshot.hasChild("erno")){
                        studentsList.add(snapshot.child("name").getValue().toString());
                        studentsEr.add(snapshot.child("erno").getValue().toString());}
                    }
                    listAdapter = new ArrayAdapter<String>(getContext(),R.layout.list_element, studentsList);
                    mainListView.setAdapter( listAdapter );
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.new_student);

            }
        });
        text=getView().findViewById(R.id.name);
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(("Tag"),listAdapter.getItem(position));
                String namesend=studentsList.get(position);
                String ernosend=studentsEr.get(position);
                Navigation.findNavController(view).navigate(StudentListModelDirections.detailStudentAction(namesend,ernosend));
            }
        }
        );
    }

}
