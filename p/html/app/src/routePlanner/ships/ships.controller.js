angular.module("portfolio").controller('ships', [
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
      name:'',
      speed:'',
      capacity:'',
      new:true
    }
    function createNewRow(){
      $scope.newRow.name='';
      $scope.newRow.speed='';
      $scope.newRow.capacity='';
    }

    $timeout(function(){
      $('#shipsTable').bootstrapTable({
        columns: [
          {
            title: 'Name',
            field: 'name'
          }, {
            title: 'Speed',
            field: 'speed'
          }, {
            title: 'Capacity',
            field: 'capacity'
          }, {
            title: '',
            formatter: function(index, row, element) {
              return '<a href="javascript:void(0);" class="remove"><i class="fa fa-trash" aria-hidden="true"></i></a>';
            },
            events: {
              'click .remove': function(e, value, row, index) {
                $http.delete(host.name + '/rest/ship/' + row.id).then(function(result) {
                  $('#shipsTable').bootstrapTable('load', result.data);
                  loadRows(result);
                  $.toaster({ message : 'Ship deleted' });
                },function(){
                  swal("Oops!", "Something went wrong!", "error")
                });
              }
            }
          }
        ]
      });

      $('#shipsTable').on('expand-row.bs.table', function(e, index, row, $detail) {
        $detail.html($compile(
          '<div class="container" style="padding:0px"><div class="row"><div class="col-md-10">'+
          '<ship-model row="rows[\''+row.id+'\']" label="\'Edit Ship\'"></ship-model>'+
          '<button class="btn btn-success pull-right" ng-click="save(\''+row.id+'\')" ng-disabled="isInvalid(rows[\''+row.id+'\'])" ng-class="{\'disabled\':isInvalid(rows[\''+row.id+'\'])}">Update</button>'+
          '</div></div></div>'
        )($scope));
      });

      $http.get(host.name + '/rest/ship').then(function(result) {
        $('#shipsTable').bootstrapTable('load', result.data);
        loadRows(result);
      },function(){
        swal("Oops!", "Something went wrong!", "error")
      });

      $scope.save = function(id){
        $http.put(host.name + '/rest/ship/' + id,$scope.rows[id]).then(function(result) {
          $.toaster({ message : 'All related Schedules Have been removed', priority : 'info' });
          $('#shipsTable').bootstrapTable('load', result.data);
          loadRows(result);
          $.toaster({ message : 'Ship saved' });
        });
      }

      $scope.isInvalid = function(row){
        if (!row.name || row.name==='') return true;
        if (!row.capacity || row.capacity === '' || row.capacity ===0) return true;
        if (!row.speed || row.speed === '' || row.speed ===0) return true;
      }

      $scope.add = function(){
        $http.post(host.name + '/rest/ship/',$scope.newRow).then(function(result) {
          $('#shipsTable').bootstrapTable('load', result.data);
          loadRows(result);
          $scope.formData.$setPristine();
          createNewRow();
          $.toaster({ message : 'Ship added' });
        });
      }

    });

    createNewRow();



  }
]);
