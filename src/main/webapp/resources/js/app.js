var angularApp = angular.module('myApp', ['ngRoute']);

angularApp.controller("mainCtrl",function($scope){
	$scope.username = "Welcome";
});