'use strict';

angular.module('myFriends').controller('FriendController', ['$scope','$rootScope','$http','FriendService', function($scope,$rootScope,$http,FriendService) {
     $scope.user = {about:'', name:'', dob:'', telnum:'', email:'', relationship:'',
                    education:'', career:'', location:'', interests:[], language:'', 
                    gender:'', areacode:'', other:''};
     $scope.addFriend = {email:'',friendName:'',image:'',friendEmail:'', isAdded: false};
     $scope.image = '';
     $scope.countries = {};
     $scope.getCountriesStates = getCountriesStates;
     $scope.GetSelectedCountry = GetSelectedCountry;
     $scope.fetchFriendsWithSimilarInterests = fetchFriendsWithSimilarInterests;
     $scope.recommendedFriends = {};
     $scope.friendRequestSent = friendRequestSent;
     $scope.getNotifications = getNotifications;
     $scope.acceptFriend = acceptFriend;
     $scope.notifications = {};
     $scope.addedFriends = {};
     $scope.friendCount = 0;
     
     
     $scope.data = {};
     
     
     initController();

    function initController() {
        $scope.user.email = $rootScope.globals.currentUser.email;
        getUser();
        getImage();
        fetchFriendsWithSimilarInterests();
        getNotifications();
        getAddedFriends();
    }
    
    function getUser(){       
        FriendService.getUser($scope.user.email).then(
        function(response) {
              $scope.user = response.data;
          },function (error){

    	});
    }
    
    function getImage(){       
        FriendService.getImage($scope.user.email).then(
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
     
     function friendRequestSent(friendAdd)
     {
    	 $scope.addFriend.email = $scope.user.email;
         $scope.addFriend.friendName= $scope.user.name;
    	 $scope.addFriend.image = $scope.image;
    	 $scope.addFriend.friendEmail = friendAdd.email;
         $scope.addFriend.isAdded = false;
    	 $http.post("/sendFriendRequest",$scope.addFriend)
    	 .then(function (success){

    	   },function (error){

    	   });
    	 
     	
     }
     
    function GetSelectedCountry() {
         $scope.strCountry = document.getElementById("country").value;
     }
    /* $scope.GetSelectedState = function () {
         $scope.strState = document.getElementById("state").value;
     };*/
     function getCountriesStates()
     {
    	 $http.get("/fetchCountriesStates")
    	 .then(function (success){
    		 $scope.countries = success.data;

    	   },function (error){

    	   });
    	 
     }
     
     function fetchFriendsWithSimilarInterests()
     {
    	 $http.post("/fetchFriendsList", $scope.user.email)
    	 .then(function (success){
    		 $scope.recommendedFriends = success.data;   
    	   },
           function (error){

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
     
     $scope.removeSuggest=function($index){ 
            $scope.recommendedFriends.splice($index,1);     
        }
        
      $scope.removeRequest=function($index){ 
            $scope.notifications.splice($index,1);     
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