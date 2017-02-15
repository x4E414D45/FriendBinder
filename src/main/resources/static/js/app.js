'use strict';
var App = angular.module('myApp',[]);
var Profile = angular.module('myProfile',['myApp']);

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

Profile.service('ServiceProfile', function(ServiceApp) {
    this.getValue = function() {
        return ServiceApp.getValue();
    };

    this.setValue = function() {
        ServiceApp.setValue('New value');
    }
});