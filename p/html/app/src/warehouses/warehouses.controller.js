angular.module("portfolio").controller('warehouses', [
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

    function createNewRow(){
      $scope.newRow = {
        name: '',
        type: '',
        capacity: '',
        storageCost: '',
        transportCost: '',
        port: {},
        new: true
      }
    }

    $timeout(function(){
      $('#warehousesTable').bootstrapTable({
        columns: [
          {
            title: 'Name',
            field: 'name'
          }, {
            title: 'Port',
            field: 'port',
            formatter: function(index, row, element) {
              return row.port.name;
            }
          }, {
            title: 'Type',
            field: 'type'
          }, {
            title: 'Capacity',
            field: 'capacity'
          }, {
            title: 'Storage Cost',
            field: 'storageCost'
          }, {
            title: 'Transport Cost',
            field: 'transportCost'
          }, {
            title: '',
            formatter: function(index, row, element) {
              return '<a href="javascript:void(0);" class="remove"><i class="fa fa-trash" aria-hidden="true"></i></a>';
            },
            events: {
              'click .remove': function(e, value, row, index) {
                $http.delete(host.name + '/warehouse/' + row.id).then(function(result) {
                  $('#warehousesTable').bootstrapTable('load', result.data);
                  loadRows(result);
                  $.toaster({ message : 'Warehous deleted' });
                },function(){
                  swal("Oops!", "Something went wrong!", "error")
                });
              }
            }
          }
        ]
      });

      $('#warehousesTable').on('expand-row.bs.table', function(e, index, row, $detail) {
        $detail.html($compile(
          '<div class="container" style="padding:0px"><div class="row"><div class="col-md-10">'+
          '<warehouse-model row="rows[\''+row.id+'\']" label="\'Edit Warehouse\'"></warehouse-model>'+
          '<button class="btn btn-success pull-right" ng-click="save(\''+row.id+'\')" ng-disabled="isInvalid(rows[\''+row.id+'\'])" ng-class="{\'disabled\':isInvalid(rows[\''+row.id+'\'])}">Update</button>'+
          '</div></div></div>'
        )($scope));
      });

      $http.get(host.name + '/warehouses').then(function(result) {
        $('#warehousesTable').bootstrapTable('load', result.data);
        loadRows(result);
      },function(){
        swal("Oops!", "Something went wrong!", "error")
      });

      $scope.save = function(id){
        $http.put(host.name + '/warehouse/'+$scope.rows[id].type+"/" + id,$scope.rows[id]).then(function(result) {
          $('#warehousesTable').bootstrapTable('load', result.data);
          loadRows(result);
          $.toaster({ message : 'Warehouse saved' });
        },function(){
          swal("Oops!", "Something went wrong!", "error")
        });
      }

      $scope.isInvalid = function(row){
        if (!row.transportCost || row.transportCost === '' || row.transportCost ===0) transportCost=0;
        if (!row.name || row.name==='') return true;
        if (!row.port || !row.port.name) return true;
        if (!row.type || row.type==='') return true;
        if (!row.capacity || row.capacity === '' || row.capacity ===0) return true;
        if (!row.storageCost || row.storageCost === '' || row.storageCost ===0) return true;
      }

      $scope.add = function(){
        $http.post(host.name + '/warehouse/'+$scope.newRow.type,$scope.newRow).then(function(result) {
          $('#warehousesTable').bootstrapTable('load', result.data);
          loadRows(result);
          $('form')[0].reset();
          createNewRow();
          $.toaster({ message : 'Warehouse added' });
        },function(){
          swal("Oops!", "Something went wrong!", "error")
        });
      }

    });

    createNewRow();



  }
]);
