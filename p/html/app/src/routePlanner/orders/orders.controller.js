angular.module("portfolio").controller('orders', [
  '$scope',
  '$http',
  '$compile',
  '$timeout',
  'host',
  function($scope, $http, $compile, $timeout, host) {

    $scope.heading = "Orders";
    $scope.description = "Orders of Products are sent to Ports and are fulfilled by Schedules"

    $scope.newRow = {
      destination:{id:"-1"},
      product:{id:"-1"},
      quantity:'',
      orderStatus: 'PLACED',
      deliveryDate: new Date(),
      new:true
    }

    $scope.createNewRow = function(){

        $scope.newRow.quantity='';
        $scope.newRow.destination={id:"-1"};
        $scope.newRow.product={id:"-1"};
    }

    $scope.columns = [
        {
          title: 'Number',
          field: 'id',
       },
       {
        title: 'Destination',
          formatter: function(index, row, element) {
            return row.destination.name;
          }
        }, {
          title: 'Product',
          formatter: function(index, row, element) {
            return row.product.name;
          }
        }, {
          title: 'Quantity',
          field: 'quantity',
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
          title: 'Status',
          field: 'orderStatus',
          formatter: function(index, row, element) {
            return {
              'PLACED': 'Placed',
              'IN_TRANSIT': 'In Transit',
              'COMPLETTE': 'Complette'
            }[row.orderStatus];
          }

        }
    ];

    $scope.expandString = function(row){
      return '<div class="container" style="padding:0px"><div class="row"><div class="col-md-10">'+
      '<order-model read-only="readOnly[\''+row.id+'\']" row="rows[\''+row.id+'\']" label="\'Edit Order\'"></order-model>'+
      '<button class="btn btn-success pull-right" ng-click="save(\''+row.id+'\')" ng-disabled="$ctrl.isInvalid(rows[\''+row.id+'\'])||readOnly[\''+row.id+'\']" ng-class="{\'disabled\':isInvalid(rows[\''+row.id+'\'])}">Update</button>'+
      '</div></div></div>'
    }

    $scope.isInvalid = function(row){
      if (!row.destination || !row.destination.id) return true;
      if (!row.product || !row.product.id) return true;
      if (!row.quantity || row.quantity === '' || row.quantity === 0) return true;
    }

    $scope.restName='order';

    $scope.saved = function(){
      $.toaster({ message : 'All related Schedules Have been removed', priority : 'info' });
    }

  }
]);
