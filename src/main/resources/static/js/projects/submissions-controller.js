angular.module('CapstoneSystem')
    .controller('SubmissionsController', function ($scope, User, SubmissionService) {
        $scope.isProfOrCoordinator = false;

        User.getCurrentUser().then(function (user) {
            $scope.projectsSupervised = [];

            if(user) {
                var isUserAprofOrCoordinator = user.role == User.PROFESSOR || user.role == User.COORDINATOR;
                $scope.isProfOrCoordinator = $scope.isAuthenticated && isUserAprofOrCoordinator;
                $scope.projectsSupervised = user.projectsSupervised;
                if(!_.isEmpty($scope.projectsSupervised)) {
                    $scope.selectedProject = $scope.projectsSupervised[0];
                    $scope.getSubmissionsForProject();
                }
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

        $scope.getTime = function(time) {
            if(time) {
                var date = new Date(time);
                return moment(date).format('MMMM Do YYYY, h:mm:ss a');
            }
        }

        $scope.addDeliverableToProject = function () {
            $scope.deliverable.project = $scope.selectedProject;
            SubmissionService.addDeliverable($scope.deliverable).then(function (res) {
                if (res.status == 200) {
                    swal('Yaah!', 'Deliverable created successfully.', 'success');
                    $scope.deliverable = {};
                    var submissions = res.data.entity;

                    if(_.isEmpty($scope.submissions)) {
                        $scope.submissions = [submissions];
                    } else {
                        $scope.submissions.push(res.data.entity);
                    }
                } else {
                    swal('Oops..!', res.data.message, 'error');
                }

                $('#addDeliverableModal').modal('toggle');
            });
        }

        $scope.getSubmissionsForProject = function () {
            SubmissionService.getSubmissionsForProject($scope.selectedProject.id).then(function (submissions) {
                if(!_.isEmpty(submissions)) {
                    $scope.submissions = submissions;
                }
            });
        }
    });
