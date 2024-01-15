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
import com.esprit.delivery_app.databinding.ActivityCreateAccountUserBinding;

public class Create_Account_User extends AppCompatActivity {

    ActivityCreateAccountUserBinding binding;
    String etuser, etmail,  etpwd, etrepwd, etphone ;

    UserModel userModel;
    Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_user);
        binding = ActivityCreateAccountUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userModel = new ViewModelProvider(this).get(UserModel.class);
        db = Room.databaseBuilder(getApplicationContext(),Database.class,"DB_user").build();

        binding.Register.setOnClickListener(v->{
            etuser=binding.username.getText().toString();
            etmail=binding.email.getText().toString();
            etpwd=binding.pwd.getText().toString();
            etrepwd=binding.repwd.getText().toString();
            etphone=binding.phonenum.getText().toString();
            if (etuser.equals("")|| etmail.equals("")|| etpwd.equals("") || etrepwd.equals("") ||etphone.equals("")) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else if (etphone.length() !=8) {
                Toast.makeText(this, "Phone number is avavailable", Toast.LENGTH_SHORT).show();
            } else if (! etmail.contains("@")&& !etmail.contains(".")) {
                Toast.makeText(this, "email is Anavailable", Toast.LENGTH_SHORT).show();
        }else if (cheackpass(etpwd,etrepwd)){
                Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
            }else {
                try {
                    Long phone = Long.parseLong(etphone);
                    CreateUser(etuser, phone, etmail, etpwd);
                    User authentification = userModel.authenticateUser(etuser,etpwd);
                    Intent intent = new Intent(Create_Account_User.this, Profile.class);
                    intent.putExtra("id",authentification.getId());
                    intent.putExtra("username", authentification.getUsername());
                    intent.putExtra("phone_number", authentification.getPhone_num());
                    intent.putExtra("email", authentification.getEmail());
                    userModel.setCurrentUser(new User());
                    Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid phone number format", Toast.LENGTH_SHORT).show();
                }

            }
        });
        binding.login.setOnClickListener(p->{
            Intent i1 = new Intent(Create_Account_User.this, Login.class);
            startActivity(i1);
        });
    }

    private void CreateUser(String username, Long phoneNumber, String email, String password) {
        User user1 = new User(username,password,email,phoneNumber);
        user1.setUsername(username);
        try {
            user1.setPhone_num(phoneNumber);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        user1.setEmail(email);
        user1.setPassword(password);
        userModel.insertuser(user1);

    }
    private boolean cheackpass(String etpwd, String etrepwd) {
        if(!etpwd.equals(etrepwd)){
            return true;
        }else return false;
    }
}