$(function() {
	loadTree();
})

function loadTree(){
	alert(456);
	$.ajax({
		type : 'get',
		url : '../login/getMemberInfo.htm?r='+Math.random(),
		cache : false,
		dataType : 'json',
		success : function(data) {
			var _treeArray = eval(data);
			if(_treeArray != null && _treeArray.length == 1){
				var _tree = _treeArray[0];
				var _children = _tree.children;
				alert(_children);
			}
		},
		error: function(data){
			alert('error');
		}
	});
	alert(789);
}