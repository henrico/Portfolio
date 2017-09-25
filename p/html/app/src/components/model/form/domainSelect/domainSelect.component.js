angular.module('portfolio').directive('formSelect', function() {
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
        scope.$watch('field', function(n, o) {
          if (n && n != o) {
            if (scope.applyModel){
              scope.data[scope.column] = getModelById(n);
            } else {
              scope.data[scope.column] = getModelById(n)['id'];
            }

          }
        })

        function getModelById(id) {
          for (var cur in scope.models) {
            if (scope.models[cur].id == id) {
              return scope.models[cur];
            }
          }
        }
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
});
