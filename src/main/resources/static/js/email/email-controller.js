angular.module('CapstoneSystem')
    .controller('EmailController', function ($scope) {
        $scope.recipients = ["ALL_STUDENTS", "STDUENTS_WITHOUT_PROJECT"];

        $scope.sendEmail = function () {
            console.log($scope.email);
        }
    });
