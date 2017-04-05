var app = angular.module('ItaSnClient', ["ngStorage", "ui.router"]);

app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/login');

    $stateProvider

        .state('login', {
            url: '/login',
            templateUrl: 'login.html',
            controller: 'LoginCtrl'
        })

        .state('home', {
            resolve:{
                userObj:  function($http, $localStorage){
                    // $http returns a promise for the url data
                    return $http({
                        method : "GET",
                        url : "http://127.0.0.1:8080/accounts",
                        headers: {
                            "Authorization" : "Bearer " + $localStorage.accessToken
                        }
                    });
                }
            },
            url: '/home',
            views: {
                '': { templateUrl: 'home-page.html' },
                'addPost@home': {
                    templateUrl: 'add-post-form.html',
                     controller: 'PostCtrl'
                },
                'posts@home': {
                    templateUrl: 'user-posts.html',
                    controller: 'PostCtrl'
                }
            },
            controller: function($scope, userObj) {
                $scope.user = userObj.data;
            }
        });

});

function getUserLast5Posts($scope, $http, $localStorage) {


    $http({
        method : "GET",
        url : "http://127.0.0.1:8080/posts",
        headers: {
            "Authorization" : "Bearer " + $localStorage.accessToken
        }
    }).then(function mySucces(response) {
        $scope.posts = response.data;
    }, function myError(response) {
        //TODO handle error response
    });

}






app.controller('LoginCtrl', function($scope, $http, $localStorage, $state) {
    $scope.login = function() {
        $http({
            method : "POST",
            url : "http://127.0.0.1:8080/oauth/token?password=" + $scope.user.password + "&username=" + $scope.user.login + "&grant_type=password&scope=write&client_secret=123456&client_id=ita-sn-ws",
            headers: {
                "Authorization" : "Basic " + btoa("ita-sn-ws:123456")
            }
        }).then(function mySucces(response) {
            $localStorage.accessToken = response.data.access_token;
            $state.go('home');
        }, function myError(response) {
            //TODO handle error response
            $scope.error = response.message;
            //$scope.resp = response.data.message;
        });
    };
});

app.controller('PostCtrl', function($scope, $http, $localStorage) {

    getUserLast5Posts($scope, $http, $localStorage);

    $scope.savePost = function() {


        $http({
            method : "POST",
            url : "http://127.0.0.1:8080/posts",
            headers: {
                "Authorization" : "Bearer " + $localStorage.accessToken
            },
            data: $scope.post
        }).then(function mySucces(response) {
            $scope.post.text = '';
        }, function myError(response) {
            //TODO handle error response
        });


    };

    $scope.refresh = function() {


        $http({
            method : "GET",
            url : "http://127.0.0.1:8080/posts",
            headers: {
                "Authorization" : "Bearer " + $localStorage.accessToken
            }
        }).then(function mySucces(response) {
            $scope.posts = response.data;
        }, function myError(response) {
            //TODO handle error response
        });


    };


});
