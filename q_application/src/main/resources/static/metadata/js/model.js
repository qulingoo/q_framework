var emnus=[{key:4,value:"int"},
			{key:3,value:"decimal"},
			{key:1,value:"char"},
			{key:12,value:"varchar"},
			{key:93,value:"timestamp"},
			{key:2005,value:"clob"},
			{key:94,value:"datetime"}
			];
var switchFieldType=function(typeId){
	 for (var i = 0; i < emnus.length; i++) {
		if(emnus[i].key==typeId)
			return emnus[i].value;
	}
}
class Dao{
	count(){
		var temp=0;
		$.ajax({
			async:false,
			url:"/table/count",
			success:function(data){
				temp=data;
			}
		})
		return temp;
	}
	findByPage(page){
		var temp=[];
		$.ajax({
			async:false,
			url:"/table/findByPage",
			data:{page:page},
			success:function(data){
				temp=data;
			}
		})
		return temp;
	}
	save(data,callback){
		$.ajax({
			async:false,
			url:"/table/addTable",
			data:data,
			success:function(datas){
				callback();
			}
		})
	}
	update(data,callback){
		$.ajax({
			url:"/table/updateTable",
			data:data,
			success:function(datas){
				callback();
			}
		})
	}
	deleteTable(id,callback){
		$.ajax({
			url:"/table/deleteTable",
			data:{id:id},
			success:function(datas){
				callback();
			}
		})
	}
	findFieldByTableId(id){
		return $.Ajax("/field/findByTableId",{table_id:id});
	}
}
class Controller{
	constructor(model)
	{
		this.dao=new Dao();
		this.model=model;
	}
	jumpPage(page){
		window.currentTablePage=page;
		model.tables=this.dao.findByPage(page);
	}
	addTable(data){
		this.dao.save(data,function(){
			model.tables.push(data);
			layer.closeAll();
		})
	}
	updateTable(data){
		var that=this;
		this.dao.update(data,function(){
			that.model.$set(that.model.currtable,"comment",data.comment);
			that.model.$set(that.model.currtable,"table_name",data.table_name);
			that.model.$set(that.model.currtable,"is_exist",data.is_exist);
			layer.closeAll();
		})
	}
}


var model=new Vue({
	el:"#model",
	data:{
		tables:new Dao().findByPage(0)
	},
	methods:{
		openAddView:function(){
			var formUtil=new FormUtil()
			formUtil.input("comment","别名");
			formUtil.input("table_name","表名");
			formUtil.select("is_exist","是否存在",[{key:0,value:"否"},{key:0,value:"是"}],"key","value");
			 layer.open({
			        type: 1 ,
			         title: '新建表'
			        ,area: ['390px', '260px']
			        ,maxmin: true
			        ,content: formUtil.get()
			        ,btn: ['保存', '取消']  
			        ,yes: function(){
			        	controller.addTable(formUtil.getData());
			        }
			        ,btn2: function(index){
			        	layer.close(index)
			        }
			        ,zIndex: layer.zIndex
			 });
		},
		openEditTable:function(table){
			this.currtable=table;
			var formUtil=new FormUtil()
			formUtil.input("comment","别名");
			formUtil.input("table_name","表名");
			formUtil.select("is_exist","是否存在",[{key:0,value:"否"},{key:1,value:"是"}],"key","value");
			 layer.open({
			        type: 1 ,
			         title: '新建表'
			        ,area: ['390px', '260px']
			        ,maxmin: true
			        ,content: formUtil.get()
			        ,btn: ['保存', '取消']  
			        ,yes: function(){
			        	var data=formUtil.getData();
			        	data.id=table.id;
			        	controller.updateTable(data);
			        }
			        ,btn2: function(index){
			        	layer.close(index)
			        }
			        ,zIndex: layer.zIndex,
			        success:function(){
			        	formUtil.setData(table);
			        }
			 });
		},
		deleteTable:function(id){
			layer.msg("确认删除?",{btn:['确认','取消'],btn1:function(){
				var dao=new Dao();
				dao.deleteTable(id,function(){
					model.tables=dao.findByPage(currentTablePage);
				})
				layer.closeAll();
			},btn2:function(){
				layer.closeAll();
			}})
			
		},
		showFieldView:function(table){
			 layer.open({
			        type: 2 ,
			         title: '字段列表'
			        ,area: ['80%', '80%']
			        ,maxmin: true
			        ,content: 'field-view.html?id='+table.id+"&table_name="+table.table_name
			        ,zIndex: layer.zIndex
			 });
		},
		uploadConfig:function(){
			
			var formdata = new FormData();  
			if($("#file").get(0).files[0])
			{
				formdata.append('file',$("#file").get(0).files[0]);  
				$.ajax({
					url : "/table/upload",
					data : formdata,
					contentType : false,
					processData : false,
					type : "post",
					 success:function(datas){
						 model.tables=new Dao().findByPage(currentTablePage) ;
						 $("#file").attr("type","text");
						 $("#file").attr("type","file");
					 },error:function(datas){
						 layer.msg("数据导入异常")
					 }
				})

			}
			 
		} ,
		
		showMongo:function(table){
			var json={};
			json._id=table.table_name;
			json.registerTableName=table.table_name;
			json.registerTableComment=table.comment;
			json.columns=[];
			
			var fields=new Dao().findFieldByTableId(table.id);
			$.each(fields,function(i,field){
				var fieldJson={};
				fieldJson.registerColumnName=field.field_name;
				fieldJson.registerColumnComment=field.comment;
				fieldJson.registerColumnDataType=switchFieldType(field.types);
				if(field.accuracy==null||field.accuracy==""){
					fieldJson.registerColumnType=fieldJson.registerColumnDataType;
				}else{
					fieldJson.registerColumnType=fieldJson.registerColumnDataType+"("+field.accuracy+")";
				}
				fieldJson.isDisabled=false;
				json.columns.push(fieldJson)
			})
			 
			var mongo="";
			mongo+="db.metasRegisterTableDoc.save("+JSON.stringify(json,null,"\t")+")"+"\n";
			layer.open({
			        type: 1 ,
			         title: 'mongo'
			        ,area: ['80%', '80%']
			        ,maxmin: true
			        ,content: "<textarea style='height:99%;width:99%;'>"+mongo+"</textarea>"
			        ,zIndex: layer.zIndex
			 });
		 
		},
		xml:function(table){
			$.Ajax("/table/xml",{id:table.id},function(data){
				layer.open({
			        type: 1 ,
			         title: 'mongo'
			        ,area: ['80%', '80%']
			        ,maxmin: true
			        ,content: "<textarea style='height:99%;width:99%;'>"+data+"</textarea>"
			        ,zIndex: layer.zIndex
			 });
			})
		}
	}
}) 
var controller=new Controller(model);
