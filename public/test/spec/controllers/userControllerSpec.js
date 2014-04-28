'use strict';

describe('Controllers', function () {
  var $scope,ctrl;

  // load the controller's module
  beforeEach(module('angApp'));
  beforeEach(function() {
    this.addMatchers({
      toEqualData: function(expected) {
        return angular.equals(this.actual, expected);
      }
    });
  });

  describe('NewsCtrl', function() {
    var mockBackend;
    var rootScope;
    beforeEach(inject(function($rootScope, $controller, $httpBackend) {
      mockBackend = $httpBackend;
      $scope = $rootScope.$new();
      rootScope = $rootScope;
      spyOn(rootScope, '$broadcast').andCallThrough();
      ctrl = $controller('NewsCtrl', {
        $scope: $scope,
        currentuser : {displayName: 'user001'}
      });
    }));

    it('newsの確認', function() {
      expect($scope.news.length).toBe(3);
    });

    it('ログインユーザ情報ブロードキャスト', function() {
      expect(rootScope.$broadcast).toHaveBeenCalledWith('currentuserBc', {displayName: 'user001'});
    });
  });

  describe('HeaderCtrl', function() {
    beforeEach(inject(function($rootScope, $controller) {
      $scope = $rootScope.$new();
      spyOn($scope, '$on').andCallThrough();
      ctrl = $controller('HeaderCtrl', {
        $scope: $scope
      });
      $rootScope.$broadcast('currentuserBc', {displayName: 'user001'});
    }));

    it('ログインユーザ情報取得', function() {
      expect($scope.$on).toHaveBeenCalled();
      expect($scope.currentuser).toEqual('user001');
    });
  });

});
