angular.module('CapstoneSystem')
    .controller('SubmissionsController', function ($scope, User, SubmissionService) {
        $scope.isProfOrCoordinator = false;

        User.getCurrentUser().then(function (user) {
            $scope.projectsSupervised = [];

            if(user) {
                var isUserAprofOrCoordinator = user.role == User.PROFESSOR || user.role == User.COORDINATOR;
                $scope.isProfOrCoordinator = $scope.isAuthenticated && isUserAprofOrCoordinator;
                $scope.projectsSupervised = user.projectsSupervised;
            }
        });

        $scope.editGrade = function() {
            //open modal
            $('#gradesModal').modal('show');
        }

        $scope.addSubmission = function() {
            //open modal
            $('#submitModal').modal('show');
        }

        $scope.editDeliverable = function() {
            //open modal
            $('#addDeliverableModal').modal('show');
        }

        $scope.openAddDeliverableToProjectModal = function () {
            $('#addDeliverableModal').modal('toggle');
        }

        $scope.addDeliverableToProject = function () {
            SubmissionService.addDeliverable($scope.submission).then(function (res) {
                if (res.status == 200) {
                    swal('Yaah!', 'Deliverable created successfully.', 'success');
                    $scope.submission = {};
                } else {
                    swal('Oops..!', res.data.message, 'error');
                }

                $('#addDeliverableModal').modal('toggle');
            });
        }
    });
