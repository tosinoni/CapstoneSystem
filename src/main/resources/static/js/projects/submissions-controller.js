angular.module('CapstoneSystem')
    .controller('SubmissionsController', function ($scope, User, Project) {
        $scope.isProfOrCoordinator = false;

        User.getCurrentUser().then(function (user) {
            if(user) {
                var isUserAprofOrCoordinator = user.role == User.PROFESSOR || user.role == User.COORDINATOR;
                $scope.isProfOrCoordinator = $scope.isAuthenticated && isUserAprofOrCoordinator;
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

        $scope.openAddDeliverableToProjectModal = function () {
            $scope.projectsSupervised = [];

            //obtaining the list of projects for prof
            User.getCurrentUser().then(function (user) {
                if(user) {
                    $scope.projectsSupervised = user.projectsSupervised;
                }
                $('#addDeliverableModal').modal('toggle');
            });
        }

        $scope.addDeliverableToProject = function () {
            Project.addDeliverable($scope.project).then(function (res) {
                if (res.status == 200) {
                    swal('Yaah!', 'Deliverable created successfully.', 'success');
                } else {
                    swal('Oops..!', res.data.message, 'error');
                }

                $('#addDeliverableModal').modal('toggle');
            });
        }

        // Set the date we're counting down to
        var countDownDate = new Date("April 5, 2018 12:00:00").getTime();

        // Update the count down every 1 second for report submission
        var x = setInterval(function() {

            // Get todays date and time
            var now = new Date().getTime();

            // Find the distance between now an the count down date
            var distance = countDownDate - now;

            // Time calculations for days, hours, minutes and seconds
            var days = Math.floor(distance / (1000 * 60 * 60 * 24));
            var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
            var seconds = Math.floor((distance % (1000 * 60)) / 1000);

            // Output the result in an element with id="demo"
            if(document.getElementById("demo")){
                document.getElementById("demo").innerHTML = days + "d " + hours + "h " + minutes + "m " + seconds + "s ";
            }
            // If the count down is over, write some text
            if (distance < 0) {
                clearInterval(x);
                document.getElementById("demo").innerHTML = "EXPIRED";
            }
        }, 1000);
    });
