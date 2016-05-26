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

      <div class="outline" ng-show="action=='upload'">
      <input type="file" id="file" class="dropzone" onchange="angular.element(this).scope().filesChanged(this)" multiple ng-show="action=='upload'"/>
      <label for="file"><strong>Choose files</strong><span class="box__dragndrop"> or drag them here</span>.</label>
      <br/>
      <span ng-repeat="file in files" style="padding-left:10px">
    	<strong>({{file.name}}){{$index!=files.length-1?',':''}}</strong> 
		</span>

      </div>
      <input type="text" placeholder="{{buttonClicked}}" ng-model="foldername" ng-show="action!='delete' && action!='upload'"/>
      <button ng-click="folderActions(foldername,action)" class="btn btn-default" data-dismiss="modal" aria-hidden="true">Submit</button>

	
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
            <li><a ng-click="toggleModal('Select File to Upload','upload')">Upload</a></li>
        </ul>
    </div>
</div>
    </td>    <td><treecontrol class="tree-classic"
   tree-model="dataForTheTree"
   options="treeOptions"
   on-selection="showSelected(node, selected, $parentNode, $index, $first, $middle, $last, $odd, $even, $path)"
   selected-node="selectedFolder">
   {{node.name}}  
          <span ng-click="downloadFile(node.id)" class="glyphicon glyphicon-download"></span>
        
</treecontrol>
    </td>
        </tr>
        </tbody>
    </table>
</body>
</html>