'use strict';

angular.module('CapstoneSystem')
    .factory('Auth', function($http, jwtHelper) {

        var config = {skipAuthorization: true};


        return {
            COORDINATOR: 'COORDINATOR',
            login: function (user) {
                return $http.post("/SECP/login", user, config)
                    .then(function (res) {
                        var user = res.data;
                        localStorage.setItem('token', user.token);
                        localStorage.setItem('userID', user.userID);
                        localStorage.setItem('loginRole', user.loginRole);
                        localStorage.setItem('username', user.username);
                        return res;
                    }, function (err) {
                        return err;
                    });
            },

            isTokenExpired: function () {
                var token = localStorage.getItem('token');
                if (token) {
                    return jwtHelper.isTokenExpired(token);
                }
                return true;
            },

            logout: function () {
                localStorage.clear();
            }
        }
    });