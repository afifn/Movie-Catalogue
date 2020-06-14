package com.kuycoding.jetpack.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class TvModel implements Parcelable {
    private String original_name;
    private String genre_ids;
    private String name;
    private String popularity;
    private String origin_country;
    private String vote_count;
    private String first_air_date;
    private String backdrop_path;
    private String original_language;
    private String id;
    private String vote_average;
    private String overview;
    private String poster_path;

    public TvModel(String original_name, String genre_ids, String name, String popularity, String origin_country, String vote_count, String first_air_date, String backdrop_path, String original_language, String id, String vote_average, String overview, String poster_path) {
        this.original_name = original_name;
        this.genre_ids = genre_ids;
        this.name = name;
        this.popularity = popularity;
        this.origin_country = origin_country;
        this.vote_count = vote_count;
        this.first_air_date = first_air_date;
        this.backdrop_path = backdrop_path;
        this.original_language = original_language;
        this.id = id;
        this.vote_average = vote_average;
        this.overview = overview;
        this.poster_path = poster_path;
    }

    protected TvModel(Parcel in) {
        original_name = in.readString();
        genre_ids = in.readString();
        name = in.readString();
        popularity = in.readString();
        origin_country = in.readString();
        vote_count = in.readString();
        first_air_date = in.readString();
        backdrop_path = in.readString();
        original_language = in.readString();
        id = in.readString();
        vote_average = in.readString();
        overview = in.readString();
        poster_path = in.readString();
    }

    public static final Creator<TvModel> CREATOR = new Creator<TvModel>() {
        @Override
        public TvModel createFromParcel(Parcel in) {
            return new TvModel(in);
        }

        @Override
        public TvModel[] newArray(int size) {
            return new TvModel[size];
        }
    };

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(String genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(original_name);
        dest.writeString(genre_ids);
        dest.writeString(name);
        dest.writeString(popularity);
        dest.writeString(origin_country);
        dest.writeString(vote_count);
        dest.writeString(first_air_date);
        dest.writeString(backdrop_path);
        dest.writeString(original_language);
        dest.writeString(id);
        dest.writeString(vote_average);
        dest.writeString(overview);
        dest.writeString(poster_path);
    }
}
