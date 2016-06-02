<%@ page language="java" contentType="text/html;" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
     <link href="/resources/css/bootstrap.css" rel="stylesheet"/>
     <link rel="stylesheet" href="/resources/css/normalize.css">
     <link rel="stylesheet" href="/resources/css/login.css">
     <script>
     var nAgt = navigator.userAgent;
     if ((verOffset=nAgt.indexOf("MSIE"))!=-1) {
    	 alert("IE doesn't support the application..");
    	}
     </script>
 </head>

  <body>
    <div class="login">
		<h1><b>WebFTP</b></h1>
		<c:choose>
    <c:when test="${param.error ne null}">
       <div class="alert alert-danger">    
                    Invalid username and password.
        </div>
    </c:when>
    <c:when test="${param.logout ne null}">
    <div class="alert alert-success">    
                     Successfully.. Logged out..
        </div>
       
    </c:when>
    
</c:choose>
		<form name="f" action="/login" method="post">               
        <input type="text" id="username" name="username" placeholder="Username" required="required" />
		<input type="password" id="password" name="password" placeholder="Password" required="required" />		
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary btn-block btn-large">Log in</button>
                </div>
        </form>
    </div>
    
  </body>
  
</html>
