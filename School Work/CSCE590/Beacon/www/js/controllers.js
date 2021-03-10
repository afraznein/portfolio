    /*Written by Anthony Frazier and Marcos Moraes
        University of South Carolina CSCE590 Spring 2017
        Latest build: 04/11/17
    */

angular.module('starter.controllers', [])

.controller('ListOrgCtrl', function($state, $scope, $cordovaGeolocation) {


   if(window.ParsePushPlugin){
    ParsePushPlugin.on('receivePN', function(pn){
        alert('yo i got this push notification:' + JSON.stringify(pn));
        alert("notification recieved " + pn);
        console.log("message recieved");
    });
 }

    var latitude;
    var longitude;
    var posOptions = { timeout: 10000, enableHighAccuracy: false };
    $cordovaGeolocation
        .getCurrentPosition(posOptions)
        .then(function(position) {
            latitude = position.coords.latitude;
            longitude = position.coords.longitude;
            var lat;
            var lon;
            var myLatlng = new google.maps.LatLng(latitude, longitude);
           // console.log(latitude);
            $scope.orgs = [];
            var Org = Parse.Object.extend("Organization");
            var query = new Parse.Query(Org);
            query.find().then(function(objs) {
                for (var i = objs.length - 1; i >= 0; i--) {
                    lat = objs[i].get("lat");
                    lon = objs[i].get("lng");

                    var myLatlng2 = new google.maps.LatLng(lat, lon);
             //       console.log(lat);
                    dist = google.maps.geometry.spherical.computeDistanceBetween(myLatlng, myLatlng2);
               //     console.log(dist);

                    if (dist <= 402336) {
                        $scope.orgs.push({
                            name: objs[i].get("name"),
                            phone: objs[i].get("phone"),
                            img: "img/beacon.png",
                            address: objs[i].get("address"),
                            city: objs[i].get("city"),
                            zipcode: objs[i].get("zipcode"),
                            state: objs[i].get("state"),
                            email: objs[i].get("email"),
                            search: objs[i].get("name"),
                            //distance: dist * 0.00062137,
                            id: objs[i].id //???                
                        });                        
                    }
                }
                $scope.apply();
            });
        });


})

