angular.module('CapstoneSystem')
    .controller('ProfileController', ['$scope', function ($scope) {
        $scope.user = JSON.parse(localStorage.getItem("user"));
        console.log($scope.user);
    }]);
