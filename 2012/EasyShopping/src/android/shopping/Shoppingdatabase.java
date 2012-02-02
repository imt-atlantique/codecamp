package android.shopping;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Shoppingdatabase {
	class Row extends Object {
        public int Id;
        public String name;
        public String adress;
        public String maxi;
        public String prix;
        public String status;
        public String statuson;
    }
	
	private static final String DATABASE_CREATE =
        "create table if not exists Produit(Id integer primary key autoincrement, "
            + "name text not null,"
            + "adress text,"
            + "maxi text,"
            + "prix text,"
            + "status text,"
            + "statuson text"
            +");";

    private static final String DATABASE_NAME = "Shoppingdatabase";

    private static final String DATABASE_TABLE = "Produit";

    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;
    
    public Shoppingdatabase(Context ctx) {
        db= ctx.openOrCreateDatabase(DATABASE_NAME, SQLiteDatabase.CREATE_IF_NECESSARY, null);
    	db.execSQL(DATABASE_CREATE);
    }
    
    public void close() {
        db.close();
    }
    
    public void createRow(String name, String adress, String maxi, String prix, String status, String statuson) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("name", name);
        initialValues.put("adress", adress);
        initialValues.put("maxi", maxi);
        initialValues.put("prix", prix);
        initialValues.put("status", status);
        initialValues.put("statuson", statuson);
        db.insert(DATABASE_TABLE, null, initialValues);
    }
    
    public void deleteRow(int rowId) {
        db.delete(DATABASE_TABLE, "Id=" + rowId, null);
    }
    
    public ArrayList<Row> fetchAllRows() {
        ArrayList<Row> ret = new ArrayList<Row>();
        try {
            Cursor c =
                db.query(DATABASE_TABLE, new String[] {
                    "Id", "name", "adress", "maxi", "prix", "status", "statuson"}, null, null, null, null, null);
            int numRows = c.getCount();
            c.moveToFirst();
            for (int i = 0; i < numRows; ++i) {
                Row row = new Row();
                row.Id = c.getInt(0);
                row.name = c.getString(1);
                row.adress = c.getString(2);
                row.maxi = c.getString(3);
                row.prix = c.getString(4);
                row.status = c.getString(5);
                row.statuson = c.getString(6);
                ret.add(row);
                c.moveToNext();
            }
        } catch (SQLException e) {
            Log.e("Exception on query", e.toString());
        }
        return ret;
    }
    
    public Row fetchRow(int rowId) {
        Row row = new Row();
        Cursor c =
            db.query(DATABASE_TABLE, new String[] {
                "Id", "name", "adress", "maxi", "prix", "status","statuson"}, "Id=" + rowId, null, null,
                null, null);

            c.moveToFirst();
            row.Id = c.getInt(0);
            row.name = c.getString(1);
            row.adress = c.getString(2);
            row.maxi = c.getString(3);
            row.prix = c.getString(4);
            row.status = c.getString(5);
            row.statuson = c.getString(6);
            return row;

    }
    
    public int getnameRow(String nameRow) {
    	Cursor c = db.query(DATABASE_TABLE, new String[] {"Id","name"}, "name='" + nameRow + "'", null, null, null, null);
        Log.d("Count",((Integer)c.getCount()).toString());
        if (c.getCount() > 0) {
            c.moveToFirst();
            return c.getInt(0);
            
        } else {
            return 10000;
        }
    }
    
    public int getAlready() {
    	Cursor c = db.query(DATABASE_TABLE, new String[] {"Id","statuson"}, "status='unchoose'", null, null, null, null);
            return c.getCount();
    }

    public void updateRow(int rowId, String name, String adress, String maxi, String prix, String status, String statuson) {
        ContentValues args = new ContentValues();
        args.put("name", name);
        args.put("adress", adress);
        args.put("maxi", maxi);
        args.put("prix", prix);
        args.put("status", status);
        args.put("statuson", statuson);
        db.update(DATABASE_TABLE, args, "Id=" + rowId, null);
    }
    
    public Cursor GetAllRows() {
        try {
            return db.query(DATABASE_TABLE, new String[] {
            		"Id", "name", "adress", "maxi", "prix", "status","statuson"}, null, null, null, null, null);
        } catch (SQLException e) {
            Log.e("Exception on query", e.toString());
            return null;
        }
    }
}
