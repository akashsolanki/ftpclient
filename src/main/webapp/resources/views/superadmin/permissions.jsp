<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
  .custom-fa input[type="checkbox"] {position:absolute;left:-999px;}

.custom-fa input[type="checkbox"]:focus + span{color:#ffffff;}

.custom-fa label{cursor: pointer;}

.custom-fa label input[type="checkbox"] + span:before { 

    font-family: FontAwesome;

    content: "\f096";  

    display: inline-block;

    font-weight: 400;

    font-style: normal;

    font-size: 1.4em;

    text-align:center;

    letter-spacing: 6px;

  }

.custom-fa label input[type="checkbox"]:checked + span:before { content: "\f046";letter-spacing: 3px; } /* checked icon */

.custom-fa label input[type="checkbox"]:indeterminate + span:before{content: "\f147";letter-spacing: 6px;}






</style>
<script>
$("[type=checkbox]").change(function () {
    $checkbox = $(this)
    $icon = $checkbox.siblings("[class*=icon-]")

    checked = $checkbox.is(":checked")

    $icon.toggleClass('icon-check', checked)
        .toggleClass('icon-check-empty', !checked)
});
</script>
<title>Users</title>
</head>
<body class="container">
<table class="table table-striped table-condensed">
        <tbody>
<!-- <tr><td>{{selectedFolder}}</td></tr> -->
        <tr>
            <td><select class="form-control" style="width:50%" ng-change="onUserSelect();" ng-model="currentUser" ng-options="user.username for user in users">
  </select>
    </td>    <td><treecontrol class="tree-classic"
   tree-model="dataForTheTree"
   options="treeOptions"
   on-selection="showSelected(node, selected, $parentNode, $index, $first, $middle, $last, $odd, $even, $path)"
   selected-nodes="selectedFolder">
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
   <b>{{node.name}}</b>  
		<span class="label label-primary custom-fa">
		    <label for="readCheck{{node.id}}"><input id="readCheck{{node.id}}" type="checkbox" ng-model="node.read" /><span> Read</span></label>
		  </span> &nbsp;
		<span class="label label-primary custom-fa">
		    <label for="writeCheck{{node.id}}"><input id="writeCheck{{node.id}}" type="checkbox" ng-model="node.write" ng-click="node.read=true"/><span> Write</span></label></span>
		    </span>
</treecontrol>
    </td>
        </tr>
        </tbody>
    </table>
    <sec:authorize access="hasAnyRole('ROLE_SUPER','ROLE_ADMIN')">
    <button ng-click="addPerm()" 
     class="btn btn-small btn-danger">Add Permissions</button>
    </sec:authorize>
</body>
</html>