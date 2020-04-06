angular.module('portfolio').component('orderModel',{

  templateUrl: 'src/routePlanner/orders/model/orderModel.html',
  bindings:{
    row: '<',
    label: '<',
    readOnly: '<'
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

    $http.get(host.name + '/rest/port').then(function(result) {
      if (result.data.length==0){
        $.toaster({ message : 'There are no Ports', priority : 'warning' });
      }
      $scope.$ctrl.portmodels = result.data;
    }, function() {
      swal("Oops!", "Something went wrong!", "error")
    });

    $http.get(host.name + '/rest/product').then(function(result) {
      if (result.data.length==0){
        $.toaster({ message : 'There are no Products', priority : 'warning' });
      }
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
