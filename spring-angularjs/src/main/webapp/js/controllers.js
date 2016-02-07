var taskControllers = angular.module('taskControllers', []);

taskControllers.controller('TaskListController', ['$scope', 'Tasks', function($scope, Tasks) {
	$scope.tasks = Tasks.query();
}]);

taskControllers.controller('TaskViewController', ['$scope', '$routeParams', 'Tasks', function($scope, $routeParams, Tasks) {
	$scope.task = Tasks.get({ id : $routeParams.taskId });

	$scope.toggleDone = function() {
		Tasks.get({ id : $scope.task.id }, function(task) {
			task.done = !task.done;
			Tasks.update(null, task).$promise.then(function(task) {
				$scope.task = task;
			});
		});
	};
}]);

taskControllers.controller('TaskAddController', ['$scope', '$location', '$routeParams', 'Tasks', function($scope, $location, $routeParams, Tasks) {
	$scope.saveTask = function() {
		var taskSave = Tasks.save($scope.task);
		return taskSave.$promise.then(
				function(value) {
					$location.path('/');
				}, function(error) {
					alert(error.data.message);
				});
	};
}]);
