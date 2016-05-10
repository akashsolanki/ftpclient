<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
</head>
<body class="container" ng-controller="fileCtrl">
 
  <modal visible="showModal">
      <input type="text" placeholder="{{buttonClicked}}" ng-model="foldername" ng-show="action!='delete'"/></br></br>
      <button ng-click="action!='delete' && folderActions(foldername,action)" class="btn btn-default" data-dismiss="modal" aria-hidden="true">Submit</button>
  </modal>
<table class="table table-striped table-condensed">
        <tbody>
<!--         <tr><td>{{selectedFolder}}</td></tr> -->
        <tr>
             <td>
     <div class="">
   <div class="dropdown">
        <button  data-toggle="dropdown" class="btn btn-default dropdown-toggle">Actions <b class="caret"></b></button>
        <ul class="dropdown-menu">
            <li><a ng-click="toggleModal('Enter Folder Name','create')">Create</a></li>
            <li><a ng-click="toggleModal('Are you sure you want to delete?','delete')">Delete</a></li>
            <li><a ng-click="toggleModal('Enter new name','rename')">Rename</a></li>
            <li><a >Upload</a></li>
        </ul>
    </div>
</div>
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