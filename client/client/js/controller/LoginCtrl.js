/**
 * Created by RiderHood on 03.05.2017.
 */
app.controller('LoginCtrl', function ($scope, $http, $localStorage, $state) {
    $scope.login = function () {
        $http({
            method: "POST",
            url: "http://127.0.0.1:8080/oauth/token?password=" + $scope.user.password + "&username=" + $scope.user.login + "&grant_type=password&scope=write&client_secret=123456&client_id=ita-sn-ws",
            headers: {
                "Authorization": "Basic " + btoa("ita-sn-ws:123456")
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