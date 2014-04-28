'use strict';

var angApp = angular.module('angApp');

angApp.factory('User', ['$resource', function($resource) {
  return $resource('/users/:id', {id: '@id'});
}]);

angApp.factory('LoginUser', ['$q','$http', function($q, $http) {
  return function() {
    var delay = $q.defer();
    $http.get('/currentuser')
    .success(function(currentuser) {
      delay.resolve(currentuser);
    })
    .error(function() {
        delay.reject();
      });
    return delay.promise;
  };
}]);

angApp.factory('LoadUsers', ['User', '$q', function(User, $q) {
  return function() {
    var delay = $q.defer();
    User.query(function(users) {
      delay.resolve(users);
    },
    function() {
        delay.reject();
      });
    return delay.promise;
  };
}]);

angApp.factory('LoadUser', ['User', '$q', function(User, $q) {
  return function(targetId) {
    var delay = $q.defer();
    User.get({id: targetId}, function(user) {
      delay.resolve(user);
    },
    function() {
        delay.reject();
      });
    return delay.promise;
  };
}]);
