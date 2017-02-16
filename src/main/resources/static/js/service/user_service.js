'use strict';

angular.module('myApp').factory('UserService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/';

    var factory = {
        createUser: createUser,
        login : login
    };

    return factory;

   

    function createUser(user) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + "register", user)
            .then(
            function (response) {
            	 deferred.resolve(response.data);            	 
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function login(user) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + "login", user)
            .then(
            function (response) {
            	 deferred.resolve(response.data);            	 
            },
            function(errResponse){
                console.error('Error while logging');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }



}]);