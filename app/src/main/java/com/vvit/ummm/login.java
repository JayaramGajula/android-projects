package com.vvit.ummm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    EditText edittextemaillogin,edittextpasswordlogin;
    TextView registertext;

    Button btnlogin;
    ProgressBar pblogin;

    FirebaseAuth mAuth;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent it=new Intent(getApplicationContext(),enter_speed.class);
            startActivity(it);
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth=FirebaseAuth.getInstance();
        pblogin=findViewById(R.id._progressbar_login);
        edittextemaillogin=findViewById(R.id._user_edit_text);
        edittextpasswordlogin=findViewById(R.id._pass_edit_text);
        btnlogin=findViewById(R.id._login_button);
        registertext=findViewById(R.id._register_now);




        registertext.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent it=new Intent(getApplicationContext(),register.class);
                startActivity(it);
                finish();
            }
        });



        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pblogin.setVisibility(View.VISIBLE);
                String email,password;
                email=edittextemaillogin.getText().toString();
                password=edittextpasswordlogin.getText().toString();


                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(login.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(login.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }


                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    pblogin.setVisibility(View.GONE);

                                    Toast.makeText(login.this, "login successful.",
                                            Toast.LENGTH_SHORT).show();

                                    Intent it=new Intent(getApplicationContext(),enter_speed.class);
                                    startActivity(it);
                                    finish();
                                }

                                else
                                {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });




            }
        });








    }
}