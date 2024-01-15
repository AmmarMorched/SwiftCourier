package com.esprit.delivery_app.Entity;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.esprit.delivery_app.Utils.ImageUtils;

@Entity(tableName = "DB_user")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "phone_number")
    private Long phone_num;
    @ColumnInfo(name = "location")
    private String location;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB,name = "profile_pic")
    private byte[] img;

    @ColumnInfo( name = "isAuthenticated")
    private Boolean isAuthenticated;

    public User() {
    }

    public User(String username, String password, String email, Long phone_num) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone_num = phone_num;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Long getPhone_num() {
        return phone_num;
    }

    public String getLocation() {
        return location;
    }

    public byte[] getImg() {
        return img;
    }

    public Boolean getAuthenticated() {
        return isAuthenticated;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_num(Long phone_num) {
        this.phone_num = phone_num;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setImg(byte[] img) {this.img = img;}

    public void setAuthenticated(Boolean authenticated) {
        isAuthenticated = authenticated;
    }

}
