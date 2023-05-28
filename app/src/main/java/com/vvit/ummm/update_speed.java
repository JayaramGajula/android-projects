package com.vvit.ummm;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class update_speed extends AppCompatActivity {
    EditText speedlimit;
    Button update_2;
    SQLhelper sqo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_speed);
        speedlimit=findViewById(R.id._speed_update_edit);
        update_2=findViewById(R.id._updtae_speed_1);
        update_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String po=speedlimit.getText().toString();
                boolean t=sqo.updatephone(po);
                if(t)
                {
                    Toast.makeText(update_speed.this, "updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}