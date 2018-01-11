function getAll() {
	var tmp = null;
	$.ajax({
		url : "../../../seg/getAll",
		async : false,
		success : function(data) {
			tmp = data;
		}
	})
	return tmp;
}


var seg=new Vue({
	el:"#seg",
	data:{
		segs:getAll(),
		reset:{
			set:false
		}
	},
	methods:{
		openAddView : function() {
			layer.open({
				type : 2,
				content : "html/add_seg.html",
				area : [ '50%', '80%' ]
			})
		},
		openUpdateView : function(id) {
			layer.open({
				type : 2,
				content : "html/update_seg.html?id="+id,
				area : [ '50%', '80%' ]
			})
		},_delete:function(id){
			$.ajax({
				url:"/seg/delete?id="+id,
				success:function(){
					reset() 
				}
			})
		},subject:function(id){
			window.seg_id=id;
			layer.open({
				type : 2,
				title:"主題列表",
				content : "subject.html",
				area : [ '100%', '100%' ]
			})
		}
	}
})
function reset() {
	seg.segs = getAll();
	functions.$set(seg.reset, "set", true);
}