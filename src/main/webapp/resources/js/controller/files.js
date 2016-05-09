'use strict';
	angularApp.controller('fileCtrl',function($scope,$http,$rootScope) {
		$scope.selectedFolder = [];
		
		$scope.getFolderList = function(){
			$http.get('./folder/folderlist').success(
					function(data) {
						$scope.dataForTheTree = angular.copy(data);
					}).error(function(data) {
				console.log("error: " + data);
			});
		};
		$scope.createFolder = function(){
			$http.post('./folder/create/testfolder',$scope.selectedFolder).success(
					function(data) {
						$scope.dataForTheTree = angular.copy(data);
					}).error(function(data) {
				console.log("error: " + data);
			});
		};
		var init = function() {
			$scope.getFolderList();
		};
		angular.element(document).ready(init);
		$scope.treeOptions = {
			    nodeChildren: "children",
			    multiSelection: false
			};
		});