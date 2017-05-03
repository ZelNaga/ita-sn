var app = angular.module('app', ["ngStorage", "ui.router"]);


app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/login');

    $stateProvider
        .state('personal', {
            url: '/personal',
            templateUrl: 'personal-info.html',
            controller: 'PersonalInfoCtrl'
        })
        .state('login', {
            url: '/login',
            templateUrl: 'login.html',
            controller: 'LoginCtrl'
        })
        .state('home', {
            url: '/home',
            templateUrl: 'home-page.html',
            controller: 'HomeController'
        })
    ;
});

app.directive('addPost', function () {
    return {
        restrict: 'E',
        templateUrl: 'templates/add-post-form.html'
    }
});

app.directive('userPost', function () {
    return {
        restrict: 'E',
        templateUrl: 'templates/user-posts.html'
    }
});



