angular.module('portfolio').component('warehouseModel',{

  templateUrl: 'src/routePlanner/warehouses/model/warehouseModel.html',
  bindings:{
    row: '<',
    label: '<',
    readOnly: '<'
  },
  controller:['$scope', '$http','host', function($scope, $http, host){

    $http.get(host.name + '/rest/port').then(function(result) {
      if (result.data.length==0){
        $.toaster({ message : 'There are no Ports', priority : 'warning' });
      }
      $scope.$ctrl.ports = result.data;
    }, function() {
      swal("Oops!", "Something went wrong!", "error")
    });

    this.models = [
      {
        id: 'EXTERNAL',
        name: 'External'
      },
      {
        id: 'INTERNAL',
        name: 'Internal'
      }
    ]
  }]

});
