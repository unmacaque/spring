var noteApp = angular.module('taskApp', [ 'ngRoute', 'taskControllers' ]);

noteApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/tasks', {
		templateUrl : 'partials/task-list.html',
		controller : 'TaskListController'
	}).when('/tasks/:taskId', {
		templateUrl : 'partials/task-view.html',
		controller : 'TaskViewController'
	}).otherwise({
		redirectTo : '/tasks'
	});
}]);
