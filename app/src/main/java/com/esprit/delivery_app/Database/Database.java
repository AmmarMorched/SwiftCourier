package com.esprit.delivery_app.Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.esprit.delivery_app.DAO.UserDao;
import com.esprit.delivery_app.Entity.User;

@androidx.room.Database(entities = {User.class}, version = 2, exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract UserDao userDao();

    public static Database INSTANCE;
        public static Database getDatabaseInscatnce(Context context) {


        if (INSTANCE == null){
            synchronized (Database.class){
                INSTANCE = Room.databaseBuilder(context, Database.class,"Delivery" )
                        .allowMainThreadQueries()
                        .addMigrations(new Migration1To2())
                        .build();

            }
        }
        return INSTANCE;
    }







}
