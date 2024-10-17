package com.example.sma;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private TextView textViewCount;
    private Button buttonIncrement;
    private EditText textEdit;
    private CheckBox checkBox;
    private TextView textCheckBox;
    private Switch toggleSwitch;
    private StateViewModel stateViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCount = findViewById(R.id.textViewCount);
        buttonIncrement = findViewById(R.id.buttonIncrement);
        textEdit = findViewById(R.id.textEdit);
        checkBox = findViewById(R.id.checkBox);
        textCheckBox = findViewById(R.id.textCheckBox);
        toggleSwitch = findViewById(R.id.toggleSwitch);

        stateViewModel = new ViewModelProvider(this).get(StateViewModel.class);

        updateCountText();
        updateEditText();
        updateCheckBox();
        updateToggleSwitch();

        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stateViewModel.incrementCount();
                updateCountText();
            }
        });
        textEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                stateViewModel.setTextEditText(textEdit.getText().toString());
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox.isChecked()) {
                    stateViewModel.setChecked(true);
                } else {
                    stateViewModel.setChecked(false);
                }
                updateCheckBox();
            }
        });
        toggleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(toggleSwitch.isChecked()) {
                    stateViewModel.setSwitched(true);
                } else {
                    stateViewModel.setSwitched(false);
                }
                updateToggleSwitch();
            }
        });
    }
    private void updateCountText() {
        textViewCount.setText("Licznik: " + stateViewModel.getCount());
    }
    private void updateEditText() {
        textEdit.setText("" + stateViewModel.getTextEditText());
    }
    private void updateCheckBox() {
        if(stateViewModel.getChecked()) {
            textCheckBox.setText("Opcja została zaznaczona");
        } else {
            textCheckBox.setText("");
        }
    }
    private void updateToggleSwitch() {
        if(stateViewModel.getSwitched()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}