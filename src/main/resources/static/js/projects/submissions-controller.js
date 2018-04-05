angular.module('CapstoneSystem')
    .controller('SubmissionsController', function ($scope, User, SubmissionService, Upload) {
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
                } else if(user.project) {
                    $scope.selectedProject = user.project;
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
            $('#submitModal').modal('toggle');
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

        $scope.changeSubmitDeliverableModalTime = function () {
            $scope.submitDeliverableModalTime = $scope.getTime($scope.selectedDeliverable.dueDate);
        }

        $scope.downloadFile = function (fileInBytes) {
            console.log(fileInBytes)
            var blob = new Blob([fileInBytes], {type: "application/pdf"}),
                url = window.URL.createObjectURL(blob);

            console.log(url);
            console.log("i am ger");
            window.open(url);
        }
        
        $scope.submitDeliverable = function () {
            Upload.upload({
                url: '/api/submissions/submitDeliverable',
                data: {
                    file: $scope.file,
                    'submissionId': $scope.selectedDeliverable.id
                }
            }).then(function (resp) {
                swal('Yaah!', $scope.selectedDeliverable.name + ' submitted successfully.', 'success');
                var index = _.findIndex($scope.submissions, function(s) { return s.id == $scope.selectedDeliverable.id; });
                $scope.submissions[index] = resp.data.entity;

                $scope.file = undefined;
                $scope.name = undefined;
                $('#submitModal').modal('toggle');
            }, function (res) {
                console.log('Error status: ' + res.status);
                swal('Oops..!', res.data.message, 'error');
            }, function (evt) {
                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
            });
        }
    });
