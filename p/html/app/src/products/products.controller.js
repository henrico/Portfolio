angular.module("portfolio").controller('products', [
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
        name:'',
        new:true
      }
    }

    $timeout(function(){
      $('#productsTable').bootstrapTable({
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
                $http.delete(host.name + '/product/' + row.id).then(function(result) {
                  $('#productsTable').bootstrapTable('load', result.data);
                  loadRows(result);
                });
              }
            }
          }
        ]
      });

      $('#productsTable').on('expand-row.bs.table', function(e, index, row, $detail) {
        $detail.html($compile(
          '<div class="container" style="padding:0px"><div class="row"><div class="col-md-10">'+
          '<model-form heading="\'Maintain Product\'">'+
          '<form-field type="\'text\'" data="rows[\''+row.id+'\']" column="\'name\'" label="\'Name\'" field-id="\''+row.id+'name\'" placeholder="\'Product Name\'"></form-field>'+
          '</model-form>'+
          '<button class="btn btn-success" ng-click="save(\''+row.id+'\')" ng-disabled="isInvalid(rows[\''+row.id+'\'])" ng-class="{\'disabled\':isInvalid(rows[\''+row.id+'\'])}">Update</button>'+
          '</div></div></div>'
        )($scope));
      });

      $http.get(host.name + '/products').then(function(result) {
        $('#productsTable').bootstrapTable('load', result.data);
        loadRows(result);
      });

      $scope.save = function(id){
        $http.put(host.name + '/product/' + id,$scope.rows[id]).then(function(result) {
          $('#productsTable').bootstrapTable('load', result.data);
          loadRows(result);
        });
      }

      $scope.isInvalid = function(row){
        if (!row.name || row.name==='') return true;
      }

      $scope.add = function(){
        $http.post(host.name + '/product/',$scope.newRow).then(function(result) {
          $('#productsTable').bootstrapTable('load', result.data);
          loadRows(result);
          $('form')[0].reset();
          createNewRow();
        });
      }

    });

    createNewRow();



  }
]);
