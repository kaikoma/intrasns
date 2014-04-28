'use strict';

angular.module('angApp', [
  'ngCookies',
  'ngResource',
  'ngSanitize',
  'ngRoute'
])
.config(function ($routeProvider) {
  $routeProvider
    .when('/', {
      controller: 'NewsCtrl',
      resolve: {
        currentuser: function(LoginUser) {
          return LoginUser();
        }
      },
      templateUrl: '/views/news.html'
    })
    .when('/users/', {
      controller: 'UserListCtrl',
      resolve: {
        users: function(LoadUsers) {
          return LoadUsers();
        }
      },
      templateUrl: '/views/user/list.html'
    })
    .when('/users/new', {
      controller: 'UserNewCtrl',
      templateUrl: '/views/user/new.html'
    })
    .when('/users/edit/:id', {
      controller: 'UserEditCtrl',
      resolve: {
        user: function($route, LoadUser) {
          return LoadUser($route.current.params.id);
        }
      },
      templateUrl: '/views/user/edit.html'
    });
}).run(['$rootScope', '$location', function($rootScope, $location){
  var path = function() { return $location.path();};
  $rootScope.$watch(path, function(newVal){
    //console.log('path:' + newVal);
    $rootScope.activetab = newVal;
  });
}]);

