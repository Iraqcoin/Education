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


	angular.module('eduCore').controller('eduTutorialController', ['$scope','$rootScope', '$routeParams', '$location','$http','$sce', 'eduCourseFactory', eduTutorialController]);

	function eduTutorialController($scope , $rootScope , $routeParams, $location,$http, $sce, eduCourseFactory) {
		$rootScope.static_domain = STATIC_DOMAIN;
		$scope.isVideo = false;
		$scope.listCourse = [];
		$scope.id = $routeParams.id;
		$scope.name = $routeParams.name.replace(/\s+/g, '-').toLowerCase();

		$scope.isPlayVideo = false;
        $scope.linkVideo = "";
		eduCourseFactory.list($routeParams.id,listCourseCallback);
		function listCourseCallback(error, dataCallback) {
			if (!error) {
				$scope.listCourse = dataCallback.data;
				if(dataCallback.data){
                    for(var x in $scope.listCourse){
                        var id = $scope.listCourse[x].id;
                        $scope.getDetails($scope.listCourse[x],id);
                    }
                }
			} else {
				console.log(error);
			}
		}

		$scope.getDetails = function(parent,id){
               if($scope.id != 0 && id != 0){
                  console.log("get list detail");
                  $scope.getJSON(DOMAIN + "/api?action=get-detail&page=1&maxRow=100&parentID=" + id , function(data){
                     if(data.data){
                           var temp = [];
                           for(var x in data.data){
                           		if(data.data[x].type == 1)
                           			temp.push(data.data[x]);
                           }
                           parent.details = temp;
                     }
                  }, function(error){
                          console.log(error);
                  });
               }
        }
        $scope.playVideo = function(id,link){
        	console.log(link);
        	// $scope.isPlayVideo = true;
          $('#course-wrap-up-video').show();
          $scope.linkVideo = $sce.trustAsResourceUrl(link);
          $('.divHide').stop().animate({ opacity: 0.1 }, 1000);
          $("body").addClass("modal-open");
        }
        $scope.closeVideo = function(){
          console.log("hide");
          $scope.linkVideo = "";
          $('#course-wrap-up-video').hide();
          $('.divHide').stop().animate({ opacity: 1 }, 1000);
          $("body").removeClass("modal-open");
          $("iframe").attr("src","");
        }

         $scope.cacheAjax = {};

         $scope.getJSON = function (url, success , error) {
                //Get from cache
                if (typeof $scope.cacheAjax[url] != "undefined") {
                    //console.log("getfromCache");
                    success($scope.cacheAjax[url]);
                    return;
                }
                $http.get(url)
                    .success(function (data) {
                        if (typeof data !== "undefined") {
                            var tmp;
                            tmp = angular.copy(data, tmp);
                            $scope.cacheAjax[url] = tmp;
                            try {
                                success(data);
                            } catch (e) {
                                console.log("error");
                            }
                        }
                    }).error(function(){
                        console.log("Get JSON Ajax Fail: " + url);
                        error("Get JSON Ajax Fail: " + url);
                    });
            }//end GetJson


	}

})();
