
'use strict';

var myApp = angular.module('myApp',[]); 

myApp.controller('MapController',['$scope','$http',function($scope,$http){

    //modifications on UI

    $scope.message = "Mark the regions on map"

    $scope.rectShape;
    $scope.locationsData =[];
    $scope.itemsPerPage = 5;
    $scope.pagedItems = [];
    $scope.currentPage = 0;

    var mapOptions = {
        zoom: 4,
        center: new google.maps.LatLng(25, 80),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    $scope.map = new google.maps.Map(document.getElementById("map"), mapOptions);
    $scope.test = "Divya";




    $scope.drawingMg = new google.maps.drawing.DrawingManager({
        drawingMode: google.maps.drawing.OverlayType.RECTANGLE,
        drawingControl: true,
        drawingControlOptions: {
            position: google.maps.ControlPosition.TOP_CENTER,

            drawingModes: [
                //google.maps.drawing.OverlayType.MARKER,
                //google.maps.drawing.OverlayType.CIRCLE,
                google.maps.drawing.OverlayType.POLYGON,
                //google.maps.drawing.OverlayType.POLYLINE,
                google.maps.drawing.OverlayType.RECTANGLE
            ]
        },

        polygonOptions: {
            fillColor: '#BCDCF9',
            fillOpacity: 0.5,
            strokeWeight: 2,
            strokeColor: '#57ACF9',
            clickable: false,
            editable: true,
            draggable : true,
            zIndex: 1
        },
        rectangleOptions:
        {
            fillColor: 'red',
            fillOpacity: 0.5,
            strokeWeight: 2,
            strokeColor: 'red',
            clickable: true,
            editable: true,
            draggable:true,
            zIndex: 1

        }
    });

    var c1,c2,c3,c4;
    google.maps.event.addListener($scope.drawingMg, 'rectanglecomplete', function(rectangle) {
        //document.getElementById('info').innerHTML += "rectangle points:" + "<br>";

        $scope.rectShape = rectangle;

        var c11 = rectangle.getBounds().getNorthEast().lat();
        var c12 = rectangle.getBounds().getNorthEast().lng();
        var c21 = rectangle.getBounds().getSouthWest().lat();
        var c22 = rectangle.getBounds().getSouthWest().lng();

        $scope.rnelat = rectangle.getBounds().getNorthEast().lat();
        $scope.rnelng = rectangle.getBounds().getNorthEast().lng();
        $scope.rswlng = rectangle.getBounds().getSouthWest().lng();
        $scope.rswlat = rectangle.getBounds().getSouthWest().lat();



        c1 = rectangle.getBounds().getNorthEast().lat() +",\n" +rectangle.getBounds().getNorthEast().lng();
        c2 = rectangle.getBounds().getNorthEast().lat()+",\n" +rectangle.getBounds().getSouthWest().lng();
        c3 = rectangle.getBounds().getSouthWest().lat()+",\n" +rectangle.getBounds().getNorthEast().lng();
        c4 = rectangle.getBounds().getSouthWest().lat()+",\n" +rectangle.getBounds().getSouthWest().lng();

        $scope.rectBounds = c11+" "+c12+" "+c21+" "+c22;






        document.getElementById("txtCoords").innerHTML = c1 +"\n"+c2+"\n"+c3+"\n"+c4;

        // alert(document.getElementById("txtCoords").innerHTML.toString());

        //document.getElementById("info").innerHTML += ne +"," +sw+ "<br>";

    });




    $scope.drawingMg.setMap($scope.map);



    $scope.range = function (size,start, end) {
        var ret = [];
        console.log(size,start, end);

        if (size < end) {
            end = size;
            if(size<$scope.gap){
                start = 0;
            }else{
                start = size-$scope.gap;
            }

        }
        for (var i = start; i < end; i++) {
            ret.push(i);
        }
        console.log(ret);
        return ret;
    };
    


    $scope.clearButton = document.getElementById("btnResetRect");
    //$scope.map.controls[google.maps.ControlPosition.BOTTOM_CENTER].push($scope.clearButton);

    $scope.submitButton = document.getElementById("btnSubmitRect");

    $scope.submitDataOnClick = function(){
        $scope.test="Spandana";
        $scope.stopLimit = document.getElementById("txtlimit");
       
        //$scope.locationsData =[];
        $scope.locationsData.push({
            place : $scope.placeName,
            limit : $scope.limit,
            co1 : $scope.rnelat,
            co2:  $scope.rnelng,
            co3: $scope.rswlat,
            co4: $scope.rswlng
        });
        $scope.mapBounds = $scope.viewPortBounds;

        $http({
            method : 'POST',
            url : 'http://localhost:8080/ScientistMapServlet',
            headers: {'Content-Type' : 'application/json'},
            data : {
                'stopLimit' : $scope.limit,
                'vCenterLat': $scope.CenterLat,
                'vCenterLng' :$scope.CenterLng,
                'rectNLat' :   $scope.rnelat,
                'rectNLng' : $scope.rnelng,
                'rectSLat'  : $scope.rswlat,
                'rectSLng' :  $scope.rswlng,
                'place' :$scope.placeName
            }

        }).success(function(data, status, headers, config) {
            alert("Updated successfully in the database");
        }).error(function(data, status, headers, config) {
        });



        document.getElementById("txtlimit").innerHTML = "";
};







    console.log("outside do click");



    $scope.clearButton.onclick = function(){


        if($scope.drawingMg.getDrawingMode()){


            //$scope.map = new google.maps.Map(document.getElementById("map"), mapOptions);
            //$scope.drawingMg.setMap($scope.map);
            //$scope.map.controls[google.maps.ControlPosition.TOP_LEFT].push($scope.placeSearch);
            //$scope.searchBox.setPlace(null);
            $scope.map.set(mapOptions);
            document.getElementById("txtlimit").innerHTML = "";

            $scope.test = "Spandana";
            $scope.rectShape.setMap(null);
            //$scope.shape.setBounds(null);


            //$scope.drawingMg.getDrawingMode().setMap(null);
        }



    };


    // $scope.deleteRowClick = function(co1,co2,co3,co4){
    //
    //     //window.deleteRowClick(co1,co2,co3,co4);
    //
    //     var index = -1;
    //     var locArr = eval( $scope.locationsData );
    //     for( var i = 0; i < locArr.length; i++ ) {
    //         if( locArr[i].co1 === co1 && locArr[i].co2 === co2 && locArr[i].co3 === co3 && locArr[i].co4 === co4 ) {
    //             index = i;
    //             break;
    //         }
    //     }
    //     if( index === -1 ) {
    //     }
    //     $scope.locationsData.splice( index, 1 );
    // };



    $scope.placeSearch = document.getElementById("placeSearch");
    $scope.searchBox = new google.maps.places.SearchBox($scope.placeSearch);
    $scope.map.controls[google.maps.ControlPosition.TOP_LEFT].push($scope.placeSearch);

    $scope.map.addListener('bounds_changed', function () {
        $scope.searchBox.setBounds($scope.map.getBounds());
    });

    $scope.markers = [];
    var bounds;
    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    $scope.searchBox.addListener('places_changed', function () {
        $scope.places = $scope.searchBox.getPlaces();
        $scope.place = $scope.searchBox.getPlaces().name;



        if ($scope.places.length == 0) {
            return;
        }

        // Clear out the old markers.
        $scope.markers.forEach(function (marker) {
            marker.setMap(null);
        });

        $scope.markers = [];
        var center;

        $scope.places.forEach(function (place) {


            if (!place.geometry) {
                console.log("Returned place contains no geometry");

                return;
            }
            var icon = {
                url: place.icon,
                size: new google.maps.Size(71, 71),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(17, 34),
                scaledSize: new google.maps.Size(25, 25)
            };

            $scope.placeName = place.name;
            //place.position


            // Create a marker for each place.
            $scope.markers.push(new google.maps.Marker({
                map: $scope.map,
                icon: icon,
                title: place.name,

                position: place.geometry.location
            }));


            var viewPortNELat = place.geometry.viewport.getNorthEast().lat();
            var viewPortNELng = place.geometry.viewport.getNorthEast().lng();
            var viewPortSWLat = place.geometry.viewport.getSouthWest().lat();
            var viewPortSWLng = place.geometry.viewport.getSouthWest().lng();

            $scope.viewPortBounds = viewPortNELat+" "+viewPortNELng+" "+viewPortSWLat+" "+viewPortSWLng;


            if (place.geometry.viewport) {
                // Only geocodes have viewport.
                bounds = place.geometry.viewport;
            } else {
                bounds = place.geometry.location;
            }
        });
        $scope.map.fitBounds(bounds);

        $scope.map.getCenter();
        $scope.CenterLat = $scope.map.getCenter().lat();
        $scope.CenterLng = $scope.map.getCenter().lng();


        //$scope.drawingManager = new google.maps


    });


    

    


}]);

// myApp.controller('ModalInstanceCtrl',["$scope","$modalForm",function($scope,$modalForm, userForm){
//
//     $scope.form = {};
//     $scope.submitForm = function () {
//         if ($scope.form.userForm.$valid) {
//             console.log('user form is in scope');
//             $modalForm.close('closed');
//         } else {
//             console.log('userform is not in scope');
//         }
//     };
//
//     $scope.cancel = function () {
//         $modalForm.dismiss('cancel');
//     };
//
// }]);



// myApp.controller('ModalController',['$scope','$element','title','close',function($scope,$element,title,close){
//
//     $scope.limit = null;
//     $scope.title = title;
//
//     alert("title is "+title);
//
//     $scope.close = function(){
//       close({limit : $scope.limit});
//     };
//
//     $scope.cancel = function(){
//         $element.modal('hide');
//
//         close({limit : $scope.limit},500);
//
//
//     }
//
// }]);



myApp.controller('HomeController',function($scope){
    $scope.message ="This is the homepage";
});


