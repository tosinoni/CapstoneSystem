'use strict';

angular.module('CapstoneSystem')
    .factory('Project', function($http) {
        var config = {skipAuthorization: true};

        return {
            getAllProjects: function () {
                return $http.get("/api/projects", config)
                .then(function (res) {
                    if(res.status == 200)
                        return res.data.entity;
                }, function (err) {
                    return err;
                });
            },

            getProjectById: function (id) {
                return $http.get("/api/projects/" + id, config)
                .then(function (res) {
                    if(res.status == 200)
                        return res.data.entity;
                }, function (err) {
                    return err;
                });
            },

            createProject: function (project) {
                return $http.post("/api/projects", project)
                .then(function (res) {
                    return res;
                }, function (err) {
                    return err;
                });
            }
        }
    });