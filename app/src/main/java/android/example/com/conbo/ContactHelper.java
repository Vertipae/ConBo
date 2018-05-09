package android.example.com.conbo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Salinaattori on 9.5.2018.
 */

public class ContactHelper extends SQLiteOpenHelper {
    private static final String TAG = ContactHelper.class.getSimpleName();

    // Declaring all these as constants makes code a lot more readable, and looking like SQL.

    // Versions has to be 1 first time or app will crash.
    private static final int DATABASE_VERSION = 1;
    private static final String CONBO_TABLE = "contact_entries";
    private static final String DATABASE_NAME = "conbo";

    // Column names...
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_PHOTO = "photo";

    // ... and a string array of columns.
    private static final String[] COLUMNS =
            {KEY_ID, KEY_NAME, KEY_PHONE, KEY_PHOTO};

    // Build the SQL query that creates the table.
    private static final String CONBO_TABLE_CREATE =
            "CREATE TABLE " + CONBO_TABLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY, " + // will auto-increment if no value passed
                    KEY_NAME + " TEXT, " +
                    KEY_PHONE + " TEXT, " +
                    KEY_PHOTO + " INTEGER);";

    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

    public ContactHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Construct ContactHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CONBO_TABLE_CREATE);
        fillDatabaseWithData(db);
        // We cannot initialize mWritableDB and mReadableDB here, because this creates an infinite
        // loop of on Create being repeatedly called.
    }

    public void fillDatabaseWithData(SQLiteDatabase db) {

        ContactModel[] contacts = new ContactModel[2];
        contacts[0] = new ContactModel("Kalevi", "0700123123", 3);
        contacts[1] =  new ContactModel("Sari", "020126126", 5);
        ContentValues values = new ContentValues();

        for (int i=0; i < contacts.length;i++) {
            // Put column/value pairs into the container. put() overwrites existing values.
            values.put(KEY_NAME, contacts[i].getmName());
            values.put(KEY_PHONE, contacts[i].getmPhone());
            db.insert(CONBO_TABLE, null, values);
        }
    }

    /**
     * Queries the database for an entry at a given position.
     *
     * @param position The Nth row in the table.
     * @return a WordItem with the requested database entry.
     */
    public ContactModel query(int position) {
        String query = "SELECT  * FROM " + CONBO_TABLE +
                " ORDER BY " + KEY_NAME + " ASC " +
                "LIMIT " + position + ",1";

        Cursor cursor = null;
        ContactModel entry = new ContactModel();

        try {
            if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry.setmName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            entry.setmPhone(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e.getMessage());
        } finally {
            // Must close cursor and db now that we are done with it.
            cursor.close();
            return entry;
        }
    }

    /**
     * Gets the number of rows in the word list table.
     *
     * @return The number of entries in CONBO_TABLE.
     */
    public long count() {
        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        }

        Long result = DatabaseUtils.queryNumEntries(mReadableDB, CONBO_TABLE);
        return result.intValue();
    }

    /**
     * Adds a single word row/entry to the database.
     *
     * @param  name New word.
     * @return The id of the inserted word.
     */
    public long insert(String name, String phone, int photo) {
        long newId = 0;
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_PHONE, phone);
        values.put(KEY_PHOTO, photo);
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            newId = mWritableDB.insert(CONBO_TABLE, null, values);
        } catch (Exception e) {
            Log.d(TAG, "INSERT EXCEPTION! " + e.getMessage());
        }
        return newId;
    }

    /**
     * Updates the word with the supplied id to the supplied value.
     *
     * @param id Id of the word to update.
     * @param name The new value of the name.
     * @return The number of rows affected or -1 of nothing was updated.
     */
    public int update(int id, String name, String phone, int photo) {
        int mNumberOfRowsUpdated = -1;
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, name);
            values.put(KEY_PHONE, phone);
            values.put(KEY_PHOTO, photo);

            mNumberOfRowsUpdated = mWritableDB.update(CONBO_TABLE, //table to change
                    values, // new values to insert
                    KEY_ID + " = ?", // selection criteria for row (in this case, the _id column)
                    new String[]{String.valueOf(id)}); //selection args; the actual value of the id

        } catch (Exception e) {
            Log.d (TAG, "UPDATE EXCEPTION! " + e.getMessage());
        }
        return mNumberOfRowsUpdated;
    }

    /**
     * Deletes one entry identified by its id.
     *
     * @param id ID of the entry to delete.
     * @return The number of rows deleted. Since we are deleting by id, this should be 0 or 1.
     */
    public int delete(int id) {
        int deleted = 0;
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            deleted = mWritableDB.delete(CONBO_TABLE, //table name
                    KEY_ID + " = ? ", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d (TAG, "DELETE EXCEPTION! " + e.getMessage());        }
        return deleted;
    }

    /**
     * Called when a database needs to be upgraded. The most basic version of this method drops
     * the tables, and then recreates them. All data is lost, which is why for a production app,
     * you want to back up your data first. If this method fails, changes are rolled back.
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ContactHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + CONBO_TABLE);
        onCreate(db);
    }
}
