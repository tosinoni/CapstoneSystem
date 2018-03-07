angular.module('CapstoneSystem')
    .controller('LoginController', ['$scope', function ($scope) {
        $('#login-form-link').click(function(e) {
            $("#login-form").delay(100).fadeIn(100);
            $("#register-form").fadeOut(100);
            $('#register-form-link').removeClass('active');
            $(this).addClass('active');
            e.preventDefault();
        });
        $('#register-form-link').click(function(e) {
            $("#register-form").delay(100).fadeIn(100);
            $("#login-form").fadeOut(100);
            $('#login-form-link').removeClass('active');
            $(this).addClass('active');
            e.preventDefault();
        });
        $('.programList').select2({
            placeholder: 'Program'
        });
        $('.sidenav').css("height",$(document).height() + "px");
    }]);
