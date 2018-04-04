angular.module('CapstoneSystem')
    .controller('ProjectController', function ($scope, $routeParams, Project, User, SwalService) {
        var projectId = $routeParams.id;
        var canUserApplyForProject = function (user, project) {
            return $scope.isUserAStudent && project.programsAllowed.includes(user.program);
        };

        var isProjectForProf = function (user, project) {
            return $scope.isUserAProf && project.supervisor.id == user.id;
        };

        Project.getProjectById(projectId).then(function (project) {
            if(project) {
                $scope.project = project;

                User.getCurrentUser().then(function (user) {
                    if(user) {
                        $scope.isUserAStudent = $scope.isAuthenticated && user.role == User.STUDENT;
                        $scope.isUserAProf = $scope.isAuthenticated && user.role == User.PROFESSOR;

                        $scope.isProjectForProf = isProjectForProf(user, project);

                        var isUserAllowedToApplyToProjects = canUserApplyForProject(user, project);

                        if(!_.isEmpty(user.appliedProjects)) {
                            var isProjectAlreadyApplied = user.appliedProjects.find(function (userProject) {
                                return project.id == userProject.id;
                            });
                            $scope.isApply = isUserAllowedToApplyToProjects && !isProjectAlreadyApplied;
                            $scope.isCancel = isProjectAlreadyApplied;
                        } else {
                            $scope.isApply = isUserAllowedToApplyToProjects;
                        }
                    }
                });
            }
        });

        $scope.getProgramsAllowed = function (programs) {
            if(programs) {
                return programs.join();
            }
        }

        $scope.applyForProject = function () {
            Project.applyForProject($scope.project).then(function (res) {
                if (res.status == 200) {
                    swal('Yaah!', 'Project Application submitted.', 'success');
                    $scope.isCancel = true;
                    $scope.isApply = false;
                } else {
                    swal('Oops..!', res.data.message, 'error');
                }
            })
        }

        $scope.cancelApplicationForProject = function () {
            var callbackForCancelProject = function() {
                Project.cancelApplicationForProject($scope.project).then(function (res) {
                    if (res.status == 200) {
                        $scope.isCancel = false;
                        $scope.isApply = true;
                        swal('Yaah!', 'Project Application canceled.', 'success');
                    } else {
                        swal('Oops..!', res.data.message, 'error');
                    }
                });
            }
            SwalService.delete(callbackForCancelProject, 'Yes, Cancel Application!');
        }

        $scope.openAddStudentToProjectModal = function () {
            $scope.appliedStudents = [];

            //obtaining the list of students real time
            Project.getProjectById(projectId).then(function (project) {
                if (project.appliedStudents) {
                    $scope.appliedStudents = project.appliedStudents;
                    $scope.project.members = project.members;
                }
                $('#addStudentsModal').modal('toggle');
            });
        }

        $scope.addStudentsToProject = function () {
            Project.addStudents($scope.project).then(function (res) {
                if (res.status == 200) {
                    swal('Yaah!', 'Students successfully added.', 'success');
                } else {
                    swal('Oops..!', res.data.message, 'error');
                }

                $('#addStudentsModal').modal('toggle');
            });
        }

        $scope.openEditProjectModal = function () {
            $scope.editProjectData = angular.copy($scope.project);
            $('#editProjectModal').modal('toggle');
        }

        $scope.editProject = function (project) {
            Project.editProject(project).then(function (res) {
                if (res.status == 200) {
                    $scope.project = res.data.entity;
                    swal('Yaah!', 'Project ' + project.name + ' edited successfully!', 'success');
                    $('#editProjectModal').modal('toggle');
                    $scope.editProjectData = {};
                } else {
                    swal('Oops..!', res.data.message, 'error');
                }
            })
        }
    });
