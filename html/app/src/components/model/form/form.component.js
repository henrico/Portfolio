angular.module('portfolio').component('modelForm',{

  templateUrl: 'src/components/model/form/form.html',
  transclude: true,
  bindings:{
    heading: '=',
    name: '='
  }

});
