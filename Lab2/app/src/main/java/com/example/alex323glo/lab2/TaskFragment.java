package com.example.alex323glo.lab2;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TaskFragment extends Fragment {

    @BindView(R.id.txt_font_size)
    protected EditText editTextFontSize;
    @BindView(R.id.txt_text)
    protected EditText editTextText;


    public TaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.ok)
    protected void onOkClicked() {

        String text = getTextFromInput(editTextText);

        if (text == null || text.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
            builder.setMessage("Помилка! Введіть коректний текст.");
            builder.create().show();
            return;
        }

        try {

            float fontSize = getFontSizeFromInput(editTextFontSize);
            ((MainActivity) getActivity()).sendMessage(text, fontSize);

        } catch (NumberFormatException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
            builder.setMessage("Помилка! Введіть коректний розмір тексту (від 1 до 200).");
            builder.create().show();
            return;
        }
    }

    private String getTextFromInput(EditText editText) {
        return editText.getText().toString();
    }

    private float getFontSizeFromInput(EditText editText) throws NumberFormatException {
        float result = Float.valueOf(editText.getText().toString());
        if (result < 1 || result > 200) {
            throw new NumberFormatException();
        }
        return result;
    }
}
