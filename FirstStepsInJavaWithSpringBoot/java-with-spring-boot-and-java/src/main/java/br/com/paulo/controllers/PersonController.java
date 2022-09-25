package br.com.paulo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulo.data.vo.v1.PersonVO;
import br.com.paulo.models.Person;
import br.com.paulo.services.PersonServices;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

	@Autowired
	private PersonServices personServices;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonVO> FindById(@PathVariable(value = "id") Long id) throws Exception {
		return ResponseEntity.ok(this.personServices.findById(id));
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PersonVO>> FindAll() throws Exception {
		return ResponseEntity.ok(this.personServices.findAll());
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonVO> Create(@RequestBody(required = true) PersonVO person) throws Exception {
		return ResponseEntity.created(URI.create("")).body(this.personServices.create(person));
	}
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO Update(@PathVariable(name = "id") String id ,@RequestBody(required = true) PersonVO person) throws Exception {
		return this.personServices.create(person);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> Delete(@PathVariable(name = "id") Long id) throws Exception {
		this.personServices.delete(id);
		return ResponseEntity.noContent().build();
	}

}
