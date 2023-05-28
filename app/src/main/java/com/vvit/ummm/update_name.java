package com.vvit.ummm;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update_name extends AppCompatActivity{
    EditText nam;
    Button update_2;
    SQLhelper sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_name);
        sql=new SQLhelper(this);
        nam=findViewById(R.id._name_update_edit);
        update_2=findViewById(R.id._updtae_name_1);

        update_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String po=nam.getText().toString();
                boolean t=sql.updatename(po);
                if(t){
                    Toast.makeText(update_name.this, "updated", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}