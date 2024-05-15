var mysql = require('mysql');
var express = require("express");

var connection = mysql.createConnection({
    host: 'localhost',
    user: 'sa',
    password: 'dev@123',
    // server: 'TTLITODEV\\TTLDEVDB',
    port: 1433 ,
    database: 'TEST'
    

});
app.use(function(req,res,next){
res.header("Access-Control-Allow-Origin","*");
res.header("Access-Control-Allow-Origin","Origin,X-Requested-Width,Content-Type,Accept");
next();
});

connection.connect(function (err) {
    if (!err) {
        console.log("Database is connected ... nn");
    } else {
        console.log(err);
    }
});

connection.end();