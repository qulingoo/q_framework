layui.use([ 'element',  'layer','upload' ], function() {
		window.laypage = layui.laypage
		window.layer = layui.layer;
		window.upload=layui.upload;
		
});

var params = getUrlParam(decodeURI(window.location.href));
class Dao{
	insert(data,callback){
		$.Ajax("/field/insert",data,callback);
	}
	update(data,callback){
		$.Ajax("/field/update",data,callback);
	}
	deleteField(id,callback){
		$.Ajax("/field/delete",{id:id},callback);
	}
	findById(id){
		return $.Ajax("/field/findById",{id:id});
	}
	findByTableId(id){
		return $.Ajax("/field/findByTableId",{table_id:id});
	}
}
class Controller{
	
}
var dao=new Dao();
var fieldModule = new Vue({
	el : "#field-module",
	data : {
		dao:dao,
		status:"list",
		params : params,
		fields:dao.findByTableId(this.params.id),
		emnus:[{key:4,value:"INTEGER"},
			{key:3,value:"DECIMAL"},
			{key:1,value:"CHAR"},
			{key:12,value:"VARCHAR"},
			{key:93,value:"TIMESTAMP"},
			{key:2005,value:"CLOB"},
			{key:94,value:"DATETIME"}
			]
	},
	methods : {
		initAddBody:function(){
			this.addForm=new FormUtil();
			this.addForm.input("comment","字段别名");
			this.addForm.input("field_name","字段名称");
			this.addForm.select("types","类型",this.emnus,"key","value");
			this.addForm.input("accuracy","长度");
			return this.addForm.get();
		},
		initUpdateBody:function(){
			this.updateForm=new FormUtil();
			this.updateForm.input("comment","字段别名");
			this.updateForm.input("field_name","字段名称");
			this.updateForm.select("types","类型",this.emnus,"key","value");
			this.updateForm.input("accuracy","长度");
			return this.updateForm.get();
		},
		addField:function(){
			var data=this.addForm.getData();
			$.print(data);
			data.table_id=this.params.id;
			if(this.fields){
				data.dindex=this.fields.length;
			}else{
				data.dindex=0;
			}
			this.dao.insert(data,function(){
				fieldModule.fields.push(data);
				fieldModule.status="list";
				 
			})
		},
		updateField:function(field){
			this.field=field;
			this.status='update';
			this.$nextTick(function(){
				 fieldModule.updateForm.setData(field)
		     })
		},
		updateFields:function(){
			var data=fieldModule.updateForm.getData();
			data.id=this.field.id;
			this.dao.update(data,function(){
				fieldModule.$set(fieldModule.field,"comment",data.comment);
				fieldModule.$set(fieldModule.field,"field_name",data.field_name);
				fieldModule.$set(fieldModule.field,"types",data.types);
				fieldModule.$set(fieldModule.field,"accuracy",data.accuracy);
				fieldModule.status='list';
			})
		},
		deleteField:function(id){
			layer.msg("确认删除?",{btn:['确认','取消'],btn1:function(){
				 dao.deleteField(id,function(){
					fieldModule.fields=dao.findByTableId(fieldModule.params.id);
				})
				layer.closeAll();
			},btn2:function(){
				layer.closeAll();
			}})
		},
		switchFieldType:function(typeId){
			 for (var i = 0; i < this.emnus.length; i++) {
				if(this.emnus[i].key==typeId)
					return this.emnus[i].value;
			}
		},addReference:function(fieldId){
			window.parent.layer.open({
		        type: 2 ,
		         title: '约束列表'
		        ,area: ['80%', '80%']
		        ,maxmin: true
		        ,content: 'field-reference.html?id='+fieldId
		        ,zIndex: window.parent.layer.zIndex 
		 });
		}
	}
})
fieldModule.status="list";