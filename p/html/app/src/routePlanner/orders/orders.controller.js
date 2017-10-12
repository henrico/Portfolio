angular.module("portfolio").controller('orders', [
  '$scope',
  '$http',
  '$compile',
  '$timeout',
  'host',
  function($scope, $http, $compile, $timeout, host) {

    function loadRows(result){
      $scope.rows = {}
      for (var cur in result.data){
        $scope.rows[result.data[cur].id] = result.data[cur];
      }
    }

    $scope.newRow = {
      destination:{id:"-1"},
      product:{id:"-1"},
      quantity:'',
      orderStatus: 'PLACED',
      deliveryDate: new Date(),
      new:true
    }

    function createNewRow(){

        $scope.newRow.quantity='';
        $scope.newRow.destination={id:"-1"};
        $scope.newRow.product={id:"-1"};
    }

    $timeout(function(){
      $('#ordersTable').bootstrapTable({
        columns: [
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

            },{
              title: '',
              formatter: function(index, row, element) {
                return '<a href="javascript:void(0);" class="remove"><i class="fa fa-trash" aria-hidden="true"></i></a>';
              },
              events: {

                'click .remove': function(e, value, row, index) {
                  $http.delete(host.name + '/rest/order/' + row.id).then(function(result) {
                    $('#ordersTable').bootstrapTable('load', result.data);
                    loadRows(result);
                    $.toaster({ message : 'Order deleted' });
                  },function(){
                    swal("Oops!", "Something went wrong!", "error")
                  });
                }
              }
          }
        ]
      });

      $('#ordersTable').on('expand-row.bs.table', function(e, index, row, $detail) {
        $detail.html($compile(
          '<div class="container" style="padding:0px"><div class="row"><div class="col-md-10">'+
          '<order-model row="rows[\''+row.id+'\']" label="\'Edit Order\'"></order-model>'+
          '<button class="btn btn-success pull-right" ng-click="save(\''+row.id+'\')" ng-disabled="isInvalid(rows[\''+row.id+'\'])" ng-class="{\'disabled\':isInvalid(rows[\''+row.id+'\'])}">Update</button>'+
          '</div></div></div>'
        )($scope));
      });

      $http.get(host.name + '/rest/order').then(function(result) {
        $('#ordersTable').bootstrapTable('load', result.data);
        loadRows(result);
      },function(){
        swal("Oops!", "Something went wrong!", "error")
      });

      $scope.save = function(id){
        $.toaster({ message : 'All related Schedules Have been removed', priority : 'info' });
        $http.put(host.name + '/rest/order/' + id,$scope.rows[id]).then(function(result) {
          $('#ordersTable').bootstrapTable('load', result.data);
          loadRows(result);
          $.toaster({ message : 'Order saved' });
        },function(){
          swal("Oops!", "Something went wrong!", "error")
        });
      }

      $scope.isInvalid = function(row){
        if (!row.destination || !row.destination.id) return true;
        if (!row.product || !row.product.id) return true;
        if (!row.quantity || row.quantity === '' || row.quantity === 0) return true;
      }

      $scope.add = function(){
        $http.post(host.name + '/rest/order/',$scope.newRow).then(function(result) {
          $('#ordersTable').bootstrapTable('load', result.data);
          loadRows(result);
          createNewRow();
          $scope.formData.$setPristine();
          $.toaster({ message : 'Order added' });
        },function(){
          swal("Oops!", "Something went wrong!", "error")
        });
      }

    });

    createNewRow();



  }
]);
