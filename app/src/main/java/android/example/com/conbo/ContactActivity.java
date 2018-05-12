package android.example.com.conbo;
// Sallamaarit Jaako 1601459 10.5.2018

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {
    EditText mName;
    EditText mPhone;
    EditText mEmail;
    EditText mAddress;
    int mId;
    Menu mMenu;
    ContactHelper mData;
    Context mContext;

    public void initializeContactHelper(ContactHelper contactHelper) {
        mData = contactHelper;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        mContext = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mData = ContactHelper.getInstance(this);
        mName = (EditText) findViewById(R.id.contact_name_single);
        mPhone = (EditText) findViewById(R.id.contact_phone_single);
        mEmail = (EditText) findViewById(R.id.contact_email_single);
        mAddress = (EditText) findViewById(R.id.contact_address_single);
        // Get extras from intent and set the values for textviews
        Bundle extras = getIntent().getExtras();
        mId = extras.getInt("id");
        mName.setText(extras.getString("name"));
        mPhone.setText(extras.getString("phone"));
        mEmail.setText(extras.getString("email"));
        mAddress.setText(extras.getString("address"));
        // Set edit text non editable
        mName.setFocusable(false);
        mPhone.setFocusable(false);
        mEmail.setFocusable(false);
        mAddress.setFocusable(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Initialize class variable for menu
        mMenu = menu;
        // Set the menu visible in view
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Set save item invisible
        menu.getItem(1).setVisible(false);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                // Set edit text editable
                mName.setFocusableInTouchMode(true);
                mPhone.setFocusableInTouchMode(true);
                mEmail.setFocusableInTouchMode(true);
                mAddress.setFocusableInTouchMode(true);
                // Set edit button invisible
                item.setVisible(false);
                // Set save button visible
                mMenu.getItem(1).setVisible(true);
                return true;
            case R.id.action_delete:
                // Alert dialog for deleting
                AlertDialog dialog = new AlertDialog.Builder(this).create();
                dialog.setTitle("Are you sure?");
                dialog.setMessage("This will permanently delete the contact");
                dialog.setCancelable(false);
                // Create button for positive answer and give it a new onclicklistener
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // delete the wanted contact from database
                        mData.delete(mId);
                        Toast.makeText(mContext, "Contact deleted successfully", Toast.LENGTH_SHORT).show();
                        // Go back to contacts view
                        Intent intent = new Intent(mContext, MainActivity.class);
                        mContext.startActivity(intent);
                    }
                });
                // Create button for negative anwser and give it a new onclicklistener
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int buttonId) {
                        // Cancel the dialog
                        dialog.cancel();
                    }
                });
                // Set icon for dialog and display it
                dialog.setIcon(android.R.drawable.ic_dialog_alert);
                dialog.show();
                return true;
            case R.id.action_save:
                // Initialize validator
                Validator validator = new Validator();
                // Initialize and enter values to list
                List<String> values = new ArrayList<>();
                values.add(mName.getText().toString());
                values.add(mPhone.getText().toString());
                values.add(mEmail.getText().toString());
                values.add(mAddress.getText().toString());
                // Validate values and return if empty fields
                if (!validator.validate(values)) {
                    Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                    return false;
                }
                // Update the given information to database
                mData.update(mId, mName.getText().toString(), mPhone.getText().toString(), mEmail.getText().toString(), mAddress.getText().toString());
                // Set edittext fields to be noneditable
                mName.setFocusable(false);
                mPhone.setFocusable(false);
                mEmail.setFocusable(false);
                mAddress.setFocusable(false);
                // Save button invisible
                item.setVisible(false);
                // Edit button visible
                mMenu.getItem(0).setVisible(true);
                Toast.makeText(this, "Contact updated successfully", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

}
