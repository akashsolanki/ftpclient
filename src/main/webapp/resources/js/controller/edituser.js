'use strict';
	angularApp.controller('editUserCtrl',function($scope,$http,$rootScope,$routeParams) {
		$scope.user = "";
		$scope.roles = "";
		
		$scope.getUser = function(username){
			$http.get('./user/get/'+username).success(
					function(data) {
						$scope.user = data;
					}).error(function(data) {
				console.log("error: " + data);
			});
		};
		$scope.getRoles = function(){
			$http.get('./user/roles/list').success(
					function(data) {
						$scope.roles = data;
					}).error(function(data) {
				console.log("error: " + data);
			});
		};
		$scope.update = function(){
			$http.post('./user/edit',$scope.user).success(
					function(data) {
						if(data.isSuccess)
							init();
						
					}).error(function(data) {
				console.log("error: " + data);
			});
		};
		var init = function() {
			$scope.getUser($routeParams.username);
			$scope.getRoles();
		};
		
		angular.element(document).ready(init);
		
		});