'use strict';

angular.module('CapstoneSystem')
    .factory('SwalService', function() {

        var deleteSwal = function(callback, confirmButtonText, subText) {
            swal({
                title: 'Are you sure?',
                text: subText,
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: confirmButtonText
            }).then(function(result) {
                if (result) {
                    callback();
                }
            }).catch(function(result) {});
        };

        return {
            delete : deleteSwal
        }
    });