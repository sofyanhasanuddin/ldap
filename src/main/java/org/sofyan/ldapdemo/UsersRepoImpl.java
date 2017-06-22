package org.sofyan.ldapdemo;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepoImpl {

	@Autowired
	private LdapTemplate ldapTemplate;

	public void create(Person person) {
		DirContextAdapter context = new DirContextAdapter(buildDn(person));
		mapToContext(person, context);
		ldapTemplate.bind(context);
	}

	public void update(Person person) {
		Name dn = buildDn(person);
		DirContextOperations context = ldapTemplate.lookupContext(dn);
		mapToContext(person, context);
		ldapTemplate.modifyAttributes(context);
	}

	public void delete(Person person) {
		ldapTemplate.unbind(buildDn(person));
	}

	@SuppressWarnings("unchecked")
	public Person findByPrimaryKey(String fullName) {
		Name dn = buildDn( fullName );
		return (Person) ldapTemplate.lookup( dn, getContextMapper() );
	}

	@SuppressWarnings("unchecked")
	public List<Person> findByName(String name) {
		LdapQuery query = query().where("objectclass").is("person").and("cn").whitespaceWildcardsLike( name );

		return ldapTemplate.search( query, getContextMapper() );
	}

	@SuppressWarnings("unchecked")
	public List<Person> findAll() {
		LdapQuery query = query()
							.where("objectclass").is("person")
							.and("objectclass").is("organizationalPerson")
							.and("objectclass").is("top")
							.and("objectclass").is("inetOrgPerson");
		return ldapTemplate.search( LdapUtils.emptyLdapName() , query.filter().encode(), getContextMapper() );
	}

	@SuppressWarnings("rawtypes")
	protected ContextMapper getContextMapper() {
		return new PersonContextMapper();
	}

	protected Name buildDn(Person person) {
		return buildDn(person.getFullName());
	}

	protected Name buildDn(String fullname) {
		return LdapNameBuilder.newInstance().add("o", "sevenSeas")
											.add("ou", "people")
											.add("cn", fullname)
											.build();
	}

	protected void mapToContext(Person person, DirContextOperations context) {
		context.setAttributeValues("objectclass", new String[] { "person", 
				"top", "organizationalPerson", "inetOrgPerson" });
		context.setAttributeValue("givenname", person.getGivenName());
		context.setAttributeValue("description", person.getDescription());
		context.setAttributeValue("sn", person.getLastName());
		context.setAttributeValue("uid", person.getUuid());
		context.setAttributeValue("userpassword", person.getUserpassword());
	}

	private static class PersonContextMapper extends AbstractContextMapper<Person> {
		public Person doMapFromContext(DirContextOperations context) {
			Person person = new Person();
			person.setGivenName(context.getStringAttribute("givenname"));
			person.setFirstName(context.getStringAttribute("givenName"));
			person.setLastName(context.getStringAttribute("sn"));
			person.setUuid(context.getStringAttribute("uid"));
			person.setDescription(context.getStringAttribute("description"));
			return person;
		}
	}

}
