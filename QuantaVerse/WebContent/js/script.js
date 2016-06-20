var QVTask = angular.module("QVTask", []);

QVTask.controller("DataCntrl", function($scope, $http) {
	$scope.MatchedData = null;
	$scope.searchQuery = function(query) {
		console.log(query);
		$http.get("Controller?query=" + query).success(function(data) {
			$scope.MatchedData = data;
		})
	}
})