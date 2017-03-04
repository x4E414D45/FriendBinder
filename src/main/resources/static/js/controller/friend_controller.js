<<<<<<< HEAD
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
     
=======
'use strict';

angular.module('myFriends').controller('FriendController', ['$scope','$rootScope','$http', function($scope,$rootScope,$http) {
	
	var self=this;
    self.addFriend={email:'',friendEmail:''};
    self.acceptedFriendDetails={acceptorEmail:'',friendEmail:'',acceptorImage:'',acceptorName:'',isAdded:''};
    self.email={};
    self.init=init;
    self.getEmail=getEmail;
    self.getImage=getImage;
    var m=null;
    self.acceptFriend=acceptFriend;
    self.fetchFriendsWithSimilarInterests=fetchFriendsWithSimilarInterests;
    self.recommendedFriends={};
    self.friendRequestSent=friendRequestSent;
    self.getNotifications=getNotifications;
    self.getName=getName;
    self.getAddedFriends=getAddedFriends;
    self.notifications={};
    self.name={};
    self.addedFriends={};
     self.friendCount={};
    
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
    
    function getAddedFriends()
    {
    	$http.post('fetchAddedFriends/',self.email)
      	 .then(function (success){
      		self.addedFriends=success.data;
      		self.friendCount=self.addedFriends.length;
      	   },function (error){

      	   });	
    	
    }
    
    function getName()
    {
    	$http.post('fetchUserDetails/',self.email)
   	 .then(function (success){
   		 self.name=success.data.name;
   	   },function (error){

   	   });	
    }
    
    
    function friendRequestSent(friendAdd)
    {
   	 self.addFriend.email=self.email;
   	 self.addFriend.image=self.data;
   	 self.addFriend.friendEmail=friendAdd.email;
   	 self.addFriend.friendName=self.name;
   	 self.addFriend.isAdded=false;
   	 $http.post('sendFriendRequest/',self.addFriend)
   	 .then(function (success){

   	   },function (error){

   	   });
   	 
    	
    }
    
    function acceptFriend(notificationAccept)
    {
    	 self.addFriend.email=self.email;
       	 self.addFriend.image=self.data;
       	 self.addFriend.friendEmail=notificationAccept.email;
       	 self.addFriend.friendName=self.name;
       	 self.addFriend.isAdded=true;
       	 $http.post('sendFriendRequest/',self.addFriend)
       	 .then(function (success){
       		 
       		 self.addFriend.email=notificationAccept.email;
           	 self.addFriend.image=notificationAccept.image;
           	 self.addFriend.friendEmail=self.email;
           	 self.addFriend.friendName=notificationAccept.name;
           	 self.addFriend.isAdded=true;
           	$http.post('sendFriendRequest/',self.addFriend)

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
   	
   		 self.email=$rootScope.globals.currentUser.email;
   		 getImage();
   		 getName();
   		 fetchFriendsWithSimilarInterests();
   		 getNotifications();
   		 getAddedFriends();
    }
    
    function init()
    {
   	 getEmail();
   	
   	
    }   

     
>>>>>>> origin/master
}]);