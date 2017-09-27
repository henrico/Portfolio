angular.module("portfolio").controller('routes', [
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
      destinationA: {id:"-1"},
      destinationB: {id:"-1"},
      distance: '',
      new:true
    }
    function createNewRow(){
      $scope.newRow.distance='';
      $scope.newRow.destinationA={id:"-1"};
      $scope.newRow.destinationB={id:"-1"};
    }

    $timeout(function(){
      $('#routesTable').bootstrapTable({
        columns: [
          {
            title: 'Port 1',
            field: 'destinationA',
            formatter: function(index, row, element) {
              return row.destinationA.name;
            }
          }, {
            title: 'Port 2',
            field: 'destinationB',
            formatter: function(index, row, element) {
              return row.destinationB.name;
            }
          }, {
            title: 'Distance',
            field: 'distance'
          }, {
            title: '',
            formatter: function(index, row, element) {
              return '<a href="javascript:void(0);" class="remove"><i class="fa fa-trash" aria-hidden="true"></i></a>';
            },
            events: {
              'click .remove': function(e, value, row, index) {
                $http.delete(host.name + '/route/' + row.id).then(function(result) {
                  $('#routesTable').bootstrapTable('load', result.data);
                  loadRows(result);
                  $.toaster({ message : 'Route deleted' });
                },function(){
                  swal("Oops!", "Something went wrong!", "error")
                });
              }
            }
          }
        ]
      });

      $('#routesTable').on('expand-row.bs.table', function(e, index, row, $detail) {
        $detail.html($compile(
          '<div class="container" style="padding:0px"><div class="row"><div class="col-md-10">'+
          '<route-model row="rows[\''+row.id+'\']" label="\'Edit Route\'"></route-model>'+
          '<button class="btn btn-success pull-right" ng-click="save(\''+row.id+'\')" ng-disabled="isInvalid(rows[\''+row.id+'\'])" ng-class="{\'disabled\':isInvalid(rows[\''+row.id+'\'])}">Update</button>'+
          '</div></div></div>'
        )($scope));
      });

      $http.get(host.name + '/routes').then(function(result) {
        $('#routesTable').bootstrapTable('load', result.data);
        loadRows(result);
      },function(){
        swal("Oops!", "Something went wrong!", "error")
      });

      $scope.save = function(id){
        $http.put(host.name + '/route/' + id,$scope.rows[id]).then(function(result) {
          $('#routesTable').bootstrapTable('load', result.data);
          loadRows(result);
          $.toaster({ message : 'Route saved' });
        });
      }

      $scope.isInvalid = function(row){
        if (!row.destinationA || !row.destinationA.id) return true;
        if (!row.destinationB || !row.destinationB.id) return true;
        if (!row.distance || row.distance === '' || row.distance ===0) return true;
      }

      $scope.add = function(){
        $http.post(host.name + '/route/',$scope.newRow).then(function(result) {
          $('#routesTable').bootstrapTable('load', result.data);
          loadRows(result);
          $scope.formData.$setPristine();
          $.toaster({ message : 'Route added' });
          createNewRow();
        });
      }

    });

    createNewRow();



  }
]);
