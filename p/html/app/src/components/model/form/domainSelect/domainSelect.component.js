angular.module('portfolio').directive('formSelect',['$timeout', function($timeout) {
  return {

    templateUrl: 'src/components/model/form/domainSelect/domainSelect.html',
    transclude: true,
    replace: true,

      link: function(scope) {
        if(scope.applyModel){
          scope.field = ""+scope.data[scope.column].id;
        } else {
          scope.field = scope.data[scope.column];
        }

        var selecting=false;
        scope.$watch('field', function(n, o) {
          if (n && n != o) {
            selecting=true;
            if (scope.applyModel){
              scope.data[scope.column] = getModelById(n);
            } else {
              if (n!=-1)
                scope.data[scope.column] = getModelById(n)['id'];
              else
                scope.data[scope.column] = '';
            }
            $timeout(function(){
              selecting=false;
            });


          }
        })

        function getModelById(id) {
          for (var cur in scope.models) {
            if (scope.models[cur].id == id) {
              return scope.models[cur];
            }
          }
        }

        scope.$watch('data.'+scope.column, function(n,o){
          if (!selecting && n && n!=o){
            if (scope.applyModel){
              scope.field = scope.data[scope.column].id;
            } else {
              scope.field = scope.data[scope.column];
            }
          }
        })
      },
    scope: {
      label: '=',
      fieldId: '=',
      placeholder: '=',
      data: '=',
      column: '=',
      type: '=',
      models: '=',
      displayColumn: '=',
      applyModel: '='
    }

  }
}]);
