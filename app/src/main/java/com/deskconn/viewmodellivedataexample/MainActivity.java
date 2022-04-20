package com.deskconn.viewmodellivedataexample;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.deskconn.viewmodellivedataexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        viewModel.seconds().observe(this, integer -> binding.textview.setText(String.valueOf(integer)));

        viewModel.finished.observe(this, aBoolean -> {
            if (aBoolean) {
                Toast.makeText(this, "Finished!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.startButton.setOnClickListener(view -> {
            if (binding.editText.getText().toString().trim().isEmpty() || binding.editText.getText().length() < 4) {
                Toast.makeText(this, "Invalid Number", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.timerValue.setValue(Long.valueOf(binding.editText.getText().toString()));
                viewModel.startTimer();
            }
        });

        binding.stopButton.setOnClickListener(view -> {
            binding.textview.setText("");
            viewModel.stopTimer();
        });
    }
}