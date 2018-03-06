// Declare app level module which depends on filters, and services
angular.module('CapstoneSystem', ['ngResource', 'ngRoute', 'routeStyles','froala'])
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
            .when('/welcome', {
                templateUrl: 'views/welcome/welcome.html',
                controller: 'WelcomeController',
                css: 'css/welcome/welcome.css',
            })
            .when('/projects', {
                templateUrl: 'views/projects/projects.html',
                controller: 'ProjectsController',
                css: 'css/projects/projects.css',
            })
            .when('/project', {
                templateUrl: 'views/projects/project.html',
                controller: 'ProjectsController',
                css: 'css/projects/projects.css',
            })
            .when('/profile', {
                templateUrl: 'views/profile/profile.html',
                controller: 'ProfileController',
                css: 'css/profile/profile.css',
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
