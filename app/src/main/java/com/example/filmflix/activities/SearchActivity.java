package com.example.filmflix.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.filmflix.R;
import com.example.filmflix.adapters.MoviesAdapter;
import com.example.filmflix.adapters.SearchAdapter;
import com.example.filmflix.api.omdb.OMDBApiService;
import com.example.filmflix.api.omdb.model.OMDBResponse;
import com.example.filmflix.api.omdb.model.Search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    private List<Search> movieList;
    private ProgressDialog progressDialog;

    private void CallService(String query){
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Searching movies...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        HashMap<String,String> queryParams=new HashMap<>();
        queryParams.put("apikey","6da2384c");
        queryParams.put("s",query);

        Call<OMDBResponse> call= OMDBApiService.apiInterface().searchOMDB(queryParams);
        call.enqueue(new Callback<OMDBResponse>() {
            @Override
            public void onResponse(Call<OMDBResponse> call, Response<OMDBResponse> response) {
                if(response.code()==200){
                    List<Search> movies= response.body().getSearch();
                    recyclerView.setAdapter(new SearchAdapter(getApplicationContext(),movies));
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<OMDBResponse> call, Throwable t) {
                Toast.makeText(SearchActivity.this,"onFauiler: "+t.getMessage(),Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.recyclerView);
        movieList=new ArrayList<>();
        adapter=new SearchAdapter(this,movieList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        final EditText query= findViewById(R.id.search_text);

        Button search= findViewById(R.id.btn_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallService(query.getText().toString().trim());

                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });
    }
}