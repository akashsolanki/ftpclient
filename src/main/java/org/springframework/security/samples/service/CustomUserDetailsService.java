package org.springframework.security.samples.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.samples.data.User;
import org.springframework.security.samples.data.UserRepository;
import org.springframework.security.samples.data.UserRole;
import org.springframework.security.samples.data.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class CustomUserDetailsService implements  UserDetailsService {
     
	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository,UserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
	}
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // TODO Auto-generated method stub
         
        System.out.println("Entering LoginServiceMySqlImpl.loadUserByUsername: " + username);
        UserDetails userDetails = null;
         
        try
        {
        	Iterable<User> list = userRepository.findAll();
            org.springframework.security.samples.data.User user = userRepository.findByUsername(username);
             
            userDetails = new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), true,
                    true, true, true, getAuthorities(username));
        }
         
        catch (UsernameNotFoundException ex)
        {
            System.out.println(ex.toString());
            throw ex;
        }
         
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
         
        return userDetails;
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
}