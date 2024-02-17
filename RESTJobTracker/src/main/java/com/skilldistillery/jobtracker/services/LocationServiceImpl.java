package com.skilldistillery.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entities.Location;
import com.skilldistillery.jobtracker.repositories.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locRepo;

	@Override
	public List<Location> findAllLocations() {
		return locRepo.findAll();
	}

	@Override
	public Location findById(int locationId) {
		return locRepo.searchById(locationId);
	}

	@Override
	public Location createLocation(Location location) {
		return locRepo.saveAndFlush(location);
	}

	@Override
	public Location updateLocation(int locationId, Location location) {
		Optional<Location> optJob = locRepo.findById(locationId);
		if (optJob.isPresent()) {
			location.setId(locationId);
			return locRepo.saveAndFlush(location);
		}
		return null;
	}

	@Override
	public boolean deleteLocation(int locationId) {
		Optional<Location> optLocation = locRepo.findById(locationId);
		if (optLocation.isPresent()) {
			locRepo.delete(optLocation.get());
			return true;
		}
		return false;
	}
}
