package org.sofyan.ldapdemo;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class GroupRepoImpl {

	@Autowired
    private LdapTemplate ldapTemplate;

    public void addMemberToGroup(String groupName, Person p) {
        Name groupDn = buildGroupDn(groupName);
        Name userDn = buildPersonDn(
            p.getFullName());

        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        ctx.addAttributeValue("uniquemember", userDn);

        ldapTemplate.modifyAttributes(ctx);
    }

    public void removeMemberFromGroup(String groupName, Person p) {
        Name groupDn = buildGroupDn(groupName);
        Name userDn = buildPersonDn(
            p.getFullName());

        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        ctx.removeAttributeValue("uniquemember", userDn);

        ldapTemplate.modifyAttributes(ctx);
    }

    private Name buildGroupDn(String groupName) {
        return LdapNameBuilder.newInstance("ou=crews,ou=groups,o=sevenSeas")
            .add("cn", groupName).build();
    }

    private Name buildPersonDn(String fullname) {
    	return LdapNameBuilder.newInstance().add("o", "sevenSeas")
				.add("ou", "people")
				.add("cn", fullname)
				.build();
   }
}
