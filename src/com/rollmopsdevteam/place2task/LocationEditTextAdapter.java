package com.rollmopsdevteam.place2task;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import com.rollmopsdevteam.place2task.util.Place;

public class LocationEditTextAdapter extends ArrayAdapter<Place> implements
		Filterable {

	private Activity _activity;

	public LocationEditTextAdapter(Activity activity) {
		super(activity.getApplicationContext(), R.layout.location_dropdown_item);
		_activity = activity;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (vi == null) {
			vi = _activity.getLayoutInflater().inflate(
					R.layout.location_dropdown_item, null);
		}

		TextView locationNameView = (TextView) vi
				.findViewById(R.id.location_name);
		TextView descriptionView = (TextView) vi
				.findViewById(R.id.optional_location_description);

		Place place = getItem(position);
		vi.findViewById(R.id.favorite_star_image).setVisibility(
				place.isFavorite() ? View.VISIBLE : View.GONE);

		descriptionView.setVisibility(place.isFavorite() ? View.VISIBLE
				: View.GONE);
		locationNameView.setTypeface(null, place.isFavorite() ? Typeface.BOLD
				: Typeface.NORMAL);

		if (place.isFavorite()) {
			String favoriteName = place.getFavoriteName();
			if (place.getAddressList().size() > 1) {
				favoriteName += " (" + place.getAddressList().size() + ")";
			}
			locationNameView.setText(favoriteName);
			descriptionView.setText("In "
					+ place.getAddressList().get(0).getCountryName());
		} else {
			locationNameView.setText(place.getAddressAsStringList().get(0));
		}

		return vi;
	}

}
