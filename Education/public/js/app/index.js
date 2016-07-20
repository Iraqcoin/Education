(function() {
	'use strict';
	
	angular.module('eduCore', ['ngRoute']);

	angular.module('eduCore').config(['$routeProvider', '$locationProvider', routes]);

	function routes($routeProvider, $locationProvider) {
		$routeProvider.when('/', {
			templateUrl : '../codeschool/views/main.html',
			controller : 'eduMainController',
		}).when('/learn/:name/:id', {
			templateUrl : '../codeschool/views/detail.html',
			controller : 'eduDetailController',
		}).when('/tutorial/:id', {
			templateUrl : '../codeschool/views/tutorial.html',
			controller : 'eduTutorialController',
		}).otherwise({
			redirectTo : '/'
		});
	}

	angular.module('eduCore').factory('eduMainFactory', ['$http', eduMainFactory]);

	function eduMainFactory($http) {
		function getListDetailCourse(callback) {
			$http.get('../codeschool/data/sub_category.json').then(function(response) {
				callback(null, response.data);
			}, function(error) {
				callback(error, null);
			});
		}

		return {
			list : getListDetailCourse
		};
	}

	angular.module('eduCore').controller('eduMainController', ['$scope', 'eduMainFactory', eduMainController]);

	function eduMainController($scope, eduMainFactory) {
		$scope.listSubCategory = [];
		$scope.getText = getText;
		
		eduMainFactory.list(listSubCategoryCallback);
		function listSubCategoryCallback(error, dataCallback) {
			if (!error) {
				$scope.listSubCategory = dataCallback.data;
			} else {
				console.log(error);
			}
		}
		
		function getText(str) {
			return str.replace(/\//g, '-').toLowerCase();
		}
	}

})();
