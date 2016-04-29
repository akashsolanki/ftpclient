package org.springframework.security.samples.config.test;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.ldap.LdapContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.samples.vo.UserVO;

public class ADContext {

	static DirContext ldapContext;
	  public static void main (String[] args) throws NamingException
	  {
	    try
	    {
	    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
	    	System.out.println(encoder.encode("user"));
	      System.out.println("started to connect");
	
	    	try{
	    		
	    		LdapContext conn = ActiveDirectory.getConnection("pegasus","Welcome@1234","ggktech.local","ldap://172.16.0.18:389");
	    	   // ActiveDirectory.getUser("bob", conn).changePassword("password", "NewPassword!", true, conn);
	    	    List<UserVO> users = ActiveDirectory.getUsers(conn);
	    	    
	    	    for(UserVO user:users)
	    	    {
	    	    	System.out.println(user.getUsername());
	    	    }
	    	    conn.close();
	    	    System.out.println("Success!");
	    	}
	    	catch(Exception e){
	    	    //Failed to authenticate user or change password...
	    	    e.printStackTrace();
	    	}
/*
	      Hashtable<String, String> ldapEnv = new Hashtable<String, String>(11);
	      ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	      //ldapEnv.put(Context.PROVIDER_URL,  "ldap://societe.fr:389");
	      ldapEnv.put(Context.PROVIDER_URL,  "ldap://172.16.0.18:389");
	      ldapEnv.put(Context.SECURITY_AUTHENTICATION, );
	      //ldapEnv.put(Context.SECURITY_PRINCIPAL, "cn=administrateur,cn=users,dc=societe,dc=fr");
	      ldapEnv.put(Context.SECURITY_PRINCIPAL, "CN=subrahmanyam.yepuri,OU=Users,OU=GGK_WaveRock,DC=ggktech,DC=local");
	      ldapEnv.put(Context.SECURITY_CREDENTIALS, "Sushmitha@7");
	      //ldapEnv.put(Context.SECURITY_PROTOCOL, "ssl");
	      //ldapEnv.put(Context.SECURITY_PROTOCOL, "simple");
	      ldapContext = new InitialDirContext(ldapEnv);

	      // Create the search controls         
	      SearchControls searchCtls = new SearchControls();

	      //Specify the attributes to return
	      String returnedAtts[]={"sn","givenName", "samAccountName"};
	      searchCtls.setReturningAttributes(returnedAtts);

	      //Specify the search scope
	      searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

	      //specify the LDAP search filter
	      String searchFilter = "(&(objectClass=user))";

	      //Specify the Base for the search
	      String searchBase = "dc=dom,dc=fr";
	      //initialize counter to total the results
	      int totalResults = 0;

	      // Search for objects using the filter
	      NamingEnumeration<SearchResult> answer = ldapContext.search(searchBase, searchFilter, searchCtls);

	      //Loop through the search results
	      while (answer.hasMoreElements())
	      {
	        SearchResult sr = (SearchResult)answer.next();

	        totalResults++;

	        System.out.println(">>>" + sr.getName());
	        Attributes attrs = sr.getAttributes();
	        System.out.println(">>>>>>" + attrs.get("samAccountName"));
	      }

	      System.out.println("Total results: " + totalResults);
	      ldapContext.close();
*/	    }
	    catch (Exception e)
	    {
	      System.out.println(" Search error: " + e);
	      e.printStackTrace();
	      System.exit(-1);
	    }
	  }
	  

}
