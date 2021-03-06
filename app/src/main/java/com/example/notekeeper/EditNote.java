package com.example.notekeeper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditNote extends AppCompatActivity {

    private int PICK_IMAGE_REQUEST = 1;

    Toolbar toolbar;
    ConstraintLayout layout;
    ToggleButton btn0;
    ToggleButton btn1;
    ToggleButton btn2;
    ToggleButton btn3;
    ToggleButton btn4;
    Button mReminder;
    EditText mTitle;
    EditText mContent;
    TextView mDateTime;
    String title;
    String id;
    String content;
    NoteDatabase db;
    String currentDate;
    String currentTime;
    String color = "noteColor0";
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Intent intent = getIntent();

        // setup toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        layout = findViewById(R.id.add_note_layout);
        btn0 = findViewById(R.id.colorBtn0);
        btn1 = findViewById(R.id.colorBtn1);
        btn2 = findViewById(R.id.colorBtn2);
        btn3 = findViewById(R.id.colorBtn3);
        btn4 = findViewById(R.id.colorBtn4);
        mTitle = findViewById(R.id.title);
        mContent = findViewById(R.id.content);
        mDateTime = findViewById(R.id.dateTime);
        mReminder = findViewById(R.id.reminderBtn);
        image = findViewById(R.id.imageView);

        mTitle.setText(intent.getStringExtra("title"));
        mContent.setText(intent.getStringExtra("content"));
        mContent.setFocusable(true);
        id = intent.getStringExtra("id");
        byte[] imagebyte = intent.getByteArrayExtra("image");
        InputStream input=new ByteArrayInputStream(imagebyte);
        Bitmap ext_pic = BitmapFactory.decodeStream(input);
        image.setImageBitmap(ext_pic);


        // get current date and time
        currentDate = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault()).format(new Date());
        currentTime = new SimpleDateFormat("HH:mm a", Locale.getDefault()).format(new Date());
        Log.d("currentDate", currentDate);
        Log.d("currentTime", currentTime);
        mDateTime.setText(currentDate + ", " + currentTime);

        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    getSupportActionBar().setTitle(s);
                } else {
                    getSupportActionBar().setTitle("Add Note");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // set listener to color btn
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = "noteColor0";
                layout.setBackgroundColor(getResources().getColor(R.color.noteColor0));
                toolbar.setBackgroundColor(getResources().getColor(R.color.noteColor0));
                btn1.setChecked(false);
                btn2.setChecked(false);
                btn3.setChecked(false);
                btn4.setChecked(false);
                if (!btn0.isChecked()) {
                    layout.setBackgroundColor(getResources().getColor(R.color.noteColor0));
                    toolbar.setBackgroundColor(getResources().getColor(R.color.noteColor0));
                    color = "noteColor0";
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = "noteColor1";
                layout.setBackgroundColor(getResources().getColor(R.color.noteColor1));
                toolbar.setBackgroundColor(getResources().getColor(R.color.noteColor1));
                btn0.setChecked(false);
                btn2.setChecked(false);
                btn3.setChecked(false);
                btn4.setChecked(false);
                if (!btn1.isChecked()) {
                    layout.setBackgroundColor(getResources().getColor(R.color.noteColor0));
                    toolbar.setBackgroundColor(getResources().getColor(R.color.noteColor0));
                    color = "noteColor0";
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = "noteColor2";
                layout.setBackgroundColor(getResources().getColor(R.color.noteColor2));
                toolbar.setBackgroundColor(getResources().getColor(R.color.noteColor2));
                btn0.setChecked(false);
                btn1.setChecked(false);
                btn3.setChecked(false);
                btn4.setChecked(false);
                if (!btn2.isChecked()) {
                    layout.setBackgroundColor(getResources().getColor(R.color.noteColor0));
                    toolbar.setBackgroundColor(getResources().getColor(R.color.noteColor0));
                    color = "noteColor0";
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = "noteColor3";
                layout.setBackgroundColor(getResources().getColor(R.color.noteColor3));
                toolbar.setBackgroundColor(getResources().getColor(R.color.noteColor3));
                btn0.setChecked(false);
                btn1.setChecked(false);
                btn2.setChecked(false);
                btn4.setChecked(false);
                if (!btn3.isChecked()) {
                    layout.setBackgroundColor(getResources().getColor(R.color.noteColor0));
                    toolbar.setBackgroundColor(getResources().getColor(R.color.noteColor0));
                    color = "noteColor0";
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = "noteColor4";
                layout.setBackgroundColor(getResources().getColor(R.color.noteColor4));
                toolbar.setBackgroundColor(getResources().getColor(R.color.noteColor4));
                btn0.setChecked(false);
                btn1.setChecked(false);
                btn2.setChecked(false);
                btn3.setChecked(false);
                if (!btn4.isChecked()) {
                    layout.setBackgroundColor(getResources().getColor(R.color.noteColor0));
                    toolbar.setBackgroundColor(getResources().getColor(R.color.noteColor0));
                    color = "noteColor0";
                }
            }
        });

        mReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mReminder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.confirm_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(this);
                // Set the dialog title and message.
                myAlertBuilder.setTitle("Discard Changes");
                myAlertBuilder.setMessage("Are you sure to discard the changes?");
                // Add the dialog buttons.
                myAlertBuilder.setPositiveButton(getString(R.string.discard_btn), new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked Delete button.
                                Toast.makeText(getApplicationContext(), "Discard", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                myAlertBuilder.setNegativeButton(getString(R.string.cancel_btn), new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User cancelled the dialog.
                                Toast.makeText(getApplicationContext(), "Cancel",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                // Create and show the AlertDialog;
                myAlertBuilder.show();
                break;
            case R.id.save:
                title = mTitle.getText().toString();
                content = mContent.getText().toString();
                Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                if (title.length() == 0) {
                    Toast.makeText(this, "Title is empty", Toast.LENGTH_SHORT).show();
                }
                else if (content.length() == 0) {
                    Toast.makeText(this, "Content is empty", Toast.LENGTH_SHORT).show();
                } else {
                    db = new NoteDatabase(this);

                    boolean deleteNote = db.deleteNote(id);
                    if (!deleteNote){
                        Log.d("failed", "deleteNote failed");
                    }else{
                        boolean insertNote = db.insertNote(title, content, currentDate, currentTime, color, bitmap);
                        if (!insertNote) {
                            Log.d("failed", "insertNote failed");
                        }
                    }

                    finish();
                }
                break;

            case R.id.addimage:
                uploadimage();
                break;
        }
//        Log.d("item", Integer.toString(item.getItemId()));
        return super.onOptionsItemSelected(item);
    }

    private void uploadimage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
