angular.module('myApp.watchflow')

        .directive('contact', function () {

            return {
                //controller: 'RequestController',
                //controllerAs: 'requestCtrl',
                //bindToController: true,
                restrict: 'E',
                replace: 'true',
                templateUrl: 'app/watchflow/request/templates/contact.html'

            };
        })

        ;