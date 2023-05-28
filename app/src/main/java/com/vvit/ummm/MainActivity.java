package com.vvit.ummm;

//import android.support.v7.app.AppCompatActivity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements LocationListener {

    FirebaseAuth auth;
    FirebaseUser user;
    TextView t1;

    String mobile,speed_l;









    /*Button btn_logout;
    TextView txt;





    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        txt=findViewById(R.id._user_details);
        btn_logout=findViewById(R.id._log_out_btn);
        user=auth.getCurrentUser();
        if(user==null)
        {
            Intent it=new Intent(getApplicationContext(),login.class);
            startActivity(it);
            finish();
        }
        else{
            txt.setText(user.getEmail());
        }
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent it=new Intent(getApplicationContext(),login.class);
                startActivity(it);
                finish();
            }
        });
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //t1=findViewById(R.id.txt_s);



        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.SEND_SMS, android.Manifest.permission.READ_SMS, Manifest.permission.CALL_PHONE}, 1000);
        } else {
            dostuff();
        }
    }

    public void dostuff() {
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (lm != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.examplemenu,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id._logout:
                auth=FirebaseAuth.getInstance();
                FirebaseAuth.getInstance().signOut();
                Intent it=new Intent(getApplicationContext(),login.class);
                startActivity(it);
                finish();
                break;
            case R.id._update_name:
                Intent i=new Intent(getApplicationContext(),update_name.class);
                startActivity(i);
                finish();
                break;
            case R.id._update_phn:
                Intent io=new Intent(getApplicationContext(),update_phn.class);
                startActivity(io);
                finish();
                break;
            case R.id._view:
                SQLhelper sqll=new SQLhelper(this);
                Cursor cus=sqll.getdata();

                    StringBuffer buffer=new StringBuffer();
                    while(cus.moveToNext())
                    {
                        buffer.append("name :"+cus.getString(0)+"\n");
                        buffer.append("phone no :"+cus.getString(1)+"\n");
                        buffer.append("speed limit :"+cus.getString(2)+"\n");

                    }

                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("User details");
                    builder.setMessage(buffer.toString());
                    builder.show();
                    break;
            case R.id._speed:
                Intent iu=new Intent(getApplicationContext(),update_speed.class);
                startActivity(iu);
                finish();
                break;



            default:
                return super.onOptionsItemSelected(item);



        }

        return super.onOptionsItemSelected(item);

    }


    @SuppressLint("Range")
    @Override
    public void onLocationChanged(@NonNull Location location) {


        SQLhelper sqol=new SQLhelper(this);
        Cursor cu= sqol.getphone();
        if(cu.moveToFirst())
        {
            mobile = cu.getString(cu.getColumnIndex("phone"));
           // t1.setText((mobile) + " km/hr");

        }
        SQLhelper sqoll=new SQLhelper(this);
        Cursor co=sqoll.getspeed();
        if(co.moveToFirst())
        {
            speed_l=co.getString(co.getColumnIndex("speed"));
           // t1.setText(speed_l);
        }


        int  count=0,n=0;
        TextView txt = (TextView) this.findViewById(R.id.txt_speed);

        if (location == null) {
            txt.setText("_._ km/hr");
           // t1.setText(speed_l);

        }
        else
        {
            float current_speed = location.getSpeed();
            txt.setText((current_speed * 3.6) + " km/hr");
            //int  count=0,n=0;

            double c = location.getSpeed();
            c = c * 3.6;

            if (c > Integer.valueOf(speed_l)) {

                if (count % 100 == 0) {
                    //String mobile = "8309736079";
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:" + mobile));
                    startActivity(i);
                    String sms = "you are driving more than 80";
                    //String mbno = "8309736079";
                    SmsManager mySsmManager = SmsManager.getDefault();
                    mySsmManager.sendTextMessage(mobile, null, sms, null, null);
                    count++;
                    //t1.setText("hello passed");

                }
            }
        }


    }
}