.controller('MapController', function($state, $scope, $ionicLoading, $compile, $cordovaGeolocation) {

    $scope.initialize = function() {

        var geocoder = new google.maps.Geocoder();
        var latitude = 0;
        var longitude;
        var ret;

        // var address = "300 main st columbia";
        // var la;
        // var lo;
        // geocoder.geocode({ "address": address }, function(result, status) {
        // console.log(status);
        // if (status == google.maps.GeocoderStatus.OK) {
        //     console.log("2 " + result[0].geometry.location.lat());
        //     console.log("3 " + result[0].geometry.location.lng());
        //     la = result[0].geometry.location.lat();
        //     lo = result[0].geometry.location.lng();
        //     // ret = result[0].geometry.location;                                
        // } else {
        //     console.log('Error: ' + status);
        // }
        // });
        // console.log("la "+la);
        // console.log("lo "+lo);
        // var myLatlng2 = new google.maps.LatLng(la, lo);
        // console.log("a"+myLatlng);
        // console.log("b"+myLatlng2);

        var posOptions = { timeout: 10000, enableHighAccuracy: false };
        $cordovaGeolocation
            .getCurrentPosition(posOptions)
            .then(function(position) {

                latitude = position.coords.latitude;
                longitude = position.coords.longitude;

                var infowindow = new google.maps.InfoWindow();
                //var myLatlng2 = new google.maps.LatLng(33.9435751, -80.9646536);
                var myLatlng = new google.maps.LatLng(latitude, longitude);

                //console.log(google.maps.geometry.spherical.computeDistanceBetween(myLatlng, myLatlng2));

                var mapOptions = {
                    center: myLatlng,
                    zoom: 10,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };

              //  console.log("New Map");
                var map = new google.maps.Map(document.getElementById("map"),
                    mapOptions);

                var marker = new google.maps.Marker({
                    position: myLatlng,
                    map: map,
                    icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
                    title: "You are Here"
                });

       /*         google.maps.event.addListener(marker, 'mousedown', function() {
                    infowindow.open(map, marker);
                });*/

                var Org = Parse.Object.extend("Organization");
                var query = new Parse.Query(Org);
                var lat;
                var lgn;
                var dist;
                var orgName;
                query.find().then(function(objs) {
             //       console.log("Searching " + objs.length);

                    var list = [];
                    for (var i = objs.length - 1; i >= 0; i--) {
             //           console.log(i);
                        lat = objs[i].get("lat");
                        lng = objs[i].get("lng");
                        orgName = objs[i].get("name");
                        if (typeof(lat) === "undefined") {
             //               console.log(objs[i].get("address") + " " + objs[i].get("city"));
                            geocoder.geocode({ "address": objs[i].get("address") + " " + objs[i].get("city") }, function(result, status) {
              //                  console.log("2 " + status);
                                if (status == google.maps.GeocoderStatus.OK) {
                                    lat = result[0].geometry.location.lat();
                                    lng = result[0].geometry.location.lng();
                                } else {
              //                      console.log('Error: ' + status);
                                }
                            });
                        }
                        var myLatlng2 = new google.maps.LatLng(lat, lng);
                        dist = google.maps.geometry.spherical.computeDistanceBetween(myLatlng, myLatlng2);
           //             console.log(lat);
           //             console.log(lng);
            //            console.log(myLatlng2);
           //             console.log(dist);
                        if (dist <= 402336) {
                            var marker2 = new google.maps.Marker({
                                position: myLatlng2,
                                map: map,
                                title: orgName
                            });

                       /*     google.maps.event.addListener(marker2, 'click', function() {
                                infowindow.open(map, marker);
                            });*/
                        }
                    }
                });

                $scope.map = map;

            }, function(err) {
                alert(err.message);
            });

        // var infowindow = new google.maps.InfoWindow();
        // //var myLatlng = new google.maps.LatLng(33.9935751, -80.9646536);
        // var myLatlng = new google.maps.LatLng(33.9935751, -80.9646536);

        // var mapOptions = {
        //     center: myLatlng,
        //     zoom: 15,
        //     mapTypeId: google.maps.MapTypeId.ROADMAP
        // };

        // console.log("New Map");
        // var map = new google.maps.Map(document.getElementById("map"),
        //     mapOptions);

        // var marker = new google.maps.Marker({
        //     position: myLatlng,
        //     map: map,
        //     title: 'Uluru (Ayers Rock)'
        // });

        // google.maps.event.addListener(marker, 'click', function() {
        //     infowindow.open(map, marker);
        // });

        // var myLatlng2 = new google.maps.LatLng(43.07693, -89.381388);

        // var marker2 = new google.maps.Marker({
        //     position: myLatlng2,
        //     map: map,
        //     title: 'Uluru (Ayers Rock)'
        // });

        // google.maps.event.addListener(marker2, 'click', function() {
        //     infowindow.open(map, marker2);
        // });

        // $scope.map = map;
    };
    //google.maps.event.addDomListener(window, 'load', initialize);

    $scope.centerOnMe = function() {
        // var posOptions = { timeout: 10000, enableHighAccuracy: false };
        // $cordovaGeolocation
        //     .getCurrentPosition(posOptions)
        //     .then(function(position) {
        //         console.log("b");
        //         var lat = position.coords.latitude
        //         var long = position.coords.longitude
        //     }, function(err) {
        //         console.log(err.message);
        //     });

        //$scope.map.setCenter(new google.maps.LatLng(pos.coords.latitude, pos.coords.longitude));

    }
    $scope.list = function() {
        $state.go("tab.list");
    }
    $scope.exit = function() {
        $state.go("login.login");
    }

})

.controller('LoginCtrl', function($state, $scope, Chats) {
    $scope.login = function(user) {

        $state.go("tab.maps");

        Parse.User.logIn(user.username, user.password, {
             success: function(user) {
          //       console.log(user.id);

                 $state.go("tab.maps");
             },
             error: function(user, error) {
                 alert("login failed:" + error);
        //         console.log("failed");


             }
         });
    }

    $scope.register = function(argument) {
        $state.go("login.setup")
    }

    $scope.changePassword = function(argument) {
        $state.go("login.password")
    }

    $scope.survivorAccess = function(argument) {
        $state.go("tab.maps");
    }


})

.controller('OrganizationCtrl', function($state, $scope) {

    $scope.neworg = function(argument) {
        $state.go("tab.neworg");
    }

    $scope.requestFood = function(argument) {
        $state.go("tab.reqfood");
    }

    $scope.editorg = function(argument) {
        $state.go("tab.editorg");
    }

    $scope.map = function(argument) {
        $state.go("tab.map");
    }


})

