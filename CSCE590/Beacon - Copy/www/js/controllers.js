angular.module('starter.controllers', [])

.controller('MapController', function($state, $scope, $ionicLoading, $compile, $cordovaGeolocation) {

    $scope.initialize = function() {


        var infowindow = new google.maps.InfoWindow();
        var myLatlng = new google.maps.LatLng(43.07493, -89.381388);

        //var latitude = position.coords.latitude;
        // var longitude = position.coords.longitude;

        // myLatlng = new google.maps.LatLng(latitude, longitude);

        //  var posOptions = { timeout: 10000, enableHighAccuracy: false };
        //  console.log("a");
        // $cordovaGeolocation
        //     .getCurrentPosition(posOptions)
        //     .then(function(position) {
        //         console.log("b");
        //         var lat = position.coords.latitude
        //         var long = position.coords.longitude
        //     }, function(err) {
        //         console.log(err.message);
        //     });

        var mapOptions = {
            center: myLatlng,
            zoom: 15,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("map"),
            mapOptions);


        var marker = new google.maps.Marker({
            position: myLatlng,
            map: map,
            title: 'Uluru (Ayers Rock)'
        });

        google.maps.event.addListener(marker, 'click', function() {
            infowindow.open(map, marker);
        });

        $scope.map = map;
    };
    //google.maps.event.addDomListener(window, 'load', initialize);
/*
    // Added Geo Function
    $scope.geo = function(org){
        // Geocode an address.
        googleMapsClient.geocode({
        address: org.address
        }, function(err, response) {
        if (!err) {
        console.log(response.json.results);
        }
        })
    };
*/
    $scope.exit = function(user) {
        $state.go("login.login");
    }

})

.controller('LoginCtrl', function($state, $scope, Chats) {
    $scope.login = function(user) {

        $state.go("tab.maps");

        // Parse.User.logIn(user.username, user.password, {
        //     success: function(user) {
        //         console.log(user.id);

        //         $state.go("tab.orders");
        //     },
        //     error: function(user, error) {
        //         alert("login failed:" + error);
        //         console.log("failed");


        //     }
        // });
    }

    $scope.register = function(argument) {
        $state.go("login.setup")
    }

    $scope.changePassword = function(argument) {
        $state.go("login.password")
    }

    $scope.survivorAccess = function(argument) {
        $state.go("tab.map");
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

.controller('EditOrgCtrl', function($state, $scope) {
    var self = this;

    var img = "";

    var Org = Parse.Object.extend("Organization");
    var query = new Parse.Query(Org);
    //query.equalTo("id", $stateParams.id);
    query.find().then(function(objs) {
        console.log("Searching");

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
                console.log("deleted");
            },
            error: function(myObject, error) {

            }
        })
    }

    $scope.request = function(argument) {
        console.log($scope.id);
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
        console.log("Searching");

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
                console.log("deleted");
            },
            error: function(myObject, error) {

            }
        })
    }

    $scope.request = function(argument) {
        console.log($scope.id);
        $state.go("tab.reqfood");
    }

    $scope.cancel = function(argument) {
        $state.go("tab.organization");
    }

})

.controller('MapCtrl', function($state, $scope, $cordovaGeolocation) {

    console.log("a");

    var options = { timeout: 10000, enableHighAccuracy: true };

    $cordovaGeolocation.getCurrentPosition(options).then(function(position) {

        var latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);

        var mapOptions = {
            center: latLng,
            zoom: 15,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };

        $scope.map = new google.maps.Map(document.getElementById("map"), mapOptions);

        google.maps.event.addListenerOnce($scope.map, 'idle', function() {

            var marker = new google.maps.Marker({
                map: $scope.map,
                animation: google.maps.Animation.DROP,
                position: latLng,
                icon: 'http://i.imgur.com/fDUI8bZ.png'
            });

            var infoWindow = new google.maps.InfoWindow({
                content: "Here You Are.!"
            });

            google.maps.event.addListener(marker, 'click', function() {
                infoWindow.open($scope.map, marker);
            });
        });
    }, function(error) {
        console.log("Could not get location");
    });

    $scope.mapCreated = function(map) {
        $scope.map = map;
    };

    $scope.centerOnMe = function() {
        console.log("Centering");
        if (!$scope.map) {
            return;
        }

        $scope.loading = $ionicLoading.show({
            content: 'Getting current location...',
            showBackdrop: false
        });

        navigator.geolocation.getCurrentPosition(function(pos) {
            console.log('Got pos', pos);
            $scope.map.setCenter(new google.maps.LatLng(pos.coords.latitude, pos.coords.longitude));
            $scope.loading.hide();
        }, function(error) {
            alert('Unable to get location: ' + error.message);
        });
    };

})

.controller('SetupCtrl', function($scope, $state, Chats) {

    $scope.register = function(user) {
        console.log(user.email);
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

.controller('ReqFoodCtrl', function($scope, $ionicHistory, $state, $stateParams) {
    $scope.cancel = function(argument) {
        $state.go("tab.organization");
    };
    $scope.reqfood = function(req) {
        console.log($scope.id);
    };

})

.controller('NewOrgCtrl', function($scope, $ionicHistory, $state) {
    $scope.cancel = function(argument) {
        $state.go("tab.organization");
    };
    $scope.neworg = function(org) {
        if (org) {

            var Org = Parse.Object.extend("Organization");
            var o = new Org();
/*
            nativegeocoder.forwardGeocode(success, failure, org.address);
            function success(coordinates) {
            alert("The coordinates are latitude = " + coordinates.latitude + " and longitude = " + coordinates.longitude);
            }
            function failure(err) {
            alert(JSON.stringify(err));
            }
*/
            o.set("name", org.name);
            o.set("address", org.address);
            o.set("city", org.city);
            o.set("zipcode", org.zipcode);
            o.set("state", org.state);
            o.set("phone", org.phone);
            o.set("email", org.email);
            var id;
            o.save(null, {
                success: function(o) {
                    console.log(o.id);
                    id = o.id;

                    var Inventory = Parse.Object.extend("Inventory");
                    var inventory = new Inventory();

                    console.log(id);
                    inventory.set("idOrg", o);
                    inventory.set("quantity", 0);
                    inventory.set("category", "Clothing");
                    inventory.save(null, {
                        success: function(inventory) {
                            //$scope.apply();
                            console.log(inventory);
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
                            console.log(inventory2);
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
                            console.log(inventory3);
                        },
                        error: function(inventory3, error) {
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

.controller('OrgDetailCtrl', function($scope, $stateParams, $state) {
    //console.log("id-" + $stateParams.Id);
    var Inventory = Parse.Object.extend("Inventory");
    var queryInv = new Parse.Query(Inventory);
    //queryInv.equalTo("idOrg", $stateParams.Id);
    queryInv.include("idOrg");
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
            console.log("id link: " + objsInv[i].get("idOrg").id);
            console.log("id link 2: " + $stateParams.Id);
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
                $scope.id = objsInv[i].get("idOrg").id;
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
        console.log($scope.id);
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
        console.log(item.id);
        console.log(item.qty);
        console.log(item.newqty);

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
                    console.log("Error");
                }
            });
        }
    }

});
