'use strict';
	angularApp.controller('fileCtrl',function($scope,$http,$rootScope) {
		$scope.selectedFolder = [];
		$scope.foldername = "";
		$scope.deleteAction = false;
		$scope.getFolderList = function(){
			$http.get('./folder/folderlist').success(
					function(data) {
						$scope.dataForTheTree = angular.copy(data);
					}).error(function(data) {
				console.log("error: " + data);
			});
		};
		$scope.folderActions = function(foldername,action){
			$scope.foldername = "";
				$http.post('./folder/'+action+'/'+foldername,$scope.selectedFolder).success(
						function(data) {
							init();
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
		
		   $scope.showModal = false;
		    $scope.buttonClicked = "";
		    $scope.toggleModal = function(btnClicked,action){
		        $scope.buttonClicked = btnClicked;
		        $scope.action = action;
		        $scope.showModal = !$scope.showModal;
		    };
		});