.controller('ListOrgCtrl', function($state, $scope, $cordovaGeolocation) {

    var latitude;
    var longitude;
    var posOptions = { timeout: 10000, enableHighAccuracy: false };
    $cordovaGeolocation
        .getCurrentPosition(posOptions)
        .then(function(position) {
            latitude = position.coords.latitude;
            longitude = position.coords.longitude;
            var lat;
            var lon;
            var myLatlng = new google.maps.LatLng(latitude, longitude);
    //        console.log(latitude);
            $scope.orgs = [];
            var Org = Parse.Object.extend("Organization");
            var query = new Parse.Query(Org);
            query.find().then(function(objs) {
                for (var i = objs.length - 1; i >= 0; i--) {
                    lat = objs[i].get("lat");
                    lon = objs[i].get("lng");

                    var myLatlng2 = new google.maps.LatLng(lat, lon);
     //               console.log(lat);
                    dist = google.maps.geometry.spherical.computeDistanceBetween(myLatlng, myLatlng2);
     //               console.log(dist);

                    if (dist <= 402336) {
                        $scope.orgs.push({
                            name: objs[i].get("name"),
                            phone: objs[i].get("phone"),
                            img: "img/beacon.png",
                            address: objs[i].get("address"),
                            city: objs[i].get("city"),
                            zipcode: objs[i].get("zipcode"),
                            state: objs[i].get("state"),
                            email: objs[i].get("email"),
                            search: objs[i].get("name"),
                            id: objs[i].id //???                
                        });                        
                    }
                }
                //$scope.apply();
                setTimeout(function() {
                    $scope.$apply(function(){

                    });
                }, 500);
            });
        });


})

.controller('EditOrgCtrl', function($state, $scope) {
    var self = this;

    var img = "";

    var Org = Parse.Object.extend("Organization");
    var query = new Parse.Query(Org);
    //query.equalTo("id", $stateParams.id);
    query.find().then(function(objs) {
   //     console.log("Searching");

        $scope.orgs = [];
        for (var i = objs.length - 1; i >= 0; i--) {

            // if (objs[i].get("category") == "Food") {
            //     img = "img/food.jpg";
            // }
            // if (objs[i].get("category") == "Stationary") {
            //     img = "img/clothing.jpg";
            // }
            // if (objs[i].get("category") == "Clothing") {
            //     img = "img/stationary.jpg";
            // }

            $scope.orgs.push({
                name: objs[i].get("name"),
                phone: objs[i].get("phone"),
                img: "img/beacon.png",
                address: objs[i].get("address"),
                city: objs[i].get("city"),
                zipcode: objs[i].get("zipcode"),
                state: objs[i].get("state"),
                email: objs[i].get("email"),
                search: objs[i].get("name"),
                id: objs[i].id //???                
            });
        }
        //$scope.apply();
         setTimeout(function() {
                    $scope.$apply(function(){

                    });
                }, 500);
    });


    $scope.remove = function(org) {
        id = org.id;

        var Org = Parse.Object.extend("Organization");
        var o = new Org();
        o.id = id;
        o.destroy({
            success: function(myObject) {
                index = $scope.orgs.indexOf(org);
                $scope.orgs.splice(index, 1);
                $scope.$apply();
                console.log("deleted");
            },
            error: function(myObject, error) {

            }
        })
    }

    $scope.request = function(argument) {
 //       console.log($scope.id);
        $state.go("tab.reqfood");
    }

    $scope.cancel = function(argument) {
        $state.go("tab.organization");
    }

})

