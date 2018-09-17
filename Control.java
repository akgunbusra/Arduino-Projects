package com.example.asus.controlcar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class Control extends Activity {
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private ProgressDialog progress;
    String address = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    Button forward,backward,btnright,btnleft,stop;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_xml);
        Intent newint = getIntent();
        forward=(Button) findViewById(R.id.forward);
        backward=(Button) findViewById(R.id.backward);
        btnright=(Button) findViewById(R.id.btnRight);
        btnleft=(Button) findViewById(R.id.btnLeft);
        stop=(Button) findViewById(R.id.stop);
        address = newint.getStringExtra(MainActivity.EXTRA_ADDRESS);
        new ConnectBT().execute();
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    btSocket.getOutputStream().write("forward ".toString().getBytes());
                }
               catch (IOException e){
                   Toast.makeText(getApplicationContext(), " Not send.", Toast.LENGTH_LONG).show();
               }

            }
        });
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    btSocket.getOutputStream().write("backward ".toString().getBytes());
                }
                catch (IOException e){
                    Toast.makeText(getApplicationContext(), " Not send.", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    btSocket.getOutputStream().write("left ".toString().getBytes());
                }
                catch (IOException e){
                    Toast.makeText(getApplicationContext(), " Not send.", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    btSocket.getOutputStream().write("right ".toString().getBytes());
                }
                catch (IOException e){
                    Toast.makeText(getApplicationContext(), " Not send.", Toast.LENGTH_LONG).show();
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    btSocket.getOutputStream().write("stop ".toString().getBytes());
                }
                catch (IOException e){
                    Toast.makeText(getApplicationContext(), " Not send.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(Control.this, "Connecting...", "Please wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                //msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                Toast.makeText(getApplicationContext(), " Connection Failed. Is it a SPP Bluetooth? Try again.", Toast.LENGTH_LONG).show();
                finish();
            }
            else
            {
               // msg("Connected.");
                Toast.makeText(getApplicationContext(), " Connected.", Toast.LENGTH_LONG).show();
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }
}
