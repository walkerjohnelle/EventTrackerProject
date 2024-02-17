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

import com.skilldistillery.jobtracker.entities.Preferences;
import com.skilldistillery.jobtracker.services.PreferencesService;

import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("api")
@RestController
public class PreferencesController {

	@Autowired
	private PreferencesService pS;

	@GetMapping(path = { "preferences", "preferences/" })
	public List<Preferences> index() {
		return pS.findAllPreferences();
	}

	@GetMapping("preferences/{prefId}")
	public Preferences show(@PathVariable("prefId") int prefId, HttpServletResponse rsp) {
		Preferences job = pS.findById(prefId);

		if (job == null) {
			rsp.setStatus(404);
		}
		return job;
	}

	@PostMapping("preferences")
	public Preferences createPreferences(@RequestBody Preferences pref, HttpServletResponse rsp) {
		Preferences createdPref = pS.createPreferences(pref);
		if (createdPref == null) {
			rsp.setStatus(404);
		}
		return createdPref;
	}

	@PutMapping("preferences/{prefId}")
	public Preferences updatePreferences(@PathVariable("prefId") int prefId, @RequestBody Preferences pref,
			HttpServletResponse rsp) {
		try {
			pref = pS.updatePreferences(prefId, pref);
			if (pref == null) {
				rsp.setStatus(404);
			}
		} catch (Exception e) {
			rsp.setStatus(400);
			pref = null;
			e.printStackTrace();
		}
		return pref;
	}

	@DeleteMapping("preferences/{prefId}")
	public void deletePreferences(@PathVariable("prefId") int prefId, HttpServletResponse rsp) {
		Preferences pref = pS.findById(prefId);

		try {
			if (pref != null) {
				pS.deletePreferences(prefId);
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
