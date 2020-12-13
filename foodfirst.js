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


//Order Details

app.post('/order', (req, res, next) => {
    var data = req.body;
    var Selected_Item_Name = data.Selected_Item_Name;
    var Selected_Item_Price = data.Selected_Item_Price;

    connection.query("SELECT * FROM orderDeatils WHERE Selected_Item_Name= ?", [Selected_Item_Name], function (err, result, fields) {
        connection.on('error', (err) => {
            console.log("[MySQL ERROR]", err);
        });
        
            var insert_cmd = "INSERT INTO orderDetails (Selected_Item_Name,Selected_Item_Price) values(?,?)";
            values = [Selected_Item_Name, Selected_Item_Price];

            console.log("executing: " + insert_cmd + " " + values);
            connection.query(insert_cmd, values, (err, results, fields) => {
                connection.on('err', (err) => {
                    console.log("[MySQL ERROR]", err);
                });
                res.json("User Ordered");
                console.log("Order Successful.");
            });
        
    });
});


app.get('/getItems', (req, res) => {   
    let sql = "SELECT * FROM items";
  let query = connection.query(sql, (err, results) => {
      console.log(results.length);
      res.setHeader('Content-Type', 'application/json');
      if(results.length !=0){

        var json = JSON.stringify({ 
            "res": "success", 
            "items": results, 
            "message": "data Received"
          });
          res.end(json);
      }else{
        var json = JSON.stringify({ 
            "res": "failed", 
            "items": results, 
            "message": "No data Received"
          });
          res.end(json);
      }
    
  });
        
  });


  app.get('/getUsername', (req, res) => {   
    let sql = "SELECT * FROM logindetails ORDER BY person_id DESC LIMIT 1 ";
  let query = connection.query(sql, (err, results) => {
      console.log(results.length);
      res.setHeader('Content-Type', 'application/json');
      res.send(results[0].name)
    
  });
        
  });


var server = app.listen(3000, () => {
    console.log("Server running at http://localhost:3000");
});
