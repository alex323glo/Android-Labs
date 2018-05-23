package com.example.alex323glo.lab1;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txt_font_size)
    protected EditText editTextFontSize;
    @BindView(R.id.txt_text)
    protected EditText editTextText;
    @BindView(R.id.txt_out)
    protected TextView outputTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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

        } catch (NumberFormatException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Помилка! Введіть коректний розмір тексту (від 1 до 200).");
            builder.create().show();
            return;
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
}
