'user strict';

angular.module('myProfile')

.controller('ProfileController', ['$scope', '$rootScope', 'ProfileService','fileUpload', function($scope, $rootScope, ProfileService, fileUpload){
    var message = "Loading....";

    $scope.user = {about:'', name:'', dob:'', telnum:'', email:'', relationship:'',
                    education:'', career:'', location:'', interests:[], language:'', gender:'', areacode:'', other:'', lat: '', lng: ''};


    $scope.interests = [];
    var interest = interest;
    $scope.image = '';

    var geocoder = new google.maps.Geocoder();
    var lat = lat;
    var lng = lng;


    initController();

    function initController() {
        $scope.user.email = $rootScope.globals.currentUser.email;
        getUser();
        getImage();
    }
    
    function getUser(){       
        ProfileService.getUser($scope.user.email).then(
        function(response) {
              $scope.user = response.data;
          },function (error){

    	});
    }
    
    function getImage(){       
        ProfileService.getImage($scope.user.email).then(
        function(response) {
              $scope.image = response.data.image;
          },function (error){

    	});
    }
    
   function codeAddress() {
    var address = document.getElementById('location').value;
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status === 'OK') {
          lat = results[0].geometry.location.lat();
          alert(lat);
          lng = results[0].geometry.location.lng();
      } else {
        alert('Geocode was not successful for the following reason: ' + status);
      }
    });
  }

    $scope.selectGender = function(choice){
      if (choice === 1){
        $scope.user.gender = "Female";
      }
      else {
        $scope.user.gender = "Male";
      }
    }

    $scope.selectRelation = function(choice){
      if (choice === 1){
        $scope.user.relationship = "Single";
      }
      else if (choice === 2){
        $scope.user.relationship = "In a relationship";
      }
      else {
        $scope.user.relationship = "Married";
      }
    }

    $scope.selectInterest = function(choice){
      if (choice === 1){
        interest = "Reading";
        $scope.interests.push(interest);
      }
      else if (choice === 2){
        interest = "Travel";
        $scope.interests.push(interest);
      }
      else if (choice === 3){
        interest = "Playing Sports";
        $scope.interests.push(interest);
      }
      else if (choice === 4){
        interest = "Cooking";
        $scope.interests.push(interest);
      }
      else if (choice === 5){
        interest = "Playing games";
         $scope.interests.push(interest);
      }
      else if (choice === 6){
        interest = "Study";
        $scope.interests.push(interest);
      }
      else {
        interest = "Shopping";
        $scope.interests.push(interest);
      }
    }

    $scope.edit = function(){
       console.log($scope.interests);
       $scope.user.interests = $scope.interests;
       var address = document.getElementById('location').value;
        geocoder.geocode( { 'address': address}, function(results, status) {
          if (status === 'OK') {
              $scope.user.lat = results[0].geometry.location.lat().toString();
              $scope.user.lng = results[0].geometry.location.lng().toString();
              console.log($scope.user);
              ProfileService.updateUser($scope.user);
          } else {
            alert('Geocode was not successful for the following reason: ' + status);
          }
        });
    }
    
    $scope.dataUpload = true;
    $scope.errVisibility = false;
    $scope.uploadFile = function ()
    {
         var file = $scope.myFile;
         console.log('file is ' );
         var uploadUrl = "/fileUpload" + "/" + $scope.user.email;
         fileUpload.uploadFileToUrl(file, uploadUrl).then(function(result){
            $scope.user.image = result;
            $scope.errors = fileUpload.getResponse();
            console.log($scope.errors);
            $scope.errVisibility = true;
        }, 
        function(error) {
        alert('error');
     })

    }
    
}])

;
