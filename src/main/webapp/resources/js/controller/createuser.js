'use strict';
	angularApp.controller('createUserCtrl',['$scope', function($scope) {
		$scope.message = "Create User Page";
		
		$scope.resetForm = function() {
			  $scope.username = "";
			  $scope.password = "";
			}
		
	
		}]);