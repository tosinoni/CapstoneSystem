angular.module('CapstoneSystem')
    .controller('WelcomeController', ['$scope', function ($scope) {
        var height = $('.content').height();
        $('.sidebar').height(height)​;
    }]);