.controller('NearOrgCtrl', function($state, $scope) {
    var self = this;

    var img = "";

    var Org = Parse.Object.extend("Organization");
    var query = new Parse.Query(Org);
    //query.equalTo("id", $stateParams.id);
    query.find().then(function(objs) {
//        console.log("Searching");

        $scope.orgs = [];
        for (var i = objs.length - 1; i >= 0; i--) {

            // if (objs[i].get("category") == "Food") {
            //     img = "img/food.jpg";
            // }
            // if (objs[i].get("category") == "Stationary") {
            //     img = "img/clothing.jpg";
            // }
            // if (objs[i].get("category") == "Clothing") {
            //     img = "img/stationary.jpg";
            // }

            $scope.orgs.push({
                name: objs[i].get("name"),
                phone: objs[i].get("phone"),
                img: "img/beacon.png",
                address: objs[i].get("address"),
                city: objs[i].get("city"),
                zipcode: objs[i].get("zipcode"),
                state: objs[i].get("state"),
                email: objs[i].get("email"),
                search: objs[i].get("name"),
                id: objs[i].id //???                
            });

        }
        $scope.apply();
    });


    $scope.remove = function(org) {
        id = org.id;

        var Org = Parse.Object.extend("Organization");
        var o = new Org();
        o.id = id;
        o.destroy({
            success: function(myObject) {
                index = $scope.orgs.indexOf(org);
                $scope.orgs.splice(index, 1);
                $scope.$apply();
//                console.log("deleted");
            },
            error: function(myObject, error) {

            }
        })
    }

    $scope.request = function(argument) {
//        console.log($scope.id);
        $state.go("tab.reqfood");
    }

    $scope.cancel = function(argument) {
        $state.go("tab.organization");
    }

})


.controller('SetupCtrl', function($scope, $state, Chats) {

    $scope.register = function(user) {
//        console.log(user.username);
 //       console.log(user.password);

        var u = new Parse.User();
        u.set("email", user.username);
        u.set("password", user.password);
        u.set("username", user.username);

        u.signUp(null, {
            success: function(u) {
 //               console.log(u.id);
            },
            error: function(user, error) {
  //              console.log("Error: " + error.code + " " + error.message);
                return;
            }
        });

        $state.go("login.login");
    }

    $scope.cancel = function(argument) {
 //       console.log("cancel");
        $state.go("login.login");
    }
})



.controller('ReqFoodCtrl', function($scope, $ionicHistory, $state, $stateParams) {
    $scope.cancel = function(argument) {
        $state.go("tab.organization");
    };
    $scope.reqfood = function(req) {
//        console.log($scope.id);
    };

})

.controller('NewOrgCtrl', function($scope, $ionicHistory, $state) {
    $scope.cancel = function(argument) {
        $state.go("tab.organization");
    };
    $scope.neworg = function(org) {
        var geocoder = new google.maps.Geocoder();
        var lat;
        var lon;

        if (org) {

            var Org = Parse.Object.extend("Organization");
            var o = new Org();
            o.set("name", org.name);
            o.set("address", org.address);
            o.set("city", org.city);
            o.set("zipcode", org.zipcode);
            o.set("state", org.state);
            o.set("phone", org.phone);
            o.set("email", org.email);

            var address = org.address + " " + org.city;
   //         console.log(address);
            geocoder.geocode({ "address": address }, function(result, status) {
   //             console.log("2 " + status);
                if (status == google.maps.GeocoderStatus.OK) {

                    lat = result[0].geometry.location.lat();
                    lon = result[0].geometry.location.lng();
                    // ret = result[0].geometry.location;
    //                console.log('Error: ' + lat);
                    o.set("lat", lat);
                    o.set("lng", lon);
                } else {
    //                console.log('Error: ' + status);
                }
            });

            var id;
            o.save(null, {
                success: function(o) {
    //                console.log(o.id);
                    id = o.id;

                    var Inventory = Parse.Object.extend("Inventory");
                    var inventory = new Inventory();

    //                console.log(id);
                    inventory.set("idOrg", o);
                    inventory.set("quantity", 0);
                    inventory.set("category", "Clothing");
                    inventory.save(null, {
                        success: function(inventory) {
                            //$scope.apply();
    //                        console.log(inventory);
                        },
                        error: function(inventory, error) {
                            alert('Failed to create new object, with error code: ' + error.message);
                        }
                    });
                    var Inventory2 = Parse.Object.extend("Inventory");
                    var inventory2 = new Inventory2();

                    inventory2.set("idOrg", o);
                    inventory2.set("quantity", 0);
                    inventory2.set("category", "Food");
                    inventory2.save(null, {
                        success: function(inventory2) {
                            //$scope.apply();
    //                        console.log(inventory2);
                        },
                        error: function(inventory2, error) {
                            alert('Failed to create new object, with error code: ' + error.message);
                        }
                    });

                    var Inventory3 = Parse.Object.extend("Inventory");
                    var inventory3 = new Inventory3();

                    inventory3.set("idOrg", o);
                    inventory3.set("quantity", 0);
                    inventory3.set("category", "Water");
                    inventory3.save(null, {
                        success: function(inventory3) {
                            //$scope.apply();
      //                      console.log(inventory3);
                        },
                        error: function(inventory3, error) {
                            alert('Failed to create new object, with error code: ' + error.message);
                        }
                    });
                    // ADDED
                    var Shift = Parse.Object.extend("Shift");
                    var shift = new Shift();

                    shift.set("idOrg", o);
                    shift.set("qtyVolunter", 0);
                    shift.save(null, {
                        success: function(shift) {
                            //$scope.apply();
   //                         console.log(shift);
                        },
                        error: function(shift, error) {
                            alert('Failed to create new object, with error code: ' + error.message);
                        }
                    });


                    $state.go("tab.editorg");
                    $scope.apply();
                },
                error: function(o, error) {
                    alert('Failed to create new object, with error code: ' + error.message);
                }
            });
        }
    };
})

