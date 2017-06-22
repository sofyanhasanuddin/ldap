package org.sofyan.ldapdemo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	
	@Autowired
	private UsersRepoImpl usersRepoImpl;
	
	@Test
	public void create() {
		
		Person p = new Person();
		p.setFirstName("Avril");
		p.setLastName("Lavigne");
		p.setDescription("User Avril");
		p.setGivenName("Avril");
		p.setUserpassword("qwerty");
		p.setUuid("dsaf21321mo");
		
		this.usersRepoImpl.create( p );
		
	}
	
	@Test
	public void update() {
		
		Person p = new Person();
		p.setFirstName("Avril");
		p.setLastName("Lavigne");
		p.setDescription("User Avril Updated");
		p.setGivenName("Avril");
		p.setUserpassword("qwerty");
		p.setUuid("dsaf21321mo");
		
		this.usersRepoImpl.update( p );
		
	}
	
	@Test
	public void delete() {
		
		Person p = new Person();
		p.setFirstName("Avril");
		p.setLastName("Lavigne");
		
		this.usersRepoImpl.delete( p );
		
	}
	
	@Test
	public void findByPrimaryKey() {
		Person p = this.usersRepoImpl.findByPrimaryKey("Avril Lavigne");
		if( p != null ) {
			System.out.println( p.getDescription() );
		}
	}
	
	@Test
	public void findAll() {
		
		List<Person> list = this.usersRepoImpl.findAll();
		for (Person person : list) {
			System.out.println( person.getFullName() );
		}
		
	}

}
