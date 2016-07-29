package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by warmfire_2 on 2016/7/29.
 */
public class ContentProviderActivity extends Activity {

    EditText cp_txt;
    Button cp_insert, cp_delete, cp_update, cp_back;
    ListView cp_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_providers);

        init();

        cp_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri resUri = insertInfo();
                showList();
                Toast.makeText(ContentProviderActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
            }
        });
        cp_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(deleteInfo() > 0){
                    showList();
                    Toast.makeText(ContentProviderActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cp_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(updateInfo() > 0){
                    showList();
                    Toast.makeText(ContentProviderActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cp_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void init(){
        cp_txt = (EditText) findViewById(R.id.cp_txt);
        cp_insert = (Button) findViewById(R.id.cp_insert);
        cp_delete = (Button) findViewById(R.id.cp_delete);
        cp_update = (Button) findViewById(R.id.cp_update);
        cp_back = (Button) findViewById(R.id.cp_back);
        cp_list = (ListView) findViewById(R.id.cp_list);
    }

    public void showList(){

        String[] mProjection =
                {
                        UserDictionary.Words.WORD,   // Contract class constant containing the word column name
                        UserDictionary.Words.APP_ID,
                        UserDictionary.Words.FREQUENCY,
                        UserDictionary.Words.LOCALE,  // Contract class constant containing the locale column name
                        UserDictionary.Words._ID
                };

        // Does a query against the table and returns a Cursor object
        Cursor mCursor = getContentResolver().query(
                UserDictionary.Words.CONTENT_URI,  // The content URI of the words table
                mProjection,                       // The columns to return for each row
                null,                  // Either null, or the word the user entered
                null,                    // Either empty, or the string the user entered
                null);

        // Defines a list of columns to retrieve from the Cursor and load into an output row
        String[] mWordListColumns =
                {
                        UserDictionary.Words.WORD,   // Contract class constant containing the word column name
                        UserDictionary.Words.APP_ID,
                        UserDictionary.Words.FREQUENCY,
                        UserDictionary.Words.LOCALE,  // Contract class constant containing the locale column name
                        UserDictionary.Words._ID
                };

        // Defines a list of View IDs that will receive the Cursor columns for each row
        int[] mWordListItems = { R.id.cp_list_word, R.id.cp_list_appid, R.id.cp_list_rate, R.id.cp_list_area, R.id.cp_list_id};

        // Creates a new SimpleCursorAdapter
        SimpleCursorAdapter mCursorAdapter = new SimpleCursorAdapter(
                getApplicationContext(),               // The application's Context object
                R.layout.cp_list_item,                  // A layout in XML for one row in the ListView
                mCursor,                               // The result from the query
                mWordListColumns,                      // A string array of column names in the cursor
                mWordListItems,                        // An integer array of view IDs in the row layout
                0);                                    // Flags (usually none are needed)

        // Sets the adapter for the ListView
        cp_list.setAdapter(mCursorAdapter);
    }

    public Uri insertInfo(){
        // Defines a new Uri object that receives the result of the insertion
        Uri mNewUri;

        // Defines an object to contain the new values to insert
        ContentValues mNewValues = new ContentValues();

                /*
                 * Sets the values of each column and inserts the word. The arguments to the "put"
                 * method are "column name" and "value"
                 */
        mNewValues.put(UserDictionary.Words.APP_ID, "example.user");
        mNewValues.put(UserDictionary.Words.LOCALE, "en_US");
        mNewValues.put(UserDictionary.Words.WORD, cp_txt.getText().toString());
        mNewValues.put(UserDictionary.Words.FREQUENCY, "100");

        mNewUri = getContentResolver().insert(
                UserDictionary.Words.CONTENT_URI,   // the user dictionary content URI
                mNewValues                         // the values to insert
        );
        return mNewUri;
    }

    public int deleteInfo(){
        // Defines selection criteria for the rows you want to delete
        String mSelectionClause = UserDictionary.Words.APP_ID + " LIKE ?";
        String[] mSelectionArgs = {"0"};

        // Defines a variable to contain the number of rows deleted
        int mRowsDeleted = 0;

        // Deletes the words that match the selection criteria
        mRowsDeleted = getContentResolver().delete(
                UserDictionary.Words.CONTENT_URI,   // the user dictionary content URI
                mSelectionClause,                    // the column to select on
                mSelectionArgs                      // the value to compare to
        );
        return mRowsDeleted;
    }

    public int updateInfo(){
        // Defines an object to contain the updated values
        ContentValues mUpdateValues = new ContentValues();

        // Defines selection criteria for the rows you want to update
        String mSelectionClause = UserDictionary.Words.LOCALE +  " LIKE ?";
        String[] mSelectionArgs = {"en_US"};

        // Defines a variable to contain the number of updated rows
        int mRowsUpdated = 0;

                /*
                 * Sets the updated value and updates the selected words.
                 */
        mUpdateValues.putNull(UserDictionary.Words.LOCALE);

        mRowsUpdated = getContentResolver().update(
                UserDictionary.Words.CONTENT_URI,   // the user dictionary content URI
                mUpdateValues,                       // the columns to update
                mSelectionClause,                    // the column to select on
                mSelectionArgs                      // the value to compare to
        );
        return mRowsUpdated;
    }

}
