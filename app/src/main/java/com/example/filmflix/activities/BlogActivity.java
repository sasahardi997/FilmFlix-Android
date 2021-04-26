package com.example.filmflix.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.filmflix.R;
import com.example.filmflix.adapters.PostAdapter;
import com.example.filmflix.model.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BlogActivity extends AppCompatActivity {

    RecyclerView postRecyclerView;
    PostAdapter postAdapter;
    List<Post> postList;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Posts");

        postRecyclerView = findViewById(R.id.postRV);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(BlogActivity.this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList = new ArrayList<>();
                for(DataSnapshot postsnap : snapshot.getChildren()){
                    Post post = postsnap.getValue(Post.class);
                    postList.add(post);
                }

                postAdapter = new PostAdapter(BlogActivity.this, postList);
                postRecyclerView.setAdapter(postAdapter);
                postRecyclerView.setHasFixedSize(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}