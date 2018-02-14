// Declare app level module which depends on filters, and services
angular.module('CapstoneSystem', ['ngResource', 'ngRoute', 'routeStyles'])
    .config(function ($routeProvider, $locationProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/home/home.html',
                controller: 'HomeController',
                css: 'css/home/home.css',
            })
            .when('/login', {
                templateUrl: 'views/login/login.html',
                controller: 'LoginController',
                css: 'css/login/login.css',
            })
            .otherwise({
                templateUrl: 'views/error/error.html'
            });
            // use the HTML5 History API
            $locationProvider.html5Mode({
                enabled: true,
                requireBase: false
            });
    })
    .run(function() {

    });
