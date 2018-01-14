angular.module('starter.controllers', [])

//.controller('LoginCtrl', function($state, $scope, Chats) {
.controller('LoginCtrl', function ($scope, $ionicModal, $state, $firebaseAuth, $ionicLoading, $rootScope) {    
    var ref = new Firebase($scope.firebaseUrl);
    var auth = $firebaseAuth(ref);

    $ionicModal.fromTemplateUrl('templates/signup.html', {
        scope: $scope
    }).then(function (modal) {
        $scope.modal = modal;
    });

    $scope.createUser = function (user) {
        console.log("Create User Function called");
        if (user && user.email && user.password && user.displayname) {
            $ionicLoading.show({
                template: 'Signing Up...'
            });

            auth.$createUser({
                email: user.email,
                password: user.password
            }).then(function (userData) {
                alert("User created successfully!");
                ref.child("users").child(userData.uid).set({
                    email: user.email,
                    displayName: user.displayname
                });
                $ionicLoading.hide();
                $scope.modal.hide();
            }).catch(function (error) {
                alert("Error: " + error);
                $ionicLoading.hide();
            });
        } else
            alert("Please fill all details");
    }

    $scope.login = function(user) {

        u = Chats.getuser(user.username);

        console.log(u);

        if (u) {
            if (u.username == user.username && u.password == user.password) {
                $state.go("tab.orders");
            } else {
                alert("User not found");
                $state.go("login.setup")
            }
        } else {
            alert("User not found");
            $state.go("login.setup")
        }
    }

    $scope.register = function(argument) {
        $state.go("login.setup")
    }

    $scope.changePassword = function(argument) {
        $state.go("login.password")
    }

})


.controller('SetupCtrl', function($scope, $state, Chats) {

    // console.log(this.address +" ****");
    $scope.register = function(user) {
        //alert("Registering...");
        if (user) {
            u = Chats.getuser(user);
            if (u) {
                alert("This user already exists");
            } else {
                if (user.email == "" && user.password == "") {
                    alert("Check Email / Password");
                } else {
                    Chats.adduser(user);
                    console.log(user.username);
                    $state.go("login.login");
                }
            }
        }
    }

    $scope.cancel = function(argument) {
        // body...
        console.log("cancel");
        $state.go("login.login");
    }

})

.controller('NewOrderCtrl', function($scope, $ionicHistory, $state, Chats) {

    $ionicHistory.clearHistory();

    $scope.neworder = function(order) {
        //alert("Registering New Order");
        if (order) {

            img = "";
            if (order.category == 0) {
                order.img = 'img/food.jpg';
            }
            if (order.category == 1) {
                order.img = 'img/stationary.jpg';
            }
            if (order.category == 2) {
                order.img = 'img/clothing.jpg';
            }

            Chats.addorder(order);
            console.log(order.name);
            $state.go("tab.orders");
        }


    };
    $scope.cancel = function(argument) {
        $state.go("tab.orders");
    };
})

.controller('OrderCtrl', function($scope, $state, Chats) {

    $scope.orders = Chats.allorders();
    $scope.remove = function(order) {
        Chats.remove(order);
    };
    $scope.neworder = function(argument) {
        $state.go("tab.neworder");
    }
})

.controller('OrderDetailCtrl', function($scope, $stateParams, $state, Chats) {
    o = Chats.getorder($stateParams.orderId);
    o.category = Chats.getcategory(o.category).name;
    $scope.order = o;
});
