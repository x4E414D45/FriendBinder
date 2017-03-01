'use strict';

var App = angular.module('myApp',['ngCookies'])

      .run(run);

      run.$inject = ['$rootScope', '$location', '$cookies', '$http'];
      function run($rootScope, $location, $cookies, $http) {
          // keep user logged in after page refresh
          $rootScope.globals = $cookies.getObject('globals') || {};
          if ($rootScope.globals.currentUser) {
              $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
          }

          $rootScope.$on('$locationChangeStart', function (event, next, current) {
              // redirect to login page if not logged in and trying to access a restricted page
              var restrictedPage = $.inArray($location.path(), ['/index.html','/profile.html']) === -1;
              var loggedIn = $rootScope.globals.currentUser;
              if (restrictedPage && !loggedIn) {
                  $location.path('/index.html');
              }
          });
      }

var Profile = angular.module('myProfile',['ngCookies','flow'])

    .run(run);

      run.$inject = ['$rootScope', '$location', '$cookies', '$http'];
      function run($rootScope, $location, $cookies, $http) {
          // keep user logged in after page refresh
          $rootScope.globals = $cookies.getObject('globals') || {};
          if ($rootScope.globals.currentUser) {
              $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
          }

          $rootScope.$on('$locationChangeStart', function (event, next, current) {
              // redirect to login page if not logged in and trying to access a restricted page
              var restrictedPage = $.inArray($location.path(), ['/index.html','/profile.html']) === -1;
              var loggedIn = $rootScope.globals.currentUser;
              if (restrictedPage && !loggedIn) {
                  $location.path('/index.html');
              }
          });
      }

    Profile.directive('fileModel', ['$parse', function ($parse) {
                return {
                    restrict: 'A',
                    link: function(scope, element, attrs) {
                    var model = $parse(attrs.fileModel);
                    var modelSetter = model.assign;
                    element.bind('change', function(){
                    scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    }]);
 
    Profile.service('fileUpload', ['$q','$http', function ($q,$http) {
            var deffered = $q.defer();
            var responseData;
            this.uploadFileToUrl = function(file, uploadUrl){
            var fd = new FormData();
            fd.append('file', file);
            return $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: { 'Content-Type' : undefined}
        })
                .success(function(response){

                /* $scope.errors = response.data.value; */
                console.log(response);
                responseData = response;
                deffered.resolve(response);
                return deffered.promise;
            })
                .error(function(error){
                deffered.reject(error);
                return deffered.promise;
        });

    }
    this.getResponse = function() {
        return responseData;
    }
}]);

var Friends = angular.module('myFriends',[])

    .run(run);

      run.$inject = ['$rootScope', '$location', '$cookies', '$http'];
      function run($rootScope, $location, $cookies, $http) {
          // keep user logged in after page refresh
          $rootScope.globals = $cookies.getObject('globals') || {};
          if ($rootScope.globals.currentUser) {
              $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
          }

          $rootScope.$on('$locationChangeStart', function (event, next, current) {
              // redirect to login page if not logged in and trying to access a restricted page
              var restrictedPage = $.inArray($location.path(), ['/index.html','/profile.html']) === -1;
              var loggedIn = $rootScope.globals.currentUser;
              if (restrictedPage && !loggedIn) {
                  $location.path('/index.html');
              }
          });
      }
