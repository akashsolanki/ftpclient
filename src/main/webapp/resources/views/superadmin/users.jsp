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
<div style="width:60%;display:inline-block;background-color: #e3e7e8;" class="jumbotron">
<sec:authorize access="hasAnyRole('ROLE_SUPER')">
    <a href="#/newuser" title="Create new user" class="pull-right btn btn-sm btn-primary glyphicon glyphicon-plus"></a>
    </sec:authorize>
    <input type="text" placeholder="Search..." class="form-control" style="width:25%"/> </br>
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
        <tr ng-repeat="user in users">
            <td>{{user.username}}</td>
            <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
            <td><a  ng-click="editUser(user.username)" class="btn btn-small btn-primary glyphicon glyphicon-edit"></a></td>
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
    	<table class="table table-striped table-condensed">
        <thead>
        <tr>
            <th style="min-width: 80px;" class="lead">Username:{{user.username}}</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><select ng-model="user.roles" ng-options="role.roleName for role in roles track by role.roleId" multiple>
  </select>
    </td>
        </tr>
        </tbody>
    </table>
    <sec:authorize access="hasAnyRole('ROLE_SUPER')">
    <button ng-click="update()" 
     class="btn btn-small btn-danger">update</button>
    </sec:authorize>
    </div>
</body>
</html>