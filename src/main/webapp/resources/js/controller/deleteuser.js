'use strict';
	angularApp.controller('deleteUserCtrl',['$scope', function($scope) {
		$scope.message = "Delete User Page";
		
		$scope.resetForm = function() {
			  $scope.username = "";
			  $scope.password = "";
			}
		
	
		}]);