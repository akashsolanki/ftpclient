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
        <tbody>
<!--         <tr><td>{{selectedFolder}}</td></tr> -->
        <tr>
            <td><button ng-click="createFolder()" 
     class="btn btn-small btn-danger">createFolder</button><button ng-click="rename()" 
     class="btn btn-small btn-danger">Rename</button>
    </td>    <td><treecontrol class="tree-classic"
   tree-model="dataForTheTree"
   options="treeOptions"
   on-selection="showSelected(node, selected, $parentNode, $index, $first, $middle, $last, $odd, $even, $path)"
   selected-node="selectedFolder">
    <span ng-switch="" on="node.type">
             <span ng-switch-when="folder" class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>
             <span ng-switch-when="pic" class="glyphicon glyphicon-picture" aria-hidden="true"></span>
             <span ng-switch-when="doc" class="glyphicon glyphicon-file" aria-hidden="true"></span>
             <span ng-switch-when="file" class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
             <span ng-switch-when="movie" class="glyphicon glyphicon-film" aria-hidden="true"></span>
             <span ng-switch-when="email" class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
             <span ng-switch-when="home" class="glyphicon glyphicon-home" aria-hidden="true"></span>
             <span ng-switch-when="trash" class="glyphicon glyphicon-trash" aria-hidden="true"></span>
         </span>
   {{node.name}}  
</treecontrol>
    </td>
        </tr>
        </tbody>
    </table>
    
    
    
</body>
</html>