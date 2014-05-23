'use strict';

angular.module('angApp', [
  'ngCookies',
  'ngResource',
  'ngSanitize',
  'ngRoute',
  'angApp.service'
])
.config(['$routeProvider', function ($routeProvider) {
  $routeProvider
    .when('/', {
      controller: 'NewsCtrl',
      resolve: {
        currentuser: ['LoginUser', function(LoginUser) {
          return LoginUser();
        }]
      },
      templateUrl: '/views/news.html'
    })
    .when('/users/', {
      controller: 'UserListCtrl',
      resolve: {
        users: ['LoadUsers', function(LoadUsers) {
          return LoadUsers();
        }]
      },
      templateUrl: '/views/user/list.html'
    })
    .when('/users/new', {
      controller: 'UserNewCtrl',
      templateUrl: '/views/user/edit.html'
    })
    .when('/users/edit/:id', {
      controller: 'UserEditCtrl',
      resolve: {
        user: ['$route', 'LoadUser', function($route, LoadUser) {
          return LoadUser($route.current.params.id);
        }]
      },
      templateUrl: '/views/user/edit.html'
    });
}]).run(['$rootScope', '$location', function($rootScope, $location){
  var path = function() { return $location.path();};
  $rootScope.$watch(path, function(newVal){
    //console.log('path:' + newVal);
    $rootScope.activetab = newVal;
  });
}]);

