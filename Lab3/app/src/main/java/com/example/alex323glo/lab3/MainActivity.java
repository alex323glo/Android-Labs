package com.example.alex323glo.lab3;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String PATH_TO_FILE = "lab3_saved_texts.json";

    @BindView(R.id.txt_font_size)
    protected EditText editTextFontSize;
    @BindView(R.id.txt_text)
    protected EditText editTextText;
    @BindView(R.id.txt_out)
    protected TextView outputTextView;


    private List<TextItem> textItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        textItemList = new ArrayList<>();
    }

    @OnClick(R.id.ok)
    protected void onOkClicked() {

        String text = getTextFromInput(editTextText);

        if (text == null || text.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Помилка! Введіть коректний текст.");
            builder.create().show();
            return;
        }

        try {

            float fontSize = getFontSizeFromInput(editTextFontSize);

            outputTextView.setText(text);
            outputTextView.setTextSize(fontSize);

            textItemList.add(new TextItem(fontSize, text));

            saveListToFile();

        } catch (NumberFormatException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Помилка! Введіть коректний розмір тексту (від 1 до 200).");
            builder.create().show();
            return;
        } catch (IOException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Помилка! Невдале оновлення історії вводів.\n" + e.getMessage() +
                    "\n" + e.getCause().getMessage());
            builder.create().show();
            return;
        }

    }

    @OnClick(R.id.load)
    protected void onLoadClicked() {
        File file = new File(this.getFilesDir(), "/" + PATH_TO_FILE);
        if (!file.exists() || file.isDirectory()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Помилка! Файл з істоією відсутній.");
            builder.create().show();
        } else {
            Intent secondaryActivityIntent = new Intent(this, SecondaryActivity.class);
            this.startActivity(secondaryActivityIntent);
        }

    }


    private float getFontSizeFromInput(EditText editTextFontSize) throws NumberFormatException {
        float result = Float.valueOf(editTextFontSize.getText().toString());
        if (result < 1 || result > 200) {
            throw new NumberFormatException();
        }
        return result;
    }

    private String getTextFromInput(EditText editTextText) {
        return editTextText.getText().toString();
    }

    private void saveListToFile() throws IOException {
        File file = new File(this.getFilesDir(), "/" + PATH_TO_FILE);
        if (!file.exists() || file.isDirectory()) {
            file.createNewFile();
        }

        try (
                FileWriter fileWriter = new FileWriter(file)
        ) {

            String json = new Gson().toJson(textItemList);

            fileWriter.write(json);
            fileWriter.flush();
        }
    }
}
