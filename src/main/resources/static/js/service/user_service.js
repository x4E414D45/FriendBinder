'use strict';

angular.module('myApp')
        .factory('UserService',['$http', function($http) {

        var userfac = {};

        userfac.createUser = function(user){
            return $http.post("/register", user);
        };
        userfac.login = function (user) {
            return $http.post("/login", user);
        };
        return userfac;
        }])
;
