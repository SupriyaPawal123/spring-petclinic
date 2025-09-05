package org.springframework.samples.petclinic.pettypes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.pettypes.model.PetTypeDetail;
import org.springframework.samples.petclinic.pettypes.service.PetTypeDetailService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetTypeDetailController {

	@Autowired
	PetTypeDetailService service;

	@GetMapping("/details")
	public List<PetTypeDetail> getAll() {
		return service.findAll();
	}

	@GetMapping("/{petTypeId}/details")
	public ResponseEntity<PetTypeDetail> getByPetTypeId(@PathVariable Integer petTypeId) {
		return service.findByPetTypeId(petTypeId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/{petTypeId}/details")
	public ResponseEntity<PetTypeDetail> create(@PathVariable Integer petTypeId, @RequestBody PetTypeDetail detail) {

		detail.setId(null); // must be null so Hibernate INSERTs
		detail.setPetTypeId(petTypeId);

		PetTypeDetail saved = service.save(detail);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@PutMapping("/{petTypeId}/details")
	public ResponseEntity<PetTypeDetail> update(@PathVariable Integer petTypeId,
			@RequestBody PetTypeDetail updatedDetail) {

		PetTypeDetail existing = service.getByPetTypeIdOrThrow(petTypeId);

		// update fields
		existing.setTemperament(updatedDetail.getTemperament());
		existing.setLengthCm(updatedDetail.getLengthCm());
		existing.setWeightKg(updatedDetail.getWeightKg());
		existing.setCoatColor(updatedDetail.getCoatColor());
		existing.setNotes(updatedDetail.getNotes());

		PetTypeDetail saved = service.save(existing);
		return ResponseEntity.ok(saved);
	}

	@DeleteMapping("/{petTypeId}/details")
	public ResponseEntity<Void> delete(@PathVariable Integer petTypeId) {
		PetTypeDetail existing = service.getByPetTypeIdOrThrow(petTypeId);

		service.deleteByPetTypeId(existing.getPetTypeId());

		// 204 = No Content (successful delete)
		return ResponseEntity.noContent().build();
	}

}
