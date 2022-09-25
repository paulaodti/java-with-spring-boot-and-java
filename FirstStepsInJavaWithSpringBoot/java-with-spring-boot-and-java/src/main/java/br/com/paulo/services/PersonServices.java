package br.com.paulo.services;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paulo.data.vo.v1.PersonVO;
import br.com.paulo.greeting.exceptions.ResourceNotFoundException;
import br.com.paulo.mapper.DozerMapper;
import br.com.paulo.models.Person;
import br.com.paulo.repositories.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	private PersonRepository personRepository;
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public PersonVO findById(Long id) {
		logger.info("Finding one person!");
		return DozerMapper.parseObject(personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!")), 
				PersonVO.class);
	}

	public List<PersonVO> findAll() {
		logger.info("Finding all persons!");
		return DozerMapper.parseListObjects(personRepository.findAll(),PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one person!");
		return DozerMapper.parseObject(personRepository.save(
				DozerMapper.parseObject(person, 
						Person.class)), 
				PersonVO.class);
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating one person!");
		var entityPerson = findById(person.getId());
		entityPerson.setFirstName(person.getFirstName());
		entityPerson.setLastName(person.getLastName());
		entityPerson.setGender(person.getGender());
		entityPerson.setAddress(person.getAddress());
		return DozerMapper.parseObject(personRepository.save(
				DozerMapper.parseObject(entityPerson, 
						Person.class)), 
				PersonVO.class);
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person!");
		personRepository.deleteById(id);
	}
}
