package com.skilldistillery.jobtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jobtracker.entities.Location;
import com.skilldistillery.jobtracker.services.LocationService;

import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("api")
@RestController
public class LocationController {

	@Autowired
	private LocationService locS;

	@GetMapping(path = { "locations", "locations/" })
	public List<Location> index() {
		return locS.findAllLocations();
	}

	@GetMapping("locations/{locationId}")
	public Location show(@PathVariable("locationId") int locationId, HttpServletResponse rsp) {
		Location location = locS.findById(locationId);

		if (location == null) {
			rsp.setStatus(404);
		}
		return location;
	}

	@PostMapping("locations")
	public Location createLocation(@RequestBody Location location, HttpServletResponse rsp) {
		Location createdLocation = locS.createLocation(location);
		if (createdLocation == null) {
			rsp.setStatus(404);
		}
		return createdLocation;
	}

	@PutMapping("locations/{locationId}")
	public Location updateLocation(@PathVariable("jobId") int locationId, @RequestBody Location location,
			HttpServletResponse rsp) {
		try {
			location = locS.updateLocation(locationId, location);
			if (location == null) {
				rsp.setStatus(404);
			}
		} catch (Exception e) {
			rsp.setStatus(400);
			location = null;
			e.printStackTrace();
		}
		return location;
	}

	@DeleteMapping("locations/{locationId}")
	public void deleteLocation(@PathVariable("locationId") int locationId, HttpServletResponse rsp) {
		Location location = locS.findById(locationId);

		try {
			if (location != null) {
				locS.deleteLocation(locationId);
				rsp.setStatus(204);
			} else {
				rsp.setStatus(404);
			}
		} catch (Exception e) {
			rsp.setStatus(400);
			e.printStackTrace();
		}
	}

}
