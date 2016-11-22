var taskApp = angular.module('taskApp', [
	'ngRoute',
	'taskControllers',
	'taskDirectives',
	'taskFilters',
	'taskServices'
]);

taskApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/tasks', {
		templateUrl : 'partials/task-list.html',
		controller : 'TaskListController'
	}).when('/tasks/:taskId', {
		templateUrl : 'partials/task-view.html',
		controller : 'TaskViewController'
	}).when('/new-task', {
		templateUrl : 'partials/task-add.html',
		controller : 'TaskAddController'
	}).otherwise({
		redirectTo : '/tasks'
	});
}]);
