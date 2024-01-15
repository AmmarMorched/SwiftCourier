package com.esprit.delivery_app.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.biometric.BiometricPrompt;
import androidx.lifecycle.LiveData;

import com.esprit.delivery_app.DAO.UserDao;
import com.esprit.delivery_app.Database.Database;
import com.esprit.delivery_app.Entity.User;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {

    public UserDao userDao;

    public LiveData<List<User>> getall;
    public LiveData<User> authenticatedUser;




    public UserRepository(Application application){
        Database db = Database.getDatabaseInscatnce(application);
        userDao = db.userDao();
        getall = userDao.getall();
    }

    public User getUserById(int userId) {
        return userDao.getuserByID(userId);
    }
    public User getUserByname(String username){return userDao.getuserByUsername(username);}
    public void inseruser(User user){
        userDao.insertUser(user);
    }
    public void deleteuser(String username){
        userDao.delete(username);
    }
    public void updateuser(User user){
         userDao.updateuser(user);
    }
    public User getBymail(String email){return userDao.updatePass(email);}
    public User authenticateUser(String username, String password) {
        // Perform authentication query
        try {
            User user = userDao.authenticateUser(username, password);
            return user;
        }catch(Exception e){
            Log.e("UserRepository", "Error in authenticating", e );
            return null;
        }
    }
    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.updateuser(users[0]);
            return null;
        }
    }




}
