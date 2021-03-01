package com.example.filmflix.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.filmflix.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class DetailActivity extends AppCompatActivity {

    private TextView nameOfMovie, plotSynopsis, userRating, releaseDate;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_detail);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        initCollapsingToolbar();

        imageView = (ImageView) findViewById(R.id.thumbnail_image_header);
        nameOfMovie = (TextView) findViewById(R.id.title);
        plotSynopsis = (TextView) findViewById(R.id.plotsynopsis);
        userRating = (TextView) findViewById(R.id.userrating);
        releaseDate = (TextView) findViewById(R.id.releasedates);

        String thumbnail = getIntent().getExtras().getString("poster_path");
        String movieName = getIntent().getExtras().getString("original_title");
        String synopsis = getIntent().getExtras().getString("overview");
        String rating = getIntent().getExtras().getString("vote_average");
        String dateOfRelease = getIntent().getExtras().getString("release_date");

        Glide.with(this)
                .load(thumbnail)
                .placeholder(R.drawable.load)
                .into(imageView);

        nameOfMovie.setText(movieName);
        plotSynopsis.setText(synopsis);
        userRating.setText(rating);
        releaseDate.setText(dateOfRelease);
    }

//    private void initCollapsingToolbar() {
//        final CollapsingToolbarLayout collapsingToolbarLayout =
//                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbarLayout.setTitle("");
//        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
//        appBarLayout.setExpanded(true);
//
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            boolean isShow = false;
//            int scrollRange = -1;
//
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if(scrollRange == -1){
//                    scrollRange = appBarLayout.getTotalScrollRange();
//                }
//                if(scrollRange + verticalOffset == 0){
//                    collapsingToolbarLayout.setTitle(getString(R.string.movie_details));
//                    isShow = true;
//                } else if(isShow){
//                    collapsingToolbarLayout.setTitle("");
//                    isShow = false;
//                }
//            }
//        });
//    }
}
