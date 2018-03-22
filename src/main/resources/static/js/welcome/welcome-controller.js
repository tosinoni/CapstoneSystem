angular.module('CapstoneSystem')
    .controller('WelcomeController', ['$scope', function ($scope) {
        var table = $('#myProjectsTable').DataTable( {
            responsive: true
        } );
        $('#myProjectsTable tbody').on('click', 'tr', function () {
            var data = table.row( this ).data();
            window.location.href = '/project';
        } );
        $('.sidenav').css("height",$(document).height() + "px");
    }]);
