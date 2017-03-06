'use strict';

angular.module('friendList').controller('FriendListController', ['$scope','$rootScope','$http','FriendListService', function($scope,$rootScope,$http,FriendListService) {
     $scope.user = {about:'', name:'', dob:'', telnum:'', email:'', relationship:'',
                    education:'', career:'', location:'', interests:[], language:'', 
                    gender:'', areacode:'', other:''};
     $scope.addFriend = {email:'',friendName:'',image:'',friendEmail:'', isAdded: false};
     $scope.image = '';
     $scope.getNotifications = getNotifications;
     $scope.notifications = {};
     $scope.addedFriends = {};
     $scope.friendCount = 0;
     
     initController();

    function initController() {
        $scope.user.email = $rootScope.globals.currentUser.email;
        getUser();
        getImage();
        getNotifications();
        getAddedFriends();
    }
    
    function getUser(){       
        FriendListService.getUser($scope.user.email).then(
        function(response) {
              $scope.user = response.data;
          },function (error){

    	});
    }
    
    function getImage(){       
        FriendListService.getImage($scope.user.email).then(
        function(response) {
              $scope.image = response.data.image;
          },function (error){

    	});
    }
     
     function getNotifications()
     {
    	 $http.post("/getNotifications", $scope.user.email)
    	 .then(function (success){
                $scope.notifications = success.data;

    	   },function (error){

    	   });
     }
         
    function getAddedFriends()
    {
    	$http.post('fetchAddedFriends/', $scope.user.email)
      	 .then(function (success){
      		$scope.addedFriends = success.data;
      		$scope.friendCount = $scope.addedFriends.length;
      	   },function (error){

      	   });	
    	
    }
     
 }]);