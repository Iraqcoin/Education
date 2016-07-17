(function() {
	'use strict';
	
	angular.module('eduCore', ['ngRoute']);

	angular.module('eduCore').config(['$routeProvider', '$locationProvider', routes]);

	function routes($routeProvider, $locationProvider) {
		$routeProvider.when('/', {
			templateUrl : '../codeschool/views/main.html',
			controller : 'eduMainController',
		}).when('/detail', {
			templateUrl : '../codeschool/views/detail.html',
			controller : 'eduDetailController',
		}).when('/tutorial', {
			templateUrl : '../codeschool/views/tutorial.html',
			controller : 'eduTutorialController',
		}).otherwise({
			redirectTo : '/'
		});
	}


	angular.module('eduCore').controller('eduMainController', ['$scope', eduMainController]);

	function eduMainController($scope) {
		console.log('eduMainController');
	}

})();
