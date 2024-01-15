package com.esprit.delivery_app.Database;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
@Entity
public class Migration1To2 extends Migration {
    public Migration1To2() {
        super(1, 2);
    }

    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
        Log.d("migrate","starting migration from 1 to 2");
        try{
            database.execSQL("ALTER TABLE DB_user ADD COLUMN Migration TEXT DEFAULT 'default_value'");
            Log.d("migrate","Migration completed");
        }catch (Exception e){
            Log.d("migrate","Migration failed"+ e.getMessage());
            throw e;
        }


    }
}
