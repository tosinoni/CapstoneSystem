'use strict';

angular.module('CapstoneSystem')
    .factory('User', function($http) {
        var config = {skipAuthorization: true};

        return {
            PROFESSOR : "PROFESSOR",
            STUDENT: "STUDENT",
            COORDINATOR: "COORDINATOR",
            getCurrentUser: function () {
                return $http.get("/api/users/currentUser")
                .then(function (res) {
                    if(res.status == 200)
                        return res.data.entity;
                }, function (err) {
                    return err;
                });
            },
            getPrograms: function () {
                return $http.get("/api/programs", config)
                .then(function (res) {
                    if(res.status == 200)
                        return res.data.entity;
                }, function (err) {
                    return err;
                });
            }
        }
    });