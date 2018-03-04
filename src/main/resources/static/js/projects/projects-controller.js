angular.module('CapstoneSystem')
    .controller('ProjectsController', ['$scope', function ($scope) {
        var table = $('#projectsTable').DataTable( {
            responsive: true
        } );
        $('.programList').select2({
            placeholder: 'Select program(s)'
        });
        $('#projectsTable tbody').on('click', 'tr', function () {
            var data = table.row( this ).data();
            window.location.href = '/project';
        } );
    }]);