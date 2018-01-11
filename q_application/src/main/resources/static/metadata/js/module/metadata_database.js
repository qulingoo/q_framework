function getDbs() {
		var tmp = null;
		$.ajax({
			url : "../../../database/getDbs",
			async : false,
			success : function(data) {
				tmp = data;
			}
		})
		return tmp;
	}
var database=new Vue({
	el:"#database",
	data:{
		databases:getDbs(),
		reset:{
			set:true
		}
	},
	methods:{
		openAddView:function(){
			layer.open({
				type:2,
				content:"html/metadata_database/add_database.html",
				area:['80%','80%']
			})
		},
		getTableName:function(id){
			var tmp = null;
			$.ajax({
				url : "../../../table/getTableById?id="+id,
				async : false,
				success : function(data) {
					tmp = data;
				}
			})
			return tmp!=null?tmp.comment:"";
		},
		deleteDb:function(id){
			$.ajax({
				url : "../../../database/delete?id="+id,
				success : function() {
					resetDb();
				}
			})
		}
	}
})

function resetDb(){
	database.databases=getDbs();
	database.$set("reset","ret","true");
}