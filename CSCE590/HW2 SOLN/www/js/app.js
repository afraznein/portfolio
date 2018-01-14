// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js

var firebaseConfig = {
  apiKey: "AIzaSyBUt0c1toiaX6zcereI2i56s9SHZdisdeA",
  authDomain: "csce590-50e2d.firebaseapp.com",
  databaseURL: "https://csce590-50e2d.firebaseio.com/"
};

function onDeviceReady() {
    angular.bootstrap(document, ["starter"]);
}
//console.log("binding device ready");
// Registering onDeviceReady callback with deviceready event
document.addEventListener("deviceready", onDeviceReady, false);

angular.module('starter', ['ionic', 'firebase', 'starter.controllers', 'starter.services'])

.run(function($ionicPlatform, $rootScope, $location, $ionicLoading, $firebaseArray) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);

    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }

    // To Resolve Bug
    ionic.Platform.fullScreen();

    firebase.initializeApp(firebaseConfig);
    var auth = firebase.auth();

    auth.onAuthStateChanged(function(user) {
      if (user) {
        // User signed in!
        console.log("Logged in as:", user.uid);
        var orderListRef = firebase.database().ref('orders/' + user.uid);
        $rootScope.orders = $firebaseArray(orderListRef);
        $rootScope.displayName = user.displayName;
        //console.log($rootScope.displayName);
      } else {
        // User logged out
        console.log("Logged out");
        $ionicLoading.hide();
        $location.path('/login');
      }
    });


    $rootScope.logout = function () {
        $ionicLoading.show({
            template: 'Logging Out...'
        });
        auth.signOut().then(function() {
          // Sign-out successful.
          console.log("sign out successfully");
        }, function(error) {
          // An error happened.
          console.log("error signout");
        });
    }


    $rootScope.$on("$stateChangeError", function (event, toState, toParams, fromState, fromParams, error) {
        // We can catch the error thrown when the $requireAuth promise is rejected
        // and redirect the user back to the home page
        if (error === "AUTH_REQUIRED") {
            $location.path("/login");
        }
    });

  });
})

.config(function($stateProvider, $urlRouterProvider) {

  // Ionic uses AngularUI Router which uses the concept of states
  // Learn more here: https://github.com/angular-ui/ui-router
  // Set up the various states which the app can be in.
  // Each state's controller can be found in controllers.js
  $stateProvider

  // login state
  .state('login', {
    url: '/login',
    templateUrl: 'templates/login.html',
    controller: 'LoginCtrl'
  })

  // setup an abstract state for the tabs directive
  .state('tab', {
    url: '/tab',
    abstract: true,
    templateUrl: 'templates/tabs.html'
  })

  // Each tab has its own nav history stack:

  .state('tab.orders', {
      url: '/orders',
      views: {
        'tab-orders': {
          templateUrl: 'templates/tab-orders.html',
          controller: 'OrdersCtrl'
        }
      }
    })
    .state('tab.order-detail', {
      url: '/orders/:orderId',
      views: {
        'tab-orders': {
          templateUrl: 'templates/order-detail.html',
          controller: 'OrderDetailCtrl'
        }
      }
    })

  .state('tab.account', {
    url: '/account',
    views: {
      'tab-account': {
        templateUrl: 'templates/tab-account.html',
        controller: 'AccountCtrl'
      }
    }
  });

  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/login');

});
