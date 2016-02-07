var taskServices = angular.module('taskServices', ['ngResource']);

taskServices.factory('Tasks', [ '$resource', function($resource) {
	return $resource('/tasks/:id', { id : '@id' },
			{ 'update' : {
				method : 'PUT'
			}}
	);
}]);
