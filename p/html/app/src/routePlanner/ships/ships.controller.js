angular.module("portfolio").controller('ships', [
  '$scope',
  '$http',
  '$compile',
  '$timeout',
  'host',
  function($scope, $http, $compile, $timeout, host) {

    $scope.heading = "Ships";
    $scope.description = "Ships are used on Schedules to fulfill product Orders"

    $scope.newRow = {
      name:'',
      speed:'',
      capacity:'',
      new:true
    }

    $scope.createNewRow = function(){
      $scope.newRow.name='';
      $scope.newRow.speed='';
      $scope.newRow.capacity='';
    }

    $scope.columns = [
      {
        title: 'Name',
        field: 'name'
      }, {
        title: 'Speed',
        field: 'speed'
      }, {
        title: 'Capacity',
        field: 'capacity'
      }
    ];

    $scope.expandString = function(row){
      return '<div class="container" style="padding:0px"><div class="row"><div class="col-md-10">'+
      '<ship-model read-only="readOnly[\''+row.id+'\']" row="rows[\''+row.id+'\']" label="\'Edit Ship\'"></ship-model>'+
      '<button class="btn btn-success pull-right" ng-click="save(\''+row.id+'\')" ng-disabled="$ctrl.isInvalid(rows[\''+row.id+'\'])||readOnly[\''+row.id+'\']" ng-class="{\'disabled\':isInvalid(rows[\''+row.id+'\'])}">Update</button>'+
      '</div></div></div>'
    }

    $scope.isInvalid = function(row){
      if (!row.name || row.name==='') return true;
      if (!row.capacity || row.capacity === '' || row.capacity ===0) return true;
      if (!row.speed || row.speed === '' || row.speed ===0) return true;
    }

    $scope.restName='ship';

    $scope.saved = function(){
      $.toaster({ message : 'All related Schedules Have been removed', priority : 'info' });
    }



  }
]);
