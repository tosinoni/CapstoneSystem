angular.module('CapstoneSystem')
    .controller('SubmissionsController', function ($scope, User, Project) {
        var table = $('#submissionsTable').DataTable( {
            responsive: true
        } );
        $scope.isProfOrCoordinator = false;

        User.getCurrentUser().then(function (user) {
            if(user) {
                var isUserAprofOrCoordinator = user.role == User.PROFESSOR || user.role == User.COORDINATOR;
                $scope.isProfOrCoordinator = $scope.isAuthenticated && isUserAprofOrCoordinator;
            }
        });

        $scope.editGrade = function(row) {
            //open modal
        }
    });
