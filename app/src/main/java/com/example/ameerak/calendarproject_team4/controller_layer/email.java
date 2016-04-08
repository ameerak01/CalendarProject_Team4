package com.example.ameerak.calendarproject_team4.controller_layer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.ameerak.calendarproject_team4.R;

/**
 * Created by mike on 12/5/2015.
 */
public class email extends AppCompatActivity implements  View.OnClickListener{

    String USER_NAME_KEY;
    Button bEmail, bSms,bnotification;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        //String username = getIntent().getStringExtra("Username");

        bEmail = (Button)findViewById(R.id.bEmail);
        //bEmail.setTextColor(Color.rgb(210, 210, 50));
        //bEmail.setBackgroundColor(Color.BLACK);
        bEmail.setOnClickListener(this);

        //bSms.setTextColor(Color.rgb(210, 210, 50));
        //bSms.setBackgroundColor(Color.BLACK);
        bSms.setOnClickListener(this);

        bnotification = (Button)findViewById(R.id.notification_button);
        //bSms.setTextColor(Color.rgb(210, 210, 50));
        //bSms.setBackgroundColor(Color.BLACK);
        bnotification.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.bEmail) {
            Intent intent = null, chooser =null;
            if(v.getId() == R.id.bEmail){
                intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mail to:)"));
                //input email address
                //two sample email address in array

                String[] to = {"gorkhali.jiten@gmail.com","daastanp@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.putExtra("Username", USER_NAME_KEY);
                intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                intent.putExtra(Intent.EXTRA_TEXT, "Message");

                intent.setType("message/rfc822");
                chooser = Intent.createChooser(intent,"bEmail");
                startActivity(chooser);
            }
        }

        else if (v.getId() == R.id.notification_button)
        {
            //startActivity(new Intent(this, Medication.class));

        //    Intent intent = new Intent (Smsemail.this, AlarmMainActivity.class);
         //   intent.putExtra("Username", username);
         //   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
           // startActivity(intent);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onResume(){
        //Get 1st name
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            USER_NAME_KEY = extras.getString("Username");
        }
        super.onResume();
    }

    @Override
    protected void onPause(){
        Bundle bundle = new Bundle();
        bundle.putString("Username", this.USER_NAME_KEY);
        onSaveInstanceState(bundle);
        super.onPause();
    }



}
