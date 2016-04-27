package org.springframework.security.samples.config.test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

/*************************************************************************************
 * Maps Spring Security GrantedAuthorities 
 * e.g. AD groups populated using LdapAuthoritiesPopulator mapped to fixed role names
 * as defined in a Map instance (e.g. populated from a property file)
 * 
 * Sample roleMap:
 * Key          Value
 * Group1       ROLE_USER
 * Group2       ROLE_ADMIN
 * Group3       ROLE_ADMIN,ROLE_USER
 *************************************************************************************/
public class MapBasedGrantedAuthorityMapper implements GrantedAuthoritiesMapper {
    private Map<String,String> roleMap;
    private String stringSeparator = ",";
    private SimpleGrantedAuthority unknownAuthorithy = new SimpleGrantedAuthority("ROLE_UNKNOWN");
    private boolean keepUnknownAuthorities = false;

    private static Logger logger = Logger
            .getLogger(GrantedAuthoritiesMapper.class.toString());

    public MapBasedGrantedAuthorityMapper(){
        
    }
    public MapBasedGrantedAuthorityMapper(Map<String,String> roleMap){
        this.roleMap = roleMap;
    }

    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
        String[] mappedValues; 
        Set<GrantedAuthority> mapped = new HashSet<GrantedAuthority>(authorities.size());
        for (GrantedAuthority auth : authorities) {
            if (roleMap==null || roleMap.containsKey(auth.getAuthority()) ) {
                mappedValues = StringUtils.split(roleMap.get(auth.getAuthority()),stringSeparator);
                for (String mappedValue: mappedValues) {                    
                    mapped.add(new SimpleGrantedAuthority(StringUtils.trimToEmpty(mappedValue)));
                }
            } else if (keepUnknownAuthorities){ 
                mapped.add(auth);
            } else if (unknownAuthorithy != null){
                mapped.add(unknownAuthorithy);
            }
        }       
        return mapped;
    }
             // getters and setters

}


    