.controller('ReqVolunteerCtrl', function($scope, $ionicHistory, $state) {
    $scope.cancel = function(argument) {
        $state.go("tab.maps");
    }

    $scope.request = function(s) {
        if (s) {

            var Org = Parse.Object.extend("Organization");
            var query = new Parse.Query(Org);
            query.equalTo("id", s.idOrg);
            query.find().then(function(objs) {
                for (var i = 0; i < objsInv.length; i++) {

                    var Shift = Parse.Object.extend("Shift");
                    var shift = new Shift();
                    shift.set("initDate", s.name);
                    shift.set("endDate", org.address);
                    shift.set("qtyVolunteers", org.city);
                    shift.set("idOrg", objsInv.id);

                    shift.save(null, {
                        success: function(shift) {
                            $state.go("tab.editorg");
                            $scope.apply();
                        },
                        error: function(shift, error) {
                            alert('Failed to create new object, with error code: ' + error.message);
                        }
                    });
                }
            });
        }
    };
})

.controller('ShiftCtrl', function($scope, $ionicHistory, $state) {
    $scope.cancel = function(argument) {
        $state.go("tab.maps");
    }

    $scope.request = function(s) {
        if (s) {

            var Org = Parse.Object.extend("Organization");
            var query = new Parse.Query(Org);
            query.equalTo("id", s.idOrg);
            query.find().then(function(objs) {
                for (var i = 0; i < objsInv.length; i++) {

                    var Shift = Parse.Object.extend("Shift");
                    var shift = new Shift();
                    shift.set("initDate", s.name);
                    shift.set("endDate", org.address);
                    shift.set("qtyVolunteers", org.city);
                    shift.set("idOrg", objsInv.id);

                    shift.save(null, {
                        success: function(shift) {
                            $state.go("tab.editorg");
                            $scope.apply();
                        },
                        error: function(shift, error) {
                            alert('Failed to create new object, with error code: ' + error.message);
                        }
                    });
                }
            });
        }
    };
})

