app.controller('PostCtrl', function($scope, $http, $localStorage) {
    $scope.posts = [];
    $scope.page = 0;
    $scope.pageSize = 15;
    $scope.token = $localStorage.accessToken; // move to rootscope

    $scope.savePost = function() {
        $http({
            method : "POST",
            url : "http://127.0.0.1:8080/posts",
            headers: {
                "Authorization" : "Bearer " + $localStorage.accessToken
            },
            data: $scope.post
        }).then(function (response) {
            $scope.refresh();
        }, function(response) {
            //TODO handle error response
        });

    };


    $scope.refresh = function() {
        $scope.posts = [];
        $scope.page = 0;
        $scope.end = false; // todo: check posts size
        $scope.loadPost();
    };

    $scope.loadPost = function() {
        $scope.getPosts($scope.page, $scope.pageSize, function(response) {
            $scope.posts.push.apply($scope.posts, response.data);
            if(response.data && response.data.length >= $scope.pageSize-1){
                $scope.page++;
            } else {
                $scope.end = true;
            }
        });
    };


    $scope.getPosts = function(page, size, onSuccess) {
        if(!page)
            page = 0;
        if(!size)
            size = 5;
        $http({
            method : "GET",
            url : "http://127.0.0.1:8080/posts?page=" + page + "&size=" + size,
            headers: {
                "Authorization" : "Bearer " + $scope.token
            }
        }).then(onSuccess, $scope.onError);

    };

    $scope.onError = function(error) {
        console.log(error);
    };

   $scope.subscribe = function() {
       document.addEventListener("scroll", function (event) {
       if(!$scope.end){
           checkForNewDiv();
           }
       });

       var checkForNewDiv = function() {
          if($(window).scrollTop() + $(window).height() > $(document).height() - 20) {
                 $scope.loadPost();
             }
       };

    };
    $scope.loadPost();
    $scope.subscribe();
});
