package com.kplo.mapbab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostViewActivity extends AppCompatActivity {

    private TextView mTitle, mDate, mTime, mPlace, mCount, mContents;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);

        mDatabase = FirebaseDatabase.getInstance().getReference();


    }
}