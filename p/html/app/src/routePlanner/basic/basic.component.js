angular.module('portfolio').component('basic',{

  templateUrl: 'src/routePlanner/basic/basic.html',
  transclude: true,
  bindings:{
    newRow: '<',
    createNewRow: '=',
    columns: '<',
    expandString: '=',
    restName: '<',
    heading: '<',
    description: '<',
    saved: '=',
    added: '=?',
    isInvalid: '=',
    forceReload: '<?',
    cantCollapse: '<?'
  },
  controller: function($scope, $timeout, $http, host, $compile){

    $scope.readOnly = [];

    expandedRows = [];

    $scope.showNewForm = false;
    $scope.showForm = function(v){
      $scope.showNewForm = v;
    }

    function loadRows(result){
      $scope.rows = {}
      for (var cur in result.data){
        $scope.rows[result.data[cur].id] = result.data[cur];
      }
      formatTable();
      $scope.readOnly = [];
      openRows();
    }

    $timeout(function(){



      $scope.$ctrl.columns.push(
        {
          title: '',
          formatter: function(index, row, element) {
            return '<a href="javascript:void(0);" class="remove text-primary"><i class="fa fa-trash fa-2x" aria-hidden="true"></i></a>'+
                  ((!$scope.$ctrl.cantCollapse)?'&nbsp;<a href="javascript:void(0);" class="edit text-primary"><i class="fa fa-pencil fa-2x" aria-hidden="true"></i></a>':'');
          },
          events: {
            'click .remove': function(e, value, row, index) {

              expandedRows[index] = false;

              for (var cur in expandedRows){
                if (expandedRows[cur] && cur > index){
                  expandedRows[cur-1] = true;
                  expandedRows[cur] = false;
                }
              }

              $http.delete(host.name + '/rest/'+ $scope.$ctrl.restName + '/' + row.id).then(function(result) {
                $('#displayTable').bootstrapTable('load', result.data);
                loadRows(result);
                $.toaster({ message : $scope.$ctrl.heading + ' deleted' });
              },function(){
                swal("Oops!", "Something went wrong!", "error")
              });
            },
            'click .edit': function(e, value, row, index) {
              $('#displayTable').bootstrapTable('expandRow', index);
              $timeout(function(){
                $scope.readOnly[row.id]=false;
              })
            }
          }
        }
      )

      $('#displayTable').bootstrapTable({
        columns: $scope.$ctrl.columns
      });

      $('#displayTable').on('expand-row.bs.table', function(e, index, row, $detail) {
        formatTable();
        $scope.readOnly[row.id] = true;
        $detail.html($compile(
          $scope.$ctrl.expandString(row)
        )($scope));
        expandedRows[index] = true;
      });

      $('#displayTable').on('collapse-row.bs.table', function(e, index) {
        formatTable();

        expandedRows[index] = false;
      });

      openRows = function() {
        for (var cur in expandedRows){
          if (expandedRows[cur]){
            $('#displayTable').bootstrapTable('expandRow', cur);
          }
        }
      }

      function load(){
        $http.get(host.name + '/rest/'+$scope.$ctrl.restName).then(function(result) {
          $('#displayTable').bootstrapTable('load', result.data);
          loadRows(result);
        },function(){
          swal("Oops!", "Something went wrong!", "error")
        });
      }
      load();

      $scope.save = function(id){
        $http.put(host.name + '/rest/'+$scope.$ctrl.restName+'/' + id,$scope.rows[id]).then(function(result) {
          $('#displayTable').bootstrapTable('load', result.data);
          loadRows(result);
          $.toaster({ message : $scope.$ctrl.heading + ' saved' });
        });

        if ($scope.$ctrl.forceReload){

          $timeout(load,100);

        }
      }

      $scope.add = function(){
        $http.post(host.name + '/rest/'+$scope.$ctrl.restName+'/',$scope.$ctrl.newRow).then(function(result) {
          $('#displayTable').bootstrapTable('load', result.data);
          loadRows(result);
          $scope.$ctrl.createNewRow();
          $.toaster({ message : $scope.$ctrl.heading + ' added' });
          if ($scope.$ctrl.forceReload){
            load();
          }
        });
      }

      $scope.$ctrl.createNewRow();

      $('.collapse').on('show.bs.collapse', function () {
            $('.collapse.in').collapse('hide');

        });
        $('.collapsed').on('click', function() {

        });



    });

    function formatTable(){
      $("#displayTable .glyphicon-plus").addClass('glyphicon-menu-right').removeClass('glyphicon-plus');
      $("#displayTable .glyphicon-minus").addClass('glyphicon-menu-down').removeClass('glyphicon-minus');
    }


  }

});
