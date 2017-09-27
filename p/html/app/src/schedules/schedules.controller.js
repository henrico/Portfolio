angular.module("portfolio").controller('schedules', [
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
      order:{id:"-1"},
      ship: {id:"-1"},
      source: {id:"-1"},
      warehouse: {id:"-1"},
      new:true
    }
    function createNewRow(){
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

    $timeout(function(){
      $('#schedulesTable').bootstrapTable({
        columns: [
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
          }, {
              formatter: function(index, row, element) {
                return '<a href="javascript:void(0);" class="remove"><i class="fa fa-trash" aria-hidden="true"></i></a>';
              },
            events: {
              'click .remove': function(e, value, row, index) {
                $http.delete(host.name + '/schedule/' + row.id).then(function(result) {
                  $('#schedulesTable').bootstrapTable('load', result.data);
                  loadRows(result);
                  $.toaster({ message : 'Schedule deleted' });
                  $scope.$broadcast("RELOAD ORDERS");
                },function(){
                  swal("Oops!", "Something went wrong!", "error")
                });
              }
            }
          }
        ]
      });

      $http.get(host.name + '/schedules').then(function(result) {
        $('#schedulesTable').bootstrapTable('load', result.data);
        loadRows(result);
      },function(){
        swal("Oops!", "Something went wrong!", "error")
      });

      $scope.isInvalid = function(row){
        if (!row.warehouse || row.warehouse.id==-1) return true;
      }

      $scope.add = function(){
        $http.post(host.name + '/schedule/',$scope.newRow).then(function(result) {
          $('#schedulesTable').bootstrapTable('load', result.data);
          loadRows(result);
          $scope.formData.$setPristine();
          createNewRow();
          $.toaster({ message : 'Schedule added' });
          $scope.$broadcast("RELOAD ORDERS");
        },function(){
          swal("Oops!", "Unable to create order. Check that you have a valid route!", "error")
        });
      }

    });

    createNewRow();



  }
]);
