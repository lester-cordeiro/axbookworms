package com.astrex.bookworms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements
        TextToSpeech.OnInitListener {

    private TextToSpeech tts= null;
    private static MainActivity sApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateUsersList();
        tts = new TextToSpeech(this, this);
        Log.i("hellovoice", "it came here");
        sApplication = this;
    }

    public static MainActivity getApplicationX() {
        return sApplication;
    }

    private void populateUsersList() {
        // Construct the data source
        ArrayList<Alphabet> arrayOfUsers = Alphabet.getUsers();
        // Create the adapter to convert the array to views
        CustomUserAdapter adapter = new CustomUserAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lvAlphabets);
        listView.setAdapter(adapter);
    }

    public void speakex(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public Set<Voice> getVoices () {
        return tts.getVoices();
    }

    public void setVoice (String value, Locale loc) {
        Voice v = new Voice(value, loc, 400, 200, true, null);
        tts.setVoice(v);
    }

    @Override
    public void onInit(int status) {
        Log.i("hellovoiceinit", "it came here");
        if (status == TextToSpeech.SUCCESS) {

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String imgSett = prefs.getString("pronounce", "");

            Locale loc = Locale.UK;
            if(imgSett.equals("US")) {
                loc = Locale.US;
            }

            int result = tts.setLanguage(loc);

            String voice = prefs.getString("voice", "");
            setVoice(voice,loc);

            int rate = prefs.getInt("rate", 80);
            Log.i("whacko", "hello" + rate);
            tts.setSpeechRate((float) rate / 100.0f);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                //Set<String> a=new HashSet<>();
                //a.add("male");//here you can give male if you want to select male voice.
                //Voice v=new Voice("en-us-x-sfg#female_2-local",new Locale("en","US"),400,200,true,a);
                //Voice v=new Voice("en-us-x-sfg-network",new Locale("en","US"),400,200,true,a);
                //tts.setVoice(v);
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    public void didTapButton(View view) {
        Button button = (Button)findViewById(R.id.button);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        button.startAnimation(myAnim);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.menu, menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }


}