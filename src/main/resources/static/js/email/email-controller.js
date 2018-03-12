angular.module('CapstoneSystem')
    .controller('EmailController', ['$scope', function ($scope) {
        $('.sidenav').css("height",$(document).height() + "px");
        $('#to').select2({
            placeholder: 'Students'
        });
    }]);
