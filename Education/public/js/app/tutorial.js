(function() {
	'use strict';

	angular.module('eduCore').factory('eduCourseFactory', ['$http', eduCourseFactory]);

	function eduCourseFactory($http) {
		function getListDetailCourse(id,callback) {
			$http.get(DOMAIN + '/api?action=get-course-detail&page=1&maxRow=100&parentID=' + id).then(function(response) {
				callback(null, response.data);
			}, function(error) {
				callback(error, null);
			});
		}

		return {
			list : getListDetailCourse
		};
	}


	angular.module('eduCore').controller('eduTutorialController', ['$scope','$rootScope', '$routeParams', '$location', 'eduCourseFactory', eduTutorialController]);

	function eduTutorialController($scope , $rootScope , $routeParams, $location, eduCourseFactory) {
		$rootScope.static_domain = STATIC_DOMAIN;
		$scope.listCourse = [];
		$scope.id = $routeParams.id;
		$scope.name = $routeParams.name.replace(/\s+/g, '-').toLowerCase();

		eduCourseFactory.list($routeParams.id,listCourseCallback);
		function listCourseCallback(error, dataCallback) {
			if (!error) {
				$scope.listCourse = dataCallback.data;
			} else {
				console.log(error);
			}
		}

	}

})();
