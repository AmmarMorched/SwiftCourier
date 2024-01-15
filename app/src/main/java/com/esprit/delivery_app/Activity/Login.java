package com.esprit.delivery_app.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.esprit.delivery_app.Database.Database;
import com.esprit.delivery_app.Entity.User;
import com.esprit.delivery_app.R;
import com.esprit.delivery_app.ViewModel.UserModel;
import com.esprit.delivery_app.databinding.ActivityLoginBinding;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Login extends AppCompatActivity {

    UserModel userModel;
    String username , password;

    ActivityLoginBinding binding;
    Database db;
    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;
    ConstraintLayout biolayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userModel = new ViewModelProvider(this).get(UserModel.class);


        ///////////////////////////////////using biometrical login /////////////////////////////////


        biolayout = findViewById(R.id.bio);
        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(this, "device doesn't support biometrical authenticating", Toast.LENGTH_SHORT).show();
                break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this, "biometric autentication doesn't work ", Toast.LENGTH_SHORT).show();
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(this, "no fingerprint assigned please fill in your username and password to login", Toast.LENGTH_SHORT).show();
        }
        Executor executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(Login.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                BiometricPrompt.CryptoObject cryptoObject = result.getCryptoObject();
                biolayout.setVisibility(View.VISIBLE);
                User user =userModel.getUserByName("morched");
                if (userexist(user)) {
                    userModel.setCurrentUser(user);
                    Intent intent = new Intent(Login.this, Dashboard.class);
                    intent.putExtra("username", user.getUsername());
                    intent.putExtra("phone_number", user.getPhone_num());
                    intent.putExtra("email", user.getEmail());
                    intent.putExtra("img",user.getImg());
                    Toast.makeText(Login.this, "Welcome Back ", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else Toast.makeText(Login.this, "User Doesn't existe ", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });
        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("biometrical")
                .setDescription("Finger Print required").setDeviceCredentialAllowed(true).build();

        biometricPrompt.authenticate(promptInfo);

        //////////////////////////////////////////////////////////simple login /////////////////////////////////////////////

        db = Room.databaseBuilder(this, Database.class, "Delivery")
                .fallbackToDestructiveMigration().build();
        binding.login.setOnClickListener(v -> {
            username = binding.username1.getText().toString();
            password = binding.password.getText().toString();
            Log.d("Login", "username" + username);
            Log.d("Login", "password" + password);
            User authentification = userModel.authenticateUser(username, password);

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "all fields are required", Toast.LENGTH_SHORT).show();
            } else {
                if (authentification != null) {
                    Intent intent = new Intent(Login.this, Profile.class);
                    Toast.makeText(this, "Welcome Back", Toast.LENGTH_SHORT).show();
                    intent.putExtra("id", authentification.getId());
                    intent.putExtra("username", authentification.getUsername());
                    intent.putExtra("phone_number", authentification.getPhone_num());
                    intent.putExtra("email", authentification.getEmail());
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Check username or password", Toast.LENGTH_SHORT).show();
                }
            }


        });

        binding.forgetpwd.setOnClickListener(a -> {
            Intent in = new Intent(Login.this, Forget_pwd.class);
            startActivity(in);
        });


    }
    public boolean userexist(User user){
        if( !user.getUsername().equals(null)){
            return true;
        }else  return false;
    }

}