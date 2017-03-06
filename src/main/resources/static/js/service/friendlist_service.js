'use strict';

angular.module('friendList')
        .factory('FriendListService',['$http', function($http) {

        var userfac = {};

        userfac.getUser = function(email){
            return $http.post("/fetchUserDetails", email);
        };
        userfac.updateUser = function(user){
            return $http.post("/userDetails", user);
        };
        userfac.getImage = function(email){
            return $http.post("/fetchImage", email);
        };
        return userfac;
        }])
;
