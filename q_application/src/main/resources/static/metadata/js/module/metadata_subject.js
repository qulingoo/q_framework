var seg_id=window.parent.seg_id;

function getAllSubject() {
	var tmp = null;
	$.ajax({
		url : "../../../subject/findBySegId?id="+seg_id,
		async : false,
		success : function(data) {
			tmp = data;
		}
	})
	return tmp;
}
var subject = new Vue({
	el : "#subject",
	data : {
		subjects : getAllSubject(),
		reset : {
			set : true
		}
	},
	methods : {
		openAddView : function() {
			 layer.open({
				type : 2,
				content : "html/add_subject.html",
				area : [ '50%', '80%' ]
			})
		},
		returnToSubject : function(id) {
			 layer.open({
				type : 2,
				content : "html/subject_info.html?id="+id,
				area : [ '50%', '80%' ]
			})
		},returnToEditSubject:function(subject){
			window.currSubject=subject;
			 layer.open({
					type : 2,
					content : "html/update_subject.html",
					area : [ '50%', '80%' ]
				})
			
		},
		nomalAttr:function(subject){
			window.currSubject=subject;
			 layer.open({
					type : 2,
					title: false, //不显示标题
					content : "html/subject_normal.html",
					area : [ '90%', '90%' ]
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
	subject.subjects = getAllSubject();
	subject.$set(subject.reset, "set", true);
}