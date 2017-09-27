var app = angular.module("portfolio", ['ui.bootstrap', 'ngRoute', 'jcs-autoValidate']);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "src/main/main.html"
    })
    .when("/warehouses", {
        templateUrl : "src/warehouses/warehouses.html"
    })
    .when("/ports", {
        templateUrl : "src/ports/ports.html"
    })
    .when("/products", {
        templateUrl : "src/products/products.html"
    })
    .when("/routes", {
        templateUrl : "src/routes/routes.html"
    })
    .when("/ships", {
        templateUrl : "src/ships/ships.html"
    })
    .when("/orders", {
        templateUrl : "src/orders/orders.html"
    })
    .when("/schedules", {
        templateUrl : "src/schedules/schedules.html"
    });

    $.toaster({ settings : {timeout: 6000} });
});

app.constant('host', {
    name: 'http://localhost:8080'
});

app.controller('indexCtrl',['$scope', '$location', function($scope,$location) {
  $scope.isActive = function (viewLocation) {
   var active = (viewLocation === $location.path());
   return active;
 };
}]);
