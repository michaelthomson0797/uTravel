package com.utravel;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This activity will provide a list of saved flights where they can then be loaded from memory.
 */
public class Load extends AppCompatActivity {

    ArrayList<File> files;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);

        final ListView loadListView = findViewById(R.id.loadListView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //ask for permissions
        boolean permissionGranted = isStoragePermissionGranted();

        ArrayList<String> results;

        //only make folders if permission is granted
        if (permissionGranted) {
            //Create folder to hold saved values
            String path = Environment.getExternalStorageDirectory() + File.separator + "uTravel";
            File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "uTravel");

            //if folder doesnt exist, create it
            if (!folder.exists()) {
                folder.mkdirs();
            }


            //if there are files to load, load them. If not, show no trips saved
            if (folder.listFiles() != null) {

                files = new ArrayList<>(Arrays.asList(folder.listFiles()));
                results = new ArrayList<>();
                for (int i = 0; i < files.size(); i++) {
                    results.add(files.get(0).getName());
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        results);
                loadListView.setAdapter(arrayAdapter);
            } else {
                results = new ArrayList<>();
                results.add("No Trips Saved");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        results);
                loadListView.setAdapter(arrayAdapter);
            }
        } else {
            results = new ArrayList<>();
            results.add("Permission not granted");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1,
                    results);
            loadListView.setAdapter(arrayAdapter);
        }

        loadListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    ObjectInput in = new ObjectInputStream(new FileInputStream(files.get(i)));
                    Trip loadedTrip = (Trip) in.readObject();
                    Intent resultsIntent = new Intent(Load.this, Details.class);
                    resultsIntent.putExtra("trip", loadedTrip);
                    Load.this.startActivity(resultsIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //checks if storage permission is granted
    public boolean isStoragePermissionGranted() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
            return false;
        }
    }
}
