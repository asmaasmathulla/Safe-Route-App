package com.s23010605.saferoute;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ManageContactsActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText editName, editMobileNo, editEmail;
    Button saveBtn;
    boolean isEditMode = false;
    String contactId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_contacts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        myDB = new DatabaseHelper(this);

        editName = findViewById(R.id.nameInput);
        editMobileNo = findViewById(R.id.mobileNoInput);
        editEmail = findViewById(R.id.emailInput);
        saveBtn = findViewById(R.id.saveBtn);

        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEdit", false);

        if (isEditMode) {
            contactId = intent.getStringExtra("ID");
            String name = intent.getStringExtra("NAME");
            String number = intent.getStringExtra("NUMBER");
            String email = intent.getStringExtra("EMAIL");

            editName.setText(name);
            editMobileNo.setText(number);
            editEmail.setText(email);

            saveBtn.setText("Update");
            updateData();
        }
        else {
            addData();
        }

    }

    public void addData(){
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDB.insertData(editName.getText().toString(), editMobileNo.getText().toString(), editEmail.getText().toString());
                if(isInserted == true){
                    Toast.makeText(ManageContactsActivity.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ManageContactsActivity.this, "Data Not Inserted Successfully", Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });
    }

    public void updateData(){
        saveBtn.setOnClickListener(view -> {

            boolean isUpdated = myDB.updateData(contactId,
                    editName.getText().toString(),
                    editMobileNo.getText().toString(),
                    editEmail.getText().toString());

            if (isUpdated) {
                Toast.makeText(ManageContactsActivity.this, "Contact Updated", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(ManageContactsActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
