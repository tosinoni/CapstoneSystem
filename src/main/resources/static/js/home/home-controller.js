angular.module('CapstoneSystem')
    .controller('HomeController', ['$scope', function ($scope) {
        $('.material-button-toggle').on("click", function () {
            $(this).toggleClass('open');
            $('.option').toggleClass('scale-on');
        });
    }]);
