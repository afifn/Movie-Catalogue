package com.kuycoding.jetpack.ui.tvshow.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kuycoding.jetpack.R;
import com.kuycoding.jetpack.data.source.local.entity.TvEntity;
import com.kuycoding.jetpack.viewModel.ViewModelFactory;

import java.util.Objects;

public class DetailTvShowActivity extends AppCompatActivity {

    public static final String EXTRA_TV = "extra_tv";
    private TextView tvTitles;
    private TextView tvPopular;
    private TextView tvVote;
    private TextView tvLanguange;
    private TextView tvRuntime;
    private TextView tvOverview;
    private ImageView imgCover;
    private ImageView imgPoster;
    private ProgressBar progressBar;
    private DetailTvShowViewModel viewModel;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        tvTitles = findViewById(R.id.txt_titles);
        tvPopular = findViewById(R.id.txt_populer);
        tvVote = findViewById(R.id.txt_vote);
        tvLanguange = findViewById(R.id.txt_lang);
        tvRuntime = findViewById(R.id.txt_runtume);
        tvOverview = findViewById(R.id.txt_overview);
        imgCover = findViewById(R.id.img_cover);
        imgPoster = findViewById(R.id.img_poster);
        progressBar = findViewById(R.id.progress_bar);

        viewModel = obtainViewModel(this);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            String courseId = extra.getString(EXTRA_TV);
            if (courseId != null) {
                viewModel.setCourseId(courseId);
            }
        }
        viewModel.getCourses.observe(this, courseWithModuleResource -> {
            if (courseWithModuleResource != null) {
                switch (courseWithModuleResource.status) {
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        if (courseWithModuleResource.data != null) {
                            progressBar.setVisibility(View.GONE);
                            populateCourses(courseWithModuleResource.data);
                        }
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    private DetailTvShowViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(DetailTvShowViewModel.class);
    }

    @SuppressLint("SetTextI18n")
    private void populateCourses(TvEntity tv) {
        tvTitles.setText(tv.getName());
        tvPopular.setText(tv.getPopularity());
        tvVote.setText(tv.getVote_average());
        tvLanguange.setText(tv.getOriginal_language());
        tvRuntime.setText(tv.getFirst_air_date());
        tvOverview.setText(tv.getOverview());

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + tv.getPoster_path())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imgPoster);

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + tv.getBackdrop_path())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imgCover);

        Objects.requireNonNull(getSupportActionBar()).setTitle(tv.getName());
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_bookmark:
                viewModel.setFavorite();
                return true;
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu = menu;
        viewModel.getCourses.observe(this, coureWithModel -> {
            if (coureWithModel != null){
                switch (coureWithModel.status) {
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        if (coureWithModel.data != null) {
                            progressBar.setVisibility(View.GONE);
                            boolean state = coureWithModel.data.isFavorite();
                            setBookmarkState(state);
                        }
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return true;
    }

    private void setBookmarkState(boolean state) {
        if (menu == null) return;
        MenuItem menuItem = menu.findItem(R.id.action_bookmark);
        if (state) {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_black_24dp));
        } else {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_border_black_24dp));
        }
    }
}
