<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
</head>
<body class="container">
<modal visible="showModal">
	
		<p>Username:{{modalusername}}</p>
		<p>Password:{{newUserPassword}}</p>
		<button class="btn btn-default" style="margin-left:40%;width:7%;" ng-click="toggleModal('','');">Ok</button>
  </modal>
<div style="width:60%;display:inline-block;background-color: #e3e7e8;" class="jumbotron">
<p class="lead">List of Users</p> <hr>
<sec:authorize access="hasAnyRole('ROLE_SUPER')">
    <a title="Create new user" class="pull-right btn btn-sm btn-primary glyphicon glyphicon-plus" ng-click="createNewUser();createUserFlag=true;editUserFlag=false;"></a>
    </sec:authorize>
    <input type="text" placeholder="Search..." class="form-control" style="width:35%" ng-model="query.username"/> </br>
<table class="table  table-condensed table-hover">
        
        <thead>
        <tr>
            <th style="min-width: 80px;"></th>
           <sec:authorize access="hasAnyRole('ROLE_SUPER')">
            <th style="width:20px;"> </th>
            </sec:authorize>
            <th style="width:20px;"> </th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="user in users | filter : query">
            <td>{{user.username}}</td>
            <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
            <td><a  ng-click="editUser(user.username);" class="btn btn-small btn-primary glyphicon glyphicon-edit"></a></td>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_SUPER')">
            <td><button confirmed-click="deleteUser('{{user.username}}')" 
    ng-confirm-click="Are you sure Want to Delete the User ?" class="btn btn-small btn-danger glyphicon glyphicon-remove"></button>
            </sec:authorize>
        </tr>
        </tbody>
    </table>
    </div>
    
    
    <div style="width:35%;display:inline-block;float:right;background-color: #e3e7e8;" class="jumbotron" ng-show="editUserFlag">
    <p class="lead">Edit User</p> <hr>
    	<table class="table table-striped table-condensed">
        <thead>
        
        </thead>
        <tbody>
        <tr>
            <td><select id="rolesList" ng-model="user.roles" ng-options="role.roleName for role in roles track by role.roleId" multiple >
  </select>
    </td>
        </tr>
        </tbody>
    </table>
    <sec:authorize access="hasAnyRole('ROLE_SUPER')">
    <button ng-click="update()" 
     class="btn btn-small btn-danger">update</button>
     <button ng-click="resetPassword();toggleModal('New password ');" 
     class="btn btn-small btn-primary">Reset Password</button>
    </sec:authorize>
    </div>
    
    <sec:authorize access="hasAnyRole('ROLE_SUPER')">
     <div style="width:35%;display:inline-block;float:right;background-color: #e3e7e8;" class="jumbotron" ng-show="createUserFlag">
    <p class="lead">Create User</p> <hr>
    <input type="text" class="form-control" placeholder="Username.." ng-model="newUser.username"/></br>
    <table class="table table-striped table-condensed">
        <thead>
        
        </thead>
        <tbody>
        <tr>
            <td><select ng-model="newUser.roles" ng-options="role.roleName for role in roles track by role.roleId" multiple>
  </select>
    </td>
        </tr>
        </tbody>
    </table>
    <button ng-click="createUser();toggleModal('Details of new User');" 
     class="btn btn-small btn-primary">Create</button>
      </div>
    </sec:authorize>
    
   
    <div id="alertDiv" class="alert alert-success fade in" ng-show="afterUpdate">
     <a class="close" ng-click="hide();" aria-label="close">&times;</a>
    <strong>Updated!</strong> Roles have been saved.
  </div>
</body>
</html>