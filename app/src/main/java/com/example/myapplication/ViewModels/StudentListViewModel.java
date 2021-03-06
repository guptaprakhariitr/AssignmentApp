package com.example.myapplication.ViewModels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class StudentListViewModel extends ViewModel {
    private static final DatabaseReference Students =
            FirebaseDatabase.getInstance().getReference("/Students");

    private final FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(Students);

    @NonNull
    public LiveData<DataSnapshot> getDataSnapshotLiveData() {
        return liveData;
    }
    public static void setTorefreshData(String refresh) {
        String uid=Students.push().getKey();
        Students.child("refresh").child("refresh").setValue(refresh);
    }
}
   class FirebaseQueryLiveData extends LiveData<DataSnapshot> {
    private static final String LOG_TAG = "FirebaseQueryLiveData";
    private final Query query;
    private final MyValueEventListener listener = new MyValueEventListener();
    public FirebaseQueryLiveData(Query query) {
        this.query = query;
    }
    public FirebaseQueryLiveData(DatabaseReference ref) {
        this.query = ref;
    }

    @Override
    protected void onActive() {
        Log.d(LOG_TAG, "onActive");
        query.addValueEventListener(listener);
    }
    @Override
    protected void onInactive() {
        Log.d(LOG_TAG, "onInactive");
        query.removeEventListener(listener);
    }

    private class MyValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
            setValue(dataSnapshot);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e(LOG_TAG, "Can't listen to query " + query, databaseError.toException());
        }
    }
}