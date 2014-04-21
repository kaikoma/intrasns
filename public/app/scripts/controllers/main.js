'use strict';

var angApp = angular.module('angApp');

angApp.controller('NewsCtrl', function($scope, $http) {
  $scope.news = [{title: 'news1', content: 'content1', update: '2014/01/01'}
				,{title: 'news2', content: 'content2', update: '2014/02/01'}
				,{title: 'news3', content: 'content3', update: '2014/03/01'}];
  $http.get('/currentuser').success(function(data){
  	$scope.currentuser = data.display_name;
  });
});
