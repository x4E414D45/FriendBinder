'use strict';
var App = angular.module('myApp',[]);
var Profile = angular.module('myProfile',['msieurtoph.ngCheckboxes','flow']);
var Friends = angular.module('myFriends',[]);


App.factory('ServiceApp', function($rootScope) {
	var data={};
	data.myValue=false;
    data.getValue = function() {
        return this.myValue;
    };

    data.setValue = function(newValue) {
        this.myValue = newValue;
    };
    return data;
});


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