var angularApp = angular.module('myApp', ['ngRoute','treeControl']);

angularApp.controller("mainCtrl",function($scope,$rootScope){
});
angularApp.directive('ngConfirmClick', [
                                 function(){
                                     return {
                                         link: function (scope, element, attr) {
                                             var msg = attr.ngConfirmClick || "Are you sure?";
                                             var clickAction = attr.confirmedClick;
                                             element.bind('click',function (event) {
                                                 if ( window.confirm(msg) ) {
                                                     scope.$eval(clickAction)
                                                 }
                                             });
                                         }
                                     };
                             }])
angularApp.config([ '$routeProvider','$httpProvider',  function($routeProvider,$httpProvider){
	$routeProvider
    	.when('/users',{
    		templateUrl: 'resources/views/superadmin/users.jsp',
    		controller: 'userCtrl'
    	}).when('/permissions',{
    		templateUrl: 'resources/views/superadmin/permissions.jsp',
    		controller: 'permissionCtrl'
    	}).when('/edituser/:username',{
    		templateUrl: 'resources/views/superadmin/edituser.jsp',
    		controller: 'editUserCtrl'
    	}).when('/files',{
    		templateUrl: 'resources/views/superadmin/files.jsp',
    		controller: 'fileCtrl'
    	});
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    
}]).run(function($rootScope) {

});