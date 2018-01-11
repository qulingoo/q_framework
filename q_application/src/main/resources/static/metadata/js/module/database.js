class Dao{
	insert(data,callback){
		$.Ajax("/ds/insert",data,callback);
	}
	update(data,callback){
		$.Ajax("/ds/update",data,callback);
	}
	_delete(id,callback){
		$.Ajax("/ds/delete",{id:id},callback);
	}
	findById(id){
		return $.Ajax("/ds/findById",{id:id});
	}
	findByType(type){
		return $.Ajax("/ds/findByType",{type:type});
	}
}
var dao=new Dao();
var database=new Vue({
	el:"#database",
	data:{
		databases:dao.findByType(1)
	},
	methods:{
		
	}
})


