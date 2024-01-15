package com.esprit.delivery_app.Database;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migration2To3 extends Migration {

    public Migration2To3() {
        super(2, 3);
    }

    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
        Log.d("migrate","starting migration from 2 to 3");
        try{
            database.execSQL("ALTER TABLE DB_user ADD COLUMN Migration TEXT DEFAULT 'default_value'");
            Log.d("migrate","Migration completed");
        }catch (Exception e){
            Log.d("migrate","Migration failed"+ e.getMessage());
            throw e;
        }


    }
}
