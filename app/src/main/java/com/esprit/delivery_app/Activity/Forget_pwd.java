package com.esprit.delivery_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.esprit.delivery_app.Database.Database;
import com.esprit.delivery_app.Entity.User;
import com.esprit.delivery_app.R;
import com.esprit.delivery_app.ViewModel.UserModel;
import com.esprit.delivery_app.databinding.ActivityForgetPwdBinding;

public class Forget_pwd extends AppCompatActivity {

    ActivityForgetPwdBinding binding;
    String email, pwd,repwd;
    UserModel userModel;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        binding = ActivityForgetPwdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userModel = new ViewModelProvider(this).get(UserModel.class);
        db = Room.databaseBuilder(getApplicationContext(), Database.class,"DB_user").build();
        binding.send.setOnClickListener(v->{
            email = binding.email.getText().toString();
            pwd = binding.enter.getText().toString();
            repwd= binding.reenter.getText().toString();
            if (email.equals("") || pwd.equals("")|| repwd.equals("")){
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }else if(! pwd.equals(repwd)){
                Toast.makeText(this, "Passwords doesn't match", Toast.LENGTH_SHORT).show();
            }else if (! email.contains("@")&& !email.contains(".")){
                Toast.makeText(this, "email doesn't existe", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(Forget_pwd.this, Profile.class);
                Toast.makeText(this, "password changed succesfully ", Toast.LENGTH_SHORT).show();
                User updated = userModel.getUserByEmail(email);
                if (updated != null) {
                    intent.putExtra("username", updated.getUsername());
                    intent.putExtra("phone_number", updated.getPhone_num());
                    intent.putExtra("email", updated.getEmail());
                    updated.setPassword(pwd);
                    userModel.updateuser(updated);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}