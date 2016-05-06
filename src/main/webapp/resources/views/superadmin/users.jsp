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
<table class="table table-striped table-condensed">
        
        <thead>
        <tr>
            <th style="min-width: 80px;">Username</th>
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
            <td><a href="#/edituser/{{user.username}}" class="btn btn-small btn-primary">edit</a></td>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_SUPER')">
            <td><button confirmed-click="deleteUser('{{user.username}}')" 
    ng-confirm-click="Are you sure Want to Delete the User ?" class="btn btn-small btn-danger">delete</button>
            </sec:authorize>
        </tr>
        </tbody>
    </table>
    <sec:authorize access="hasAnyRole('ROLE_SUPER')">
    <a href="#/newuser" class="btn btn-small">create new user</a>
    </sec:authorize>
</body>
</html>