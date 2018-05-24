package com.example.alex323glo.lab3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;

import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.ButterKnife;
import com.google.gson.Gson;

import java.io.*;
import java.util.List;
import java.util.Map;

public class SecondaryActivity extends Activity {

    private static final String PATH_TO_FILE = "lab3_saved_texts.json";

    @BindView(R.id.secondary_txt_list)
    protected TextView textItemsListTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        ButterKnife.bind(this);

        try {
            textItemsListTextView.setText(loadAnStringifyListOfTexts());
            textItemsListTextView.setTextSize(18F);
        } catch (IOException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Помилка! Файл з істоією не може бути завантажено.");
            builder.create().show();
        }
    }

    private String loadAnStringifyListOfTexts() throws IOException {
        try (
                FileReader reader = new FileReader(new File(this.getFilesDir(), "/" + PATH_TO_FILE));
        ) {
            List fromJson = new Gson().fromJson(reader, List.class);

            StringBuilder builder = new StringBuilder();

            try {
                for (int i = 0; i < fromJson.size(); i++) {
                    Map<String, Object> textItem = (Map<String, Object>) fromJson.get(i);
                    builder.append(i + 1).append(". ")
                            .append('\"').append(textItem.get("value")).append("\" (size: ")
                            .append(textItem.get("size")).append(")\n");

                }
            } catch (Exception e) {
                new AlertDialog.Builder(this)
                        .setMessage("Помилка! Неможливо прочитати історію.")
                        .create()
                        .show();
            }

            return builder.toString();
        }
    }

    @OnClick(R.id.secondary_back)
    protected void onSecondaryBackClicked() {
        finish();
    }

}
