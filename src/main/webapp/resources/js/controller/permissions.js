'use strict';
	angularApp.controller('permissionCtrl',function($scope,$http,$rootScope) {
		$scope.message = "Permissions Page";
		$scope.selectedFolder = [];
		$scope.users = [];
		$scope.initDataForTheTree = "";
		$scope.currentUser="";
		
		$scope.getUsers = function(pageIndex){
			$http.get('./user/list').success(
					function(data) {
						$scope.users = data;
					}).error(function(data) {
				console.log("error: " + data);
			});
		};
		$scope.getTree = function(){
			$http.get('./folder/list').success(
					function(data) {
						$scope.initDataForTheTree = angular.copy(data);
						$scope.dataForTheTree = angular.copy(data);
					}).error(function(data) {
				console.log("error: " + data);
			});
		};
		$scope.addPerm = function(){
			$scope.currentUser.folders = $scope.selectedFolder;
			$http.post('./folder/saveUserFile',$scope.currentUser).success(
					function(data) {
						if(data.isSuccess)
							{
							alert("Updated Successfully...");
							}
					}).error(function(data) {
				console.log("error: " + data);
			});
		};
		var init = function() {
			$scope.getUsers($scope.currentPageIndex);
			$scope.getTree();
		};
		angular.element(document).ready(init);
		$scope.treeOptions = {
			    nodeChildren: "children",
			    multiSelection: true
			   
			};
		$scope.onUserSelect = function(){
			$http.get('./folder/userlist/'+$scope.currentUser.username).success(
					function(data) {
						$scope.dataForTheTree = angular.copy($scope.initDataForTheTree);
						$scope.selectedFolder = data;
					}).error(function(data) {
				console.log("error: " + data);
			});
			};
		
		
		$scope.showToggle = function(node, expanded, $parentNode, $index, $first, $middle, $last, $odd, $even, $path) {
	         var parent = $parentNode?("child of: " + $parentNode.label):"root node";
	         var location = $first?"first":($last?"last":("middle node at index " + $index));
	         var oddEven = $odd?"odd":"even";
	         var path = $path().map(function(node) {return node.label});
	         $("#events-listing").append(""+node.label+ (expanded?" expanded":" collapsed") +" (" + parent + ", " + location +", " + oddEven + ", ["+ path+"])");
	     };
		$scope.showSelected = function(node, selected, $parentNode, $index, $first, $middle, $last, $odd, $even, $path) {
	         var parent = $parentNode?("child of: " + $parentNode.label):"root node";
	         var location = $first?"first":($last?"last":("middle node at index " + $index));
	         var oddEven = $odd?"odd":"even";
	         var path = $path().map(function(node) {return node.label});
	         $("#events-listing").append(""+node.label+ (selected?" selected":" deselected") +" (" + parent + ", " + location +", " + oddEven + ", ["+ path +"])");
	     };
		});