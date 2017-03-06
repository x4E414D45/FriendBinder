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
     $scope.acceptFriend = acceptFriend;
     
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
     function acceptFriend(notificationAccept)
    {
    	 $scope.addFriend.email = $scope.user.email;
       	 $scope.addFriend.image = $scope.image;
       	 $scope.addFriend.friendEmail = notificationAccept.email;
       	 $scope.addFriend.friendName = $scope.user.name;
       	 $scope.addFriend.isAdded = true;
       	 $http.post("/sendFriendRequest", $scope.addFriend)
       	 .then(function (success){
       		 
       		 $scope.addFriend.email = notificationAccept.email;
           	 $scope.addFriend.image = notificationAccept.image;
           	 $scope.addFriend.friendEmail = $scope.user.email;
           	 $scope.addFriend.friendName = notificationAccept.name;
           	 $scope.addFriend.isAdded = true;
           	$http.post("/sendFriendRequest", $scope.addFriend)

       	   },function (error){

       	});
        
     }
     
     
 }]);