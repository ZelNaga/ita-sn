var app = angular.module('app', ["ngStorage", "ui.router"]);


app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/login');

    $stateProvider
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
        .state('personal', {
            url: '/personal',
            templateUrl: 'personal-info.html',
            controller: 'PersonalInfoCtrl'
        })
        .state('contacts', {
            url: '/contacts',
            templateUrl: 'contacts.html',
            controller: 'ContactsCtrl'
        })
        .state('education', {
            url: '/education',
            templateUrl: 'education.html',
            controller: 'EducationCtrl'
        })
        .state('career', {
            url: '/career',
            templateUrl: 'career-page.html',
            controller: 'CareerCtrl'
        })
        .state('group', {
            url: '/group',
            templateUrl: 'group.html',
            controller: 'GroupCtrl'
        })
        .state('friends', {
            url: '/friends',
            templateUrl: 'friends.html',
            controller: 'FriendsCtrl'
        })
        .state('news', {
            url: '/news',
            templateUrl: 'news.html',
            controller: 'NewsCtrl'
        })
        .state('photo', {
            url: '/photo',
            templateUrl: 'photo.html',
            controller: 'PhotoCtrl'
        })
        .state('video', {
            url: '/video',
            templateUrl: 'video.html',
            controller: 'VideoCtrl'
        })
    ;
});

app.directive('navbar', function () {
    return {
        restrict: 'E',
        templateUrl: 'templates/navbar.html'
    }
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



