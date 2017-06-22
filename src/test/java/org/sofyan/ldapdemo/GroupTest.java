package org.sofyan.ldapdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupTest {
	
	@Autowired
	private GroupRepoImpl groupRepoImpl;
	
	@Test
	public void add() {
		
		Person p = new Person();
		p.setFirstName("Avril");
		p.setLastName("Lavigne");
		
		this.groupRepoImpl.addMemberToGroup("HMS Victory", p);
		
	}
	
	@Test
	public void remove() {
		
		Person p = new Person();
		p.setFirstName("Avril");
		p.setLastName("Lavigne");
		
		this.groupRepoImpl.removeMemberFromGroup("HMS Victory", p);
		
	}
	

}
