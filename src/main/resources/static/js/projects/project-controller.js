angular.module('CapstoneSystem')
    .controller('ProjectController', function ($scope, $routeParams, Project) {
        var projectId = $routeParams.id;

        var canUserApplyForProject = function (user, project) {
            var isUserAStudent = $scope.isAuthenticated && user.role == User.STUDENT;
            return isUserAStudent && project.programsAllowed.contains(user.program);
        };

        Project.getProjectById(projectId).then(function (project) {
            if(project) {
                $scope.project = project;

                User.getCurrentUser().then(function (user) {
                    if(user) {
                        $scope.isApply = canUuserApply(user, project);
                    }
                });
            }
        });

        $scope.getProgramsAllowed = function (programs) {
            if(programs) {
                return programs.join();
            }
        };
    });
