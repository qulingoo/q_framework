function getAllFunction() {
	var tmp = null;
	$.ajax({
		url : "../../../function/getAll",

		async : false,
		success : function(data) {
			tmp = data;
		}
	})
	return tmp;
}

var functions = new Vue({
	el : "#functions",
	data : {
		functions : getAllFunction(),
		reset : {
			set : true
		}
	},
	methods : {
		openAddView : function() {
			layer.open({
				type : 2,
				content : "html/add_functions.html",
				area : [ '50%', '80%' ]
			})
		},
		openUpdateView : function(id) {
			layer.open({
				type : 2,
				content : "html/update_functions.html?id="+id,
				area : [ '50%', '80%' ]
			})
		},
		_delete:function(db_id){
			$.ajax({
				url:"/function/delete?id="+db_id,
				success:function(){
					reset();
				}
			})
		}
	}
});
function reset() {
	functions.functions = getAllFunction();
	functions.$set(functions.reset, "set", true);
}