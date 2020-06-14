package com.kuycoding.jetpack.ui.movie.detail;

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
import com.kuycoding.jetpack.data.source.local.entity.MovieEntity;
import com.kuycoding.jetpack.viewModel.ViewModelFactory;

import java.util.Objects;

public class DetailMovieActivity extends AppCompatActivity {
    public final static String EXTRA_MOVIE = "extra_movie";
    private TextView tvTitle;
    private TextView tvPopular;
    private TextView tvVote;
    private TextView tvLanguange;
    private TextView tvRuntime;
    private TextView tvDate;
    private TextView tvOverview;
    private ImageView imgCover;
    private ImageView imgPoster;
    private ProgressBar progressBar;
    private DetailMovieViewModel viewModel;
    private Menu menu;

    public DetailMovieActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        tvTitle = findViewById(R.id.txt_title);
        tvPopular = findViewById(R.id.txt_populer);
        tvVote = findViewById(R.id.txt_vote);
        tvLanguange = findViewById(R.id.txt_lang);
        tvRuntime = findViewById(R.id.txt_runtume);
        tvDate = findViewById(R.id.txt_release);
        tvOverview = findViewById(R.id.txt_overview);
        imgCover = findViewById(R.id.img_cover);
        imgPoster = findViewById(R.id.img_poster);
        progressBar = findViewById(R.id.progress_bar);
        viewModel = obtainViewModel(this);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            String courseId = extra.getString(EXTRA_MOVIE);
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
                            populateCourse(courseWithModuleResource.data);
                        }
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    private DetailMovieViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(DetailMovieViewModel.class);
    }

    @SuppressLint("SetTextI18n")
    private void populateCourse(MovieEntity movie) {
        tvTitle.setText(movie.getTitle());
        tvPopular.setText(movie.getPopularity());
        tvVote.setText(movie.getVote_average());
        tvLanguange.setText(movie.getOriginal_language());
        tvRuntime.setText(movie.getVote_count());
        tvDate.setText(movie.getRelease_date());
        tvOverview.setText(movie.getOverview());

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + movie.getPoster_path())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imgPoster);

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + movie.getBackdrop_path())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imgCover);

        Objects.requireNonNull(getSupportActionBar()).setTitle(movie.getTitle());
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
            if (coureWithModel != null) {
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
