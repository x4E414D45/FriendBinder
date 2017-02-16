'use strict';

angular.module('myApp').controller('UserController', ['$scope','$http','$window','UserService' , function($scope,$http,$window,UserService) {
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
    	UserService.createUser(user).then(
           function (response) {
              message = response;
              if(message)
              {
                  $window.location.href = '/profile.html';
                  alert("Account has been created successfully")
              }
              if(!message)
              {
                  alert("This email has been used")
                  $window.location.href = '/index.html';                
              }
          },
          function (error) {
             console.log(error);      
          });
    }
    
    
    function loginDetails(user){
    	UserService.login(user).then(
            function (response) {
            	message = response;
                if(message["name"] !== null)
                {
                    $window.location.href = '/profile.html';
                }
                else
                {
                   //dataService.dataObj=message.name;
                   //sharedService.prepForBroadcast(message.name);
                   //ServiceApp.setValue(message.name);   
                   alert("Email or password incorrect");                  
                }
           },
           function (error) {
              console.log(error);
           });   	
        }  

    function submit() {
        console.log('Saving New User', self.user);
        createUser(self.user);
        reset();
    }
    
    function loginSubmit() {
        console.log('Saving New User', self.login);
        loginDetails(self.login);
        reset();
    }

    function reset(){
        self.user={name:'',email:'',password:''};
        $scope.myForm.$setPristine(); //reset Form
    }
    

}]);