.controller('ListDetailCtrl', function($scope, $stateParams, $state) {
    //console.log("id-" + $stateParams.Id);
    var Inventory = Parse.Object.extend("Inventory");
    var queryInv = new Parse.Query(Inventory);
    //queryInv.equalTo("idOrg", $stateParams.Id);
 //   console.log($stateParams.Id);
    //queryInv.include("idOrg");
    //queryInv.equalTo("idOrg", $stateParams.Id);        
    queryInv.find().then(function(objsInv) {

        // for (var i = objsInv.length - 1; i >= 0; i--) {
        //     console.log("fsgssss");
        //     var obj = results[i];
        //     $scope.name = obj.get("Organization").get("name");
        //     console.log("fsgssss");
        // }
        $scope.itens = [];
        //for (var i = objsInv.length - 1; i >= 0; i--) {
        for (var i = 0; i < objsInv.length; i++) {
   //         console.log("id link: " + objsInv[i].get("idOrg").id);
   //         console.log("id link 3: " + $stateParams.Id);
            // console.log("id link 3: " + objsInv[i].get("idOrg").id === $stateParams.Id);
            if (objsInv[i].get("idOrg").id === $stateParams.Id) {

                // console.log("a-"+objsInv[i].id);
                // console.log("b-"+$stateParams.Id);
                // if (objsInv[i].id === $stateParams.Id) {  

                $scope.name = objsInv[i].get("idOrg").get("name");

                //console.log("id link: " + objsInv[i].get("idOrg").id);

                //console.log(objsInv[i].get("idOrg").get("address"));   
                //console.log("as-afds");
                $scope.address = objsInv[i].get("idOrg").get("address");
                $scope.city = objsInv[i].get("idOrg").get("city");
                $scope.zipcode = objsInv[i].get("idOrg").get("zipcode");
                $scope.state = objsInv[i].get("idOrg").get("state");
                $scope.email = objsInv[i].get("idOrg").get("email");

    //            console.log(objsInv[i].get("idOrg").get("lng"));

                $scope.id = objsInv[i].get("idOrg").id;
                // $scope.lat = objsInv[i].get("idOrg").get("lat");
                // $scope.lng = objsInv[i].get("idOrg").get("lng");
                // console.log("id link: " + objsInv[i].get("idOrg").id);
                // console.log("id link 2: " + objsInv[i].get("idOrg").id);
                //console.log("id link 3: " + objsInv[i].get("idOrg").id === $stateParams.Id);
                if (objsInv[i].get("idOrg").id === objsInv[i].get("idOrg").id) {
                    $scope.itens.push({

                        qty: objsInv[i].get("quantity"),
                        category: objsInv[i].get("category"),
                        //date: objsInv[i].get("date"),
                        //idOrg: objsInv[i].get("idOrg"),
                        id: objsInv[i].id //???    

                    });
                }
                //}
            }
        }
        $scope.apply();
    });

    $scope.request = function(id) {
 //       console.log($scope.id);
        $state.go("tab.reqfood");
    }

    $scope.reduce = function(item) {
        var Inventory = Parse.Object.extend("Inventory");
        var inventory = new Inventory();

        if (item.newqty <= item.qty && item.newqty > 0) {
            var qty = item.qty - item.newqty;

            inventory.set("id", item.id);
            inventory.set("quantity", qty);

            inventory.save(null, {
                success: function(exercises) {
                    alert("Current Inventory: " + qty);
                    $state.go("tab.editorg");
                },
                error: function(exercises, error) {
                    console.log("Error");
                }
            });
        }
    }

    $scope.add = function(item) {
 //       console.log(item.id);
 //       console.log(item.qty);
 //       console.log(item.newqty);

        var Inventory = Parse.Object.extend("Inventory");
        var inventory = new Inventory();

        if (item.newqty > 0) {
            var qty = item.newqty + item.qty;

            inventory.set("id", item.id);
            inventory.set("quantity", qty);

            inventory.save(null, {
                success: function(exercises) {
                    alert("Current Inventory: " + qty);
                    $state.go("tab.editorg");

                },
                error: function(exercises, error) {
   //                 console.log("Error");
                }
            });
        }
    }

})

.controller('VolunteerListCtrl', function($scope, $stateParams, $state) {

    var Shift = Parse.Object.extend("Shift");
    var queryShi = new Parse.Query(Shift);

 //   console.log($stateParams.Id);
    
    queryInv.find().then(function(objsShi) {


        $scope.itens = [];
        //for (var i = objsInv.length - 1; i >= 0; i--) {
        for (var i = 0; i < objsShi.length; i++) {
   //         console.log("id link: " + objsShi[i].get("idOrg").id);
   //         console.log("id link 3: " + $stateParams.Id);
            // console.log("id link 3: " + objsInv[i].get("idOrg").id === $stateParams.Id);
            if (objsShi[i].get("idOrg").id === $stateParams.Id) {


                $scope.name = objsShi[i].get("idOrg").get("name");

                $scope.address = objsShi[i].get("idOrg").get("address");
                $scope.city = objsShi[i].get("idOrg").get("city");
                $scope.zipcode = objsShi[i].get("idOrg").get("zipcode");
                $scope.state = objsShi[i].get("idOrg").get("state");
                $scope.email = objsShi[i].get("idOrg").get("email");


                $scope.id = objsShi[i].get("idOrg").id;

                if (objsShi[i].get("idOrg").id === objsShi[i].get("idOrg").id) {
                    $scope.itens.push({

                        Shifts: objsShi[i].get("Date"),
                        id: objsShi[i].id //???    

                    });
                }
                //}
            }
        }
        $scope.apply();
    });

    $scope.confirm = function(item) {
        var Shift = Parse.Object.extend("Shift");
        var shift = new Shift();

        if (item.newqty <= item.qty && item.newqty > 0) {
            var qty = item.qty - item.newqty;

            shift.set("qtyVolunteer", qty);

            inventory.save(null, {
                success: function(exercises) {
                    alert("Current Shifts: " + qty);
                    $state.go("tab.map");
                },
                error: function(exercises, error) {
    //                console.log("Error");
                }
            });
        }
    }

})

