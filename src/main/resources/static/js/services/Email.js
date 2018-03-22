'use strict';

angular.module('CapstoneSystem')
    .factory('Email', function($http) {
        return {
            sendEmail: function (email) {
                return $http.post("/api/email/", email)
                .then(function (res) {
                    return res;
                }, function (err) {
                    return err;
                });
            }
        }
    });