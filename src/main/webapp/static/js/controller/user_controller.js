'use strict';

angular.module('myApp').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
    var self = this;
    self.user={id:null,name:'',email:'',password:''};
    self.users=[];
    self.init=init;
    var message=message;
    var isShowRegister=isShowRegister;
    self.submit = submit;
    self.reset = reset;
    self.showRegister=showRegister;
    
    function init() {
    	debugger;
    	self.isShowRegister=false;
      }
 
    function showRegister(){
    	  debugger; 
    	self.isShowRegister=true;
    }



    function createUser(user){
        UserService.createUser(user)
            .then( function (d){
            self.message=d},
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }




    function submit() {
        if(self.user.id===null){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }else{
            updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
        reset();
    }



    function reset(){
        self.user={id:null,username:'',address:'',email:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);