.controller('OrgDetailCtrl', function($scope, $stateParams, $state) {
    //console.log("id-" + $stateParams.Id);
    var Inventory = Parse.Object.extend("Inventory");
    var queryInv = new Parse.Query(Inventory);
    //queryInv.equalTo("idOrg", $stateParams.Id);
    //queryInv.include("idOrg");
    //queryInv.equalTo("idOrg", $stateParams.Id);        
    queryInv.find().then(function(objsInv) {

        // for (var i = objsInv.length - 1; i >= 0; i--) {
        //     console.log("fsgssss");
        //     var obj = results[i];
        //     $scope.name = obj.get("Organization").get("name");
        //     console.log("fsgssss");
        // }
        $scope.itens = [];
        //for (var i = objsInv.length - 1; i >= 0; i--) {
        for (var i = 0; i < objsInv.length; i++) {
//            console.log("id link: " + objsInv[i].get("idOrg").id);
//            console.log("id link 2: " + $stateParams.Id);
            // console.log("id link 3: " + objsInv[i].get("idOrg").id === $stateParams.Id);
            if (objsInv[i].get("idOrg").id === $stateParams.Id) {

                // console.log("a-"+objsInv[i].id);
                // console.log("b-"+$stateParams.Id);
                // if (objsInv[i].id === $stateParams.Id) {  

                $scope.name = objsInv[i].get("idOrg").get("name");

                //console.log("id link: " + objsInv[i].get("idOrg").id);

                //console.log(objsInv[i].get("idOrg").get("address"));   
                //console.log("as-afds");
                $scope.address = objsInv[i].get("idOrg").get("address");
                $scope.city = objsInv[i].get("idOrg").get("city");
                $scope.zipcode = objsInv[i].get("idOrg").get("zipcode");
                $scope.state = objsInv[i].get("idOrg").get("state");
                $scope.email = objsInv[i].get("idOrg").get("email");

//                console.log(objsInv[i].get("idOrg").get("lng"));

                $scope.id = objsInv[i].get("idOrg").id;
                // $scope.lat = objsInv[i].get("idOrg").get("lat");
                // $scope.lng = objsInv[i].get("idOrg").get("lng");
                // console.log("id link: " + objsInv[i].get("idOrg").id);
                // console.log("id link 2: " + objsInv[i].get("idOrg").id);
                //console.log("id link 3: " + objsInv[i].get("idOrg").id === $stateParams.Id);
                if (objsInv[i].get("idOrg").id === objsInv[i].get("idOrg").id) {
                    $scope.itens.push({

                        qty: objsInv[i].get("quantity"),
                        category: objsInv[i].get("category"),
                        //date: objsInv[i].get("date"),
                        //idOrg: objsInv[i].get("idOrg"),
                        id: objsInv[i].id //???    

                    });
                }
                //}
            }
        }
        $scope.apply();
    });

    $scope.request = function(id) {
  //      console.log($scope.id);
        $state.go("tab.reqfood");
    }

    $scope.reduce = function(item) {
        var Inventory = Parse.Object.extend("Inventory");
        var inventory = new Inventory();

        if (item.newqty <= item.qty && item.newqty > 0) {
            var qty = item.qty - item.newqty;

            inventory.set("id", item.id);
            inventory.set("quantity", qty);

            inventory.save(null, {
                success: function(exercises) {
                    alert("Current Inventory: " + qty);
                    $state.go("tab.editorg");
                },
                error: function(exercises, error) {
  //                  console.log("Error");
                }
            });
        }
    }

    $scope.add = function(item) {
//        console.log(item.id);
//        console.log(item.qty);
//        console.log(item.newqty);

        var Inventory = Parse.Object.extend("Inventory");
        var inventory = new Inventory();

        if (item.newqty > 0) {
            var qty = item.newqty + item.qty;

            inventory.set("id", item.id);
            inventory.set("quantity", qty);

            inventory.save(null, {
                success: function(exercises) {
                    alert("Current Inventory: " + qty);
                    $state.go("tab.editorg");

                },
                error: function(exercises, error) {
  //                  console.log("Error");
                }
            });
        }
    }

});
