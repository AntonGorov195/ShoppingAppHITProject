package com.example.shoppingapphitproject.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingapphitproject.MainActivity;
import com.example.shoppingapphitproject.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Login extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // טעינת הפריסה
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // טיפול בכפתור התחברות
        Button loginButton = view.findViewById(R.id.login); // ודא שה-ID "Login" קיים ב-XML
        loginButton.setOnClickListener(v -> {
            EditText usernameEditText = view.findViewById(R.id.usernameInput);
            EditText passwordEditText = view.findViewById(R.id.passwordInput);

            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "אנא מלא את כל השדות", Toast.LENGTH_SHORT).show();
                return;
            }

            MainActivity mainActivity = (MainActivity) getActivity();
            if (mainActivity != null) {
                mainActivity.login(username, password, view);
            }
        });


        // טיפול בכפתור הרשמה
        Button registerButton = view.findViewById(R.id.register); // ודא שה-ID "Register" קיים ב-XML
        registerButton.setOnClickListener(v -> {
            // ניווט ל-FragmentTwo
            Navigation.findNavController(view).navigate(R.id.action_login_to_register);
        });

        return view;
    }
}