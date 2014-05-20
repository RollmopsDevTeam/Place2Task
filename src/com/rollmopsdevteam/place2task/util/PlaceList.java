package com.rollmopsdevteam.place2task.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.util.Log;

public class PlaceList extends ArrayList<Place> {

	private static final long serialVersionUID = 6932570688740820271L;
	private static PlaceList _instance = null;
	private static Context _context;
	private static PlaceListDBHelper _placeListDBHelper;
	
	// do not create TaskList by yourself. Instead use TaskList.getInstance()
	private PlaceList() {
		if (_context == null) {
			Log.e(Constants.LOG_TAG,
					"Context is not set. Use PlaceList.setContext() before calling PlaceList.getInstance()!");
		} else {
			Log.v(Constants.LOG_TAG, "Creating " + PlaceList.class.getName());
			_placeListDBHelper = new PlaceListDBHelper(_context);
		}
	}
	
	public void updateFromDB() {
		Log.v(Constants.LOG_TAG, "Updating PlaceList from DB.");
		clear();
		
		SQLiteDatabase db = _placeListDBHelper.getReadableDatabase();
		
		Cursor c = db.query(DBContract.PlaceEntryContract.TABLE_NAME,
				DBContract.PlaceEntryContract.PROJECTION, null, null, null, null, null, null);
		
		HashMap<String, Place> placesMap = new HashMap<>();
		
		if (c != null) {
			while (c.moveToNext()) {
				Place place;
				String placeName = c.getString(c.getColumnIndex(DBContract.PlaceEntryContract.COLUMN_NAME_PLACE_NAME));
				if( placesMap.containsKey(placeName)) {
					place = placesMap.get(placeName);
				} else {
					place = new Place();
					placesMap.put(placeName, place);
				}
				place.setIsFavorite(true);
				place.setName(placeName);
				Address address = new Address(Locale.getDefault());
				address.setLatitude(c.getDouble(c.getColumnIndex(DBContract.PlaceEntryContract.COLUMN_NAME_ADDRESS_LAT)));
				address.setLongitude(c.getDouble(c.getColumnIndex(DBContract.PlaceEntryContract.COLUMN_NAME_ADDRESS_LNG)));
				address.setCountryName(c.getString(c.getColumnIndex(DBContract.PlaceEntryContract.COLUMN_NAME_COUNTRY)));
				address.setUrl(c.getString(c.getColumnIndex(DBContract.PlaceEntryContract.COLUMN_NAME_URL)));
				place.addAddress(address);
			}
		}
	}
	
	public void storeToDB() {
		Log.v(Constants.LOG_TAG, "Storing PlaceList to DB.");
		SQLiteDatabase db = _placeListDBHelper.getWritableDatabase();
		ContentValues values = new ContentValues();

		for (Place place : this) {
			List<Address> addresses = place.getAddressList();
			if( addresses != null ) {
				for( Address address : place.getAddressList() ) {
					values.clear();
					values.put(DBContract.PlaceEntryContract.COLUMN_NAME_PLACE_NAME, place.getFavoriteName());
					values.put(DBContract.PlaceEntryContract.COLUMN_NAME_DISTANCE, place.getDistanceInMeters());
					values.put(DBContract.PlaceEntryContract.COLUMN_NAME_ADDRESS_STRING, address.toString());
					values.put(DBContract.PlaceEntryContract.COLUMN_NAME_COUNTRY, address.getCountryName());
					values.put(DBContract.PlaceEntryContract.COLUMN_NAME_ADDRESS_LAT, address.getLatitude());
					values.put(DBContract.PlaceEntryContract.COLUMN_NAME_ADDRESS_LNG, address.getLongitude());
					values.put(DBContract.PlaceEntryContract.COLUMN_NAME_URL, address.getUrl());
					// TODO check to rather use UPDATE instead of INSERT here
					// we use CONFLICT_IGNORE here since this is just meant to be an
					// update
					db.insertWithOnConflict(DBContract.PlaceEntryContract.TABLE_NAME,
							null, values, SQLiteDatabase.CONFLICT_IGNORE);
				}
			}
		}
	}

	
	
	public static void setContext(Context context) {
		_context = context;
	}

	public static PlaceList getInstance() {
		if (_instance == null) {
			_instance = new PlaceList();
		}
		return _instance;
	}

	
}
