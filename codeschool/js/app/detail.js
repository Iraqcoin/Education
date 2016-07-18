(function() {
	'use strict';

	angular.module('eduCore').factory('eduDetailFactory', ['$http', eduDetailFactory]);

	function eduDetailFactory($http) {
		function getListCategory(id, callback) {
			$http.get('../codeschool/data/category_' + id + '.json').then(function(response) {
				callback(null, response.data);
			}, function(error) {
				callback(error, null);
			});
		}

		function getListCourse(callback) {
			$http.get('../codeschool/data/course.json').then(function(response) {
				callback(null, response.data);
			}, function(error) {
				callback(error, null);
			});
		}

		return {
			listCate : getListCategory,
			listCourse : getListCourse
		};
	}


	angular.module('eduCore').controller('eduDetailController', ['$scope', '$routeParams', '$location', 'eduDetailFactory', eduDetailController]);

	function eduDetailController($scope, $routeParams, $location, eduDetailFactory) {
		$scope.listCategory = [];
		$scope.listCourse = [];
		$scope.getCoursesByCateId = getCoursesByCateId;

		eduDetailFactory.listCate($routeParams.id, listCategoryCallback);
		eduDetailFactory.listCourse(listCourseCallback);

		function listCategoryCallback(error, dataCallback) {
			if (!error) {
				$scope.listCategory = dataCallback.data;
			} else {
				$location.path('/');
			}
		}

		function listCourseCallback(error, dataCallback) {
			if (!error) {
				$scope.listCourse = dataCallback.data;
				getCoursesByCateId();
			} else {
				console.log(error);
			}
		}

		function getCoursesByCateId(cateId) {
			return _.filter($scope.listCourse, {
				'cate_id' : cateId
			});
		}

	}

})();
