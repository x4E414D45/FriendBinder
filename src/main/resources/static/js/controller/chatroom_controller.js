'use strict';

angular.module('myChatroom').controller('ChatroomController', ['$scope','$rootScope','$http', function($scope,$rootScope,$http) {
	
var self=this;

 self.addFriend={email:'',friendEmail:''};
 self.messageDetails={senderEmail:'',senderName:'',receiverName:'',receiverEmail:'',message:''};
    self.fetchMessage={senderEmail:'',receiverEmail:''};
    self.email={};
    self.init=init;
    self.getImage=getImage;
    self.getNotifications=getNotifications;
    self.getName=getName;
    self.getAddedFriends=getAddedFriends;
    self.getMessages=getMessages;
    self.notifications={};
    self.name={};
    self.addedFriends={};
     self.friendCount={};
     self.retrievedMessages={};
     self.sendMessages=sendMessages;
     self.showMessageBox=showMessageBox;
     self.messageBody={};
     var isShowMessageBox=false;
     
     function showMessageBox()
     {
     	
    	 isShowMessageBox=true;
     }
    
    self.data={};
    var image=image;
    
    function getMessages(details)
    {

    	 self.messageDetails.receiverEmail=details.email;
    	 self.messageDetails.receiverName=details.name;
    	 
    	self.fetchMessage.receiverEmail=details.email;
    	self.fetchMessage.senderEmail=self.email
    	
    	 $http.post('getMessages/',self.fetchMessage)
       	 .then(function (success){
       		 self.retrievedMessages=success.data;

       	   },function (error){

       	   });
    }
    
    function sendMessages()
    {
   	    self.messageDetails.senderEmail=self.email;
    	self.messageDetails.message=self.messageBody;
    	self.messageDetails.senderName=self.name;
    	self.messageBody=null;
    	$http.post('sendMessage/',self.messageDetails)
     	 .then(function (success){
     		 $http.post('getMessages/',self.fetchMessage)
           	 .then(function (success){
           		 self.retrievedMessages=success.data;

           	   },function (error){

           	   });
     	   },function (error){

     	   });
    }
    
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
    
    
    function getImage()
    {
   	 $http.post('fetchImage/',self.email)
   	 .then(function (success){
   		 self.data=success.data.image;
   	   },function (error){

   	   });
    }
    

    
    function init()
    {
    	self.email=$rootScope.globals.currentUser.email;
    	
    	
    	
    	
    	getImage();
    	getName();
    	getAddedFriends();
    	getNotifications();
    }   

     
}]);