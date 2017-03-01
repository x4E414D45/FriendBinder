'use strict';

angular.module('myProfile')
        .factory('ProfileService',['$http', function($http) {

        var userfac = {};

        userfac.getUser = function(email){
            return $http.post("fetchUserDetails", email);
        };
        userfac.updateUser = function(user){
            return $http.post("userDetails", user);
        };
        userfac.getImage = function(email){
            return $http.post("fetchImage", email);
        };
        return userfac;
        }])
;



