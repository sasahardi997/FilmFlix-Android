package com.example.filmflix.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.filmflix.R;
import com.example.filmflix.api.omdb.model.Search;

import java.util.List;

public class SearchAdapter extends  RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private Context context;
    private List<Search> movieList;

    public SearchAdapter(Context context, List<Search> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {

        viewHolder.title.setText(movieList.get(i).getTitle());

        float vote = 6.7f;
        String voteS = String.format("%.01f", vote);
        viewHolder.userrating.setText(voteS);

        String poster = movieList.get(i).getPoster();
        Glide.with(context).load(poster).placeholder(R.drawable.load).into(viewHolder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder{

        public TextView title, userrating;
        public ImageView thumbnail;

        public MyViewHolder(View view){
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            userrating = (TextView) view.findViewById(R.id.userrating);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

        }
    }
}
