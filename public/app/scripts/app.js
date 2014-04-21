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
        templateUrl: '/views/news.html',
        controller: 'NewsCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
