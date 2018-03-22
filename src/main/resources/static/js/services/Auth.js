'use strict';

angular.module('CapstoneSystem')
    .factory('Auth', function($http, jwtHelper) {

        var config = {skipAuthorization: true};


        return {
            COORDINATOR: 'COORDINATOR',

            login: function (user) {
                return $http.post("/api/users/login", user, config)
                    .then(function (res) {
                        console.log(res);
                        if(res.status == 200 && res.data && res.data.entity) {
                            var user = res.data.entity;
                            localStorage.setItem('token', user.token);
                            localStorage.setItem('user', JSON.stringify(user));
                        }
                        return res;
                    }, function (err) {
                        return err;
                    });
            },

            register: function (user) {
                return $http.post("/api/users/sign-up", user, config)
                    .then(function (res) {
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