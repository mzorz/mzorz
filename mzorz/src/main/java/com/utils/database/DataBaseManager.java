package com.utils.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zorz.mario.R;

import java.util.HashMap;

public class DataBaseManager {
	static Context mContext;
	private static SQLiteDatabase database;

	public static String DATA_BASE = "mzorz.db";

	public static final String TABLE_USERPROFILE = "userprofile";
	
	public static SQLiteDatabase getDatabase(Context context) {
		if (database == null) {
			database = new DBConnection(context, DATA_BASE).getDB();
		}
		return database;
	}

	public static void closeDatabase(Context context) {
		getDatabase(context).close();
	}

	public static void initializeDB(Context context) {
		mContext = context;
		createAndInitializeTables();
	}

	private static void createAndInitializeTables() {
		createUserProfileTable();
	}

	public static Cursor getCursorFromQuery(String query, Context context) {
		return getDatabase(context).rawQuery(query, null);
	}

	public static String getRowValue(String table, String where,
			String columnIndex, Context context) {
		String data = "";
		String query = "SELECT * FROM " + table + " WHERE " + where;
		Cursor cursor = getDatabase(context).rawQuery(query, null);
		if (cursor != null) {
			int columnText = cursor.getColumnIndexOrThrow(columnIndex);
			while (cursor.moveToNext()) {
				data = cursor.getString(columnText);
			}
		}
		;
		cursor.close();
		return data;
	}
	

	private static void createUserProfileTable() {
		DBConnection dbc = new DBConnection(mContext, DATA_BASE);
		
		HashMap<String, String> fields = new HashMap<String, String>();
		fields.put(mContext.getString(R.string._id), "INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT");
		fields.put(mContext.getString(R.string.email), "VARCHAR(200) NOT NULL");
		fields.put(mContext.getString(R.string.first_name), "VARCHAR(200) NULL");
		fields.put(mContext.getString(R.string.last_name), "VARCHAR(200) NULL");
		fields.put(mContext.getString(R.string.user_id), "VARCHAR(200) NOT NULL");
		fields.put(mContext.getString(R.string.device_id), "INTEGER NULL");

		dbc.createTable(TABLE_USERPROFILE, fields);
		dbc.closeConnection();
	}
	
}
