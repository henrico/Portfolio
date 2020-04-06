angular.module("portfolio").controller('ports', [
  '$scope',
  '$http',
  '$compile',
  '$timeout',
  'host',
  function($scope, $http, $compile, $timeout, host) {

    $scope.heading = "Ports";
    $scope.description = "Indicate which Products your Ports produce. Then link Warehouses to a Port."

    $scope.newRow = {
      name: '',
      id: '-1',
      products: [],
      new: true
    }

    $scope.createNewRow = function() {
      $scope.newRow.name = '';
      $scope.newRow.id = '-1';
    }

    $scope.columns = [{
      title: 'Name',
      field: 'name'
    }];

    $scope.expandString = function(row) {
      return '<div class="container" style="padding:0px"><div class="row"><div class="col-md-10">' +
        '<port-model read-only="readOnly[' + row.id + ']" row="rows[' + row.id + ']" label="\'Editing Port\'"></port-model>' +
        '<button class="btn btn-success pull-right" ng-click="save(' + row.id + ')" ng-disabled="$ctrl.isInvalid(rows[' + row.id + '])||readOnly[' + row.id + ']" ng-class="{\'disabled\':isInvalid(rows[' + row.id + '])}">Update</button>' +
        '</div></div></div>'
    }

    $scope.saved = function() {
      $.toaster({
        message: 'All related Schedules Have been removed',
        priority: 'info'
      });
    }

    $scope.isInvalid = function(row) {
      if (!row.name || row.name === '') return true;
    }

    $scope.restName = 'port';



  }
]);
