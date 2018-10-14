package com.example.roshnimd.trojanhackspeechtextv1;

import android.app.Activity;
import android.os.Bundle;

import android.speech.tts.TextToSpeech;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;


import android.os.Build;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity2 extends Activity {
    private TextToSpeech textToSpeech;
    private EditText editText_text;
    private Button button;
    private RadioButton radio_en;
    private RadioButton radio_fr;
    RadioGroup radioGroup;

    private boolean ready;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText_text = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        radio_en = (RadioButton) findViewById(R.id.radio_en);
        radio_fr = (RadioButton) findViewById(R.id.radio_fr);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);


        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                Log.e("TTS", "TextToSpeech.OnInitListener.onInit...");
                setTextToSpeechLanguage();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setTextToSpeechLanguage();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
            }
        });
    }


    @Override
    public void onPause() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }

    private void speakOut() {
        if (!ready) {
            Toast.makeText(this, "Text to Speech not ready", Toast.LENGTH_LONG).show();
            return;
        }
        // Text to Speak
        String toSpeak = editText_text.getText().toString();
        Toast.makeText(this, toSpeak, Toast.LENGTH_SHORT).show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
        }
        else {
            textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
        }

    }

    private Locale getUserSelectedLanguage() {
        int checkedRadioId = this.radioGroup.getCheckedRadioButtonId();
        if (checkedRadioId == R.id.radio_en) {
            return Locale.ENGLISH;
        } else if (checkedRadioId == R.id.radio_fr) {
            return Locale.FRANCE;
        }
        return null;
    }


    private void setTextToSpeechLanguage() {
        Locale language = this.getUserSelectedLanguage();
        if (language == null) {
            this.ready = false;
            Toast.makeText(this, "Not language selected", Toast.LENGTH_SHORT).show();
            return;
        }
        int result = textToSpeech.setLanguage(language);
        if (result == TextToSpeech.LANG_MISSING_DATA) {
            this.ready = false;
            Toast.makeText(this, "Missing language data", Toast.LENGTH_SHORT).show();
            return;
        } else if (result == TextToSpeech.LANG_NOT_SUPPORTED) {
            this.ready = false;
            Toast.makeText(this, "Language not supported", Toast.LENGTH_SHORT).show();
            return;
        } else {
            this.ready = true;
            Locale currentLanguage = textToSpeech.getDefaultLanguage();
            Toast.makeText(this, "Language " + currentLanguage, Toast.LENGTH_SHORT).show();
        }
    }



}