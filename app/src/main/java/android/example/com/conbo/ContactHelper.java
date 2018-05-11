package android.example.com.conbo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.io.Serializable;

/**
 * Created by Sallamaarit Jaako 1601459 on 9.5.2018.
 */

public class ContactHelper extends SQLiteOpenHelper {
    private static final String TAG = ContactHelper.class.getSimpleName();
    // Instance of ContactHelper to be shared with other activities
    private static ContactHelper sInstance;


    // Initialize required database configuration
    private static final int DATABASE_VERSION = 1;
    private static final String CONBO_TABLE = "contact_entries";
    private static final String DATABASE_NAME = "conbo";

    // Initialize the column names
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_PHOTO = "photo";


    private static final String[] COLUMNS =
            {KEY_ID, KEY_NAME, KEY_PHONE, KEY_PHOTO};

    // Create table clause
    private static final String CONBO_TABLE_CREATE =
            "CREATE TABLE " + CONBO_TABLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
                    KEY_NAME + " TEXT, " +
                    KEY_PHONE + " TEXT, " +
                    KEY_PHOTO + " INTEGER);";

    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

    // Public method which shares the instance of ContactHelper with other activities
    public static synchronized ContactHelper getInstance(Context context) {
        // Check if instance is not yet created
        if (sInstance == null) {
            // If not created yet, call the constructor
            sInstance = new ContactHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    // Private constructor so a new database instance cannot be created from outside
    private ContactHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Construct ContactHelper");
    }

    // Initialize the table and test data
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CONBO_TABLE_CREATE);
        fillDatabaseWithData(db);
    }

    // Method that adds two contact items to database
    public void fillDatabaseWithData(SQLiteDatabase db) {
        // Initialize an array of contact models and add to new models into it
        ContactModel[] contacts = new ContactModel[2];
        contacts[0] = new ContactModel("Seppo", "0700123123", 0, ColorGenerator.MATERIAL.getRandomColor());
        contacts[1] = new ContactModel("Sallamaarit", "020126126", 1, ColorGenerator.MATERIAL.getRandomColor());
        // Initialize values to be added to database
        ContentValues values = new ContentValues();

        for (int i = 0; i < contacts.length; i++) {
            // Insert data into values and insert it into database
            values.put(KEY_ID, contacts[i].getmId());
            values.put(KEY_NAME, contacts[i].getmName());
            values.put(KEY_PHONE, contacts[i].getmPhone());
            values.put(KEY_PHOTO, contacts[i].getmPhoto());
            db.insert(CONBO_TABLE, null, values);
        }
    }


    // Clause for getting a single row of data
    public ContactModel query(int position) {
        String query = "SELECT  * FROM " + CONBO_TABLE +
                " ORDER BY " + KEY_NAME + " ASC " +
                "LIMIT " + position + ",1";
        // Initialize cursor and contact model
        Cursor cursor = null;
        ContactModel entry = new ContactModel();

        try {
            // Check if readable database is already initialized
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            // Execute the query and insert fetched data into the new contactModel
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry.setmId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry.setmName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            entry.setmPhone(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
            entry.setmPhoto(cursor.getInt(cursor.getColumnIndex(KEY_PHOTO)));
        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e.getMessage());
        } finally {
            // Close cursor and return the fetched data as a contactModel
            cursor.close();
            return entry;
        }
    }

    // Return the amount of rows in database
    public long count() {
        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        }

        Long result = DatabaseUtils.queryNumEntries(mReadableDB, CONBO_TABLE);
        return result.intValue();
    }

    // Clause for inserting data into database
    public long insert(String name, String phone) {
        long newId = 0;
        // Initialize values and enter the given data
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_PHONE, phone);
        values.put(KEY_PHOTO, ColorGenerator.MATERIAL.getRandomColor());
        try {
            // Check if writable database already initialized
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            // Save the values into database
            newId = mWritableDB.insert(CONBO_TABLE, null, values);
        } catch (Exception e) {
            Log.d(TAG, "INSERT EXCEPTION! " + e.getMessage());
        }
        // Return the Id of the new row
        return newId;
    }

    // Clause for updating a row in database
    public int update(int id, String name, String phone) {
        int mNumberOfRowsUpdated = -1;
        try {
            // Check if writable database already initialized
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            // Initialize values and enter the given data
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, name);
            values.put(KEY_PHONE, phone);
            // Save the updated row to the database
            mNumberOfRowsUpdated = mWritableDB.update(CONBO_TABLE,
                    values,
                    KEY_ID + " = ?",
                    new String[]{String.valueOf(id)});

        } catch (Exception e) {
            Log.d(TAG, "UPDATE EXCEPTION! " + e.getMessage());
        }
        // Return the number of rows updated
        return mNumberOfRowsUpdated;
    }

    // Clause for deleting a row from database
    public int delete(int id) {
        int deleted = 0;
        try {
            // Check if writable database already initialized
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            // Delete the given row from database
            deleted = mWritableDB.delete(CONBO_TABLE,
                    KEY_ID + " = ? ", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d(TAG, "DELETE EXCEPTION! " + e.getMessage());
        }
        // Return the number of rows deleted
        return deleted;
    }

    // Determines what happens if the database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ContactHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + CONBO_TABLE);
        onCreate(db);
    }
}
