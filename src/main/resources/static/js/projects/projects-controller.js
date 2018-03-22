angular.module('CapstoneSystem')
    .controller('ProjectsController', function ($scope, User, Project) {
        $scope.isProfOrCoordinator = false;
        $scope.projects = [];
        $scope.projectHeaders = ['ID', 'NAME', 'SUPERVISOR', 'MEMBERS', 'PROGRAMS'];
        $scope.programs = [];

        User.getCurrentUser().then(function (user) {
            if(user) {
                var isUserAprofOrCoordinator = user.role == User.PROFESSOR || user.role == User.COORDINATOR;
                $scope.isProfOrCoordinator = $scope.isAuthenticated && isUserAprofOrCoordinator;
            }
        });

        User.getPrograms().then(function (programs) {
            $scope.programs = programs;
        });

        Project.getAllProjects().then(function (projects) {
            if(projects) {
                $scope.projects = projects;
            }
        });

        $scope.createProject = function () {
            if($scope.project) {
                Project.createProject($scope.project).then(function (res) {
                    if (res.status == 200) {
                        $scope.projects.push(res.data.entity);
                        swal('Yaah!', 'Project ' + $scope.project.name + ' added!', 'success');
                        $('#newProjectModal').modal('toggle');
                        $scope.project = {};
                    } else {
                        swal('Oops..!', res.data.message, 'error');
                    }
                })
            }

        }

        $('.studentList').select2({
            placeholder: 'Select student(s)'
        });

        $scope.froalaOptions = {
            toolbarButtons: ['bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', '|', 'fontFamily', 'fontSize', 'color', 'inlineStyle', 'paragraphStyle', '|', 'paragraphFormat', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', 'quote', '-', 'insertLink', 'insertImage', 'insertVideo', 'insertFile', 'insertTable', '|', 'emoticons', 'specialCharacters', 'insertHR', 'selectAll', 'clearFormatting', '|', 'print', 'help', 'html', '|', 'undo', 'redo']
        }

        $('.sidenav').css("height",$(document).height() + "px");
    });
