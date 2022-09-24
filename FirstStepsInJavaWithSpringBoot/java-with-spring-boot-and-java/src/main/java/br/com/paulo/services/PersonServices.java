package br.com.paulo.services;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.paulo.greeting.exceptions.ResourceNotFoundException;
import br.com.paulo.models.Person;
import br.com.paulo.repositories.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	private PersonRepository personRepository;
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public Person findById(Long id) {
		logger.info("Finding one person!");
		return personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	}

	public List<Person> findAll() {
		logger.info("Finding all persons!");
		return personRepository.findAll();
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");
		return personRepository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Updating one person!");
		var entityPerson = findById(person.getId());
		entityPerson.setFirstName(person.getFirstName());
		entityPerson.setLastName(person.getLastName());
		entityPerson.setGender(person.getGender());
		entityPerson.setAddress(person.getAddress());
		return personRepository.save(entityPerson);
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person!");
		personRepository.deleteById(id);
	}
}
