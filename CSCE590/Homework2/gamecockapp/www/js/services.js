angular.module('starter.services', ['firebase'])
    
.factory("Auth", ["$firebaseAuth", "$rootScope", function ($firebaseAuth, $rootScope) {
    var ref = new Firebase(firebaseUrl);
    return $firebaseAuth(ref);
}])

.factory('Orders', function($firebase) {
    // Might use a resource here that returns a JSON array

    var ref = new Firebase(firebaseUrl);

    var categories = [{
        id: 1,
        name: 'Clothes'
    }, {
        id: 2,
        name: 'Food'
    }, {
        id: 3,
        name: 'Stationary'
    }];

    // Hopefully this will instatiate an array of orders, that
    // I can fill with the order function based on input parameters
    var orders = [];

    var users = []; 
    // I want this function to return a new user
    // localStorage.setItem("users", JSON.stringify(users));
    var storedNames = JSON.parse(localStorage.getItem("users"));
    //console.log(storedNames.length + "users found");
    //console.log(storedNames[0].username);


    return {
        addUser:function(user){
            users.push({username: user.userName, password: user.password});
            console.log(users.length +" saved");
            localStorage.setItem("users", JSON.stringify(users));
            alert("one user saved");
            console.log(user.username);
        },
        checkUser: function(username)
        {
            alert("getting username!")
            for (var i=0; i < users.length(); i++)
            {
                if (username == users[i].username)
                {
                    return users[i];
                }
            }
            return null;
        },
        getAllOrders: function() {
            return orders;
        },
        addOrder: function(inputOrder)
        {
            orders.push({
            productName: inputOrder.pname,
            productQty: inputOrder.pqty,
            productPrice: inputOrder.pprice,
            productCategory: inputOrder.pcategory,
            productOrderDate: inputOrder.porderdate
        });
          alert("saved an order")  
        }
    };
});
