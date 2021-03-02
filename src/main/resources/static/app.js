var myApp = angular.module('scrap', ['ui.router', 'ngStorage', 'ngSanitize', 'ngCsv']);

myApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/book');

    $stateProvider
    	
        .state('book', {
        	url: '/book',
        	templateUrl: 'views/book.html',
        	controller: 'BookController as bc'
        })
        
        .state('contact', {
        	url: '/contact',
        	params: {
        		contactName: ''
		    }, 
        	templateUrl: 'views/contact.html',
        	controller: 'ContactController as cc'
        });
});