package com.rollmopsdevteam.place2task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AutoCompleteTextView;

import com.rollmopsdevteam.place2task.util.Constants;
import com.rollmopsdevteam.place2task.util.Place;

public class LocationEditText extends AutoCompleteTextView {

	private static final int _maxNumberLines = 10;
	private LocationEditTextAdapter _locationAdapter;
	private List<LocationSearchTask> _locationSearchTasks;
	private List<Place> _favoritePlaces;

	public LocationEditText(Context context) {
		super(context);
		init(context);
	};

	public LocationEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public LocationEditTextAdapter getAdapter() {
		return _locationAdapter;
	}

	public void setFavoritePlaces(List<Place> favoritePlaces) {
		_favoritePlaces = favoritePlaces;
	}

	public int getMaxNumberOfLines() {
		return _maxNumberLines;
	}
	
	public Place forceGetPlaceFromText() {
		List<Address> addresses = getAddressesFromString(getText().toString(), _maxNumberLines );
		Place ret = null;
		if( addresses != null && addresses.size() > 0) {
			if( addresses.size() > 1 ) {
				// TODO show prompt to choose addresses (multiselect)
			} else {
				ret = new Place();
				ret.addAddress(addresses.get(0));
			}
		}
		return ret;
	}

	private List<Address> getAddressesFromString( String location, int max ) {
		List<Address> addresses = null;
		try {
			// Getting a maximum of _maxNumberLines Address that matches the
			// input text
			addresses = new Geocoder(getContext(), Locale.getDefault())
					.getFromLocationName(getText().toString(), max);
		} catch (IOException e) {
			Log.e(Constants.LOG_TAG, "IOException: " + e.getMessage());
		}
		return addresses;
	}
	
	private void init(Context context) {

		_locationAdapter = new LocationEditTextAdapter((Activity) context);
		_locationSearchTasks = new ArrayList<LocationSearchTask>();
		_favoritePlaces = new ArrayList<Place>();

		setAdapter(_locationAdapter);
		setMaxLines(_maxNumberLines);
		setHint(R.string.task_location_hint);
		setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
		setThreshold(1);

		addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				// Getting user input location

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				for (LocationSearchTask task : _locationSearchTasks) {
					task.cancel(true);
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
				String location = getText().toString();
				if (location != null && !location.equals("")) {
					LocationSearchTask task = new LocationSearchTask();
					task.execute(location);
					_locationSearchTasks.add(task);
				}
			}
		});
	}

	public class LocationSearchTask extends
			AsyncTask<String, Void, List<Address>> {
		@Override
		protected List<Address> doInBackground(String... locationName) {
			((Activity) getContext()).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					_locationAdapter.clear();
				}
			});

			for (final Place favoritePlace : _favoritePlaces) {
				if (favoritePlace.getFavoriteName().toLowerCase()
						.contains(locationName[0].toLowerCase())
						|| favoritePlace.getAddressStringList().get(0)
								.toLowerCase()
								.contains(locationName[0].toLowerCase())) {
					((Activity) getContext()).runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_locationAdapter.add(favoritePlace);
						}
					});
				}
			}

			((Activity) getContext()).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					_locationAdapter.getFilter().filter("");
				}
			});

			Log.v(Constants.LOG_TAG, "@doInBackground");
			
			// get addresses
			return getAddressesFromString(locationName[0], _maxNumberLines);
		}

		@Override
		protected void onPostExecute(List<Address> addresses) {
			Log.v(Constants.LOG_TAG, "@onPostExecute");
			if (addresses != null && addresses.size() > 0) {
				for (Address address : addresses) {
					final Place place = new Place();
					place.addAddress(address);
					((Activity) getContext()).runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_locationAdapter.add(place);
						}
					});
				}
				((Activity) getContext()).runOnUiThread(new Runnable() {
					@Override
					public void run() {
						_locationAdapter.getFilter().filter("");
					}
				});

			}

		}
	}

}
