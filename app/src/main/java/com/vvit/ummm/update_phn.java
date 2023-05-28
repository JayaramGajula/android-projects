package com.vvit.ummm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update_phn extends AppCompatActivity {
EditText phone_no;
Button update_1;
SQLhelper sq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sq=new SQLhelper(this);
        setContentView(R.layout.activity_update_phn);
        phone_no=findViewById(R.id._phn_update_edit);
        update_1=findViewById(R.id._updtae_phn_1);
        update_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String po=phone_no.getText().toString();
                boolean t=sq.updatephone(po);
                if(t){
                    Toast.makeText(update_phn.this, "updated", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


}