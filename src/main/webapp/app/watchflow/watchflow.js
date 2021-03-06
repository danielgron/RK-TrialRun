'use strict';

angular.module('myApp.watchflow', ['ngRoute']);



angular.module('myApp.watchflow').controller('WatchFlowCtrl', [function () {
        var vm = this;
        vm.active = 1;
        vm.select = select;
        
        function select(){
            window.console.log('hurra!');
        }


    }]);


angular.module('myApp.watchflow').config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/watchflow', {
            templateUrl: 'app/watchflow/templates/watchflow.html',
            controller: 'WatchFlowCtrl',
            controllerAs: 'wfc'
        });
        $routeProvider.when('/singleNewWatchCard/:param',{
           templateUrl: 'app/watchflow/newWatchCard/singleview/singlenewwatchcard.html' 
        });
        $routeProvider.when('/assignquatity/:param',{
           templateUrl: 'app/watchflow/newWatchCard/singleview/assignquatity.html' 
        });
        $routeProvider.when('/request',{
           templateUrl: 'app/watchflow/request/templates/request.html' 
       });
       $routeProvider.when('/approvedrequest',{
           templateUrl: 'app/watchflow/request/templates/approvedrequest.html' 
       });
       $routeProvider.when('/sentrequest',{
           templateUrl: 'app/watchflow/request/templates/sentrequest.html' 
       });
       $routeProvider.when('/singlepending/:param',{
           templateUrl: 'app/watchflow/pending/single/pendingsingle.html',
           controller: 'PendingSingleCtrl',
           controllerAs: 'pendingCtrl'
       });
        $routeProvider.when('/singlefunctions/:param',{
           templateUrl: 'app/watchflow/pending/single/TEMPfunction.html'
       });
       $routeProvider.when('/watchcard/:param',{
           templateUrl: 'app/watchflow/pending/finishedwatchcard/viewWatchCard.html'
       });
    }]);


