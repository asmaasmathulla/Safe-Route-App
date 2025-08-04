package com.s23010605.saferoute;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    Context context;
    ArrayList<ContactModel> contactList;
    OnItemClickListener listener;

    // Constructor
    public ContactAdapter(Context context, ArrayList<ContactModel> contactList, OnItemClickListener listener) {
        this.context = context;
        this.contactList = contactList;
        this.listener = listener;
    }

    // Interface for Edit & Call button clicks
    public interface OnItemClickListener {
        void onEditClick(ContactModel contact);
        void onDeleteClick(ContactModel contact);
    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        ContactModel contact = contactList.get(position);
        holder.tvName.setText(contact.getName());
        holder.tvNumber.setText(contact.getMobileNo());

        // Set avatar first letter
        if (!contact.getName().isEmpty()) {
            holder.tvAvatar.setText(contact.getName().substring(0, 1).toUpperCase());
        }

        holder.btnEdit.setOnClickListener(v -> listener.onEditClick(contact));
        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(contact));
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvNumber, tvAvatar;
        ImageButton btnEdit, btnDelete;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvAvatar = itemView.findViewById(R.id.tvAvatar);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}













//package com.s23010605.saferoute;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
//
//    Context context;
//    ArrayList<ContactModel> contactList;
//
//    public ContactAdapter(Context context, ArrayList<ContactModel> contactList) {
//        this.context = context;
//        this.contactList = contactList;
//    }
//
//    public class ContactViewHolder extends RecyclerView.ViewHolder {
//        TextView tvName, tvNumber, tvAvatar;
//        ImageButton btnEdit, btnDelete;
//
//        public ContactViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            tvName = itemView.findViewById(R.id.tvName);
//            tvNumber = itemView.findViewById(R.id.tvNumber);
//            tvAvatar = itemView.findViewById(R.id.tvAvatar);
//            btnEdit = itemView.findViewById(R.id.btnEdit);
//            btnDelete = itemView.findViewById(R.id.btnDelete);
//        }
//    }
//
//    @NonNull
//    @Override
//    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
//        return new ContactViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
//        ContactModel contact = contactList.get(position);
//
//        holder.tvName.setText(contact.getName());
//        holder.tvNumber.setText(contact.getMobile());
//        holder.tvAvatar.setText(contact.getName().substring(0, 1).toUpperCase());
//
//        // On Edit Click
//        holder.btnEdit.setOnClickListener(v -> {
//            Toast.makeText(context, "Edit " + contact.getName(), Toast.LENGTH_SHORT).show();
//            // TODO: Open ManageContactActivity with pre-filled data
//        });
//
//        // On Delete Click
//        holder.btnDelete.setOnClickListener(v -> {
//            Toast.makeText(context, "Delete " + contact.getName(), Toast.LENGTH_SHORT).show();
//            // TODO: Delete from database and refresh list
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return contactList.size();
//    }
//}
