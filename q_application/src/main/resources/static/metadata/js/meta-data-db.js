function getTables() {
	var temp = {};
	$.ajax({
		url : "/meta/db/getTables",
		type : "post",
		async : false,
		success : function(data) {
			temp = data;
		}
	});
	return temp;
}
var app = new Vue({
	el : '#app',
	data : {
		status : "field-list",
		currentTableIndex : -1,
		tables : getTables(),
		fields : []
	},
	methods : {
		chooseTable : function(index, event) {
			if (this.currentTable != null) {

				this.currentTable.removeClass("choose-table");
			}
			this.currentTableIndex = index;
			this.currentTable = $(event.target).addClass("choose-table");
			
			var data = this.tables[this.currentTableIndex];
			this.currentTableData = data;
			this.loadFields();
		},
		addTable : function() {
			var data = {};
			data.code = $("#code").val();
			data.comment = $("#comment").val();
			data.id = "db@" + data.code;
			$.ajax({
				url : "/meta/db/addTable",
				type : "post",
				data : data,
				dataType : "json",
				success : function(message) {
					if (message.type == "success") {
						app.tables.push(data);
						app.status = "field-list";

					} else {
						alert(message.message)
					}
				}
			});
		},
		backfillTable : function() {
			
			
			this.status = "updateTable";
		},
		deleteTable : function() {
			if (confirm("确认删除?")) {
				var data = this.tables[this.currentTableIndex];
				$.ajax({
					url : "/meta/db/deleteTable",
					type : "post",
					data : {
						tableId : data.id
					},
					dataType : "json",
					success : function(message) {
						if (message.type == "success") {
							app.tables = getTables()
							app.status = "field-list";
						} else {
							alert(message.message)
						}
					}
				});
			}
		},
		updateTable : function() {
			var data = this.currentTableData
			data.id = this.tables[this.currentTableIndex].id
			$.ajax({
				url : "/meta/db/updateTable",
				type : "post",
				data : data,
				dataType : "json",
				success : function(message) {
					if (message.type == "success") {
						app.tables = getTables()
						app.status = "field-list";
						app.currentTableData.id = "db@"
								+ app.currentTableData.code;
						app.currentTableData = null;
					} else {
						alert(message.message)
					}
				}
			});
		},
		loadFields : function() {
			this.status = "field-list";
			if (this.currentTableIndex != -1) {
				$.ajax({
					url : "/meta/db/getFields",
					type : "post",
					data : {
						tableId : this.tables[this.currentTableIndex].id
					},
					dataType : "json",
					success : function(fields) {
						app.fields = fields;
					}
				});

			} else {
				this.fields = [];
			}

		},
		addField : function() {
			var data={};
			data.tableId=this.currentTableData.id;
			data.code=$("#code").val();
			data.comment=$("#comment").val();
			data.type=$("#type").val();
			data.length=$("#length").val();
			data.decimals=$("#decimals").val();
			data.allownull=$("input[name=allownull]:checked").val();
			data.iskey=$("input[name=iskey]:checked").val();
			data.id="db@"+this.currentTableData.code+"."+data.code;
			$.ajax({
				url : "/meta/db/addField",
				type : "post",
				data : data,
				dataType : "json",
				success : function(message) {
					if (message.type == "success") {
						app.fields.push(data);
						app.status = "field-list";
					} else {
						alert(message.message)
					}
				}
			});
		},
		chooseField(index,event){
			if (this.currentField != null) {
				this.currentField.removeClass("choose-field");
			}
			this.currentFieldIndex = index;
			this.currentField = $(event.target).parent("tr").addClass("choose-field");
			var data = this.fields[this.currentFieldIndex];
			this.currentFieldData = data;
		},updateField:function(){
			console.info(this.currentFieldData);
			this.currentFieldData.tableId=this.currentTableData.id;
			$.ajax({
				url : "/meta/db/updateField",
				type : "post",
				data : this.currentFieldData,
				dataType : "json",
				success : function(message) {
					if (message.type == "success") {
						app.fields=app.loadFields(app.currentTableData.id);
						app.status = "field-list";
					} else {
						alert(message.message)
					}
				}
			});
		},deleteField:function(){
			if (confirm("确认删除?")) {
				$.ajax({
					url : "/meta/db/deleteField",
					type : "post",
					data : {
						tableId : app.currentTableData.id,
						fieldId : app.currentFieldData.id,
					},
					dataType : "json",
					success : function(message) {
						if (message.type == "success") {
							app.fields=app.loadFields(app.currentTableData.id);
							app.status = "field-list";
						} else {
							alert(message.message)
						}
					}
				});
			}
		},getXML:function(){
			$.ajax({
				url : "/meta/db/toString",
				type : "post",
				dataType : "text",
				success : function(xml) {
					 $("#xml-area").val(xml);
				}
			});
		}
	}
});