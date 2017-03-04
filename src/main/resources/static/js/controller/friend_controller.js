'use strict';

angular.module('myFriends').controller('FriendController', ['$scope','$rootScope','$http','FriendService', function($scope,$rootScope,$http,FriendService) {
	
     var self=this;
     $scope.user = {about:'', name:'', dob:'', telnum:'', email:'', relationship:'',
                    education:'', career:'', location:'', interests:[], language:'', 
                    gender:'', areacode:'', other:''};
     $scope.addFriend = {email:'',name:'',image:'',friendEmail:''};
     $scope.image = '';
     $scope.countries = {};
     $scope.getCountriesStates = getCountriesStates;
     $scope.GetSelectedCountry = GetSelectedCountry;
     $scope.fetchFriendsWithSimilarInterests = fetchFriendsWithSimilarInterests;
     $scope.recommendedFriends = {};
     $scope.friendRequestSent = friendRequestSent;
     $scope.getNotifications = getNotifications;
     $scope.notifications = {};
     
     $scope.data = {};
     
     
     initController();

    function initController() {
        $scope.user.email = $rootScope.globals.currentUser.email;
        getUser();
        getImage();
        fetchFriendsWithSimilarInterests();
        getNotifications();
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
         $scope.addFriend.name = $scope.user.name;
    	 $scope.addFriend.image = $scope.image;
    	 $scope.addFriend.friendEmail = friendAdd.email;
    	 $http.post("/addFriend",$scope.addFriend)
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
     
}]);