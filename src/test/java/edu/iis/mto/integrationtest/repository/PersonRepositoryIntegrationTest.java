package edu.iis.mto.integrationtest.repository;

import static edu.iis.mto.integrationtest.repository.PersonBuilder.person;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import edu.iis.mto.integrationtest.model.Person;


public class PersonRepositoryIntegrationTest extends IntegrationTest {

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void testCanAccessDbAndGetTestData() {
		List<Person> foundTestPersons = personRepository.findAll();
		assertEquals(2, foundTestPersons.size());
	}

	@Test
	@DirtiesContext
	public void testSaveNewPersonAndCheckIsPersisted() {
		long count = personRepository.count();
		personRepository.save(a(person().withId(count + 1)
				.withFirstName("Roberto").withLastName("Mancini")));
		assertEquals(count + 1, personRepository.count());
		assertEquals("Mancini", personRepository.findOne(count + 1)
				.getLastName());
		
	}
	
	@Test
	@DirtiesContext
	public void testDeletePersonAndCheckIsDeleted() {
		long count = personRepository.count();
		personRepository.delete(count);		
		assertEquals(count - 1, personRepository.count());		
	}
	
//	@Test
//	@DirtiesContext
//	public void testReadPersonAndCheckIsRedaCorrect() {
//		Person person = personRepository.getOne(personRepository.count());
//		assertEquals(person.getFirstName(), personRepository.getOne(personRepository.count()).getFirstName());
//		
//	}
	
	@Test
	@DirtiesContext
	public void testUpdatePersonAndCheckIsUptated() {
		long count = personRepository.count();
		personRepository.save(a(person().withId(count).withFirstName("Arsene").withLastName("Wenger")));
		assertEquals(count, personRepository.count());	
		assertEquals("Wenger", personRepository.findOne(count).getLastName());
	}

	private Person a(PersonBuilder builder) {
		return builder.build();
	}

}
