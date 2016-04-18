describe('TaskApp', function() {

	var TasksSpy;

	beforeEach(function() {
		TasksSpy = {
				'query': function() {
					return { status: 200 };
				}
		};
	});

	describe('TaskListController', function() {
		beforeEach(module('taskControllers'));
    beforeEach(module('taskServices'));

		var $_controller_, scope;

		beforeEach(inject(function($controller) {
			scope = {};
			$_controller_ = $controller;
		}));

		it('should load task data in scope', function() {
			spyOn(TasksSpy, 'query');
			$_controller_('TaskListController', {
				$scope : scope,
				Tasks : TasksSpy
			});
			expect(TasksSpy.query).toHaveBeenCalled();
		});
	});

	describe('TaskViewController', function() {

	});

	describe('TaskAddController', function() {

	});
});
