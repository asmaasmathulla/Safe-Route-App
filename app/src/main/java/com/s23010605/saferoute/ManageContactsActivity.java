package com.s23010605.saferoute;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ManageContactsActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText editName, editMobileNo, editEmail, editId;
    Button saveBtn, seeAllContactsBtn, editBtn, deleteBtn;

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

        myDB = new DatabaseHelper(this);


        editName = findViewById(R.id.nameInput);
        editMobileNo = findViewById(R.id.mobileNoInput);
        editEmail = findViewById(R.id.emailInput);
        editId = findViewById(R.id.idInput);
        saveBtn = findViewById(R.id.saveBtn);
        seeAllContactsBtn = findViewById(R.id.seeAllContactsBtn);
        editBtn = findViewById(R.id.editBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        addData();
        viewAll();
        updateData();
        deleteData();
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
            }
        });
    }

    public void viewAll(){
        seeAllContactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor results = myDB.getAllData();
                if(results.getCount()==0){
                    showMessage("Error Message :", "No data available in the database");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(results.moveToNext()){
                    buffer.append("ID :" + results.getString(0)+ "\n");
                    buffer.append("NAME :" + results.getString(1)+ "\n");
                    buffer.append("MOBILE_NO :" + results.getString(2)+ "\n");
                    buffer.append("EMAIL :" + results.getString(3)+ "\n\n");

                    showMessage("List of Data", buffer.toString());
                }

            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(ManageContactsActivity.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void updateData(){
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate = myDB.updateData(editId.getText().toString(), editName.getText().toString(), editMobileNo.getText().toString(), editEmail.getText().toString());
                if(isUpdate == true)
                    Toast.makeText(ManageContactsActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ManageContactsActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void deleteData(){
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedatarows = myDB.deleteData(editId.getText().toString());
                if(deletedatarows>0)
                    Toast.makeText(ManageContactsActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ManageContactsActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
            }
        });
    }

}