angular.module('portfolio').component('orderModel',{

  templateUrl: 'src/orders/model/orderModel.html',
  bindings:{
    row: '=',
    label: '='
  },
  controller:[
  '$scope',
  '$timeout',
  '$http',
  '$compile',
  'host',
  'uibDateParser',
  function($scope, $timeout, $http, $compile, host) {
    $timeout(function(){
       if (!$scope.$ctrl.row.deliveryDate) $scope.$ctrl.row.deliveryDate = new Date();
       $scope.$ctrl.dt = new Date($scope.$ctrl.row.deliveryDate);
       $scope.$ctrl.row.deliveryDate = parse($scope.$ctrl.dt);
    });

    $scope.c = function(){
      $scope.$ctrl.row.deliveryDate = parse($scope.$ctrl.dt);
    }

    var parse = function(d){
      if (d)
        return d.getFullYear()+'-'+((""+(d.getMonth()+1)).length===1?'0'+(d.getMonth()+1):(d.getMonth()+1))
          +'-'+((""+(d.getDate())).length===1?'0'+(d.getDate()):(d.getDate()));
      else {
        return '';
      }
    }

    $http.get(host.name + '/ports').then(function(result) {
      $scope.$ctrl.portmodels = result.data;
    }, function() {
      swal("Oops!", "Something went wrong!", "error")
    });

    $http.get(host.name + '/products').then(function(result) {
      $scope.$ctrl.productsmodels = result.data;
    }, function() {
      swal("Oops!", "Something went wrong!", "error")
    });

    this.models = [
      {
        id: 'PLACED',
        name: 'Placed'
      },
      {
        id: 'IN_TRANSIT',
        name: 'In Transit'
      },
      {
        id: 'COMPLETTE',
        name: 'Complette'
      }
    ]

    $scope.options = {
      minDate: new Date()
    }

  }]

});
