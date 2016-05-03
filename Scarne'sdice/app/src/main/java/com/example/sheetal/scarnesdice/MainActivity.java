package com.example.sheetal.scarnesdice;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonroll;
    ImageView roll;
    Button buttonhold;
    Button buttonreset;
    int usertotal,userturn,comptotal,compturn,comptotal1,usertotal1=0;
    String y="Your Score: ";
    Handler h2;
    String c="Computer Score: ";
    String ct="Computer Total Score: ";
    String yt="Your Total Score: ";
    TextView yourscore;
    Runnable r2;
    TextView computerscore;
    TextView computertotalscore;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        buttonroll = (Button) findViewById(R.id.buttonroll);
        buttonhold=(Button)findViewById(R.id.buttonhold);
        buttonreset=(Button)findViewById(R.id.buttonreset);
        yourscore=(TextView)findViewById(R.id.yourscore);
        computerscore=(TextView)findViewById(R.id.computerscore);
        computertotalscore=(TextView)findViewById(R.id.computertotalscore);
        buttonhold.setOnClickListener(this);
        buttonroll.setOnClickListener(this);
        buttonreset.setOnClickListener(this);
        roll=(ImageView)findViewById(R.id.roll);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

    public void buttonrollclickuser()
    {
        int k=1 + (int)(Math.random() * 6);
        if(k==1)
            roll.setImageResource(R.drawable.dice1);
        else if(k==2)
            roll.setImageResource(R.drawable.dice2);
        else if(k==3)
            roll.setImageResource(R.drawable.dice3);
        else if(k==4)
            roll.setImageResource(R.drawable.dice4);
        else if(k==5)
            roll.setImageResource(R.drawable.dice5);
        else
            roll.setImageResource(R.drawable.dice6);
        if(k!=1)
        {
            userturn=k;
            usertotal1+=k;
            userturn();
        }
        else
        {
            userturn=0;
            usertotal1=0;
            userturn();
            buttonholdclick();
        }
    }
    void computerturn()
    {
        yourscore.setText(y+usertotal);
        computerscore.setText(c+compturn);
        computertotalscore.setText(ct+comptotal);
    }
    void userturn()
    {
        yourscore.setText(ct+comptotal);
        computerscore.setText(y+userturn);
        computertotalscore.setText(yt+usertotal);
    }
    void rollclickcomp()
    {
        int k=1 + (int)(Math.random() * 6);
        compturn=k;
        comptotal1+=k;
        if(k==1)
            roll.setImageResource(R.drawable.dice1);
        else if(k==2)
            roll.setImageResource(R.drawable.dice2);
        else if(k==3)
            roll.setImageResource(R.drawable.dice3);
        else if(k==4)
            roll.setImageResource(R.drawable.dice4);
        else if(k==5)
            roll.setImageResource(R.drawable.dice5);
        else
            roll.setImageResource(R.drawable.dice6);
    }
    void buttonholdclick()
    {
        userturn=0;
        usertotal+=usertotal1;
        computertotalscore.setText(yt+usertotal);
        computerturn();
        buttonhold.setEnabled(false);
        buttonroll.setEnabled(false);
        comptotal1=0;
        final Handler h2 = new Handler();
        Runnable run = new Runnable() {

            @Override
            public void run() {
                if(comptotal1<20) {
                    rollclickcomp();
                    computerturn();
                    h2.postDelayed(this, 500);
                }
                else
                {
                    comptotal+=comptotal1;
                    comptotal1=0;
                    check();
                    h2.removeCallbacks(this);
                }
            }
        };
        h2.postDelayed(run,500);

        buttonroll.setEnabled(true);
            buttonhold.setEnabled(true);
            compturn = 0;
    }

    void buttonresetclick()
    {
        usertotal=0;
        userturn=0;
        comptotal=0;
        compturn=0;
        yourscore.setText(ct + 0);
        computerscore.setText(y + 0);
        computertotalscore.setText(yt+0);
    }

    void check()
    {
        if(usertotal>=100)
        {
            Toast.makeText(getApplicationContext(),"User Wins!",Toast.LENGTH_SHORT).show();
        }
        else if(comptotal>=100)
        {
            Toast.makeText(getApplicationContext(),"Computer Wins!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.buttonroll: buttonrollclickuser();
                check();
                break;
            case R.id.buttonhold: buttonholdclick();
                break;
            case R.id.buttonreset: buttonresetclick();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.sheetal.scarnesdice/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.sheetal.scarnesdice/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
