package com.example.asus.controlcar;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_ENABLE_BT = 1; //REQUEST_ENABLE_BT is a request code that you provide. It's really just a number that you provide for onActivityResult. It will be the requestCode (first parameter) of onActivityResult when the activity returns. You could put any number you want as long as it's consistent in the return method.
    Button btn;
    ListView lst;
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    public static String EXTRA_ADDRESS = "device_address";
    public ArrayList list = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.start);
        lst=(ListView)findViewById(R.id.deviceList);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBluetoothAdapter==null){
                    Toast.makeText(getApplicationContext(), " Device doesn't support Bluetooth", Toast.LENGTH_LONG).show();
                }
                else if(mBluetoothAdapter!=null){
                    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
                    //Toast.makeText(getApplicationContext(), " button work ", Toast.LENGTH_LONG).show();
                    if (pairedDevices.size() > 0) {
                        // There are paired devices. Get the name and address of each paired device.
                        for (BluetoothDevice device : pairedDevices) {
                            String deviceName = device.getName();
                            String deviceHardwareAddress = device.getAddress(); // MAC addres
                            list.add("Name: "+deviceName+"MAC Address: "+deviceHardwareAddress);
                           // Toast.makeText(getApplicationContext(), " click ", Toast.LENGTH_LONG).show();
                        }
                        ArrayAdapter aAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,list);
                        lst.setAdapter(aAdapter);
                        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Toast.makeText(getApplicationContext(),
                                        "Device: " + list.get(i),
                                        Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), " click 2 ", Toast.LENGTH_LONG).show();

                                String info = ((TextView) view).getText().toString();
                                String address = info.substring(info.length() - 17);
                                // Make an intent to start next activity.
                               // Toast.makeText(getApplicationContext(), " click ",Toast.LENGTH_LONG).show();
                                Intent in = new Intent(MainActivity.this, Control.class);
                                //Change the activity.
                                in.putExtra(EXTRA_ADDRESS, address); //this will be received at Control (class) Activity
                                startActivity(in);/**/
                            }
                        });
                    }
                }
            }
        });

    }
}
