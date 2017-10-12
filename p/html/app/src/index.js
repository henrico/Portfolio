var app = angular.module("portfolio", ['ui.bootstrap', 'ngRoute', 'jcs-autoValidate']);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "src/main/main.html"
    })
    .when("/warehouses", {
        templateUrl : "src/routePlanner/warehouses/warehouses.html"
    })
    .when("/ports", {
        templateUrl : "src/routePlanner/ports/ports.html"
    })
    .when("/products", {
        templateUrl : "src/routePlanner/products/products.html"
    })
    .when("/routes", {
        templateUrl : "src/routePlanner/routes/routes.html"
    })
    .when("/ships", {
        templateUrl : "src/routePlanner/ships/ships.html"
    })
    .when("/orders", {
        templateUrl : "src/routePlanner/orders/orders.html"
    })
    .when("/schedules", {
        templateUrl : "src/routePlanner/schedules/schedules.html"
    })
    .when("/neuralNet", {
        templateUrl : "src/machineLearning/neuralNet/neuralNet.html"
    });

    $.toaster({ settings : {timeout: 6000} });
});

var server = '@restServerName@';
if (server === '@'+'restServerName@'){
  server  = 'http://localhost:8080';
}

app.constant('host', {
    name: server
});

app.controller('indexCtrl',['$scope', '$location', function($scope,$location) {
  $scope.isActive = function (viewLocation) {
   var active = (viewLocation === $location.path());
   return active;
 };

 $scope.app = 'home';
}]);
