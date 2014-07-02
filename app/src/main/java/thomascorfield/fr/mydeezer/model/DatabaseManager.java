package thomascorfield.fr.mydeezer.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper implements FavoritesRepository, RecentRepository {

    private static final String DATABASE_NAME = "mydb.sqlite";
    private static final int CURRENT_DB_VERSION = 1;

    private SQLiteDatabase db;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, CURRENT_DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        db = sqLiteDatabase;

        sqLiteDatabase.execSQL("create table History\n" +
                "(\n" +
                " request \"VARCHAR()\" not null\n" +
                ");");
        sqLiteDatabase.execSQL("create table Favorite\n" +
                "(\n" +
                " identifier \"VARCHAR()\" not null\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        switch (i) {

            case 1:
            case 2:
            case 3:
            default:
        }
    }

    @Override
    public void add(Music music) {

        if (!isFavorite(music)) {

            ContentValues values = new ContentValues();
            values.put("identifier", music.getIdentifier());
            this.getWritableDatabase().insert("Favorite", null, values);
        }
    }

    @Override
    public void remove(Music music) {

        this.getWritableDatabase().delete("Favorite", "identifier =" + music.getIdentifier(), null);

        /*** OR ***/

        /*String sqlStmt =
                "DELETE FROM Favorite WHERE identifier ) \""
                + music.getIdentifier()
                +"\";";
        this.getWritableDatabase().execSQL(sqlStmt);*/
    }

    @Override
    public boolean isFavorite(Music music) {
        String table = "Favorite";
        String column = "identifier";
        String predicate = music.getIdentifier();

        Cursor c;
        c = this.getReadableDatabase().rawQuery("SELECT " + column + " FROM " + table +
                " WHERE " + column + " = \"" + predicate + "\";", null);

        return c.getCount() != 0;
    }

    @Override
    public void add(String request) {

        String requestLowerCase = request.toLowerCase();

        if (!existInHistory(requestLowerCase)) {

            ContentValues values = new ContentValues();
            values.put("request", requestLowerCase);
            this.getWritableDatabase().insert("History", null, values);
        }
    }

    private boolean existInHistory(String requestLowerCase) {
        String table = "History";
        String[] columns = {"request"};
        String predicate = requestLowerCase;

        Cursor c;
        c = this.getReadableDatabase().query(false, table, columns,
                "request =" + predicate, null, null, null, null, null);

        return c.getCount() != 0;
    }

    @Override
    public ArrayList<String> getRequestMatchingPredicate(String predicate) {
        return null;
    }
}
