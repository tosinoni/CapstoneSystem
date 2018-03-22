angular.module('CapstoneSystem')
    .controller('EmailController', function ($scope, Email) {
        $scope.recipients = ["ALL_STUDENTS", "STDUENTS_WITHOUT_PROJECT"];

        $scope.sendEmail = function () {
            Email.sendEmail($scope.email).then(function (res) {
                console.log(res);
                if (res.status == 200) {
                    swal('Yaah!', 'Email Sent', 'success');
                    $scope.email = {};
                } else {
                    swal('Oops..!', res.data.message, 'error');
                }
            })
        }
    });
