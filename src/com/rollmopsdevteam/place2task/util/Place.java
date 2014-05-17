package com.rollmopsdevteam.place2task.util;

import android.location.Address;

public class Place {

	private String _favoriteName;
	private Address _address;
	private String _addressString;
	private float _distanceInMeters;
	private boolean _isFavorite;

	public Place() {
		_isFavorite = false;
	}
	
	public final void setName(String name) {
		_favoriteName = name;
	}

	public final void setAddress(Address address) {
		_address = address;
		_addressString = _address.getAddressLine(0);
		if(_address.getMaxAddressLineIndex() > 1 ) {
			for(int i = 1; i < _address.getMaxAddressLineIndex(); i++ ) {
				_addressString += ", " + _address.getAddressLine(i);
			}
		}
	}
	
	public final void setDistanceInMeters(float distance) {
		_distanceInMeters = distance;
	}
	
	public final void setIsFavorite(boolean isFavorite) {
		_isFavorite = isFavorite;
	}
	
	public final Address getAddress() {
		return _address;
	}
	
	public final boolean isFavorite() {
		return _isFavorite;
	}
	
	public final String getFavoriteName() {
		return _favoriteName;
	}
	
	public final String getAddressString() {
		return _addressString;
	}
}
