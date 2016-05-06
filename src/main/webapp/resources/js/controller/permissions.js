'use strict';
	angularApp.controller('permissionCtrl',function($scope,$http,$rootScope) {
		$scope.message = "Permissions Page";
		$scope.selectedFolder = [{"id":3,"name":"folder2","type":true,"path":"/folder2","parentId":1,"read":true,"write":false,"children":[]}];
		$scope.users = [];
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
						$scope.dataForTheTree = data;
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