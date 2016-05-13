'use strict';
	angularApp.controller('userCtrl',function($scope,$http,$rootScope) {
		console.log('check');
		$scope.users = [];
		$scope.editUserFlag = false;
		$scope.afterUpdate = false;
		$scope.createUserFlag = false;
		$scope.newUser = {
				username : "",
				commonName : "",
				distinguishedName : "",
				roles : [],
				folders : null
			};
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
			$scope.showModal = false;
			$scope.createNewUser = function(){
				$scope.roles = [];
				$http.get('./user/roles/list').success(
						function(data) {
							$scope.roles = data;
						}).error(function(data) {
					console.log("error: " + data);
				});
			};
			
			$scope.createUser = function(){
				$scope.createUserFlag = true;
				alert(JSON.stringify($scope.newUser));
				
				$http.post('./user/create',$scope.newUser).success(
						function(data) {
							$scope.responseObject = data;
							$scope.newUserPassword = $scope.responseObject.returnObject;
							$scope.getUsers($scope.currentPageIndex);
							$scope.modalusername = $scope.newUser.username;
						}).error(function(data) {
					console.log("error: " + data);
				});
			};
			
			$scope.editUser = function(username){
				$scope.editUserFlag = true;
				$scope.createUserFlag=false;
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
				$scope.resetPassword = function(){
					$http.post('./user/reset',$scope.user).success(
							function(data) {
								$scope.responseObject = data;
								$scope.newUserPassword = $scope.responseObject.returnObject;
								$scope.modalusername = $scope.user.username;
							}).error(function(data) {
						console.log("error: " + data);
					});
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
		
		 $scope.toggleModal = function(btnClicked,action){
		        $scope.buttonClicked = btnClicked;
		        $scope.action = action;
		        $scope.showModal = !$scope.showModal;
		    };
		angular.element(document).ready(init);
		
		});