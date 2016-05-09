<%@ page language="java" contentType="text/html;"
     %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="/resources/css/menu.css" rel="stylesheet"/>
<link href="/resources/css/bootstrap.css" rel="stylesheet"/>
</head>
<body class="" ng-app="myApp">
  <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
             <a href="#menu" id="toggle"><span></span></a>
  <div id="menu" ng-controller="mainCtrl">
    <ul>
    
    <sec:authorize access="hasAnyRole('ROLE_SUPER','ROLE_ADMIN')">
      <li><a href="#/users" onclick="$('#toggle').removeClass('on');">Users</a></li>
     </sec:authorize>
     <sec:authorize access="hasAnyRole('ROLE_SUPER','ROLE_ADMIN')">
      <li><a href="#/permissions"  onclick="$('#toggle').removeClass('on');">Permissions</a></li>
      </sec:authorize>
      <sec:authorize access="hasRole('ROLE_USER')">
      <li><a href="#/files"  onclick="$('#toggle').removeClass('on');">Files</a></li>
      </sec:authorize>
      <li><a href="#/myaccount"  onclick="$('#toggle').removeClass('on');">My Profile</a></li>
    </ul>
     <c:url var="logoutUrl" value="/logout"/>
    <form class="form-inline" action="${logoutUrl}" method="post">
          <input type="submit" value="Log out" />
      </form>
  </div>
               
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container" style="margin-top:100px;" >
        <header class="jumbotron hero-spacer"> 
           <div>
           <p>
        Hello <b><c:out value="${pageContext.request.remoteUser}"/></b>
            </p>FreeSpace: ${freespace} 
     			<div data-ng-view></div>
			</div>
        </header>

        <hr>


  <script src="/resources/js/shared/menu.js">
    </script>
    <script src="/resources/js/shared/jquery.min.js">
    </script>
    <script src="/resources/js/shared/bootstrap.min.js">
    </script>
    <script src="/resources/js/shared/angular.min.js"></script>
    <script src="/resources/js/shared/angular-route.min.js"></script>
    <script src="/resources/js/shared/angular-sanitize.js"></script>
    <script type="text/javascript" src="/resources/js/shared/angular-tree-control.js"></script>
<!-- link for CSS when using the tree as a Dom element -->
<link rel="stylesheet" type="text/css" href="/resources/css/tree-control.css">
<!-- link for CSS when using the tree as an attribute -->
<link rel="stylesheet" type="text/css" href="/resources/css/tree-control-attribute.css">
     <script src="/resources/js/app.js">
    </script>
    <script src="/resources/js/controller/users.js"></script>
    <script src="/resources/js/controller/edituser.js"></script>
    <script src="/resources/js/controller/permissions.js"></script>
    <script src="/resources/js/controller/files.js"></script>
</body>
</html>