'use strict';

angular.module('myFriends').controller('FriendController', ['$scope','$http', function($scope,$http) {
	
     var self=this;
     self.addFriend={email:'',friendEmail:''};
     self.email={};
     self.init=init;
     self.getEmail=getEmail;
     self.countries={};
     self.getImage=getImage;
     var m=null;
     self.getCountriesStates=getCountriesStates;
     self.GetSelectedCountry=GetSelectedCountry;
     self.fetchFriendsWithSimilarInterests=fetchFriendsWithSimilarInterests;
     self.recommendedFriends={};
     self.friendRequestSent=friendRequestSent;
     self.getNotifications=getNotifications;
     self.notifications={};
     
     self.data={};
     var image=image;
     
     function getNotifications()
     {
    	 $http.post('getNotifications/',self.email)
    	 .then(function (success){
    		 self.notifications=success.data;

    	   },function (error){

    	   });
     }
     
     
     function friendRequestSent(friendAdd)
     {
    	 self.addFriend.email=self.email;
    	 self.addFriend.image=self.data;
    	 self.addFriend.friendEmail=friendAdd.email;
    	 $http.post('addFriend/',self.addFriend)
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
    	 $http.get('fetchCountriesStates/')
    	 .then(function (success){
    		 self.countries=success.data;

    	   },function (error){

    	   });
    	 
     }
     
     function fetchFriendsWithSimilarInterests()
     {
    	 $http.post('fetchFriendsList/',self.email)
    	 .then(function (success){
    		 self.recommendedFriends=success.data;
    		 console.log(self.data);

    	   },function (error){

    	   });
     }
     
     function getImage()
     {
    	 $http.post('fetchImage/',self.email)
    	 .then(function (success){
    		 self.data=success.data.image;
    	   },function (error){

    	   });
     }
     
     function getEmail()
     {
    	 $http.get('getEmail/')
    	 .then(function (success){
    		 self.email=success.data.name;
    		 getImage();
    		 fetchFriendsWithSimilarInterests();
    		 getNotifications();

    	   },function (error){

    	   });
     }
     
     function init()
     {
    	 getCountriesStates();
    	 getEmail();
    	
    	
     }   

}]);