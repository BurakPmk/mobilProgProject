package com.example.test03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity implements RecyclerViewInterface {


    private static final String TAG = "test";
    private ArrayList<Person> persons;
    public static ArrayList<String> parents;
    public static ArrayList<String> friends;
    public static ArrayList<String> job;

    public static final int REQUEST_READ_CONTACTS = 1;
    private RecyclerView recyclerView;
    private PersonRecyclerAdapter personRecyclerAdapter;
    private RadioButton rbFriends;
    private RadioButton rbParents;
    private RadioButton rbJob;
    private Button buttonSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_acvtivity);
        persons = new ArrayList<>();
        parents = new ArrayList<>();
        friends = new ArrayList<>();
        job = new ArrayList<>();
        rbFriends = (RadioButton) findViewById(R.id.rb_friends);
        rbParents = (RadioButton) findViewById(R.id.rb_parents);
        rbJob = (RadioButton) findViewById(R.id.rb_job);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        buttonSms = (Button) findViewById(R.id.bttn_sms);



        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED)
        {


            Cursor contact = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            while (contact.moveToNext())
            {
                String name = contact.getString(contact.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = contact.getString(contact.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String contactID = contact.getString(contact.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));

                Person object = new Person(name,number,ContactPhoto(contactID));
                persons.add(object);
            }

            contact.close();
            viewSettings();
        }
        else
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},REQUEST_READ_CONTACTS);

        buttonSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ContactActivity.this,SmsActivity.class);
                startActivity(i);
            }
        });


    }

    private void viewSettings()
    {

        personRecyclerAdapter = new PersonRecyclerAdapter(persons,this);
        recyclerView.setAdapter(personRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public Bitmap ContactPhoto (String contactID)
    {
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,Long.valueOf(contactID));
        Uri photoUri = Uri.withAppendedPath(contactUri,ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
        Cursor cursor = getContentResolver().query(photoUri,new String[]{ContactsContract.Contacts.Photo.PHOTO},null,null,null);
        if(cursor!=null && cursor.getCount()>0)
        {
            cursor.moveToNext();
            byte[] data = cursor.getBlob(0);
            if(data!=null)
                return BitmapFactory.decodeStream(new ByteArrayInputStream(data));
            else
                return null;
        }
        cursor.close();
        return null;
    }


    @Override
    public void onLongItemClick(int position) {
        if(rbFriends.isChecked())
        {

            friends.add(persons.get(position).getNumber());

            Log.d(TAG,friends.toString());



        } else if (rbJob.isChecked())
        {
            job.add(persons.get(position).getNumber());
            Log.d(TAG, String.valueOf(friends.size()));
        }else if (rbParents.isChecked())
        {
            parents.add(persons.get(position).getNumber());

        }
    }




}