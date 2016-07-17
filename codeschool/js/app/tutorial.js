(function() {
	'use strict';

	angular.module('eduCore').factory('eduCourseFactory', ['$http', eduCourseFactory]);

	function eduCourseFactory($http) {
		function getListDetailCourse(callback) {
			$http.get('../codeschool/data/detail_course.json').then(function(response) {
				callback(null, response.data);
			}, function(error) {
				callback(error, null);
			});
		}

		return {
			list : getListDetailCourse
		};
	}


	angular.module('eduCore').controller('eduTutorialController', ['$scope', 'eduCourseFactory', eduTutorialController]);

	function eduTutorialController($scope, eduCourseFactory) {
		$scope.listCourse = [];
		
		eduCourseFactory.list(listCourseCallback);
		function listCourseCallback(error, dataCallback) {
			if (!error) {
				$scope.listCourse = dataCallback.data;
			} else {
				console.log(error);
			}
		}

	}

})();
