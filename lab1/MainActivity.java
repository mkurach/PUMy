package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView procent;
    EditText rachunek;
    TextView napiwek;
    TextView calosc;

    double rachunekKwota;
    double napiwekProcent;
    double napiwekKwota;
    double calaKwota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        procent = findViewById(R.id.procNap);
        rachunek = findViewById(R.id.kwotaRach);
        napiwek = findViewById(R.id.napiwek);
        calosc = findViewById(R.id.pelnaKwota);

        rachunek.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        rachunekKwota = Double.parseDouble(rachunek.getText().toString());
                        updateRzeczy();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                }
        );

        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        procent.setText("Napiwek: "+String.valueOf(i)+" %");
                        napiwekProcent = (double)i;
                        updateRzeczy();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

    }

    void updateRzeczy() {
        //policz nowe wartosci 2
        napiwekKwota = napiwekProcent*0.01*rachunekKwota;
        calaKwota = rachunekKwota + napiwekKwota;
        //wyswietl
        napiwek.setText("Kwota napiwku: "+new DecimalFormat("0.00").format(napiwekKwota));
        calosc.setText("Cała kwota: "+new DecimalFormat("0.00").format(calaKwota));
    }
}

