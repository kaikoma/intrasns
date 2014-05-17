// 動作確認用mockサーバー
// grunt exで起動できます。
'use strict';

var express = require('express'),
 port = 3000;

var app = express();

app.configure(function(){
  app.use(express.methodOverride());
  app.use(express.bodyParser());
  app.use(express.static(__dirname + '/app'));
  app.use(app.router);
});

var usersMap = [
  {
    id: 1,
    userId: 'user001',
    name: 'ユーザ　００１',
    email: 'user001@aaa.com',
    resignationFlg: '0',
    adminFlg: '0'
  },
  {
    id: 2,
    userId: 'user002',
    name: 'ユーザ　００２',
    email: 'user002@aaa.com',
    resignationFlg: '0',
    adminFlg: '0'
  },
  {
    id: 3,
    userId: 'user003',
    name: 'ユーザ　００３',
    email: 'user003@aaa.com',
    resignationFlg: '0',
    adminFlg: '0'
  },
  {
    id: 4,
    userId: 'customer001',
    name: '利用者　００１',
    email: 'customer001@xxx.com',
    resignationFlg: '0',
    adminFlg: '0'
  },
  {
    id: 5,
    userId: 'customer002',
    name: '利用者　００２',
    email: 'customer002@xxx.com',
    resignationFlg: '0',
    adminFlg: '0'
  },
  {
    id: 6,
    userId: 'customer003',
    name: '利用者　００３',
    email: 'customer003@xxx.com',
    resignationFlg: '0',
    adminFlg: '0'
  },
  {
    id: 7,
    userId: 'admin001',
    name: '管理者　００１',
    email: 'admin001@zzz.com',
    resignationFlg: '0',
    adminFlg: '1'
  },
  {
    id: 8,
    userId: 'admin002',
    name: '管理者　００２',
    email: 'admin002@zzz.com',
    resignationFlg: '0',
    adminFlg: '1'
  },
  {
    id: 9,
    userId: 'admin003',
    name: '管理者　００３',
    email: 'admin003@zzz.com',
    resignationFlg: '0',
    adminFlg: '1'
  }
];
var nextId = 4;

app.get('/', function(req, res){
  res.redirect('/login.html');
});

app.get('/logout', function(req, res){
  res.redirect('/login.html');
});

app.post('/googleauth', function(req, res){
  res.redirect('/home.html');
});

app.get('/currentuser', function(req, res){
  res.send({ displayName: 'ログイン中ユーザ', email: 'aaa@bbb.com' });
});

app.get('/users', function(req, res){
  console.log('user list');
  res.send(usersMap);
});

app.get('/users/:id', function(req, res){
  console.log('user show');
  for (var i = 0; i < usersMap.length; i++) {
    if (usersMap[i].id === parseInt(req.params.id)) {
      res.send(usersMap[i]);
      return;
    }
  }
  res.send('存在しないユーザです。');
});

app.post('/users', function(req, res){
  console.log('user add');
  var user = req.body;
  user.id = nextId;
  nextId++;
  usersMap.push(user);
  res.send(user);
});

app.post('/users/:id', function(req, res){
  console.log('user update');
  for (var i = 0; i < usersMap.length; i++) {
    if (usersMap[i].id === parseInt(req.params.id)) {
      usersMap[i].userId = req.body.userId;
      usersMap[i].name = req.body.name;
      usersMap[i].email = req.body.email;
      usersMap[i].resignationFlg = req.body.resignationFlg;
      usersMap[i].adminFlg = req.body.adminFlg;
      res.send(usersMap[i]);
      console.log('updated:' + usersMap[i]);
      return;
    }
  }
  res.send('存在しないユーザです。');
});

app.delete('/users/:id', function(req, res){
  console.log('user delete');
  for (var i = 0; i < usersMap.length; i++) {
    if (usersMap[i].id === parseInt(req.params.id)) {
      usersMap.splice(i, 1);
      res.send(null);
      return;
    }
  }
  res.send('存在しないユーザです。');
});

app.listen(port);
console.log('Now serving the app at http://localhost:' + port + '/');
