angular.module('portfolio').directive('formField',function(){ return{

  templateUrl: 'src/components/model/form/formField/formField.html',
  transclude: true,
  replace: true,
  scope:{
    label: '=',
    fieldId: '=',
    placeholder: '=',
    data: '=',
    column: '=',
    type: '=',
    max: '=',
    min: '=',
    required: '='
  },
  link: function(scope){
    if (scope.required === undefined || scope.required === null){
      scope.req=true;
    } else {
      scope.req=scope.required;
    }
  }

}});
