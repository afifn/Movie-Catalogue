package com.kuycoding.jetpack.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable {
    private String popularity;
    private String vote_count;
    private String poster_path;
    private String id;
    private String adult;
    private String backdrop_path;
    private String original_language;
    private String original_title;
    private String genre_ids;
    private String title;
    private String vote_average;
    private String overview;
    private String release_date;

    public MovieModel(String popularity, String vote_count, String poster_path, String id, String adult, String backdrop_path, String original_language, String original_title, String genre_ids, String title, String vote_average, String overview, String release_date) {
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.poster_path = poster_path;
        this.id = id;
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.original_language = original_language;
        this.original_title = original_title;
        this.genre_ids = genre_ids;
        this.title = title;
        this.vote_average = vote_average;
        this.overview = overview;
        this.release_date = release_date;
    }

    protected MovieModel(Parcel in) {
        popularity = in.readString();
        vote_count = in.readString();
        poster_path = in.readString();
        id = in.readString();
        adult = in.readString();
        backdrop_path = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        genre_ids = in.readString();
        title = in.readString();
        vote_average = in.readString();
        overview = in.readString();
        release_date = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(String genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(popularity);
        dest.writeString(vote_count);
        dest.writeString(poster_path);
        dest.writeString(id);
        dest.writeString(adult);
        dest.writeString(backdrop_path);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeString(genre_ids);
        dest.writeString(title);
        dest.writeString(vote_average);
        dest.writeString(overview);
        dest.writeString(release_date);
    }
}
