'use strict';

angular.module('myApp').controller('UserController', ['$scope','$http','$window','ServiceApp' , function($scope,$http,$window,ServiceApp) {
    var self = this;
    self.user={name:'',email:'',password:''};
    self.login={email:'',password:''};
    self.users=[];
    var message=message;
    var name=name;
    self.isShowRegister=false;
    self.submit = submit;
    self.reset = reset;
    self.showRegister=showRegister;
    self.loginDetails=loginDetails;
    self.loginSubmit=loginSubmit;
    self.send=send;
    //$scope.text = 'Hey';
    
     function send(response){
      //self.dataShare.sendData(response);
    }
 
    function showRegister(){
    	  debugger; 
    	self.isShowRegister=true;
    }



    function createUser(user){
    	  $http.post('register',user)
          .success(function (response, status) {
              message = response;
              if(message)
              $window.location.href = '/profile.html'
              if(!message)
            	  $window.location.href = '/index.html'
              console.log(message);
          })
          .error(function (response, status) {
             
       
          });
    }
    
    
    function loginDetails(login){
    	$http.post('login/',login)
         	  //$http.post('test/'+self.login.email)
               .success(function (response, status) {
            	   message=response;
                   if(message.name==null)
                	   $window.location.href = '/index.html';
                   else
                	   {
                	   //dataService.dataObj=message.name;
                	   //sharedService.prepForBroadcast(message.name);
                	   ServiceApp.setValue(message.name);
                	   $window.location.href = '/profile.html';
                	   }
                   console.log(message);
               })
               .error(function (response, status) {
                  
            
               });
    	
  }    

    function submit() {
        if(self.user.email!=null){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }else{
            //updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
        reset();
    }
    
    function loginSubmit() {
        if(self.login.email!=null){
            console.log('Saving New User', self.login);
            loginDetails(self.login);
        }else{
            //updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
        reset();
    }

    function reset(){
        self.user={name:'',email:'',password:''};
        $scope.myForm.$setPristine(); //reset Form
    }
    

}]);