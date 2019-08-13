package ujjwal.anand.globallogic.admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Admin.db";
    public static final String TABLE_NAME = "table_login";
    public static final String ID = "LoginID";
    public static final String PASSWORD = "Password";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table "+TABLE_NAME+" (LoginID Text, Password Text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String id,String pass){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("Select "+ID+" from " + TABLE_NAME+" where "+ID+"= \""+id+"\"",null);
        if(res!=null && res.moveToFirst()){
            return false;
        }else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID,id);
            contentValues.put(PASSWORD,pass);
            long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
            if(result==-1){
                return false;
            }else {
                return true;
            }
        }
    }

    public void deleteAll(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from "+TABLE_NAME);

    }

    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("Select * from " + TABLE_NAME,null);
        return res;
    }

    public String getReqPassword(String user){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("Select "+PASSWORD+" from " + TABLE_NAME+" where "+ID+"= \""+user+"\"",null);

        Log.i("info",res.toString());

        if(res!=null && res.moveToFirst())
            return res.getString(0);
        else
            return "not found";
    }
}
