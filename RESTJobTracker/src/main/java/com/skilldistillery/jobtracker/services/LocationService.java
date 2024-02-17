package com.skilldistillery.jobtracker.services;

import java.util.List;

import com.skilldistillery.jobtracker.entities.Location;

public interface LocationService {
	List<Location> findAllLocations();

	Location findById(int locationId);

	Location createLocation(Location locationId);

	Location updateLocation(int locationId, Location location);

	boolean deleteLocation(int locationId);
}
