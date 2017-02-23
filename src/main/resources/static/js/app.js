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
              var restrictedPage = $.inArray($location.path(), ['/index','/profile']) === -1;
              var loggedIn = $rootScope.globals.currentUser;
              if (restrictedPage && !loggedIn) {
                  $location.path('/index');
              }
          });
      }
