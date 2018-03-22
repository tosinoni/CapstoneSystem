angular.module('CapstoneSystem')
    .controller('ProjectController', function ($scope, $routeParams, Project) {
        var projectId = $routeParams.id;
        Project.getProjectById(projectId).then(function (project) {
            if(project) {
                console.log(project);
                $scope.project = project;
            }
        });

        $scope.getProgramsAllowed = function (programs) {
            if(programs) {
                return programs.join();
            }
        }
    });
