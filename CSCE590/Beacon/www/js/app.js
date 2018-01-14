// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js

    /*Written by Anthony Frazier and Marcos Moraes
        University of South Carolina CSCE590 Spring 2017
        Latest build: 04/11/17
    */
angular.module('starter', ['ionic', 'starter.controllers', 'starter.directives', 'starter.services', 'ngCordova'])

.run(function($ionicPlatform) {
    $ionicPlatform.ready(function() {

        appid = 'vRSGCiFjE4ou3efoZAYzQsR1iyShj8WSXJ8r5ooy';
        jskey = 'iGKUbu28bOFEvfZcBX3oTGVwxhsfYyTh09Vp52b0';
        Parse.initialize(appid, jskey);
        Parse.serverURL = 'https://parseapi.back4app.com';

        var query = new Parse.Query(Parse.User);
        query.find().then(function(objs) {
            //console.log("User no:" + objs.length);
        });


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
   $ionicPlatform.ready(function(){
   if(window.ParsePushPlugin){
      ParsePushPlugin.getInstallationId(function(id) {
         // note that the javascript client has its own installation id,
         // which is different from the device installation id.
          alert("device installationId: " + id);
      }, function(e) {
          alert('error');
      });

      ParsePushPlugin.getSubscriptions(function(subscriptions) {
          alert(subscriptions);
      }, function(e) {
          alert('error');
      });

      ParsePushPlugin.subscribe('SampleChannel', function(msg) {
          alert('OK');
      }, function(e) {
          alert('error');
      });

      ParsePushPlugin.unsubscribe('SampleChannel', function(msg) {
          alert('OK');
      }, function(e) {
          alert('error');
      });
   }
});

    $ionicPlatform.ready(function(){
   if(window.ParsePushPlugin){
    ParsePushPlugin.on('receivePN', function(pn){
        alert('yo i got this push notification:' + JSON.stringify(pn));
        alert("notification recieved " + pn);
        console.log("message recieved");
    });

    //
    //you can also listen to your own custom events
    // Note: to push custom event, include 'event' key in your push payload,
      // e.g. {alert: "sup", event:'chat'}
    ParsePushPlugin.on('receivePN:chat', chatEventHandler);
    ParsePushPlugin.on('receivePN:serverMaintenance', serverMaintenanceHandler);

      //
      // When the app is off or in background, push notifications get added
      // to the notification tray. When a user open a notification, you
      // can catch it via openPN
      ParsePushPlugin.on('openPN', function(pn){
        alert('a notification was opened:' + JSON.stringify(pn));
    });
   }
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

    // .state('tab.neworder', {
    //     url: '/neworder',
    //     views: {
    //         'tab-neworder': {
    //             templateUrl: 'templates/tab-neworder.html',
    //             controller: 'NewOrderCtrl'
    //         }
    //     }
    // })

    .state('tab.map', {
        url: '/map',
        views: {
            'tab-map': {
                templateUrl: 'templates/tab-map.html',
                controller: 'MapCtrl'
            }
        }
    })

    .state('tab.maps', {
        url: '/maps',
        views: {
            'tab-maps': {
                templateUrl: 'templates/tab-maps.html',
                controller: 'MapController'
            }
        }
    })

    .state('tab.list', {
            url: '/list',
            views: {
                'tab-list': {
                    templateUrl: 'templates/tab-list.html',
                    controller: 'ListOrgCtrl'
                }
            }
        })
        .state('tab.list-detail', {
            url: '/list-detail/:Id',
            views: {
                'tab-list': {
                    templateUrl: 'templates/list-detail.html',
                    controller: 'ListDetailCtrl'
                }
            }
        })

    .state('tab.neworg', {
        url: '/neworg',
        views: {
            'tab-neworg': {
                templateUrl: 'templates/tab-neworg.html',
                controller: 'NewOrgCtrl'
            }
        }
    })

    .state('tab.editorg', {
            url: '/editorg',
            views: {
                'tab-editorg': {
                    templateUrl: 'templates/tab-editorg.html',
                    controller: 'EditOrgCtrl'
                }
            }
        })
        .state('tab.org-detail', {
            url: '/orgs-detail/:Id',
            views: {
                'tab-editorg': {
                    templateUrl: 'templates/org-detail.html',
                    controller: 'OrgDetailCtrl'
                }
            }
        })
        // .state('tab.reqfood', {
        //     url: '/reqfood/:id',
        //     views: {
        //         'tab-reqfood': {
        //             templateUrl: 'templates/tab-reqfood.html',
        //             controller: 'ReqFoodCtrl'
        //         }
        //     }
        // })

    .state('tab.organization', {
        url: '/organization',
        views: {
            'tab-organization': {
                templateUrl: 'templates/tab-organization.html',
                controller: 'OrganizationCtrl'
            }
        }
    })

    // .state('tab.orders', {
    //         url: '/orders',
    //         views: {
    //             'tab-orders': {
    //                 templateUrl: 'templates/tab-orders.html',
    //                 controller: 'OrderCtrl'
    //             }
    //         }
    //     })
    //     .state('tab.order-detail', {
    //         url: '/orders/:Id',
    //         views: {
    //             'tab-orders': {
    //                 templateUrl: 'templates/order-detail.html',
    //                 controller: 'OrderDetailCtrl'
    //             }
    //         }
    //     })

    //Added
    .state('tab.volunteerlist', {
            url: '/volunteer',
            views: {
                    templateUrl: 'templates/tab-volunteerlist.html',
                    controller: 'VolunteerListCtrl'
                }
            })

    .state('login', {
        url: '/login',
        abstract: true,
        templateUrl: 'templates/login.html'
    })

    .state('login.login', {
            url: '/login',
            views: {
                'indexContainer': {
                    templateUrl: 'templates/login-login.html',
                    controller: 'LoginCtrl'
                }
            }
        })
        .state('login.setup', {
            url: '/setup',
            views: {
                'indexContainer': {
                    templateUrl: 'templates/login-setup.html',
                    controller: 'SetupCtrl'
                }
            }
        });

    // if none of the above states are matched, use this as the fallback
    $urlRouterProvider.otherwise('/login/login');

});
