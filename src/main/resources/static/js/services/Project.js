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
            },

            applyForProject: function (project) {
                return $http.post("/api/projects/apply", project)
                .then(function (res) {
                    return res;
                }, function (err) {
                    return err;
                });
            },

            cancelApplicationForProject: function (project) {
                return $http.post("/api/projects/cancelApplication", project)
                .then(function (res) {
                    return res;
                }, function (err) {
                    return err;
                });
            },

            addStudents: function (project) {
                return $http.post("/api/projects/addStudents", project)
                .then(function (res) {
                    return res;
                }, function (err) {
                    return err;
                });
            }
        }
    });