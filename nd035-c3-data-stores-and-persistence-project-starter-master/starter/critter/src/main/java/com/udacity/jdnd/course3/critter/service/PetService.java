package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repo.CustomerRepository;
import com.udacity.jdnd.course3.critter.repo.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PetService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PetRepository petRepository;

    public Pet savePet(Pet pet, Long customerId) {
        Customer customer = customerRepository.getReferenceById(customerId);
        List<Pet> pets = new ArrayList<>();
        pet.setCustomer(customer);
        pet = petRepository.save(pet);
        pets.add(pet);
        customer.setPets(pets);
        customerRepository.save(customer);
        return pet;
    }

    public List<Pet> getPetsByCustomerId(Long customerId) {
        return petRepository.findPetByCustomerId(customerId);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet getPetById(Long petId) {
        return petRepository.findById(petId).get();
    }
}
