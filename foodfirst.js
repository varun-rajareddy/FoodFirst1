var express = require('express')

const app = express();
var mysql = require('mysql');
var bodyparser = require('body-parser')

var connection = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "",
    database: 'FoodFirst1'
});

app.use(bodyparser.json());
app.use(bodyparser.urlencoded({ extended: true }));


// User login

app.post('/login', (req, res, next) => {
    var data = req.body;
    var name = data.name;
    var number = data.number;

    connection.query("SELECT * FROM logindetails WHERE name= ?", [name], function (err, result, fields) {
        connection.on('error', (err) => {
            console.log("[MySQL ERROR]", err);
        });

        if (result && result.length) {
            res.json("User already exists");

        }
        else {
            var insert_cmd = "INSERT INTO logindetails (name,number) values(?,?)";
            values = [name, number];

            console.log("executing: " + insert_cmd + " " + values);
            connection.query(insert_cmd, values, (err, results, fields) => {
                connection.on('err', (err) => {
                    console.log("[MySQL ERROR]", err);
                });
                res.json("logged in!");
                console.log("Logged Successful.");
            });
        }
    });
});



var server = app.listen(3000, () => {
    console.log("Server running at http://localhost:3000");
});
