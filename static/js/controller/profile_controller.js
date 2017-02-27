'use strict';

angular.module('myProfile').controller('ProfileController', ['$sce','$scope','$http','fileUpload','$q', function($sce,$scope,$http,fileUpload,$q) {
     var self=this;
     self.user={email:'',about:'',name:'',dob:'',gender:'',areacode:'',telnum:'',email:'',relationship:'',other:'',career:'',location:''};
     self.fetched={}
     self.fetched.interests=[];
     self.test={};
     var message=message;
     var name=name;
     self.email={};
     self.init=init;
     self.detailSubmit=detailSubmit;
     self.user.interests = ['Travelling','OnlineGames','Cooking','Sports','Studying','Sleeping','Partying'];
     self.getEmail=getEmail;
     self.countries={};
     self.getUserDetails=getUserDetails;
     self.getImage=getImage;
     self.uploadFile=uploadFile;
     self.getCountriesStates=getCountriesStates;
     self.data={};

self.data={};
     var image=image;
     
     function getCountriesStates()
     {
    	 $http.get('fetchCountriesStates/')
    	 .then(function (success){
    		 self.countries=success.data;

    	   },function (error){

    	   });
    	 
     }
 
     
     
     
     function getImage()
     {
    	 $http.post('fetchImage/',self.email)
    	 .then(function (success){
    		 self.data=success.data.image;
    		 console.log(self.data);

    	   },function (error){

    	   });
     }
    
 
     $scope.dataUpload = true;
     $scope.errVisibility = false;
     function uploadFile()
     {
     var file = $scope.myFile;
     console.log('file is ' );
    var uploadUrl = "/fileUpload"+"/"+self.email;
     fileUpload.uploadFileToUrl(file, uploadUrl).then(function(result){
    	self.user.image=result;
     $scope.errors = fileUpload.getResponse();
     console.log($scope.errors);
     $scope.errVisibility = true;
     }, function(error) {
     alert('error');
     })

    }
     
     function getEmail()
     {
    	 $http.get('getEmail/')
    	 .then(function (success){
    		 self.email=success.data.name;
    		 getUserDetails();
    		 getImage();

    	   },function (error){

    	   });
     }
     
     function getUserDetails()
     {
    	 $http.post('fetchUserDetails/',self.email)
    	 .then(function (success){
    		 self.user=success.data;
    		 self.fetched.interests=self.user.interests;
    		 self.user.interests=['Travelling','OnlineGames','Cooking','Sports','Studying','Sleeping','Partying'];

    	   },function (error){

    	   });
     }
     
     function init()
     {
    	 getCountriesStates();
    	 //self.user.interests = {Travelling:false,OnlineGames:false,Cooking:false,Sports:false,Studying:false,Sleeping:false,Partying:false};
    	 getEmail();
    	/* name= ServiceProfile.getValue;
    	 console.log(name);*/
     }
     
     function detailSubmit()
     {
    	 self.user.interests=self.checkboxList;
    	 if(self.user.other!=null)
    		 {
    		 
    	 var str_array = self.user.other.split(',');
    	 for(var i = 0; i < str_array.length; i++) {
    		   self.user.interests.push(str_array[i]);
    		}
    			 
    		 }

    	 self.user.email=self.email;
    	 
    	$http.post('userDetails/',self.user)
         .then(function (success) {
        	 init();
             message = success;
             if(message)
             init();
             if(!message)
           	  //$window.location.href = '/index.html'
             console.log(message);
         },function (error){

  	   });
   }
     
     self.add = function(value){
    	    if (!angular.isArray(self.checkboxList)){
    	    	self.checkboxList = [];
    	    }
    	    if (-1 === self.checkboxList.indexOf(value)){
    	    	self.checkboxList.push(value);
    	    }
    	  }
     self.remove = function(value){
    	    if (!angular.isArray(self.checkboxList)) {
    	      return;
    	    }
    	    var index = self.checkboxList.indexOf(value);
    	    if (-1 !== index){
    	    	self.checkboxList.splice(index, 1);
    	    }
     }
   

}]);