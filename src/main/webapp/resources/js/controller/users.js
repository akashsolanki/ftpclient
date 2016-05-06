'use strict';
	angularApp.controller('userCtrl',function($scope,$http,$rootScope) {
		console.log('check');
		$scope.users = [];
		
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