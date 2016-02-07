var taskDirectives = angular.module('taskDirectives', []);

taskDirectives.directive('task', [ function() {
	return {
		templateUrl : 'partials/task.html'
	};
}]);
