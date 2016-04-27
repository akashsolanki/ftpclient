<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:sec="http://www.springframework.org/security/tags"
    xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0">
  <jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
  
  <jsp:output omit-xml-declaration="true" />
  <jsp:output doctype-root-element="HTML"
              doctype-system="about:legacy-compat" />
             
<html lang="en">

  <head>
    
    <title>Hello Security</title>
    <c:url var="faviconUrl" value="/resources/img/favicon.ico"/>
    <link rel="icon" type="image/x-icon" href="${faviconUrl}"/>
    <c:url var="bootstrapUrl" value="/resources/css/bootstrap.css"/>
    <link href="${bootstrapUrl}" rel="stylesheet"></link>
    <c:url var="bootstrapResponsiveUrl" value="/resources/css/bootstrap-responsive.css"/>
    <link href="${bootstrapResponsiveUrl}" rel="stylesheet"></link>
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
  </head>

  <body ng-app="AngularApp">
    <div class="container">
      <h1>This is secured!</h1>
      <p>
        Hello <b><c:out value="${pageContext.request.remoteUser}"/></b>---------------------
            </p>Greeting : ${greeting}
      <c:url var="logoutUrl" value="/logout"/>
      
	<form class="form-inline" action="${logoutUrl}" method="post">
          <input type="submit" value="Log out" />
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      </form>
      <div ng-controller="superCtrl">
      <sec:authorize access="hasRole('ROLE_SUPER')">
			Super Content Booott 
	 </sec:authorize>
	 </div>
	 <sec:authorize access="hasRole('ROLE_ADMIN')">
			Admin Content
	 </sec:authorize>
	  <sec:authorize access="hasRole('ROLE_USER')">
			User Content
	 </sec:authorize>
    </div>
  </body>
</html>
</jsp:root>
