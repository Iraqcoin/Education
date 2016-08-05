(function() {
	'use strict';
	
	angular.module('eduCore', ['ngRoute']);

	angular.module('eduCore').config(['$routeProvider', '$locationProvider', routes]);

	function routes($routeProvider, $locationProvider) {
		$routeProvider.when('/', {
			templateUrl : '/static/views/main.html',
			controller : 'eduMainController',
		})
		.when('/learn/:name/:id', {
			templateUrl : '/static/views/detail.html',
			controller : 'eduDetailController',
		})
		.when('/tutorial/:name/:id', {
			templateUrl : '/static/views/tutorial.html',
			controller : 'eduTutorialController',
		})
		.otherwise({
			redirectTo : '/'
		});
	}

	angular.module('eduCore').config(function($interpolateProvider, $httpProvider) {
            $interpolateProvider.startSymbol('[[').endSymbol(']]');
            $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
            $httpProvider.defaults.transformRequest = function(obj) {
                var str = [];
                for (var p in obj) {
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                }
                return str.join("&");
            };
	});

	angular.module('eduCore').factory('eduMainFactory', ['$http', eduMainFactory]);

	function eduMainFactory($http) {
		function getListDetailCourse(callback) {
			$http.get(DOMAIN + '/api?action=get-sub-category&page=1&maxRow=100&parentID=1').then(function(response) {
				callback(null, response.data);
			}, function(error) {
				callback(error, null);
			});
		}

		return {
			list : getListDetailCourse
		};
	}

	angular.module('eduCore').controller('rootController', ['$scope','$rootScope','$http', rootController]);
	function rootController($scope , $rootScope , $http) {
		$rootScope.checkAuthen = function(fnCallback){
			$http.get(DOMAIN + "/user/sign_in?action=check")
	        .success(function (data) {
	            fnCallback(data);
	        }).error(function(){
	            console.log("Get JSON Ajax Fail: " + url);
	        });
		}
	}

	angular.module('eduCore').controller('eduMainController', ['$scope', 'eduMainFactory','$rootScope', eduMainController]);

	function eduMainController($scope, eduMainFactory , $rootScope) {
		$rootScope.static_domain = STATIC_DOMAIN;
        console.log($rootScope.static_domain);
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
