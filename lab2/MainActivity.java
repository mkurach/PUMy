package com.example.lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText login;
    EditText haslo;
    Button przycisk;
    //MenuItem rejestracja;


    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Password = "passwordKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.editTextTextPersonName);
        haslo = findViewById(R.id.editTextTextPassword);
        przycisk = findViewById(R.id.button);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, this.MODE_PRIVATE);

        przycisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences editor = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                String loginStr = login.getText().toString();
                String hasloStr = haslo.getText().toString();
                String zapisane = editor.getString(loginStr, "Not in database");

                if(hasloStr.equals(zapisane)) {
                    Intent intentBA = new Intent(getBaseContext(), Zalogowane.class);
                    startActivity(intentBA);
                    Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.autor:
                Toast.makeText(this, "Małgorzata Kurach", Toast.LENGTH_LONG).show();
                return true;
            case R.id.rejestracja:
                Intent intentMA = new Intent(this, Rejestracja.class);
                startActivity(intentMA);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}