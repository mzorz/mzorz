package com.utils.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class DBConnection {

	private static final int DATABASE_VERSION = 1;
	SQLiteDatabase DB;
	String query;
	Context c;

	public DBConnection(Context context, String dbName) {

		OpenHelper openHelper = new OpenHelper(context, dbName);
		try {
			DB = openHelper.getWritableDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createTable(String name, HashMap<String, String> values) {
		query = "CREATE TABLE IF NOT EXISTS " + name + "( ";
		Iterator<Entry<String, String>> it = values.entrySet().iterator();
		String query2 = "";
		while (it.hasNext()) {
			Entry<String, String> e = it.next();
			query2 = query2 + (String) e.getKey() + " " + (String) e.getValue()
					+ ", ";
		}
		query += (query2.substring(0, query2.length() - 2));
		query += ");";
		DB.execSQL(query);
	}

	public void dropTable(String name) {
		query = "DROP TABLE IF EXISTS " + name + ";";
		DB.execSQL(query);
	}

	public void updateData(String tabla, ContentValues cv, String whereClause) {
		int queryResult = DB.update(tabla, cv, whereClause, null);
		if (queryResult == -1) {
			throw new SQLiteException();
		}
 	}
	
	public void updateData(String tabla, ContentValues cv, String whereClause, String data) {
 		int queryResult = DB.update(tabla, cv, whereClause, new String[]{data});

		if (queryResult == -1) {
			throw new SQLiteException();
		}
 	}
	

	public void insertData(String tabla, ContentValues cv) {
		int queryResult = (int) DB.insert(tabla, null, cv);
		if (queryResult == -1) {
			throw new SQLiteException();
		}
 	}

	public void deleteData(String tabla, String nombrecampoid, String id) {
		String sql = "DELETE FROM " + tabla + " WHERE " + nombrecampoid + "= '"
				+ id + "'";
		DB.execSQL(sql);
	}

	public Cursor selectData(String table) {
		String SQL = "SELECT * FROM " + table;

		Cursor cur = DB.rawQuery(SQL, null);

		return cur;
	}

	public void closeConnection() {
		if (DB != null) {
			DB.close();
		}
	}

	public SQLiteDatabase getDB() {
		return DB;
	}

	public void setDB(SQLiteDatabase dB) {
		DB = dB;
	}

	private static class OpenHelper extends SQLiteOpenHelper {

		OpenHelper(Context context, String DATABASE_NAME) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// db.execSQL("CREATE TABLE " + TABLE_NAME
			// + " (id INTEGER PRIMARY KEY, name TEXT)");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Log.w("Example",
			// "Upgrading database, this will drop tables and recreate.");
			// db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			// onCreate(db);
		}
	}

}
