package com.rollmopsdevteam.place2task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.rollmopsdevteam.place2task.util.Constants;

public class LocationEditText extends AutoCompleteTextView {

	private static final int _maxNumberLines = 6;

	private ArrayAdapter<String> _locationAdapter;

	private List<String> _favoriteList;

	public LocationEditText(Context context) {
		super(context);
		init(context);
	};

	public LocationEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public void setFavoriteList( List<String> favoriteList ) {
		_favoriteList = favoriteList;
	}
	
	private void init(Context context) {
		
		_favoriteList = new ArrayList<String>();
		
		_locationAdapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_dropdown_item_1line);

		setAdapter(_locationAdapter);
		setMaxLines(_maxNumberLines);
		setHint(R.string.task_location_hint);
		setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
		setThreshold(1);

		addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// Getting user input location
				String location = getText().toString();

				if (location != null && !location.equals("")) {
					new LocationSearchTask().execute(location);
				}
			}
		});
	}

	private class LocationSearchTask extends
			AsyncTask<String, Void, List<Address>> {

		@Override
		protected List<Address> doInBackground(String... locationName) {
			List<Address> addresses = null;

			try {
				// Getting a maximum of _maxNumberLines Address that matches the
				// input text
				addresses = new Geocoder(getContext(), Locale.getDefault())
						.getFromLocationName(locationName[0], _maxNumberLines);
			} catch (IOException e) {
				Log.e(Constants.LOG_TAG, "IOException: " + e.getMessage());
			}
			return addresses;
		}

		@Override
		protected void onPostExecute(List<Address> addresses) {
			_locationAdapter.clear();
			_locationAdapter.addAll(_favoriteList);
			if (addresses != null && addresses.size() > 0) {
				for (Address address : addresses) {
					String addressString = "";
					for (int line = 0; line < address.getMaxAddressLineIndex(); line++) {
						addressString += line > 0 ? ", "
								+ address.getAddressLine(line) : address
								.getAddressLine(line);
					}
					if( addressString.length() > 0 ) {	
						_locationAdapter.add(addressString);
					}
				}
				// only filter first word
				_locationAdapter.getFilter().filter(getText().toString().split("\\s+|,|\\.")[0]);
			}
			
		}

	}

}
