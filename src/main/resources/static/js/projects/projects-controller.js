angular.module('CapstoneSystem')
    .controller('ProjectsController', function ($scope, User, Project) {
        $scope.isProfOrCoordinator = false;
        $scope.projects = [];
        $scope.projectHeaders = ['ID', 'NAME', 'SUPERVISOR', 'MEMBERS', 'PROGRAMS'];
        $scope.createProjectData = {}; //the data sent to the modal for create project

        User.getCurrentUser().then(function (user) {
            if(user) {
                var isUserAprofOrCoordinator = user.role == User.PROFESSOR || user.role == User.COORDINATOR;
                $scope.isProfOrCoordinator = $scope.isAuthenticated && isUserAprofOrCoordinator;
            }
        });

        Project.getAllProjects().then(function (projects) {
            if(projects) {
                console.log(projects);
                $scope.projects = projects;
            }
        });

        $scope.createProject = function (project) {
            if(project) {
                Project.createProject(project).then(function (res) {
                    if (res.status == 200) {
                        $scope.projects.push(res.data.entity);
                        swal('Yaah!', 'Project ' + project.name + ' added!', 'success');
                        $('#newProjectModal').modal('toggle');
                        $scope.createProjectData = {};
                    } else {
                        swal('Oops..!', res.data.message, 'error');
                    }
                })
            }

        }

        $('.studentList').select2({
            placeholder: 'Select student(s)'
        });

        $('.sidenav').css("height",$(document).height() + "px");
    });
