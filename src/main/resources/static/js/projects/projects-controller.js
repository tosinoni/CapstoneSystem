angular.module('CapstoneSystem')
    .controller('ProjectsController', ['$scope', function ($scope) {
        $('#projectsTable').DataTable( {
            responsive: true
        } );
        $('.programList').select2({
            placeholder: 'Select program(s)'
        });
    }]);
