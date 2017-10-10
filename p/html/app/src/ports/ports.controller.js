angular.module("portfolio").controller('ports', [
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
        id: '-1',
        products: [],
        new:true
      }

    function createNewRow(){
      $scope.newRow.name='';
      $scope.newRow.id= '-1';
    }

    $timeout(function(){
      $('#portsTable').bootstrapTable({
        columns: [
          {
            title: 'Name',
            field: 'name'
          }, {
            title: '',
            formatter: function(index, row, element) {
              return '<a href="javascript:void(0);" class="remove"><i class="fa fa-trash" aria-hidden="true"></i></a>';
            },
            events: {
              'click .remove': function(e, value, row, index) {
                $http.delete(host.name + '/rest/port/' + row.id).then(function(result) {
                  $('#portsTable').bootstrapTable('load', result.data);
                  loadRows(result);
                  $.toaster({ message : 'Port deleted' });
                },function(){
                  swal("Oops!", "Something went wrong!", "error")
                });
              }
            }
          }
        ]
      });

      $('#portsTable').on('expand-row.bs.table', function(e, index, row, $detail) {
        $detail.html($compile(
          '<div class="container" style="padding:0px"><div class="row"><div class="col-md-10">'+
          '<port-model row="rows[\''+row.id+'\']" label="\'Edit Port\'"></port-model>'+
          '<button class="btn btn-success pull-right" ng-click="save(\''+row.id+'\')" ng-disabled="isInvalid(rows[\''+row.id+'\'])" ng-class="{\'disabled\':isInvalid(rows[\''+row.id+'\'])}">Update</button>'+
          '</div></div></div>'
        )($scope));
      });

      $http.get(host.name + '/rest/port').then(function(result) {
        $('#portsTable').bootstrapTable('load', result.data);
        loadRows(result);
      },function(){
        swal("Oops!", "Something went wrong!", "error")
      });

      $scope.save = function(id){
        $http.put(host.name + '/rest/port/' + id,$scope.rows[id]).then(function(result) {
          $.toaster({ message : 'All related Schedules Have been removed', priority : 'info' });
          $('#portsTable').bootstrapTable('load', result.data);
          loadRows(result);
          $.toaster({ message : 'Port saved' });
        },function(){
          swal("Oops!", "Something went wrong!", "error")
        });
      }

      $scope.isInvalid = function(row){
        if (!row.name || row.name==='') return true;
      }

      $scope.add = function(){
        $http.post(host.name + '/rest/port/',$scope.newRow).then(function(result) {
          $('#portsTable').bootstrapTable('load', result.data);
          loadRows(result);
          $scope.formData.$setPristine();
          createNewRow();
          $.toaster({ message : 'Port added' });
        },function(){
          swal("Oops!", "Something went wrong!", "error")
        });
      }

    });

    createNewRow();



  }
]);
