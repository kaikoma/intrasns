'use strict';

var angApp = angular.module('angApp');

angApp.controller('NewsCtrl', ['$rootScope', '$scope', '$http', 'currentuser',
  function($rootScope, $scope, $http, currentuser) {

  $scope.news = [{title: 'news1', content: 'content1', update: '2014/01/01'},
                {title: 'news2', content: 'content2', update: '2014/02/01'},
                {title: 'news3', content: 'content3', update: '2014/03/01'}];
  $rootScope.$broadcast('currentuserBc', currentuser);
}]);

angApp.controller('HeaderCtrl', ['$scope', function($scope) {
  $scope.$on('currentuserBc', function(event, currentuser) {
    $scope.currentuser = currentuser.displayName;
  });
}]);
