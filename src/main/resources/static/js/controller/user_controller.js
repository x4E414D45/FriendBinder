'use strict';

angular.module('myApp')

    .controller('UserController', ['$http','$scope', '$location', 'AuthenticationService','UserService', function($http,$scope, $location, AuthenticationService, UserService) {

    $scope.user={email:'',password:''};
    $scope.showForm = false;
    $scope.loginInfo = {email:'', password:''};
    $scope.showMessage = false;
    $scope.successLogin = false;
    var message = message;

    initController();

    function initController() {
        AuthenticationService.ClearCredentials();
        $http.get('updateAllVectors/')
        .then(function (success){
   		 
   	   },function (error){

   	   });
    }

    $scope.login = function(){
    console.log($scope.loginInfo);
    $scope.dataLoading = true;
    UserService.login($scope.loginInfo).then(
      function(response){
          message = response.data;
          if (message){
            AuthenticationService.SetCredentials($scope.loginInfo.email, $scope.loginInfo.password);
            window.location = 'profile.html';
          }
          else {
              $scope.dataLoading = false;
              alert('Email or password is wrong');
          }
    });
  }

    $scope.toggleDetails = function() {
        $scope.showForm = !$scope.showForm;
    }

    $scope.submit = function(){
      console.log($scope.user);
      UserService.createUser($scope.user).then(
        function(response){
           message = response.data;
          if (message)
          {
               AuthenticationService.SetCredentials($scope.user.email, $scope.user.password);
               alert('Account has been created successfully');
               window.location = 'profile.html';
          }
          else
          {
               alert('This email has been used');
          }
        });
    }

}])

;
