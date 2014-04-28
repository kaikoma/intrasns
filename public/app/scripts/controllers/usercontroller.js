'use strict';

var angApp = angular.module('angApp');

angApp.controller('UserListCtrl', function($scope, users) {
  $scope.users = users;
  $scope.predicate = 'userId';
  $scope.reverse = false;
  var sortNone = 'fa fa-sort inactive-sort-icon';
  var sortAsc = 'fa fa-sort-asc active-sort-icon';
  var sortDesc = 'fa fa-sort-desc active-sort-icon';
  $scope.sortIcon = {userId: sortAsc,
                     name: sortNone,
                     email: sortNone,
                     adminFlg: sortNone};

  $scope.sort = function(sortColumn) {
    if ($scope.predicate === sortColumn) {
      $scope.reverse = !$scope.reverse;
      if ($scope.reverse) {
        $scope.sortIcon[sortColumn] = sortDesc;
      } else {
        $scope.sortIcon[sortColumn] = sortAsc;
      }
    } else {
      $scope.predicate = sortColumn;
      $scope.reverse = false;
      for (var key in $scope.sortIcon) {
        if (key === sortColumn) {
          $scope.sortIcon[key] = sortAsc;
        } else {
          $scope.sortIcon[key] = sortNone;
        }
      }
    }
  };
});

angApp.controller('UserNewCtrl', function($scope, $location, User) {
  $scope.user = new User();

  $scope.submit = function() {
    $scope.user.resignationFlg = '0';
    $scope.user.$save(function() {
      $location.path('/users/');
    });
  };
  $scope.cancel = function() {
    $location.path('/users/');
  };
});

angApp.controller('UserEditCtrl', function($scope, $location, $routeParams, User, user) {
  $scope.user = user;
  console.log($scope.user);

  $scope.submit = function() {
    User.save({}, $scope.user);
    $location.path('/users/');
  };
  $scope.delete = function() {
    User.remove({}, $scope.user);
    $location.path('/users/');
  };
  $scope.cancel = function() {
    $location.path('/users/');
  };

});
