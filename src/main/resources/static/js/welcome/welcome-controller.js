angular.module('CapstoneSystem')
    .controller('WelcomeController', function ($scope, User, Project) {
        $scope.isProfOrCoordinator = false;
        $scope.studentHasProject = false;
        $scope.projects = [];
        $scope.projectHeaders = ['ID', 'NAME', 'SUPERVISOR', 'MEMBERS', 'PROGRAMS'];
        $scope.programs = [];

        User.getCurrentUser().then(function (user) {
            if(user) {
                var isUserAprofOrCoordinator = user.role == User.PROFESSOR || user.role == User.COORDINATOR;
                $scope.isProfOrCoordinator = $scope.isAuthenticated && isUserAprofOrCoordinator;
                if(user) {
                    $scope.studentHasProject =false;
                }
                if(user.projectsSupervised) {
                    $scope.projects = user.projectsSupervised;
                }
                console.log(user);
            }
        });

        User.getPrograms().then(function (programs) {
            $scope.programs = programs;
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
