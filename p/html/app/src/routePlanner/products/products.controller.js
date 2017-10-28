angular.module("portfolio").controller('products', [
  '$scope',
  '$http',
  '$compile',
  '$timeout',
  'host',
  function($scope, $http, $compile, $timeout, host) {

    $scope.heading = "Products";
    $scope.description = "Manage your products then make them available in a Port"

    $scope.newRow = {
      name:''
    }
    $scope.createNewRow = function(){
      $scope.newRow.name='';
    }

    $scope.columns = [
      {
        title: 'Name',
        field: 'name'
      }
    ];


    $scope.expandString = function(row){
      return '<div class="container" style="padding:0px"><div class="row"><div class="col-md-10">'+
      '<product-model read-only="readOnly[\''+row.id+'\']" row="rows[\''+row.id+'\']" label="\'Editing Product\'"></product-model>'+
      '<button class="btn btn-success pull-right" ng-click="save(\''+row.id+'\')" ng-disabled="$ctrl.isInvalid(rows[\''+row.id+'\'])||readOnly[\''+row.id+'\']" ng-class="{\'disabled\':isInvalid(rows[\''+row.id+'\'])}">Update</button>'+
      '</div></div></div>'
    }

    $scope.isInvalid = function(row){
      if (!row.name || row.name==='') return true;
    }

    $scope.restName='product';

    $scope.saved = function(){
    }



  }
]);
