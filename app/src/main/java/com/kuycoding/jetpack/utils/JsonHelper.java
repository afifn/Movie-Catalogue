package com.kuycoding.jetpack.utils;

import android.app.Application;

import com.kuycoding.jetpack.data.source.remote.response.MovieModel;
import com.kuycoding.jetpack.data.source.remote.response.TvModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsonHelper {
    private Application application;
    public JsonHelper(Application application) {
        this.application = application;
    }

    private String parsingFileToString(String fileName) {
        try {
            InputStream is = application.getAssets().open(fileName);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer);

        } catch (IOException e) {
             e.printStackTrace();
             return null;
        }
    }

    public List<MovieModel> loadMovie(){
        ArrayList<MovieModel> list = new ArrayList<>();
        try {
            JSONObject responseObject = new JSONObject(Objects.requireNonNull(parsingFileToString("movie.json")));
            JSONArray listArray = responseObject.getJSONArray("results");
            for (int i = 0; i < listArray.length(); i++) {
                JSONObject course = listArray.getJSONObject(i);

                String popularity = course.getString("popularity");
                String vote_count = course.getString("vote_count");
                String poster_path = course.getString("poster_path");
                String id = course.getString("id");
                String adult = course.getString("adult");
                String backdrop_path = course.getString("backdrop_path");
                String original_language = course.getString("original_language");
                String original_title = course.getString("original_title");
                String genre_ids = course.getString("genre_ids");
                String title = course.getString("title");
                String vote_average = course.getString("vote_average");
                String overview = course.getString("overview");
                String release_date = course.getString("release_date");

                MovieModel movieModel = new MovieModel(popularity,vote_count,poster_path,id,adult,backdrop_path,original_language,original_title,genre_ids,title,vote_average,overview,release_date);
                list.add(movieModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MovieModel> loadMovieById(String courseId) {
        String fileName = String.format("movie.json", courseId);
        ArrayList<MovieModel> list = new ArrayList<>();
        try {
            String result = parsingFileToString(fileName);
            if (result != null) {
                JSONObject responseObject = new JSONObject(result);
                JSONArray listArray = responseObject.getJSONArray("results");
                for (int i = 0; i < listArray.length(); i++) {
                    JSONObject course = listArray.getJSONObject(i);

                    String popularity = course.getString("popularity");
                    String vote_count = course.getString("vote_count");
                    String poster_path = course.getString("poster_path");
                    String id = course.getString("id");
                    String adult = course.getString("adult");
                    String backdrop_path = course.getString("backdrop_path");
                    String original_language = course.getString("original_language");
                    String original_title = course.getString("original_title");
                    String genre_ids = course.getString("genre_ids");
                    String title = course.getString("title");
                    String vote_average = course.getString("vote_average");
                    String overview = course.getString("overview");
                    String release_date = course.getString("release_date");

                    MovieModel movieModel = new MovieModel(popularity,vote_count,poster_path,id,adult,backdrop_path,original_language,original_title,genre_ids,title,vote_average,overview,release_date);
                    list.add(movieModel);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<TvModel> loadTv() {
        ArrayList<TvModel> list = new ArrayList<>();
        try {
            JSONObject responseObject = new JSONObject(Objects.requireNonNull(parsingFileToString("tv.json")));
            JSONArray listArray = responseObject.getJSONArray("results");
            for (int i = 0; i < listArray.length(); i++) {
                JSONObject course = listArray.getJSONObject(i);

                String original_name = course.getString("original_name");
                String genre_ids = course.getString("genre_ids");
                String name = course.getString("name");
                String popularity = course.getString("popularity");
                String origin_country = course.getString("origin_country");
                String vote_count = course.getString("vote_count");
                String first_air_date = course.getString("first_air_date");
                String backdrop_path = course.getString("backdrop_path");
                String original_language = course.getString("original_language");
                String id = course.getString("id");
                String vote_average = course.getString("vote_average");
                String overview = course.getString("overview");
                String poster_path = course.getString("poster_path");

                TvModel tvModel = new TvModel(original_name, genre_ids, name, popularity, origin_country, vote_count, first_air_date, backdrop_path, original_language,id, vote_average,overview,poster_path);
                list.add(tvModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<TvModel> loadTvShowById(String courseId) {
        String fileName = String.format("tv.json", courseId);
        ArrayList<TvModel> list = new ArrayList<>();
        try {
            String result = parsingFileToString(fileName);
            if (result != null) {
                JSONObject responseObject = new JSONObject(result);
                JSONArray listArray = responseObject.getJSONArray("results");
                for (int i = 0; i < listArray.length(); i++) {
                    JSONObject course = listArray.getJSONObject(i);

                    String original_name = course.getString("original_name");
                    String genre_ids = course.getString("genre_ids");
                    String name = course.getString("name");
                    String popularity = course.getString("popularity");
                    String origin_country = course.getString("origin_country");
                    String vote_count = course.getString("vote_count");
                    String first_air_date = course.getString("first_air_date");
                    String backdrop_path = course.getString("backdrop_path");
                    String original_language = course.getString("original_language");
                    String id = course.getString("id");
                    String vote_average = course.getString("vote_average");
                    String overview = course.getString("overview");
                    String poster_path = course.getString("poster_path");

                    TvModel tvModel = new TvModel(original_name, genre_ids, name, popularity, origin_country, vote_count, first_air_date, backdrop_path, original_language,id, vote_average,overview,poster_path);
                    list.add(tvModel);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
