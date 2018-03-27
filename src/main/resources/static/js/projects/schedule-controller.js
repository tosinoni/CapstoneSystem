angular.module('CapstoneSystem')
    .controller('ScheduleController', function ($scope, $routeParams, Project, User) {
        $scope.isCoordinator = false;

        User.getCurrentUser().then(function (user) {
            if(user) {
                $scope.isCoordinator = $scope.isAuthenticated && (user.role == User.COORDINATOR);
            }
        });
    });
