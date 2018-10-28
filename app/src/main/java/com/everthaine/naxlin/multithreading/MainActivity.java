package com.everthaine.naxlin.multithreading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {


    private ExecutorService threadPool = Executors.newCachedThreadPool();
    private EditText display = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.textDisplayer);
    }

    /*
        This event handler method will be called when the user gets a phone call, switches to another
        app, or if (heaven forbid) you switch to another activity in your app.
     */
    @Override
    protected void onStop() {
        super.onStop();
        threadPool.shutdownNow();
    }

    public void doStuff(View view) {

        WeakReference displayReference = new WeakReference(display);
        threadPool.execute(()->{
            try {
                //some stuff that will take a long time
                //In real shipping code this, along with ALL data retrieval
                // and storage code, should be in the Model part of your app (MVC).
                URL googURL = new URL("https://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=077ea2e253d383ac38376dc064945dce");
                HttpURLConnection theConnection = (HttpURLConnection)googURL.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(theConnection.getInputStream()));
                String inputLine;
                StringBuilder resultBuilder = new StringBuilder();
                while ((inputLine = in.readLine()) != null){
                    //make sure we didn't get a phone call or some such thing
                    if (Thread.interrupted()){
                        return;
                    }
                    resultBuilder.append(inputLine);
                }
                in.close();

                String resp = String.valueOf(resultBuilder);

                Gson gson = new Gson();
                Weather weather = gson.fromJson(resp, Weather.class);
                weather.display();

                //done with long-time stuff

                EditText displayToUse = (EditText)displayReference.get();
                //make sure the display element is still around
                if (displayToUse != null){
                    //make sure we didn't get a phone call or some such thing
                    if (Thread.interrupted()){
                        return;
                    }
                    displayToUse.post(()->{
                        displayToUse.setText(resultBuilder.toString());
                    });
                }
            }
            catch(Exception e){
                //for debugging purposes
                e.printStackTrace();
            }
        });

    }
}