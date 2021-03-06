package com.uproject.am.popularmoviesapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.uproject.am.popularmoviesapp.entities.Movie;

public final class DbUtils {

    public static ContentValues toContentValue(Movie movie) {
        ContentValues movieValues = new ContentValues();
        movieValues.put(MovieContracts.MOVIES_TABLE._ID, movie.getId());
        movieValues.put(MovieContracts.MOVIES_TABLE.COLUMN_TITLE, movie.getOriginalTitle());
        movieValues.put(MovieContracts.MOVIES_TABLE.COLUMN_OVERVIEW, movie.getOverview());
        movieValues.put(MovieContracts.MOVIES_TABLE.COLUMN_POSTER_IMAGE, movie.getPosterPath());
        movieValues.put(MovieContracts.MOVIES_TABLE.COLUMN_RELEASE_DATE, movie.getMovieReleaseDate());
        movieValues.put(MovieContracts.MOVIES_TABLE.COLUMN_VOTE_AVERAGE, movie.getVoteAverage());
        return movieValues;
    }

    public static boolean isFavorite(Context context, String id) {
        Cursor cursor = context.getContentResolver().query(
                MovieContracts.MOVIES_TABLE.CONTENT_URI,
                null,   // projection
                MovieContracts.MOVIES_TABLE._ID + " = ?", // selection
                new String[]{id},   // selectionArgs
                null    // sort order
        );
        if (cursor != null) {
            int numRows = cursor.getCount();
            cursor.close();
            return (numRows > 0);
        }
        return false;
    }

}