function getHtml(url) {
	var html = $.ajax({
		url : url,
		async : false,
		dataType : "html",
		global : false,
		scriptCharset : 'utf-8'
	}).responseText;
	return html;
}
Array.prototype.removeByValue = function(val) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == val) {
			this.splice(i, 1);
			break;
		}
	}
}
layui.use('element', function() {
	var $ = layui.jquery, element = layui.element;
});
Vue.component('category', {
	template : getHtml("./features/category.vue"),
	props : [ "features", 'seg', 'queryitems' ],
	data : function() {
		return {
			status : "category-list",
			selector:"1"
		}
	},
	methods : {
		getAllAttribute : function() {
			var array = [];
			var subjects = this.seg.subjects;
			for (var i = 0; i < subjects.length; i++) {
				var subject = subjects[i];
				var colls = subject.attributeCollections;
				for (var j = 0; j < colls.length; j++) {
					var attrs = colls[j].normalAttributes;
					for (var k = 0; k < attrs.length; k++) {
						array.push(  attrs[k] );
					}
				}
			 
			}
			return array
		},
		addGroupSelect : function() {
			var attrs=this.getAllAttribute();
			var select=$("<select>").addClass("layui-input").width(100).css("display","inline-block");
			$.each(attrs,function(i,attr){
				select.append("<option value='"+attr.id+"'>"+attr.display+"</option>")
			})
			var input=$("<input>").addClass("layui-input").width(100).css("display","inline-block");
			select.dblclick(()=>{
				$(select).remove();
				$(input).remove();
			})
			$("#group-select").append(select).append(input).append("<br/>");
		},
		init : function() {
			if (!this.features[0]) {
				this.features[0] = {};
				this.features[0].id="querynode@base";
				this.features[0].display="属性查询";
				this.features[0].type="querynode";
				this.features[0].subject = {};
				this.features[0].subject.category = [];
			}
			this.$nextTick(()=>{
				this.features[0].subject.id=$("#subject").val();
				this.features[0].subject.display=$("#subject").val();
			})
		},
		addQueryItem : function() {
			var that = this;
			$(".unadd input:checked").each(function(i, el) {
				$(".added").append($(this).parent().remove());

			})
		},
		changeSelector : function() {
			this.selector = $("#category-type").val();
		},
		queryItemFilter : function() {
			var categorys = this.features[0].subject.category;
			var array = [];
			for (var i = 0; i < this.queryitems.length; i++) {
				var queryitem = this.queryitems[i];
				var flag = true;
				for (var j = 0; j < categorys.length; j++) {
					for (var k = 0; k < categorys[j].queryitems.length; k++) {
						var $queryitem = categorys[j].queryitems[k];
						if ($queryitem.id == queryitem.id) {
							flag = false;
							break;
						}

					}
				}
				if (flag) {
					array.push(queryitem);
				}
			}
			return array;
		},
		delQueryItem : function() {
			var that = this;
			$(".added input:checked").each(function(i,el){
					$(".unadd").append($(el).parent().remove());
			})
		},
		saveCategory:function(){
			var category={};
			category.display=$("#category-name").val();
			category.id=this.seg.id+"-category-basicinfo";
			category.type=$("#category-type").val();
			category.typename=$("#category-type option:checked").text();
			category.order="1";
			category.queryitems=[];
			$(".added input").each((i,el)=>{
				var queryitem=$.parseJSON($(el).val());
				category.queryitems.push({id:queryitem.id,display:queryitem.display})
			})
			if(category.type==2){
				category.groupbys=[];
				$("#group-select>select").each((i,el)=>{
					var dic_id=$($("#group-select>input").get(i)).val();
					category.groupbys.push({
						attribute:$(el).val(),
						attributeType:"nomal",
						dic:dic_id,
						order:"1"
					})
				})
			}
			this.features[0].subject.category.push(category);
			this.status = "category-list";
		},
		delCategory:function(category){
			if(confirm("确认删除？")){
				this.features[0].subject.category.removeByValue(category);
			}
		},returnToCategory:function(category){
			this.currCategory=category;
			this.status='category-queryitem-list'
		},returnToEditCategory:function(category){
			this.currCategory=category;
			this.status="category-edit"
			if(category.type=="2"){
				this.selector="2"
				this.$nextTick(()=>{
					$("#category-name").val(category.display);
					$("#category-type").val(category.type);
					var that=this;
						$.each(category.groupbys,(i,groupby)=>{
							that.addGroupSelect();
							$($("#group-select>select").get(i)).val(groupby.attribute);
							$($("#group-select>input").get(i)).val(groupby.dic);
						})
				})
			}
		},editCategory:function(){
			this.$set(this.currCategory,"display",$("#category-name").val());
			this.currCategory.type=$("#category-type").val();
			this.currCategory.typename=$("#category-type option:checked").text();
			this.currCategory.order="1";
			this.currCategory.queryitems=[];
			$(".added input").each((i,el)=>{
				var queryitem=$.parseJSON($(el).val());
				this.currCategory.queryitems.push({id:queryitem.id,display:queryitem.display})
			})
			if(this.currCategory.type==2){
				this.currCategory.groupbys=[];
				$("#group-select>select").each((i,el)=>{
					var dic_id=$($("#group-select>input").get(i)).val();
					this.currCategory.groupbys.push({
						attribute:$(el).val(),
						attributeType:"nomal",
						dic:dic_id,
						order:"1"
					})
				})
			}
			this.status = "category-list";
		}
	}
})
Vue.component('order-query', {
	template : getHtml("./features/order-query.vue"),
	props : [ "features", 'seg', 'queryitems' ],
	data:function(){
		return {status:"init",
			subjects:{
				display:"订单查询",
				id:"",
				category:{
					display:"订单信息",
					type:"3",
					typename:"行为自定义",
					order:"1",
					behavior:{
						id:"behavior@order",
						display:"订单查询",
						behaviorSubject:"",
						order:"1",
						item:[]
					}
			}
			}
		}
	},
	methods: {
		init:function(){
			if (!this.features[1]) {
				this.features[1] = {};
				this.features[1].id="querynode@order";
				this.features[1].display="订单查询";
				this.features[1].type="querynode";
				this.features[1].subject = this.subjects;
				this.features[1].subject.category.id = this.seg.id+"-category-orderinfo";
			}
			this.status="list";
		},
		getOtherQueryItem:function(){
				var items = this.features[1].subject.category.behavior.item;
				var array = [];
				for (var i = 0; i < this.queryitems.length; i++) {
					var queryitem = this.queryitems[i];
					
					var flag = true;
					 for (var j = 0; j < items.length; j++) {
						 var item = items[j];
						if(queryitem.id==item.id){
							flag=false;
							break;
						}
						
					}
					if (flag) {
						array.push(queryitem);
					}
				}
				return array;
		},
		save:function(){
			var items = this.features[1].subject.category.behavior.item;
			var item={};
			var queryitem=$.parseJSON($("#queryitem").val());
			item.id=queryitem.id;
			item.display=queryitem.display;
			item.x=$("#x").val();
			item.y=$("#y").val();
			items.push(item);
			this.status="list"
		}
		,del:function(item){
			if(confirm("确认删除？")){
				this.features[1].subject.category.behavior.item.removeByValue(item);
			}
		}
	}
}
)
Vue.component('customer-data', {
	template : getHtml("./features/customer-data.vue"),
	props : [ "features", 'seg', 'queryitems' ],
	data:function(){
		return {status:"init" 
			 
		}
	},
	methods: {
		init:function(){
			if (!this.features[2]) {
				this.features[2] = {};
				this.features[2].id="querynode@customerinfo";
				this.features[2].display="客户基本信息查询";
				this.features[2].type="crudlist";
				this.features[2].subject = this.subjects;
				 
			}
			this.status="list";
		},
		getAllAttribute : function() {
			var array = [];
			var subjects = this.seg.subjects;
			for (var i = 0; i < subjects.length; i++) {
				var subject = subjects[i];
				var colls = subject.attributeCollections;
				for (var j = 0; j < colls.length; j++) {
					var attrs = colls[j].normalAttributes;
					for (var k = 0; k < attrs.length; k++) {
						array.push(  attrs[k] );
					}
				}
			 
			}
			return array
		}
	}
}
)
new Vue({
	el : "#features",
	data : {
		features : window.parent.editor.data.features,
		seg : window.parent.editor.data.segmentation,
		queryitems : window.parent.editor.data.queryitems
	},
	methods : {
		
	}
})