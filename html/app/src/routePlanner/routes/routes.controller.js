angular.module("portfolio").controller('routes', [
  '$scope',
  '$http',
  '$compile',
  '$timeout',
  'host',
  function($scope, $http, $compile, $timeout, host) {

    $scope.heading = "Routes";
    $scope.description = "Add Routes to connect two Ports"

    $scope.newRow = {
      destinationA: {id:"-1"},
      destinationB: {id:"-1"},
      distance: '',
      new:true
    }

    $scope.createNewRow = function(){
      $scope.newRow.distance='';
      $scope.newRow.destinationA={id:"-1"};
      $scope.newRow.destinationB={id:"-1"};
    }

    $scope.expandString = function(row){
      return '<div class="container" style="padding:0px"><div class="row"><div class="col-md-10">'+
      '<route-model read-only="readOnly[\''+row.id+'\']" row="rows[\''+row.id+'\']" label="\'Editing Route\'"></route-model>'+
      '<button class="btn btn-success pull-right" ng-click="save(\''+row.id+'\')" ng-disabled="$ctrl.isInvalid(rows[\''+row.id+'\'])||readOnly[\''+row.id+'\']" ng-class="{\'disabled\':isInvalid(rows[\''+row.id+'\'])}">Update</button>'+
      '</div></div></div>'
    }

    $scope.columns = [
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
      }
    ];

    $scope.isInvalid = function(row){
      if (!row.destinationA || !row.destinationA.id) return true;
      if (!row.destinationB || !row.destinationB.id) return true;
      if (!row.distance || row.distance === '' || row.distance ===0) return true;
    }

    $scope.restName='route';

    $scope.saved = function(){
    }



  }
]);
