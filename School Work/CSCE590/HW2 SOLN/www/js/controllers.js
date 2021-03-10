angular.module('starter.controllers', [])

.controller('LoginCtrl', function ($scope, $ionicModal, $state, $ionicLoading, $rootScope) {
    //console.log('Login Controller Initialized');

    var auth = firebase.auth();

    $ionicModal.fromTemplateUrl('templates/signup.html', {
        scope: $scope
    }).then(function (modal) {
        $scope.modal = modal;
    });

    $scope.createUser = function (user) {
        console.log("Create User Function called");
        if (user && user.email && user.password && user.displayName) {
            $ionicLoading.show({
                template: 'Signing Up...'
            });

            auth.createUserWithEmailAndPassword(user.email,
              user.password).then(function (userData) {
                alert("User created successfully!");
                var cur = auth.currentUser;
                /*cur.sendEmailVerification().then(function() {
                  // Email sent.
                  alert("A verifying email has been sent");
                }, function(error) {
                  // An error happened.
                });*/

                cur.updateProfile({
                  displayName: user.displayName
                }).then(function() {
                  console.log("update dispaly name successfully");
                }, function(error) {
                  // An error happened.
                });

                $ionicLoading.hide();
                $scope.modal.hide();
                $state.go('tab.orders');
            }).catch(function (error) {
                alert("Error: " + error);
                $ionicLoading.hide();
            });
        } else
            alert("Please fill all details");
    }

    $scope.signIn = function (user) {

        if (user && user.email && user.pwdForLogin) {
            $ionicLoading.show({
                template: 'Signing In...'
            });
            auth.signInWithEmailAndPassword(
                user.email,
                user.pwdForLogin
            ).then(function (authData) {
                $ionicLoading.hide();
                $state.go('tab.orders');
            }).catch(function (error) {
                alert("Authentication failed:" + error.message);
                $ionicLoading.hide();
            });
        } else
            alert("Please enter email and password both");
    }

    $scope.reSet = function (user) {
        if (user.email) {
            auth.sendPasswordResetEmail(user.email).then(function() {
                console.log("reset email sent");
                alert("reset email sent");
            }).catch(function(error) {
                alert("reset email failed:" + error.message);
            });
        } else
            alert("Please enter email");
    }
})

.controller('OrdersCtrl', function($scope, $rootScope, $ionicModal, $state, $ionicLoading) {
  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
  //
  //$scope.$on('$ionicView.enter', function(e) {
  //});

  $ionicModal.fromTemplateUrl('templates/new-order.html', {
      scope: $scope
  }).then(function (modal) {
      $scope.modal = modal;
  });

  //ref of orders based on user id
  $scope.createOrder = function(product){
    if (product && product.name && product.quantity && product.price && product.category) {
      $ionicLoading.show({
          template: 'Create Order...'
      });

      var d1 = new Date();
      product.createDate = d1.toLocaleDateString("en-US"); 

      $rootScope.orders.$add(product);

      $ionicLoading.hide();
      $scope.modal.hide();
      $state.go('tab.orders');
    }
    else
        alert("Please fill all details");
  }

  $scope.remove = function(order) {
    $rootScope.orders.$remove(order);
  };
})

.controller('OrderDetailCtrl', function($scope, $stateParams, $rootScope) {
  //console.log($stateParams.orderId);
  $scope.order = $rootScope.orders.$getRecord($stateParams.orderId);
})

.controller('AccountCtrl', function($scope, $rootScope) {});
