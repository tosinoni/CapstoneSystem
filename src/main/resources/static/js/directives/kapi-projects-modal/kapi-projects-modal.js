'use strict';

angular.module('CapstoneSystem')
    .directive('kapiProjectsModal', function (User) {
        return {
            restrict: 'E',
            scope: {
                frmname: '@',
                title: '@',
                inputname: '@',
                name: '@',
                modalData: '=', //this is the data to be used to populate the modal
                save: '&saveFn'
            },
            templateUrl: '/js/directives/kapi-projects-modal/kapi-projects-modal.html',
            link: function ($scope, element, attrs) {
                $scope.programs = [];

                $scope.froalaOptions = {
                    toolbarButtons: ['bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', '|', 'fontFamily', 'fontSize', 'color', 'inlineStyle', 'paragraphStyle', '|', 'paragraphFormat', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', 'quote', '-', 'insertLink', 'insertImage', 'insertVideo', 'insertFile', 'insertTable', '|', 'emoticons', 'specialCharacters', 'insertHR', 'selectAll', 'clearFormatting', '|', 'print', 'help', 'html', '|', 'undo', 'redo']
                }

                User.getPrograms().then(function (programs) {
                    $scope.programs = programs;
                });

                $scope.submit = function() {
                    $scope.save({project: $scope.modalData});
                }
            } //DOM manipulation
        };
    });
