angular.module('CapstoneSystem')
    .controller('ProjectController', function ($scope, $routeParams, Project, User) {
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

                        if(user.appliedProjects) {
                            var isProjectAlreadyApplied = user.appliedProjects.find(function (userProject) {
                                return project.id == userProject.id;
                            });

                            $scope.isApply = canUserApplyForProject(user, project) && !isProjectAlreadyApplied;
                            $scope.isCancel = !$scope.isApply;
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
                } else {
                    swal('Oops..!', res.data.message, 'error');
                }
            })
        }
    });
