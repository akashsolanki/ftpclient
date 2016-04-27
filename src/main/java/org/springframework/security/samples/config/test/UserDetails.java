package org.springframework.security.samples.config.test;

import java.util.Collection;

import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

public class UserDetails extends LdapUserDetailsMapper {
	
	private LdapTemplate ldapTemplate;
	
	private String[] userAttributes = {
	        "distinguishedName","cn","name","uid",
	        "sn","givenname","memberOf","samaccountname",
	        "userPrincipalName"
	    };
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
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Object passwordValue = ctx.getObjectAttribute("userPassword");
		
		// TODO Auto-generated method stub
		return super.mapUserFromContext(ctx, username, authorities);
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
