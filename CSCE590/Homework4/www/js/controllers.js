angular.module('starter.controllers', ['ngOpenFB', 'ngCordova'])

.controller('LoginCtrl', function($state, $scope, Camera, $q, UserService, $ionicLoading, ngFB) {
    //***************************************
    // This is the success callback from the login method

    $scope.login = function(user) {

        Parse.User.logIn(user.username, user.password, {
            success: function(user) {
                console.log(user.id);

                $state.go("tab.orders");
            },
            error: function(user, error) {
                alert("login failed:" + error);
                console.log("failed");
            }
        });
    }

    $scope.fbLogin = function () {
    ngFB.login({scope: 'email'}).then(
        function (response) {
            if (response.status === 'connected') {
                console.log('Facebook login succeeded');
                $scope.closeLogin();
            } else {
                alert('Facebook login failed');
            }
        });
}

    $scope.register = function(argument) {
        $state.go("login.setup")
    }

    $scope.changePassword = function(argument) {
        $state.go("login.password")
    }

})

.controller('SetupCtrl', function($scope, $state, Camera) {

    $scope.register = function(user) {
        console.log(user.username);
        console.log(user.password);

        var u = new Parse.User();
        u.set("email", user.username);
        u.set("password", user.password);
        u.set("username", user.username);

        u.signUp(null, {
            success: function(u) {
                console.log(u.id);
            },
            error: function(user, error) {
                console.log("Error: " + error.code + " " + error.message);
                return;
            }
        });

        $state.go("login.login");
    }

    $scope.cancel = function(argument) {
        console.log("cancel");
        $state.go("login.login");
    }

})

.controller('NewOrderCtrl', function($scope, $ionicHistory, $state, $cordovaCamera) {
   $scope.takeImage = function() {
        var options = {
            quality: 80,
            destinationType: Camera.DestinationType.DATA_URL,
            sourceType: Camera.PictureSourceType.CAMERA,
            allowEdit: true,
            encodingType: Camera.EncodingType.JPEG,
            targetWidth: 250,
            targetHeight: 250,
            popoverOptions: CameraPopoverOptions,
            saveToPhotoAlbum: false
        };
         
        $cordovaCamera.getPicture(options).then(function(imageData) {
            $scope.srcImage = "data:image/jpeg;base64," + imageData;
        }, function(err) {
            // error
        });
    };

/*
    document.getElementById("takeImage").addEventListener("click", takeImage);

    function takeImage() {
      navigator.camera.getPicture(onSuccess, onFail, { 
      quality: 80,
      destinationType: Camera.DestinationType.DATA_URL
   });

        function onSuccess(imageData) {
        var Image = document.getElementById('myImage');
        Image.src = "data:image/jpeg;base64," + imageData;
        }

        function onFail(message) {alert('Failed because: ' + message);}
    }
*/
/*
$scope.takeImage = function() {
    console.log('Getting camera');
    Camera.getPicture({
      quality: 80,
      targetWidth: 250,
      targetHeight: 250,
      sourceType: 1,
      saveToPhotoAlbum: false

    }).then(function(imageURI) {
      console.log(imageURI);
      $scope.lastPhoto = imageURI;
    }, function(err) {
      console.err(err);
    });
/*    
    $Camera.getPicture(options).then(function(imageData) {
        $scope.Image = "data:image/jpeg;base64," + imageData;
    }, function(err) {
        // error
    });
*/
/*
    $scope.takeImage = function() {
        var options = {
            quality: 80,
            destinationType: Camera.DestinationType.DATA_URL,
            sourceType: Camera.PictureSourceType.CAMERA,
            allowEdit: true,
            encodingType: Camera.EncodingType.JPEG,
            targetWidth: 250,
            targetHeight: 250,
            popoverOptions: CameraPopoverOptions,
            saveToPhotoAlbum: false
        };

        $Camera.getPicture(options).then(function(imageData) {
            $scope.Image = "data:image/jpeg;base64," + imageData;
        }, function(err) {
            // error
        });
    }

*/
/*
 $scope.takeImage = function() {
    console.log('Getting camera');
    Camera.getPicture({
      quality: 75,
      targetWidth: 320,
      targetHeight: 320,
      saveToPhotoAlbum: false
    }).then(function(imageURI) {
      console.log(imageURI);
      $scope.lastPhoto = imageURI;
    }, function(err) {
      console.err(err);
    });

}
*/
    $scope.neworder = function(order) {
        if (order) {

            var Order = Parse.Object.extend("Order");
            var o = new Order();
            o.set("name", order.name);
            o.set("quantity", order.qty);
            o.set("date", order.date);
            o.set("price", order.price);
            o.set("category", order.category);
            if ($scope.Image != null)
            {
                 o.set("pic", Parse.File("mypic.jpg", { base64: $scope.Image }));
            }
            o.save(null, {
                success: function(o) {
                    $scope.apply();
                },
                error: function(o, error) {
                    alert('Failed to create new object, with error code: ' + error.message);
                }
            });

            $state.go("tab.orders");
        }


    };
    $scope.cancel = function(argument) {
        $state.go("tab.orders");
    };
    $scope.addpic = function(argument) {
        $state.go("tab.orders");
    };
})


.controller('OrderCtrl', function($scope, $state) {        

        appid = 'bSppaB5PgE8g2IsuRur1pTF8CAtxw1lZNqPWudzH';
        jskey = 'GHh7inqZiYzBPjm7JDCFp8LQ1NCK8opZElroXN9i';
        Parse.initialize(appid, jskey);
        Parse.serverURL = 'https://parseapi.back4app.com';

    var self = this;

    var img = "";

    var Order = Parse.Object.extend("Order");
    var query = new Parse.Query(Order);

    query.find().then(function(objs) { console.log("Searching");

        $scope.orders = [];
        for (var i = objs.length - 1; i >= 0; i--) {

            $scope.orders.push({
                qty: objs[i].get("quantity"),
                name: objs[i].get("name"),
                img: img,
                price: objs[i].get("price"),
                date: objs[i].get("date"),
                category: objs[i].get("category"),
                search: objs[i].get("name"),
                id: objs[i].id             
            });

        }
        $scope.apply();
    });


    $scope.remove = function(order) {
        id = order.id;

        var Order = Parse.Object.extend("Order");
        var o = new Order();
        o.id = id;
        o.destroy({
            success: function(myObject) {
                console.log("deleted");
                index = $scope.orders.indexOf(order);
                $scope.orders.splice(index, 1);
                $scope.$apply();
            },
            error: function(myObject, error) {

            }
        })
    }

    $scope.neworder = function(argument) {
        $state.go("tab.neworder");
    }
})

.controller('OrderDetailCtrl', function($scope, $stateParams, $state) {

    var Order = Parse.Object.extend("Order");
    var query = new Parse.Query(Order);
    query.equalTo("id", $stateParams.id);
    query.find().then(function(objs) {
        for (var i = objs.length - 1; i >= 0; i--) {
            if (objs[i].id === $stateParams.Id) {
                $scope.name = objs[i].get("name");
                $scope.orderId = objs[i].get("orderId");
                $scope.qty = objs[i].get("quantity");
                $scope.price = objs[i].get("price");
                $scope.date = objs[i].get("date");
                $scope.category = objs[i].get("category");
                $scope.pic = objs[i].get("pic").url();
            }

        }
    });

});
