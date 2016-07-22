(function() {
	'use strict';

	angular.module('eduCore').factory('eduDetailFactory', ['$http', eduDetailFactory]);

	function eduDetailFactory($http) {
		function getListCategory(id, callback) {
			$http.get(DOMAIN + '/api?action=get-category&page=1&maxRow=100&parentID=' + id).then(function(response) {
				callback(null, response.data);
			}, function(error) {
				callback(error, null);
			});
		}

		function getListCourse(cate,callback) {
			console.log(cate);
			for(var x in cate){
				$http.get(DOMAIN + '/api?action=get-course&page=1&maxRow=100&parentID=' + cate[x].id).then(function(response) {
					callback(null, response.data);
				}, function(error) {
					callback(error, null);
				});
			}
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
		

		function listCategoryCallback(error, dataCallback) {
			if (!error) {
				$scope.listCategory = dataCallback.data;
				eduDetailFactory.listCourse($scope.listCategory,listCourseCallback);
			} else {
				$location.path('/');
			}
		}

		function listCourseCallback(error, dataCallback) {
			if (!error) {
				$scope.listCourse = $scope.listCourse.concat(dataCallback.data);
				console.log($scope.listCourse);
				getCoursesByCateId();
			} else {
				console.log(error);
			}
		}

		function getCoursesByCateId(cateId) {
			//console.log($scope.listCourse);
			// console.log(_.filter($scope.listCourse, {
			// 	'cate_id' : cateId
			// }));
			return _.filter($scope.listCourse, {
				'cate_id' : cateId
			});
		}

	}

})();
