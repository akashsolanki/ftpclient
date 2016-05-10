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

angularApp.directive('modal', function () {
    return {
      template: '<div class="modal fade">' + 
          '<div class="modal-dialog">' + 
            '<div class="modal-content">' + 
              '<div class="modal-header">' + 
                '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' + 
                '<h4 class="modal-title">{{ buttonClicked }}</h4>' + 
              '</div>' + 
              '<div class="modal-body" ng-transclude> </div>' + 
            '</div>' + 
          '</div>' + 
        '</div>',
      restrict: 'E',
      transclude: true,
      replace:true,
      scope:true,
      link: function postLink(scope, element, attrs) {
          scope.$watch(attrs.visible, function(value){
          if(value == true)
            $(element).modal('show');
          else
            $(element).modal('hide');
        });

        $(element).on('shown.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = true;
          });
        });

        $(element).on('hidden.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = false;
          });
        });
      }
    };
  });