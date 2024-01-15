package com.esprit.delivery_app.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.esprit.delivery_app.Entity.User;

import java.util.List;

@androidx.room.Dao
public interface UserDao {
    @Query("select * from DB_user")
    LiveData<List<User>> getall();
    @Query("select * from DB_user where id=:id")
    public User getuserByID(int id);
    @Query("select * from DB_user where username=:username")
    User getuserByUsername(String username);
    @Insert
     void insertUser (User user);
    @Query("DELETE FROM DB_user WHERE username=:username")
    void delete(String username);
    @Update
    void updateuser(User user);
    @Query("SELECT * FROM DB_user WHERE username = :username AND password = :password")
    User authenticateUser(String username, String password);

    @Query("SELECT * FROM DB_user WHERE isAuthenticated = 1 LIMIT 1")
    LiveData<User> getAuthenticatedUser();

    @Query("select * from DB_user where email= :emailuser")
    User updatePass(String emailuser);
}
