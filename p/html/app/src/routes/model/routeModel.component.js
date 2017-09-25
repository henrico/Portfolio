angular.module('portfolio').component('routeModel',{

  templateUrl: 'src/routes/model/routeModel.html',
  bindings:{
    row: '=',
    label: '='
  },
  controller:[
  '$scope',
  '$timeout',
  '$http',
  '$compile',
  'host',
  function($scope, $timeout, $http, $compile, host) {

    $http.get(host.name + '/ports').then(function(result) {
      $scope.$ctrl.models = result.data;
    }, function() {
      swal("Oops!", "Something went wrong!", "error")
    });
  }]

});
