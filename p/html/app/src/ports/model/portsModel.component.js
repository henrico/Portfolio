angular.module('portfolio').component('portModel', {

  templateUrl: 'src/ports/model/portsModel.html',
  bindings: {
    row: '=',
    label: '='
  },
  controller: [
    '$scope',
    '$timeout',
    '$http',
    '$compile',
    'host',
    function($scope, $timeout, $http, $compile, host) {

      function portHasProduct(product) {
        for (var cur in $scope.$ctrl.row.products) {
          if ($scope.$ctrl.row.products[cur].id === product.id) {
            return true;
          }
        }
        return false;
      }

      function getProductById(id){
        for (cur in $scope.products){
          if ($scope.products[cur].id == id){
            return $scope.products[cur];
          }
        }
      }

      function removeProduct(product){
        var index=-1;
        for (var cur in $scope.$ctrl.row.products){
          if ($scope.$ctrl.row.products[cur].id == product.id) {
            index=cur;
            break;
          }
        }
        $scope.$ctrl.row.products.splice(index,1);

      }

      $timeout(function() {
        $('#portsTable' + $scope.$ctrl.row.id).bootstrapTable({
          columns: [
            {
              title: 'Name',
              field: 'name'
            }, {
              title: 'Produce',
              formatter: function(index, row, element) {
                return '<input type="checkbox" ' + (portHasProduct(row)
                  ? 'checked'
                  : '') + ' ng-click="check(\''+row.id+'\')">';
              }
            }
          ]
        });

        $http.get(host.name + '/rest/product').then(function(result) {
          if (result.data.length==0){
            $.toaster({ message : 'There are no Products', priority : 'warning' });
          }
          $('#portsTable' + $scope.$ctrl.row.id).bootstrapTable('load', result.data);
          var table = angular.element('#portsTable' + $scope.$ctrl.row.id);
          $compile(table)($scope);
          $scope.products = result.data;
        }, function() {
          swal("Oops!", "Something went wrong!", "error")
        });
      }, 100);

      $scope.check = function(id){
        var product = getProductById(id);
        if (portHasProduct(product)){
          removeProduct(product);
        } else {
          $scope.$ctrl.row.products.push(product);
        }
      }

    }
  ]

});
