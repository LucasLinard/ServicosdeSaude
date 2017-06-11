package tech.linard.android.serviosdesade.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class SaudeProvider extends ContentProvider {

    // The URI Matcher used by this content provider.
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    static final int ESTABELECIMENTO = 100;
    static final int ESTABELECIMENTO_ID = 101;

    private SaudeDbHelper mOpenHelper;


    public SaudeProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = SaudeContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, SaudeContract.PATH_ESTABELECIMENTO, ESTABELECIMENTO);
        matcher.addURI(authority, SaudeContract.PATH_ESTABELECIMENTO+ "/*", ESTABELECIMENTO_ID);

        return matcher;
    }

    @Override
    public String getType(Uri uri) {

        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = sUriMatcher.match(uri);
        switch(match) {
            case ESTABELECIMENTO:
                return SaudeContract.EstabelecimentoEntry.CONTENT_TYPE;
            case ESTABELECIMENTO_ID:
                return SaudeContract.EstabelecimentoEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case ESTABELECIMENTO:
                long _id = db.insertWithOnConflict(SaudeContract.EstabelecimentoEntry.TABLE_NAME,
                        null,
                        values,
                        SQLiteDatabase.CONFLICT_REPLACE);
                if ( _id > 0 )
                    returnUri = SaudeContract.EstabelecimentoEntry.buildEstabelecimentoUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }


    @Override
    public boolean onCreate() {
        mOpenHelper = new SaudeDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case ESTABELECIMENTO:
                retCursor = mOpenHelper
                        .getReadableDatabase()
                        .query(SaudeContract.EstabelecimentoEntry.TABLE_NAME,
                                projection,
                                selection,
                                selectionArgs,
                                null,
                                null,
                                sortOrder);
                break;
            case ESTABELECIMENTO_ID:
                retCursor = mOpenHelper
                        .getReadableDatabase()
                        .query(SaudeContract.EstabelecimentoEntry.TABLE_NAME,
                                projection,
                                selection,
                                selectionArgs,
                                null,
                                null,
                                sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;

        switch(match) {
            case ESTABELECIMENTO:
                rowsUpdated = db.update(SaudeContract.EstabelecimentoEntry.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

}
