'use strict';

describe('UserServices', function () {

  // load the controller's module
  beforeEach(module('angApp'));
  beforeEach(function() {
    this.addMatchers({
      toEqualData: function(expected) {
        return angular.equals(this.actual, expected);
      }
    });
  });

  describe('User', function() {
    var mockBackend, userService;
    beforeEach(inject(function($httpBackend, User) {
      mockBackend = $httpBackend;
      userService = User;
    }));

    it('ユーザ情報取得', function() {
      var mockUser = {id: 1, userId: 'user001', name: 'ユーザ　００１', email: 'user001', resignationFlg: '0', adminFlg: '0'};

      mockBackend.expectGET('/users/1').respond(mockUser);

      var loadedUser;
      userService.get({id: 1}, function(user) {
        loadedUser = user;
      });
      expect(loadedUser).toBeUndefined();
      mockBackend.flush();
      expect(loadedUser).toEqualData(mockUser);
    });
  });

  describe('LoadUsers', function() {
    var mockBackend, load;
    beforeEach(inject(function($httpBackend, LoadUsers) {
      mockBackend = $httpBackend;
      load = LoadUsers;
    }));

    it('全ユーザ情報取得', function() {
      var mockUsers = [
        {id: 1, userId: 'user001', name: 'ユーザ　００１', email: 'user001', resignationFlg: '0', adminFlg: '0'},
        {id: 2, userId: 'user002', name: 'ユーザ　００２', email: 'user002', resignationFlg: '0', adminFlg: '0'},
        {id: 3, userId: 'user003', name: 'ユーザ　００３', email: 'user003', resignationFlg: '0', adminFlg: '0'},
        {id: 4, userId: 'user004', name: 'ユーザ　００４', email: 'user004', resignationFlg: '0', adminFlg: '0'},
        {id: 5, userId: 'user005', name: 'ユーザ　００５', email: 'user005', resignationFlg: '0', adminFlg: '0'}
      ];

      mockBackend.expectGET('/users').respond(mockUsers);

      var loadedUsers;
      load().then(function(users) {
        loadedUsers = users;
      });
      expect(loadedUsers).toBeUndefined();
      mockBackend.flush();
      expect(loadedUsers.length).toBe(5);
      expect(loadedUsers).toEqualData(mockUsers);
    });
  });

  describe('LoadUser', function() {
    var mockBackend, load;
    beforeEach(inject(function($httpBackend, LoadUser) {
      mockBackend = $httpBackend;
      load = LoadUser;
    }));

    it('ユーザ情報取得', function() {
      var mockUser = {id: 1, userId: 'user001',
      name: 'ユーザ　００１', email: 'user001', resignationFlg: '0', adminFlg: '0'};
      mockBackend.expectGET('/users/1').respond(mockUser);

      var loadedUser;
      load('1').then(function(user) {
        loadedUser = user;
      });
      expect(loadedUser).toBeUndefined();
      mockBackend.flush();
      expect(loadedUser).toEqualData(mockUser);
    });
  });

});
