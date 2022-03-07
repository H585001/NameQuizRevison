package com.example.namequizapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.namequizapp.database.Person;
import com.example.namequizapp.database.PersonDatabase;

public class AddPersonActivity extends AppCompatActivity {

    ImageView imageView;
    Button addPerson;
    EditText editText;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Uri imageURI;

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        mGetContent.launch(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        imageView = findViewById(R.id.addImage);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        editText = findViewById(R.id.edittext);
        radioGroup = findViewById(R.id.sex);

        addPerson = findViewById(R.id.addPersonButton);
        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                String name = editText.getText().toString();
                if (radioId > 0 && imageView.getDrawable() != null && name != null) {
                    Person p = new Person();
                    p.setName(name);
                    p.setSex(findViewById(radioId).toString());
                    p.setPicture(imageURI.toString());
                    PersonDatabase db = PersonDatabase.getDbInstance(getApplicationContext());
                    db.personDao().insertPerson(p);
                    finish();
                }
            }
        });

    }

    ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() != Activity.RESULT_OK || result.getData() == null)
            return;
        Uri uri = result.getData().getData();
        getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        this.imageURI = uri;
        imageView.setImageURI(uri);
    });
}