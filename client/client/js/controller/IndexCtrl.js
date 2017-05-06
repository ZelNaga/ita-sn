/**
 * Created by RiderHood on 03.05.2017.
 */
app.controller('IndexCtrl', function ($rootScope, $scope,  $state) {
    // debugger;
    if($state.includes('login')) {
        $scope.hideNavbar = true;
    } else {
        $scope.hideNavbar = false;
    }
});