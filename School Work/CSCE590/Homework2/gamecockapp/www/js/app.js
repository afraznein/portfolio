// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js

var firebaseUrl = "https://homework2-csce590.firebaseio.com";
var firebase = require('firebase');

function onDeviceReady() {
    angular.bootstrap(document, ["starter"]);
}

document.addEventListener("deviceready", onDeviceReady, false);

//angular.module('starter', [])
angular.module('starter', ['ionic', 'firebase', 'starter.controllers', 'starter.services'])

/*
.run(function($ionicPlatform) {
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
    });
})
*/

.run(function ($ionicPlatform, $rootScope, $location, Auth, $ionicLoading) {
$ionicPlatform.ready(function () {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins.Keyboard) 
    {
        cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
    }
    if (window.StatusBar)
    {
        // org.apache.cordova.statusbar required
        StatusBar.styleDefault();
    }
    // To Resolve Bug
    ionic.Platform.fullScreen();

    $rootScope.firebaseUrl = firebaseUrl;
    $rootScope.displayName = null;

    Auth.$onAuth(function (authData) {
    if (authData)
    {
        console.log("Logged in as:", authData.uid);
    } 
    else 
    {
        console.log("Logged out");
        $ionicLoading.hide();
        $location.path('/login');
    }

    });


    $rootScope.logout = function () {
        console.log("Logging out from the app");
        $ionicLoading.show({
            template: 'Logging Out...'
        });
        Auth.$unauth();
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

    // setup an abstract state for the tabs directive
        .state('tab', {
        url: '/tab',
        abstract: true,
        templateUrl: 'templates/tabs.html'
    })

    // Each tab has its own nav history stack:
    // The login state will 
    .state('login', {
            url: '/login',
            templateUrl: 'templates/login.html',
            controller: 'LoginCtrl'
        })

    .state('tab.orders', {
            url: '/orders',
            views: {
                'tab-orders': {
                    templateUrl: 'templates/tab-orders.html',
                    controller: 'OrdersCtrl'
                }
            }
        })
        .state('order-detail', {
            url: '/order:details',
            views: {
                'order-details': {
                    templateUrl: 'templates/order-detail.html',
                    controller: 'OrderDetailCtrl'
                }
            }
        })

    .state('tab.newOrder', {
        url: '/newOrder',
        views: {
            'tab-newOrder': {
                templateUrl: 'templates/tab-newOrder.html',
                controller: 'NewOrderCtrl'
            }
        }
    });

    // if none of the above states are matched, use this as the fallback
    $urlRouterProvider.otherwise('/login');

});
