angular.module('starter.controllers', [])

.controller('LoginCtrl', function ($scope, $ionicModal, $state, $firebaseAuth, $ionicLoading, $rootScope) {    
    var ref = new Firebase($scope.firebaseUrl);
    var auth = $firebaseAuth(ref);

    $ionicModal.fromTemplateUrl('templates/login.html', {
        scope: $scope
    }).then(function (modal) {
        $scope.modal = modal;
    });

    $scope.createUser = function (user) {
        console.log("creating a new user");
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

        tempUser = Orders.checkUser(user.username);

        console.log(tempUser);

        if (tempUser)
        {
        if (tempUser.username == user.username && tempUser.password == user.password)
        {
           console.log("Found a valid user");
           $state.go("tab.orders");
        } 
        else 
        {
          alert("User not found");
          $state.go("login")
        }
        }
        else
        {
          $state.go("login")
        }
    }

    $scope.register = function(user) {
          
        tempUser = Chats.checkUser(user);
        if (tempUser) 
        {
            alert("This user already exists");
        } 
        else
        {
          Orders.addUser(user);
          console.log(user.username);
          alert("Registered!");
          $state.go("tab-orders");
        }
      }

}) // End LoginCtrl


.controller('OrdersCtrl', function($scope, $state, Orders) {
  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
  //
  //$scope.$on('$ionicView.enter', function(e) {
  //});

  $scope.orders = Orders.getAllOrders();
  $scope.remove = function(order) {
    Orders.remove(order);
  }
  $scope.neworder = function(argument){
    $state.go("tab.newOrder");
  }
})

.controller('OrderDetailCtrl', function($scope, $stateParams, $state, Orders) {
})

.controller('NewOrderCtrl', function($scope, $state, Orders) {
// this function will add a new order to the array of orders
$scope.addOrder = function(order){

  Orders.addOrder(order);
  console.log(order.name);
  $state.go("tab.orders"); }
});
