angular.module('CapstoneSystem')
    .controller('ProfileController', function ($scope, User) {
        User.getCurrentUser().then(function (user) {
            if(user) {
                console.log(user);
                $scope.user = user;
            }
        });
    });
