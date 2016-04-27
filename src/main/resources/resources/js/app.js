var angularApp = angular.module('AngularApp', []);

angularApp.controller("superCtrl",function($scope){
	$scope.message = "Welcome, Mr. Super";
});