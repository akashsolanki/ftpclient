var angularApp = angular.module('myApp', ['ngRoute']);

angularApp.controller("mainCtrl",function($scope){
	$scope.username = "Welcome";
});

angularApp.config([ '$routeProvider',  function($routeProvider){
    $routeProvider
    	.when('/createUser',{
    		templateUrl: 'resources/views/superadmin/createUser.jsp',
    		controller: 'createUserCtrl'
    	}).when('/updateUser',{
    		templateUrl: 'resources/views/superadmin/updateUser.jsp',
    		controller: 'updateUserCtrl'
    	}).when('/deleteUser',{
    		templateUrl: 'resources/views/superadmin/deleteUser.jsp',
    		controller: 'deleteUserCtrl'
    	});
    	

    
}]).run(function($rootScope) {
	
});