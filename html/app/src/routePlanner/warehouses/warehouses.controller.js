angular.module("portfolio").controller('warehouses', [
  '$scope',
  '$http',
  '$compile',
  '$timeout',
  'host',
  function($scope, $http, $compile, $timeout, host) {

    $scope.heading = "Warehouses";
    $scope.description = "Add a Warehouse and select a Port it will supply."

    $scope.newRow = {
      name: '',
      type: "-1",
      capacity: '',
      storageCost: '',
      transportCost: '',
      port: {id:"-1"},
      new: true
    }

    $scope.createNewRow = function(){

        $scope.newRow.id= '';
        $scope.newRow.name= '';
        $scope.newRow.type= "-1";
        $scope.newRow.capacity= '';
        $scope.newRow.storageCost= '';
        $scope.newRow.transportCost= '';
        $scope.newRow.port= {id:"-1"};

    }

    $scope.columns = [
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
      }
    ];

    $scope.expandString = function(row){
      return '<div class="container" style="padding:0px"><div class="row"><div class="col-md-10">'+
      '<warehouse-model read-only="readOnly[\''+row.id+'\']" row="rows[\''+row.id+'\']" label="\'Editing Warehouse\'"></warehouse-model>'+
      '<button class="btn btn-success pull-right" ng-click="save(\''+row.id+'\')" ng-disabled="$ctrl.isInvalid(rows[\''+row.id+'\'])||readOnly[\''+row.id+'\']" ng-class="{\'disabled\':isInvalid(rows[\''+row.id+'\'])}">Update</button>'+
      '</div></div></div>'
    }

    $scope.saved = function(){
      $.toaster({ message : 'All related Schedules Have been removed', priority : 'info' });
    }

    $scope.isInvalid = function(row){
      if (!row.transportCost || row.transportCost === '' || row.transportCost ===0) transportCost=0;
      if (!row.name || row.name==='') return true;
      if (!row.port || row.port.id===-1) return true;
      if (!row.type || row.type===-1) return true;
      if (!row.capacity || row.capacity === '' || row.capacity ===0) return true;
      if (!row.storageCost || row.storageCost === '' || row.storageCost ===0) return true;
    }

    $scope.restName='warehouse';



  }
]);
