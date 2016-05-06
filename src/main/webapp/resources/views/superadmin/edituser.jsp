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
            <th style="min-width: 80px;">Username:{{user.username}}</th>
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
</body>
</html>