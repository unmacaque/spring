var taskFilters = angular.module('taskFilters', []);

taskFilters.filter('taskListFilter', function() {
	return function(tasks, state) {
		if (!state) {
			return tasks;
		}
		var filtered = [];
		for (var i = 0; i < tasks.length; i++) {
			var task = tasks[i];
			if (state == 'notdone' && !task.done) {
				filtered.push(task);
			}
		}
		return filtered;
	};
});

taskFilters.filter('taskDoneFilter', function() {
	return function(done) {
		return done ? '\u2713 Done' : '\u2718 Not Done';
	};
});
