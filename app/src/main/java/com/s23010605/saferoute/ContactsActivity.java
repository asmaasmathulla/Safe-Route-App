package com.s23010605.saferoute;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    RecyclerView recyclerView;
    ContactAdapter contactAdapter;
    ArrayList<ContactModel> contactList;
    ImageButton plusIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacts);
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

        // Initialize views
        recyclerView = findViewById(R.id.recyclerViewContacts);
        plusIcon = findViewById(R.id.plusIcon);

        myDB = new DatabaseHelper(this);

        // Load contacts from the database
        loadContacts();

        // Navigate to ManageContactsActivity when plus is clicked
        plusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactsActivity.this, ManageContactsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadContacts() {
        contactList = new ArrayList<>();
        contactList = myDB.getAllContactModels(); // We will create this method in DatabaseHelper
        contactAdapter = new ContactAdapter(this, contactList, new ContactAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(ContactModel contact) {
                Intent intent = new Intent(ContactsActivity.this, ManageContactsActivity.class);
                intent.putExtra("isEdit", true);
                intent.putExtra("ID", contact.getId());
                intent.putExtra("NAME", contact.getName());
                intent.putExtra("NUMBER", contact.getMobileNo());
                intent.putExtra("EMAIL", contact.getEmail());
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(ContactModel contact) {
                int deleted = myDB.deleteData(contact.getId());
                if (deleted > 0) {
                    Toast.makeText(ContactsActivity.this, "Contact deleted", Toast.LENGTH_SHORT).show();
                    loadContacts(); // Reload the updated list
                } else {
                    Toast.makeText(ContactsActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contactAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadContacts(); // call the method that loads contacts into RecyclerView again
    }

}