package com.example.cosmin.clientpart;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private final static int PORT = 60123;
    private final static String hostname = "10.0.2.2";
    private final static String debugString = "Debug";
    private Socket socket = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread() {

            @Override
            public void run() {

                try

                {   //Connecting
                    Log.i(debugString, "Attempting to connect to server");
                    socket = new Socket(hostname, PORT);
                    Log.i(debugString, "Connection Established");
                    //Send Message
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    bw.write("Hello from Client");
                    bw.newLine();
                    bw.flush();



                    //Receive message from the server
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println("Message from the Server:" + br.readLine());


                } catch (
                        IOException e
                        )

                {
                    Log.e(debugString, e.getMessage());

                }
            }

        }.start();
    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);

        BufferedWriter bw = null;


        //Send message to Server
        try {
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write(editText.getText().toString());
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();

        }


    }
}
