package com.example.filmflix.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.filmflix.R;
import com.example.filmflix.model.Post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddPostActivity extends AppCompatActivity {

    ImageView movieImage;
    EditText movieDescription, movieIs;
    TextView movieTitle;
    Button addPost;
    String thumbnail;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        movieImage = findViewById(R.id.imageView);
        movieTitle = findViewById(R.id.title);
        movieDescription = findViewById(R.id.description);
        movieIs = findViewById(R.id.movie_is);
        addPost = findViewById(R.id.add_post);
        ratingBar = findViewById(R.id.rating);

        thumbnail = getIntent().getExtras().getString("thumbnail");
        Glide.with(AddPostActivity.this).load(thumbnail).into(movieImage);
        final String title = getIntent().getExtras().getString("title");
        movieTitle.setText(title);

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!movieTitle.getText().toString().isEmpty() && !movieDescription.getText().toString().isEmpty() && !thumbnail.equals("")){

                    Post post = new Post(title, movieDescription.getText().toString(), thumbnail, movieIs.getText().toString(), ratingBar.getRating());
                    addPost(post);

                } else {
                    Toast.makeText(AddPostActivity.this, "All fields must be fill", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addPost(Post post) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Posts").push();

        String key = myRef.getKey();
        post.setKey(key);

        myRef.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AddPostActivity.this, "Post added successfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddPostActivity.this, BlogActivity.class));
            }
        });
    }
}