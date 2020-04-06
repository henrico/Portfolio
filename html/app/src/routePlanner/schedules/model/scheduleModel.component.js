angular.module('portfolio').component('scheduleModel',{

  templateUrl: 'src/routePlanner/schedules/model/scheduleModel.html',
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
       if (!$scope.$ctrl.row.collectionDate) $scope.$ctrl.row.collectionDate = new Date();
       $scope.$ctrl.dt = new Date($scope.$ctrl.row.collectionDate);
       $scope.$ctrl.row.collectionDate = parse($scope.$ctrl.dt);
    });

    $scope.c = function(){
      $scope.$ctrl.row.collectionDate = parse($scope.$ctrl.dt);
    }

    var parse = function(d){
      if (d)
        return d.getFullYear()+'-'+((""+(d.getMonth()+1)).length===1?'0'+(d.getMonth()+1):(d.getMonth()+1))
          +'-'+((""+(d.getDate())).length===1?'0'+(d.getDate()):(d.getDate()));
      else {
        return '';
      }
    }

    $scope.options = {
      minDate: new Date()
    }

    $scope.$on("RELOAD ORDERS",function(){
      $scope.$ctrl.row.order={id:"-1"};
      $http.get(host.name + '/rest/schedule/unfilledOrders').then(function(result) {
        $scope.$ctrl.orders = result.data;
        if (result.data.length==0){
          $.toaster({ message : 'There are no Unfilled Orders', priority : 'info' });
        }
        $scope.$ctrl.row.warehouse={id:"-1"};
        for (var cur in $scope.$ctrl.orders){
          var order = $scope.$ctrl.orders[cur];
          order.displayText = order.id+' - '+order.quantity+' Crates of '+order.product.name+' by '+order.deliveryDate;
        }
      }, function() {
        swal("Oops!", "Something went wrong!", "error")
      });
    })

    $http.get(host.name + '/rest/schedule/unfilledOrders').then(function(result) {
      $scope.$ctrl.orders = result.data;
      if (result.data.length==0){
        $.toaster({ message : 'There are no Unfilled Orders.', priority : 'info' });
      }
      $scope.$ctrl.row.warehouse={id:"-1"};
      for (var cur in $scope.$ctrl.orders){
        var order = $scope.$ctrl.orders[cur];
        order.displayText = order.id+' - '+order.quantity+' Crates of '+order.product.name+' by '+order.deliveryDate;
      }
    }, function() {
      swal("Oops!", "Something went wrong!", "error")
    });

    function reloadShip(){
      $scope.$ctrl.row.ship={id:"-1"};
      $scope.$ctrl.row.warehouse={id:"-1"};

      $http.get(host.name + '/rest/schedule/shipsForOrder/'+$scope.$ctrl.row.order.id+'/'+$scope.$ctrl.row.source.id+"/"+$scope.$ctrl.row.collectionDate).then(function(result) {
        $scope.$ctrl.ships = result.data;
        if (result.data.length==0 && $scope.$ctrl.row.source.id!=-1){
          $.toaster({ message : 'There are no ships that can make this delivery in the give time frame.', priority : 'warning' });
        }
      }, function() {
        swal("Oops!", "Something went wrong!", "error")
      });
    }

    $scope.$watch('$ctrl.row.order',function(n,o){
      if (n!==o){
        $scope.options.maxDate = new Date(n.deliveryDate);
        $scope.$ctrl.row.ship={id:"-1"};
        $scope.$ctrl.row.source={id:"-1"};
        $scope.$ctrl.row.warehouse={id:"-1"};
        $http.get(host.name + '/rest/schedule/portsProducingProduct/'+$scope.$ctrl.row.order.product.id).then(function(result) {
          if (result.data.length==0){
            $.toaster({ message : 'There are no ports that can supply this order.', priority : 'warning' });
          }
          $scope.$ctrl.ports = result.data;
        }, function() {
          swal("Oops!", "Something went wrong!", "error")
        });
      }
    });

    $scope.$watch('$ctrl.row.ship',function(n,o){
      if (n!==o){
        $scope.$ctrl.row.warehouse={id:"-1"};
        $http.get(host.name + '/rest/schedule/warehousesForOrder/'+$scope.$ctrl.row.order.id+'/'+$scope.$ctrl.row.source.id+"/"+$scope.$ctrl.row.ship.id+'/'+$scope.$ctrl.row.collectionDate).then(function(result) {
          $scope.$ctrl.warehouses = result.data;
          if (result.data.length==0 && $scope.$ctrl.row.ship.id!=-1){
            $.toaster({ message : 'There are no warehouses with capacity to store the product with the given time frame.', priority : 'warning' });
          }
        }, function() {
          swal("Oops!", "Something went wrong!", "error")
        });
      }
    });

    $scope.$watch('$ctrl.row.collectionDate',function(n,o){
      if (n!==o){
        reloadShip();
      }
    });

    $scope.$watch('$ctrl.row.source',function(n,o){
      if (n!==o){
        reloadShip();
      }
    });

  }]

});
