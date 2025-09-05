package org.springframework.samples.petclinic.pettypes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pettypes.model.PetTypeDetail;
import org.springframework.samples.petclinic.pettypes.repo.PetTypeDetailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PetTypeDetailService {

	@Autowired
	PetTypeDetailRepository repository;

	public List<PetTypeDetail> findAll() {
        return repository.findAll();
    }

    public Optional<PetTypeDetail> findByPetTypeId(Integer petTypeId) {
        return repository.findByPetTypeId(petTypeId);
    }

    @Transactional
    public PetTypeDetail save(PetTypeDetail detail) {
        return repository.save(detail);
    }

    @Transactional
    public void deleteByPetTypeId(Integer petTypeId) {
        repository.deleteByPetTypeId(petTypeId);
    }
    
    @Transactional(readOnly = true)
    public PetTypeDetail getByPetTypeIdOrThrow(Integer petTypeId) {
        return repository.findByPetTypeId(petTypeId)
                .orElseThrow(() -> new RuntimeException());
    }

	

	


}
