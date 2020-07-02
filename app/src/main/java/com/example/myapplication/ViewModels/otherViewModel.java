package com.example.myapplication.ViewModels;

import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class otherViewModel extends ViewModel {
    private static final DatabaseReference Students =
            FirebaseDatabase.getInstance().getReference("/Students");
    public static void setData(String name,String er) {
        String uid=Students.push().getKey();
        Students.child(uid).child("name").setValue(name);
        Students.child(uid).child("erno").setValue(er);
    }
}
