'user strict';

angular.module('myApp')

.controller('ProfileController', ['$scope', '$rootScope', 'UserService', function($scope, $rootScope, UserService){
    $scope.message = "Loading....";
    $scope.user = {about:'', name:'', birth:'', tel:'', email:'', relationship:'',
                    education:'', career:'', location:'', interest:[], language:'', gender:'', image:[], areacode:''};

    $scope.check = null;
    $scope.interest = '';

    initController();

    function initController() {
        $scope.user.email = $rootScope.globals.currentUser.email;
        UserService.getUser($rootScope.globals.currentUser.email).then(
        function(response) {
              $scope.user = response.data;
          }
      );
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
        $scope.user.relationship = "Marriaged";
      }
    }

    $scope.selectInterest = function(choice){
      if (choice === 1){
        $scope.interest = "Reading";
        $scope.user.interest.push($scope.interest);
      }
      else if (choice === 2){
        $scope.interest = "Travel";
        $scope.user.interest.push($scope.interest);
      }
      else if (choice === 3){
        $scope.interest = "Playing Sports";
        $scope.user.interest.push($scope.interest);
      }
      else if (choice === 4){
        $scope.interest = "Cooking";
        $scope.user.interest.push($scope.interest);
      }
      else if (choice === 5){
        $scope.interest = "Playing Games";
          $scope.user.interest.push($scope.interest);
      }
      else if (choice === 6){
        $scope.interest = "Study";
        $scope.user.interest.push($scope.interest);
      }
      else {
        $scope.interest = "Shopping";
        $scope.user.interest.push($scope.interest);
      }
    }

    $scope.edit = function(){
       console.log($scope.user);
       UserService.updateUser($scope.user);
       alert("Information Updated");
    }
}])

;
