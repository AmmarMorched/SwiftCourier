package com.esprit.delivery_app.ViewModel;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.esprit.delivery_app.Database.Database;
import com.esprit.delivery_app.Entity.User;
import com.esprit.delivery_app.Repository.UserRepository;
import com.esprit.delivery_app.Utils.ImageUtils;

import java.util.List;

public class UserModel extends AndroidViewModel {

    public UserRepository userRepository;
    public LiveData<List<User>> getall;
    public  LiveData<User> authenticatedUser;


    public User getuserbyID(int id){
        return userRepository.getUserById(id);
    }
    public User getUserByName(String username){return userRepository.getUserByname(username);}
    public UserModel( @NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        getall = userRepository.getall;

    }
    public void insertuser(User user){
        userRepository.inseruser(user);
    }

    public void deleteuser(String username){
        userRepository.deleteuser(username);
    }

    public void updateuser(User user){
        userRepository.updateuser(user);
    }
    public User authenticateUser(String username, String password) {
        return userRepository.authenticateUser(username, password);
    }

    public LiveData<User> getAuthenticatedUser() {
        return authenticatedUser;
    }

    private MutableLiveData<User> currentUser = new MutableLiveData<>();

    public void setCurrentUser(User user) {
        currentUser.setValue(user);
    }

    public LiveData<User> getCurrentUser() {
        return currentUser;
    }
    public User getUserByEmail(String email){return userRepository.getBymail(email);}
    public User getUserById(int userId) {
        return userRepository.getUserById(userId);
    }
}
