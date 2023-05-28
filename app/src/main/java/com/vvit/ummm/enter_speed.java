package com.vvit.ummm;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
public class enter_speed extends AppCompatActivity {

    EditText user,phn,speed_3;
    Button insert_1;
    SQLhelper DB;

    Boolean c=false;

    @Override
    protected void onStart() {
        super.onStart();

        // Get the shared preferences object
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);

        // Get the boolean flag that indicates whether the app has been launched before or not
        boolean launchedBefore = preferences.getBoolean("launched_before", false);

        // If the app has been launched before, set c to true to skip the activity
        if(launchedBefore) {
            c = true;
        }
        // Otherwise, mark the app as launched and display the activity
        else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("launched_before", true);
            editor.apply();
        }

        // If c is true, skip this activity and start the main activity
        if(c==true) {
            Intent it=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(it);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_speed);
        user=findViewById(R.id._use_name_insert);
        phn=findViewById(R.id._phone_insert);
        speed_3=findViewById(R.id._speed_insert);
        insert_1=findViewById(R.id._insert);
        DB=new SQLhelper(this);

        insert_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=user.getText().toString();
                String phone=phn.getText().toString();
                String ss=speed_3.getText().toString();

                c=DB.insertdata(name,phone,ss);

                if(c==true) {
                    Toast.makeText(enter_speed.this, "data inserted", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(enter_speed.this, "data not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
