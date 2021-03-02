(function() {
	'use strict';
	
	myApp.controller('ContactController', ['$scope', '$http', '$state', '$stateParams', '$window', function($scope, $http, $state, $stateParams, $window) {
			
		console.log('ZNJU');	
		var vm = this;
		
		vm.uploadPhoto = uploadPhoto;
		vm.getPhoto = getPhoto;
		vm.editContact = editContact;
		vm.goToBook = goToBook;
		
		$scope.imageFile = '';
		$scope.retrievedImage = '';
		$scope.stepsModel = '';
		$scope.name = '';
		$scope.address = '';
		
		$scope.contactName = $stateParams.contactName;
		console.log('name: ' + $scope.contactName);
		if ($scope.contactName) {
			getPhoto();
		}
		
		var contactId = '';
		
		function getPhoto() {
			console.log('Get photo from database');
			$http({
				method: 'GET',
				url: '/contact/get/' + $scope.contactName
			})
			.then(function (response) {
				console.log(response.status);
				if(response && response.data) {
					contactId = response.data.id;
					$scope.name = response.data.name;
					$scope.address = response.data.address;
					var photo = response.data.picByte;
          			$scope.stepsModel = 'data:image/jpeg;base64,' + photo;
          						
				}
			})
			.catch(function (response) {
				console.log(response.status);
			});
		}
		
		function editContact() {
			console.log('Edit COntact');

			if (! $scope.imageFile) {
				$window.alert('Must choose image first');
				return;  
			}			

			var res = $scope.name.concat('-', $scope.address, '-', $scope.contactName);
			console.log(res);
			
			const url = 'http://localhost:8080/contact/edit';
			const uploadImageData = new FormData();
    		uploadImageData.append('imageFile', $scope.imageFile, res);
    		
    		var config = {
    	   		transformRequest: angular.identity,
    	   		transformResponse: angular.identity,
	   			headers : {
	   				'Content-Type': undefined //'multipart/form-data'
	   	    	}
      		};
    		
      		$http.post(url, uploadImageData, config)
       			.then(function (response) {
					console.log(response);				
				})
				.catch(function (response) {
					console.log(response);
			});
		}
		
		function uploadPhoto() {
			console.log('uplload phoott');
			
			if (! $scope.imageFile) {
				$window.alert('Must choose image first');
				return;  
			}
			
			var res = $scope.name.concat('-', $scope.address);
			console.log(res);
			
			const url = 'http://localhost:8080/contact/upload';
			const uploadImageData = new FormData();
    		uploadImageData.append('imageFile', $scope.imageFile, res);
    		
    		var dataFile = {
    			id: 'bla',
    			file: uploadImageData
    		}
    		
    		var config = {
    	   		transformRequest: angular.identity,
    	   		transformResponse: angular.identity,
	   			headers : {
	   				'Content-Type': undefined //'multipart/form-data'
	   	    	}
      		};
    		
      		$http.post(url, uploadImageData, config)
       			.then(function (response) {
					console.log(response);				
				})
				.catch(function (response) {
					console.log(response);
			});
		}
		
		$scope.imageUpload = function(event){
         	var files = event.target.files[0]; //FileList object
         	$scope.imageFile = event.target.files[0];
         	
            var reader = new FileReader();
            reader.onload = $scope.imageIsLoaded; 
            reader.readAsDataURL(files);
    	}

    	$scope.imageIsLoaded = function(e){
       	 	$scope.$apply(function() {
            	$scope.stepsModel = e.target.result;
        	});
    	}
		
		function goToBook() {
			$state.go('book');
		}
	}]);
})();