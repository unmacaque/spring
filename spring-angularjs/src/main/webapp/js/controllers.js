var taskControllers = angular.module('taskControllers', []);

taskControllers.controller('TaskListController', ['$scope', 'Tasks', function($scope, Tasks) {
	$scope.tasks = Tasks.query();
}]);

taskControllers.controller('TaskViewController', ['$scope', '$routeParams', 'Tasks', function($scope, $routeParams, Tasks) {
	$scope.task = Tasks.get({ id : $routeParams.taskId });
}]);
