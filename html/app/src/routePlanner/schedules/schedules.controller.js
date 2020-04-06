angular.module("portfolio").controller('schedules', [
  '$scope',
  '$http',
  '$compile',
  '$timeout',
  'host',
  function($scope, $http, $compile, $timeout, host) {

    $scope.heading = "Schedules";
    $scope.description = "Select an Order to be shipped. Only Ships that can meet the delivery date are available."

    $scope.newRow = {
      order:{id:"-1"},
      ship: {id:"-1"},
      source: {id:"-1"},
      warehouse: {id:"-1"},
      new:true
    }
    $scope.createNewRow = function(){
      $scope.newRow.order={id:"-1"};
    }

    var parse = function(d){
      if (d)
        return d.getFullYear()+'-'+((""+(d.getMonth()+1)).length===1?'0'+(d.getMonth()+1):(d.getMonth()+1))
          +'-'+((""+(d.getDate())).length===1?'0'+(d.getDate()):(d.getDate()));
      else {
        return '';
      }
    }

    $scope.columns = [
      {
        title: 'Order',
        formatter: function(index, row, element) {
          return row.order.id;
        }
      }, {
        title: 'From Port',
        formatter: function(index, row, element) {
          return row.source.name;
        }
      }, {
        title: 'Ship',
        formatter: function(index, row, element) {
          return row.ship.name;
        }
      }, {
        title: 'Collection Date',
        field: 'collectionDate',
        formatter: function(index, row, element) {
          var parse = function(d){
            if (d)
              return d.getFullYear()+'-'+((""+(d.getMonth()+1)).length===1?'0'+(d.getMonth()+1):(d.getMonth()+1))
                +'-'+((""+(d.getDate())).length===1?'0'+(d.getDate()):(d.getDate()));
            else {
              return '';
            }
          }
          return parse(new Date(row.collectionDate));
        }
      }, {
        title: 'Delivery Date',
        field: 'deliveryDate',
        formatter: function(index, row, element) {
          var parse = function(d){
            if (d)
              return d.getFullYear()+'-'+((""+(d.getMonth()+1)).length===1?'0'+(d.getMonth()+1):(d.getMonth()+1))
                +'-'+((""+(d.getDate())).length===1?'0'+(d.getDate()):(d.getDate()));
            else {
              return '';
            }
          }
          return parse(new Date(row.deliveryDate));
        }
      }, {
        title: 'Stored Crates',
        field: 'storedCrates'
      }, {
        title: 'Cost',
        field: 'cost'
      }
    ]

    $scope.isInvalid = function(row){
        if (!row.warehouse || row.warehouse.id==-1) return true;
      }

    $scope.added = function(){
      $scope.$broadcast("RELOAD ORDERS");
    }

    $scope.restName='schedule';

    $scope.saved = function(){
    }

  }
]);
