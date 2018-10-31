package com.example.haomengchao.lab5project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.loopj.android.http.*;


import android.view.View;
import java.util.ArrayList;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import cz.msebera.android.httpclient.Header;





public class MainActivity extends AppCompatActivity {


    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
    private TextView recogresult;
    private Button button;
    private EditText ipaddress;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recogresult = findViewById(R.id.textView);
        ipaddress = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoiceRecognitionActivity();
            }
        });

    }
    private void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> result = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(result.get(0));
            recogresult.setText(stringBuilder);
            String textweget = stringBuilder.toString();

            String url = ipaddress.getText().toString();

            String result_get = requestpost(url,textweget);
            StringBuilder resultrepo = new StringBuilder();
            resultrepo.append("test");


        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private String requestpost(String url, String content){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("text",content);
        final StringBuilder resultrepo = new StringBuilder();
        client.post(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onSuccess(int i, Header[] headers, String s) {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }
        });
        return resultrepo.toString();


    }

}






