(function () {
    var module = angular.module('app-directives');

    module.directive('addPost', function () {
        return {
            restrict: 'E',
            templateUrl: 'templates/add-post-form.html'
        }
    });

    module.directive('userPost', function () {
        return {
            restrict: 'E',
            templateUrl: 'templates/user-posts.html'
        }
    });

})();