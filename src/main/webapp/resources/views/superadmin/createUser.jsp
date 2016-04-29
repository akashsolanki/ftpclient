<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body class="container">
<h4>Create User</h4>
<form role="form" name="userForm">
	<div class="form-group">
	<div class="row">
	 <div class="col-xs-3">
	 <label for="inputdefault">User name</label>
    <input class="form-control" ng-model="username" id="inputdefault" type="text" value="" size="20">
    </div>
    </div>
    <div class="row">
     <div class="col-xs-3">
	 <label for="inputdefault">Password</label>
    <input class="form-control" ng-model="password" id="inputdefault" type="password" value="" size="20">
	</div>
	</div>
	 <div class="row">
     <div class="col-xs-3">
	 <label for="sel1">Roles</label>
  <select class="form-control" id="sel1" ng-model="role">
  <option>Admin</option>
    <option>User</option>
  </select>
	</div>
	</div>
	<div class="row">
	<div class="col-xs-2 mrgTop">
	<input type="submit" value="Submit" class="btn btn-default"/>
	</div>
		
		
	<div class="col-xs-3 mrgTop">
	<input type="button" value="Reset" class="btn btn-default" ng-click="resetForm()"/>
	</div>
		</div>
		</div>
</form>
</body>
</html>