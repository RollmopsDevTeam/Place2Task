package com.rollmopsdevteam.place2task.util;

import java.util.ArrayList;
import java.util.List;

import android.location.Address;

public class Place {

	private String _favoriteName;
	private List<Address> _addressList;
	private int _distanceInMeters;
	private boolean _isFavorite;

	public Place() {
		_isFavorite = false;
		_addressList = new ArrayList<Address>();
	}

	public final void setName(String name) {
		_favoriteName = name;
	}

	public final void addAddress(Address address) {
		_addressList.add(address);
	}

	public final void setDistanceInMeters(int distance) {
		_distanceInMeters = distance;
	}

	public final void setIsFavorite(boolean isFavorite) {
		_isFavorite = isFavorite;
	}

	public final List<Address> getAddressList() {
		return _addressList;
	}

	public final boolean isFavorite() {
		return _isFavorite;
	}

	public final String getFavoriteName() {
		return _favoriteName;
	}
	
	public final int getDistanceInMeters() {
		return _distanceInMeters;
	}
}
