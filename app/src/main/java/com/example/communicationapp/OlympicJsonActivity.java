package com.example.communicationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class OlympicJsonActivity extends AppCompatActivity {

    TextView textResult;
    Button btnJson;
    String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olympic_json);
        textResult = findViewById(R.id.txtOlympic);
        btnJson = findViewById(R.id.btnjson);

        jsonString = "{\"eventName\": \"men 100 meters\"," +
                "\"resultList\": [{\"medalColour\": \"bronze\", \"athleteName\": \"John\"}," +
                "{\"medalColour\": \"silver\", \"athleteName\": \"Mary\"}," +
                "{\"medalColour\": \"gold\", \"athleteName\": \"James\" }]}";

        textResult.setText(jsonString);

        btnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadHandler();
            }
        });
    } // on create

    private void uploadHandler() {
        String strUrl = "https://jsoncrack.com/editor";

        try {
            URL urlStr = new URL(strUrl);

            HttpURLConnection connection = (HttpURLConnection) urlStr.openConnection();

            JsonThread jsonThread = new JsonThread(this,connection,jsonString);

            Thread mythread = new Thread(jsonThread,"Json");
            mythread.start();


        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }//uploadHandler

    class JsonThread implements Runnable{
        AppCompatActivity appCompatActivity;
        HttpURLConnection httpURLConnection;
        String jsonPayload;

        public JsonThread(AppCompatActivity appCompatActivity, HttpURLConnection httpURLConnection, String jsonPayload){
            this.appCompatActivity = appCompatActivity;
            this.httpURLConnection = httpURLConnection;
            this.jsonPayload = jsonPayload;
        }

        @Override
        public void run() {
            if (prepareConnection(httpURLConnection))
            {
                String result = postJson(httpURLConnection);
                textResult.setText(result);
            }
            else {
                textResult.setText("☹️bad response");
            }
        }//override run

        private boolean prepareConnection(HttpURLConnection httpURLConnection){
            httpURLConnection.setDoOutput(true);

            try {
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                return true;
            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            }
        }//prepareConnection

        private String postJson(HttpURLConnection connection){
            String response = "";
            try {
                String postParameters = "jsonpayload="+ URLEncoder.encode(jsonString,"UTF-8");

                connection.setFixedLengthStreamingMode(postParameters.getBytes().length);

                PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
                printWriter.print(postParameters);
                printWriter.close();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK){
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String temp = "";

                    while ((temp = bufferedReader.readLine()) != null){
                        response += temp;
                    }
                }
                else
                {
                    response = "Error in exception";
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return response;
        }// postJson
    }
} // class