angular.module('starter.services', [])

.service('UserService', function() {
  // For the purpose of this example I will store user data on ionic local storage but you should save it on a database
  var setUser = function(user_data) {
    window.localStorage.starter_facebook_user = JSON.stringify(user_data);
  };

  var getUser = function(){
    return JSON.parse(window.localStorage.starter_facebook_user || '{}');
  };

  return {
    getUser: getUser,
    setUser: setUser
  };
})

.factory('Camera', ['$q', function($q) {

    var categories = [{
        id: 0,
        name: 'Food'    
    }, {
        id: 1,
        name: 'Stationary'
    }, {
        id: 2,
        name: 'Clothing'
    }];

    // Some fake testing data
    var orders = [{
        id: 0,
        name: 'Order 1 Food',
        qty: 10,
        price: 75,
        category: 0,
        date: '01/02',
        img: 'img/food.jpg'
    }, {
        id: 1,
        name: 'Order 2 Stationary',
        qty: 5,
        price: 175,
        category: 1,
        date: '01/01',
        img: 'img/stationary.jpg'
    }, {
        id: 2,
        name: 'Order 2 Clothing',
        qty: 1,
        price: 99,
        category: 2,
        date: '01/03',
        img: 'img/clothing.jpg'
    }];

    var users = [{
        username: 'a',
        password: 'a',
    }, {
        username: 'jimmy',
        password: 'Sparrow',
    }, {
        username: 'user2',
        password: 'xxxxx',
    }];
    // localStorage.setItem("users", JSON.stringify(users));
    var storedNames = JSON.parse(localStorage.getItem("users"));
    //console.log(storedNames.length + "users found");
    //console.log(storedNames[0].username);

    //...

    return {
        adduser: function(user) {
            users.push({ username: user.username, password: user.password });
            console.log(users.length + " saved");
            localStorage.setItem("users", JSON.stringify(users));
            //alert("one user saved");
            console.log(user.username);
        },
        getuser: function(username) {
            //alert("asfdasdfa");
            console.log(username);
            for (var i = 0; i < users.length; i++) {
                if (users[i].username == username) {
                    return users[i];
                }
            }
            return null;
        },
        allorders: function() {
            return orders;
        },
        addorder: function(order) {

            orders.push({
                id: orders.length + 1,
                name: order.name,
                qty: order.qty,
                price: order.price,
                category: order.category,
                date: order.date,
                img: order.img
            });

            //alert("one order saved");

            console.log(orders.length);

        },
        getorder: function(orderId) {
            for (var i = 0; i < orders.length; i++) {
                if (orders[i].id === parseInt(orderId)) {
                    return orders[i];
                }
            }
            return null;
        },
        getcategory: function(categoryId) {
            for (var i = 0; i < categories.length; i++) {
                if (categories[i].id === parseInt(categoryId)) {
                    return categories[i];
                }
            }
            return null;
        }//,
/*
        getPicture: function(options) {
         var q = $q.defer();

         navigator.camera.getPicture(function(result) {
            q.resolve(result);
         }, function(err) {
            q.reject(err);
         }, options);

         return q.promise;
      }
  */
    };
}]);
