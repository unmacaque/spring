var taskControllers = angular.module('taskControllers', []);

taskControllers.controller('TaskListController', ['$scope', '$http', function($scope, $http) {
	$http.get('/tasks').success(function(data) {
		$scope.tasks = data;
	});
}]);

taskControllers.controller('TaskViewController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {
	$http.get('/tasks/' + $routeParams.taskId).success(function(data) {
		$scope.task = data;
	});
}]);
