'use strict';
	angularApp.controller('userCtrl',function($scope,$http,$rootScope) {
		console.log('check');
		$scope.users = [];
		$scope.editUserFlag = false;
		$scope.afterUpdate = false;
		$scope.getUsers = function(pageIndex){
			$http.get('./user/list').success(
					function(data) {
						$scope.users = data;
					}).error(function(data) {
				console.log("error: " + data);
			});
		};
		var init = function() {
			$scope.getUsers($scope.currentPageIndex);
			
			$scope.editUser = function(username){
				$scope.editUserFlag = true;
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
					$scope.editUserFlag = false;
					$("#alertDiv").show();
					$scope.afterUpdate = true;
					$http.post('./user/edit',$scope.user).success(
							function(data) {
								if(data.isSuccess)
									init();
								
							}).error(function(data) {
						console.log("error: " + data);
					});
					 window.setTimeout(function() {$scope.afterUpdate = false;$("#alertDiv").hide();}, 3000);
				};
				$scope.hide = function(){ $("#alertDiv").hide();
				};
				var init = function() {
					$scope.getUser(username);
					$scope.getRoles();
				};
				
				init();
				
			};
		};
		$scope.deleteUser = function(username){
			var data = $.param({
				
	        });
			$http.post('./user/delete/'+username).success(
					function(data) {
						if(data.isSuccess)
						{
						 init();	
							}
					}).error(function(data) {
				console.log("error: " + data);
			});
		};
		angular.element(document).ready(init);
		
		});