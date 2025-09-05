package org.springframework.samples.petclinic.pettypes.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.pettypes.model.PetTypeDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface PetTypeDetailRepository extends JpaRepository<PetTypeDetail, Integer> 
{
	Optional<PetTypeDetail> findByPetTypeId(Integer petTypeId);
	void deleteByPetTypeId(Integer petTypeId);
}