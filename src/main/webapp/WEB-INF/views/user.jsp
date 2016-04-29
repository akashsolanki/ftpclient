<%@ page language="java" contentType="text/html;"
     %>
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
            <!-- Brand and toggle get grouped for better mobile display -->
           
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
             <a href="#menu" id="toggle"><span></span></a>
  <div id="menu" ng-controller="mainCtrl">
    <ul>
      <li><a href="#/createUser">Create</a></li>
      <li><a href="#/updateUser">Update</a></li>
      <li><a href="#/deleteUser">Delete</a></li>
    </ul>
  </div>
               
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container" style="margin-top:100px;" >
        <!-- Jumbotron Header -->
        <header class="jumbotron hero-spacer"> 
           <div>
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
     <script src="/resources/js/app.js">
    </script>
    <script src="/resources/js/controller/createuser.js"></script>
</body>
</html>