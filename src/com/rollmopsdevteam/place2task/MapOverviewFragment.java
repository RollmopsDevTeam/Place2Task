package com.rollmopsdevteam.place2task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;

public class MapOverviewFragment extends MapFragment {

	private MapView _mapView;
	private GoogleMap _map;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		setHasOptionsMenu(true);
		View fragment = inflater.inflate(R.layout.fragment_map_overview,
				container, false);

		switch (GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getActivity())) {
		case ConnectionResult.SUCCESS:
			Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
			_mapView = (MapView) fragment.findViewById(R.id.map);
			_mapView.onCreate(savedInstanceState);
			if (_mapView != null) {
				_map = _mapView.getMap();
				_map.getUiSettings().setMyLocationButtonEnabled(true);
				CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
						new LatLng(43.1, -87.9), 10);
				_map.animateCamera(cameraUpdate);
			}
			break;
		case ConnectionResult.SERVICE_MISSING:
			Toast.makeText(getActivity(), "SERVICE MISSING", Toast.LENGTH_SHORT)
					.show();
			break;
		case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
			Toast.makeText(getActivity(), "UPDATE REQUIRED", Toast.LENGTH_SHORT)
					.show();
			break;
		default:
			Toast.makeText(
					getActivity(),
					GooglePlayServicesUtil
							.isGooglePlayServicesAvailable(getActivity()),
					Toast.LENGTH_SHORT).show();
		}

		return fragment;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.my_places_menu, menu);
	}
}