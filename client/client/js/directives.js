(function () {
    var module = angular.module('app-directives');

    module.directive('navbar', function () {
        return {
            restrict: 'E',
            templateUrl: 'templates/navbar.html'
        }
    });

    module.directive('addPost', function () {
        return {
            restrict: 'E',
            templateUrl: 'templates/add-post-form.html'
        }
    });

    module.directive('use  rPost', function () {
        return {
            restrict: 'E',
            templateUrl: 'templates/user-posts.html'
        }
    });

})();