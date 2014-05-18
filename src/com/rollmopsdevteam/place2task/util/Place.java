package com.rollmopsdevteam.place2task.util;

import java.util.ArrayList;
import java.util.List;

import android.location.Address;

public class Place {

	private String _favoriteName;
	private List<Address> _addressList;
	private List<String> _addressStringList;
	private float _distanceInMeters;
	private boolean _isFavorite;

	public Place() {
		_isFavorite = false;
		_addressList = new ArrayList<Address>();
		_addressStringList = new ArrayList<String>();
	}

	public final void setName(String name) {
		_favoriteName = name;
	}

	public final void addAddress(Address address) {
		_addressList.add(address);
		String addressString = address.getAddressLine(0);
		if (address.getMaxAddressLineIndex() > 1) {
			for (int i = 1; i < address.getMaxAddressLineIndex(); i++) {
				addressString += ", " + address.getAddressLine(i);
			}
		}
		_addressStringList.add(addressString);
	}

	public final void setDistanceInMeters(float distance) {
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

	public final List<String> getAddressStringList() {
		return _addressStringList;
	}
}
