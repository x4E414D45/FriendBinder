'use strict';

angular.module('myProfile').controller('ProfileController', ['$scope','$http','ServiceProfile', function($scope,$http,ServiceProfile) {
     var self=this;
     self.user={email:'',about:'',name:'',dob:'',gender:'',areacode:'',telnum:'',email:'',relationship:'',other:'',career:'',location:''};
     self.test={};
     var name=name;
     self.init=init;
     self.detailSubmit=detailSubmit;
     self.user.interests = {Travelling:false,OnlineGames:false,Cooking:false,Sports:false,Studying:false,Sleeping:false,Partying:false};
     self.getEmail=getEmail;
     self.getUserDetails=getUserDetails;
     
     function getEmail()
     {
    	 $http.get('getEmail/')
    	 .then(function (success){
    		 self.user.email=success.data.name;
    		 getUserDetails();

    	   },function (error){

    	   });
     }
     
     function getUserDetails()
     {
    	 $http.post('fetchUserDetails/',self.user.email)
    	 .then(function (success){
    		 self.user=success.data;

    	   },function (error){

    	   });
     }
     
     function init()
     {
    	 self.user.interests = {Travelling:false,OnlineGames:false,Cooking:false,Sports:false,Studying:false,Sleeping:false,Partying:false};
    	 getEmail();
    	/* name= ServiceProfile.getValue;
    	 console.log(name);*/
     }
     
     function detailSubmit()
     {
    	$http.post('userDetails/',self.user)
         .success(function (response, status) {
             message = response;
             if(message)
             $window.location.href = '/home-ajs.html'
             if(!message)
           	  $window.location.href = '/index.html'
             console.log(message);
         })
         .error(function (response, status) {
            
      
         });
    	
     if(self.user.name!=null)
    		 {
    		 console.log(self.test);
    		 }
    	 
     }

   

}]);