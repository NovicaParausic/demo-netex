(function() {
	'use strict';
	
	myApp.controller('BookController', ['$scope', '$http', '$state', function($scope, $http, $state) {
		
		console.log('BLA');	
		var vm = this;
		
		vm.deleteFromBook = deleteFromBook; 
		vm.editContact = editContact;
		vm.searchContact = searchContact;
		
		$scope.addressBook = '';
		$scope.name = '';
		$scope.data = {};
	   
	    $scope.myHeaderData = {
	        id: 'Name',
	        name: 'Address'
	    };
	  
		function getAddressBook() {
			console.log('get');
			$http({
				method: 'GET',
				url: '/contact'
			})
			.then(function (response) {
				console.log(response.status);
				if(response && response.data) {
					$scope.addressBook = response.data;
				}
			})
			.catch(function (response) {
				console.log(response.status);
			});
		}
		
		getAddressBook();
	
		function searchContact() {
			console.log($scope.name);
			if ($scope.name) {	
				$http ({
					method: 'GET',
					url: '/contact/search/' + $scope.name
				})
				.then (function (response) {
					console.log(response.status);
					if(response && response.data) {
						$scope.addressBook = response.data;
					}
				})
				.catch (function (response) {
					console.log(response);
				});
			}
		}
	
		function deleteFromBook(id) {
			console.log('delete: ' + id);
			
			$http ({
				method: 'DELETE',
				url: '/contact/delete/' + id
			})
			.then (function (response) {
				console.log(response.status);
				getAddressBook();
			})
			.catch (function (response) {
				console.log(response);
			});
		}
		
		function editContact(id) {
			console.log(id);
			
			$state.go('contact', {
				contactName : id
			});
			
		}
	
	}]);
})();

