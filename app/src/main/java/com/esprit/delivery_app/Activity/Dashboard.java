package com.esprit.delivery_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.esprit.delivery_app.R;
import com.esprit.delivery_app.ViewModel.UserModel;
import com.esprit.delivery_app.databinding.ActivityCreateAccountUserBinding;

public class Dashboard extends AppCompatActivity {

    ImageView profile, logout;

    ActivityCreateAccountUserBinding binding;
    UserModel userModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        userModel = new ViewModelProvider(this).get(UserModel.class);
        binding = ActivityCreateAccountUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        profile =findViewById(R.id.profile);
        logout = findViewById(R.id.about);
        userModel.getCurrentUser();
            profile.setOnClickListener(v->{
                 Intent intent = new Intent(Dashboard.this, Profile.class);
                startActivity(intent);
            });



            logout.setOnClickListener(v->{
                Intent intent = new Intent(Dashboard.this, Login.class);
                Toast.makeText(this, "Logging out ", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            });



    }
}