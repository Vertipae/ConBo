package android.example.com.conbo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {
EditText mName;
EditText mPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mName = (EditText) findViewById(R.id.contact_name_single);
        mPhone = (EditText) findViewById(R.id.contact_phone_single);
        // Get extras from intent and set the values for textviews
        Bundle extras = getIntent().getExtras();
        mName.setText(extras.getString("name"));
        mPhone.setText(extras.getString("phone"));
        mName.setFocusable(false);
        mPhone.setFocusable(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                mName.setFocusableInTouchMode(true);
                mPhone.setFocusableInTouchMode(true);
                // Save missing
                Log.d("Hello", "edit");
                return true;
            case R.id.action_delete:
                Log.d("Hello", "delete");
                return true;
            default:
                return false;
        }
    }

}
