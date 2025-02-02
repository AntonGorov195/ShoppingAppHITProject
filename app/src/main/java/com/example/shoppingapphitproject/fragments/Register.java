package com.example.shoppingapphitproject.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.shoppingapphitproject.MainActivity;
import com.example.shoppingapphitproject.R;

public class Register extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // חיבור בין ה-XML לקוד Java
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // קישור לשדות הקלט והכפתור
        EditText usernameEditText = view.findViewById(R.id.username1);
        EditText passwordEditText = view.findViewById(R.id.password1);
        EditText confirmPasswordEditText = view.findViewById(R.id.repassword);
        EditText phoneEditText = view.findViewById(R.id.phone);
        Button registerButton = view.findViewById(R.id.registerButton1);

        // כפתור רישום
        registerButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();

            // קריאה לפונקציית הרישום שנמצאת ב-MainActivity
            MainActivity mainActivity = (MainActivity) getActivity();
            if (mainActivity != null) {
                mainActivity.registerUser(username, password, confirmPassword, phone, view);
            }
        });

        return view;
    }
}