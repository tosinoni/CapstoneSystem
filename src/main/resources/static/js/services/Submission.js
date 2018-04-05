'use strict';

angular.module('CapstoneSystem')
    .factory('SubmissionService', function($http) {
        return {
            addDeliverable: function (submissionDTO) {
                return $http.post("/api/submissions/addDeliverable", submissionDTO)
                .then(function (res) {
                    return res;
                }, function (err) {
                    return err;
                });
            },

            getSubmissionsForProject: function (projectId) {
                return $http.get("/api/submissions/project/" + projectId)
                .then(function (res) {
                    return res;
                }, function (err) {
                    return err;
                });
            }
        }
    });