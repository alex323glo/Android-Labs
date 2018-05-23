package com.example.alex323glo.lab2;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txt_font_size)
    protected EditText editTextFontSize;
    @BindView(R.id.txt_text)
    protected EditText editTextText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void sendMessage(String output, float fontSize) {
        ((ResultFragment) getSupportFragmentManager().findFragmentById(R.id.result)).showOutput(output, fontSize);
    }

}
