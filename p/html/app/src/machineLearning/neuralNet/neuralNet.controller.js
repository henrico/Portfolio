angular.module("portfolio").controller('neuralNetCtrl', [
  '$scope',
  '$http',
  '$timeout',
  'host',
  function($scope, $http, $timeout, host) {
    $http.get(host.name+"/colors").then((result)=>{
      $scope.schemes = result.data;
    });

    $scope.random = function(){
      $http.get(host.name+"/colors/random").then((result)=>{
        $scope.randomscheme = result.data;
      });
    }

    $scope.fromColor = function(){
      var val = $('#cp').colorpicker('getValue');
      function hexToRGB(hex) {
          var r = parseInt(hex.slice(1, 3), 16),
              g = parseInt(hex.slice(3, 5), 16),
              b = parseInt(hex.slice(5, 7), 16);
              return r+"/"+g+"/"+b;
      }

      $http.get(host.name+"/colors/fromcolor/"+hexToRGB(val)).then((result)=>{
        $scope.randomscheme = result.data;
      });
    }

    function isTraining(){
      $scope.isTraining=true;
      $http.get(host.name+"/colors/training/").then((result)=>{
        $scope.isTraining=result.data.training;
        $scope.interation = result.data.interation;
        $scope.error = result.data.errorFactor;

        if ($scope.isTraining){
          $timeout(isTraining,1000);
        } else {
          $scope.random();

          $(function() {
             $('#cp').colorpicker({
                 color: '#ffaa00',
                 container: true,
                 inline: true
             });
         });
        }
      });
    }

    isTraining();


  }
]);
