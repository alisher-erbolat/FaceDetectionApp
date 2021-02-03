package com.example.myfacedetectionapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    Context context;
    private static String DATABASE_NAME = "mydatabase.db";

    private static int DATABASE_VERSION = 1;
    private static String createTableQuery = "create table faceDetection (imageID TEXT" + ",image BLOB)";

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(createTableQuery);
            Toast.makeText(context, "Table created succ ins our db", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void storeImage(ModelClass modelClass){
        try{
            SQLiteDatabase objectSqliteDb = this.getWritableDatabase();
            Bitmap imageToStoreBitmap = modelClass.getImage();

            byteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            imageInBytes = byteArrayOutputStream.toByteArray();

            ContentValues contentValues = new ContentValues();
            contentValues.put("imageID", modelClass.getImageID());
            contentValues.put("image", imageInBytes);

            long checkIfQueryRun = objectSqliteDb.insert("facedetection", null, contentValues);
            if (checkIfQueryRun != -1){
                Toast.makeText(context, "Data added into our table", Toast.LENGTH_LONG).show();
                objectSqliteDb.close();
            }else {
                Toast.makeText(context, "Fails to add data", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<ModelClass> getAllImagesData(){
        try {
            SQLiteDatabase objectSqliteDatabase = this.getReadableDatabase();
            ArrayList<ModelClass> objectModelClassList = new ArrayList<>();

            Cursor objectCursor = objectSqliteDatabase.rawQuery("select * from facedetection", null);
            if (objectCursor.getCount() != 0){
                while (objectCursor.moveToNext()){
                    String imageID = objectCursor.getString(0);
                    byte[] imageBytes = objectCursor.getBlob(1);

                    Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    objectModelClassList.add(new ModelClass(imageID, objectBitmap));
                }
                return objectModelClassList;
            }else {
                Toast.makeText(context, "No values exists in Database", Toast.LENGTH_SHORT).show();
                return null;
            }
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
