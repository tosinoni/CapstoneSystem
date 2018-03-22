'use strict';

angular.module('CapstoneSystem')
    .directive('kapiProjectsTable', function () {
    return {
        restrict: 'E',
        scope: {
            name: '@',
            data: '=',
            modalname: '@',
            buttonname: '@',
            isproforcoordinator: '=',
            headers: '=',
         },
        templateUrl: '/js/directives/kapi-projects-table/kapi-projects-table.html',
        link: function ($scope, element, attrs) {
            
            $scope.projectClicked = function (row) {
                window.location.href = '/project/' + row.id;
            }

            $scope.getProgramsAllowed = function (programs) {
                if(programs) {
                    return programs.join();
                }
            }
        } //DOM manipulation
    };
});
