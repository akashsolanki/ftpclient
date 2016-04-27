package org.springframework.security.samples.config.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.samples.data.UserRepository;
import org.springframework.security.samples.data.UserRole;
import org.springframework.security.samples.data.UserRoleRepository;

public class UserDetails extends LdapUserDetailsMapper {
	
	private LdapTemplate ldapTemplate;
	
	private UserRoleRepository userRoleRepository;

	private String[] userAttributes = {
	        "distinguishedName","cn","name","uid",
	        "sn","givenname","memberOf","samaccountname",
	        "userPrincipalName"
	    };
	public UserDetails(UserRoleRepository userRoleRepository) {
		// TODO Auto-generated constructor stub
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	protected String mapPassword(Object passwordValue) {
		// TODO Auto-generated method stub
		return super.mapPassword(passwordValue);
	}
	
	@Override
	protected GrantedAuthority createAuthority(Object role) {
		// TODO Auto-generated method stub
		return super.createAuthority(role);
	}
	@Override
	public org.springframework.security.core.userdetails.UserDetails mapUserFromContext(DirContextOperations ctx,
			String username, Collection<? extends GrantedAuthority> authorities) {
		try {
			authorities = getAuthorities(username);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Object passwordValue = ctx.getObjectAttribute("userPassword");
		
		// TODO Auto-generated method stub
		return super.mapUserFromContext(ctx, username, authorities);
	}
	 public Collection<GrantedAuthority> getAuthorities(String username) {
         
	        System.out.println("Entering LoginServiceMySqlImpl.getAuthorities");
	     
	        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
	         
	        Iterator<UserRole> it = userRoleRepository.findByUsername(username).iterator();
	         
	        while(it.hasNext())
	        {
	            UserRole roleMap = it.next();
	            authList.add(new SimpleGrantedAuthority(userRoleRepository.roleByRoleID(roleMap.getRoleId())));
	        }
	         
	    return authList;
	    }
	 private String toDC(String domainName) {
	        StringBuilder buf = new StringBuilder();
	        for (String token : domainName.split("\\.")) {
	            if(token.length()==0)   continue;   // defensive check
	            if(buf.length()>0)  buf.append(",");
	            buf.append("DC=").append(token);
	        }
	        return buf.toString();
	    }
	@Override
	public void mapUserToContext(org.springframework.security.core.userdetails.UserDetails user,
			DirContextAdapter ctx) {
		System.out.println(user.getPassword()+"------------------");
		// TODO Auto-generated method stub
		super.mapUserToContext(user, ctx);
	}
	@Override
	public void setPasswordAttributeName(String passwordAttributeName) {
		// TODO Auto-generated method stub
		super.setPasswordAttributeName(passwordAttributeName);
	}
	

}
