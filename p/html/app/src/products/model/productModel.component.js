angular.module('portfolio').component('productModel',{

  templateUrl: 'src/products/model/productModel.html',
  bindings:{
    row: '=',
    label: '='
  }

});
