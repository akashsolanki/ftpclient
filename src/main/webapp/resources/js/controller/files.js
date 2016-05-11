'use strict';


angularApp.controller('fileCtrl',function($scope,$http,$rootScope) {
	
		$scope.selectedFolder = [];
		$scope.foldername = "";
		$scope.deleteAction = false;
		$scope.getFolderList = function(){
			$http.get('./folder/folderlist').success(
					function(data) {
						$scope.dataForTheTree = angular.copy(data);
					}).error(function(data) {
				console.log("error: " + data);
			});
		};
		
		$scope.filesChanged = function(ele){
			console.log(ele.files);
			console.log($scope.files);
			if($scope.files==undefined)
				{
				$scope.files = [];
				}
			angular.forEach(ele.files, function(file, key) {
					console.log(file);
					$scope.files.push(file);  
					});
			$scope.$apply();
		}
		
		$scope.downloadFile = function() {
		    // Use an arraybuffer
		    $http.get('./folder/download/'+$scope.selectedFolder.id, { responseType: 'arraybuffer' })
		    .success( function(data, status, headers) {

		        var octetStreamMime = 'application/octet-stream';
		        var success = false;

		        // Get the headers
		        headers = headers();
console.log(headers['content-disposition'].split(/["']/g)[1].replace(/["']/g, ""));
		        // Get the filename from the x-filename header or default to "download.bin"
		        var filename = headers['content-disposition'].split(/["']/g)[1].replace(/["']/g, "") || 'download.bin';

		        // Determine the content type from the header or default to "application/octet-stream"
		        var contentType = headers['content-type'] || octetStreamMime;

		        try
		        {
		            // Try using msSaveBlob if supported
		            console.log("Trying saveBlob method ...");
		            var blob = new Blob([data], { type: contentType });
		            if(navigator.msSaveBlob)
		                navigator.msSaveBlob(blob, filename);
		            else {
		                // Try using other saveBlob implementations, if available
		                var saveBlob = navigator.webkitSaveBlob || navigator.mozSaveBlob || navigator.saveBlob;
		                if(saveBlob === undefined) throw "Not supported";
		                saveBlob(blob, filename);
		            }
		            console.log("saveBlob succeeded");
		            success = true;
		        } catch(ex)
		        {
		            console.log("saveBlob method failed with the following exception:");
		            console.log(ex);
		        }

		        if(!success)
		        {
		            // Get the blob url creator
		            var urlCreator = window.URL || window.webkitURL || window.mozURL || window.msURL;
		            if(urlCreator)
		            {
		                // Try to use a download link
		                var link = document.createElement('a');
		                if('download' in link)
		                {
		                    // Try to simulate a click
		                    try
		                    {
		                        // Prepare a blob URL
		                        console.log("Trying download link method with simulated click ...");
		                        var blob = new Blob([data], { type: contentType });
		                        var url = urlCreator.createObjectURL(blob);
		                        link.setAttribute('href', url);

		                        // Set the download attribute (Supported in Chrome 14+ / Firefox 20+)
		                        link.setAttribute("download", filename);

		                        // Simulate clicking the download link
		                        var event = document.createEvent('MouseEvents');
		                        event.initMouseEvent('click', true, true, window, 1, 0, 0, 0, 0, false, false, false, false, 0, null);
		                        link.dispatchEvent(event);
		                        console.log("Download link method with simulated click succeeded");
		                        success = true;

		                    } catch(ex) {
		                        console.log("Download link method with simulated click failed with the following exception:");
		                        console.log(ex);
		                    }
		                }

		                if(!success)
		                {
		                    // Fallback to window.location method
		                    try
		                    {
		                        // Prepare a blob URL
		                        // Use application/octet-stream when using window.location to force download
		                        console.log("Trying download link method with window.location ...");
		                        var blob = new Blob([data], { type: octetStreamMime });
		                        var url = urlCreator.createObjectURL(blob);
		                        window.location = url;
		                        console.log("Download link method with window.location succeeded");
		                        success = true;
		                    } catch(ex) {
		                        console.log("Download link method with window.location failed with the following exception:");
		                        console.log(ex);
		                    }
		                }

		            }
		        }

		        if(!success)
		        {
		            // Fallback to window.open method
		            console.log("No methods worked for saving the arraybuffer, using last resort window.open");
		            window.open(httpPath, '_blank', '');
		        }
		    })
		    .error(function(data, status) {
		        console.log("Request failed with status: " + status);

		        // Optionally write the error out to scope
		        $scope.errorDetails = "Request failed with status: " + status;
		    });
		};
		
		
		$scope.folderActions = function(foldername,action){
				if(action!="upload")
				{
					$http.post('./folder/'+action+(action!='delete'?('/'+foldername):''),$scope.selectedFolder).success(
							function(data) {
								init();
							}).error(function(data) {
						console.log("error: " + data);
					});
				}
				else
					{
					var fd = new FormData();
					angular.forEach($scope.files, function(file, key) {
						  fd.append("file",file);
						});
					
					
					fd.append('folderId',$scope.selectedFolder.id);
					$http.post('./folder/upload', fd, {
						transformRequest : angular.identity,
						headers : {
							'Content-Type' : undefined
						}
					}).success(function() {
						alert("Success..");
						init();
					}).error(function() {
					});
					
			}
				$scope.foldername="";
				$scope.files=undefined;
				
		};
		
		var init = function() {
			$scope.getFolderList();
		};
		angular.element(document).ready(init);
		$scope.treeOptions = {
			    nodeChildren: "children",
			    multiSelection: false
			};
		
		   $scope.showModal = false;
		    $scope.buttonClicked = "";
		    $scope.toggleModal = function(btnClicked,action){
		        $scope.buttonClicked = btnClicked;
		        $scope.action = action;
		        $scope.showModal = !$scope.showModal;
		    };
		});
