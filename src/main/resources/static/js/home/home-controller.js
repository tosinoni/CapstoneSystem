angular.module('CapstoneSystem')
    .controller('HomeController', ['$scope', function ($scope) {
        $('.material-button-toggle').on("click", function () {
            $(this).toggleClass('open');
            $('.option').toggleClass('scale-on');
        });
        $('.material-button-toggle').on("hover", function () {
            $(this).toggleClass('open');
            $('.option').toggleClass('scale-on');
        });
        $('.container.ng-scope').css("background","transparent");
    }]);
