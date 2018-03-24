// Declare app level module which depends on filters, and services
angular.module('CapstoneSystem', ['ngResource', 'ngRoute', 'routeStyles','froala', 'angular-jwt', 'datatables'])
    .config(function ($routeProvider, $locationProvider, jwtOptionsProvider, $httpProvider) {

        //configuring authentication
        jwtOptionsProvider.config({
            unauthenticatedRedirectPath: '/login',
            authPrefix: 'Bearer ',
            tokenGetter: ['Auth', function(Auth) {
                if (Auth.isTokenExpired()) {
                    return null;
                }

                return localStorage.getItem('token');
            }]
        });
        $httpProvider.interceptors.push('jwtInterceptor');

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
                requiresLogin: true
            })
            .when('/projects', {
                templateUrl: 'views/projects/projects.html',
                controller: 'ProjectsController',
                css: 'css/projects/projects.css',
            })
            .when('/project/:id', {
                templateUrl: 'views/projects/project.html',
                controller: 'ProjectController',
                css: 'css/projects/projects.css',
            })
            .when('/profile', {
                templateUrl: 'views/profile/profile.html',
                controller: 'ProfileController',
                css: 'css/profile/profile.css',
                requiresLogin: true
            })
            .when('/email', {
                templateUrl: 'views/email/email.html',
                controller: 'EmailController',
                css: 'css/email/email.css',
            })
            .when('/announcements', {
                templateUrl: 'views/home/announcements.html',
                controller: 'AnnouncementsController',
                css: 'css/home/announcements.css',
            })
            .when('/schedule', {
                templateUrl: 'views/projects/schedule.html',
                controller: 'ScheduleController',
                css: 'css/projects/schedule.css',
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
    .run(function($rootScope, Auth, authManager, $location) {
        $rootScope.logout = function() {
            Auth.logout();
            location.reload();
        }

        //side nav height css changes
        $('.sidenav').css("height",$(document).height() + "px");

        $rootScope.getHomeUrl = function() {
            if(Auth.isTokenExpired()) {
                return '/login';
            }
            return '/welcome';
        }

        $rootScope.$on("$locationChangeStart", function(event) {
            $rootScope.isHomePage = $location.path() === "/";
        });

        authManager.checkAuthOnRefresh();
        authManager.redirectWhenUnauthenticated();
        $('.sidenav').css("height",$(document).height() + "px");
    });
