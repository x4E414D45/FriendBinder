'use strict';

angular.module('myApp')
        .constant("baseURL","http://www.rafikie.com/")
        .factory('UserService',['$http','baseURL', function($http,baseURL) {

        var userfac = {};


        userfac.createUser = function(user){
            return $http.post(baseURL + "register", user);
        };
        userfac.getUser = function(email){
            return $http.post(baseURL + "fetchUserDetails", email);
        };
        userfac.login = function (user) {
            return $http.post(baseURL + "login", user);
        };
        userfac.updateUser = function(email, user){
            return $http.put(baseURL + "update/" + email, user);
        };

        return userfac;
        }])
;
