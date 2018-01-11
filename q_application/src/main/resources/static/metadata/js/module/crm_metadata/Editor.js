var Editor = function() {

	this.editor = new UI.Div();
	this.editor.setClass("editor");
	this.data = {
		database : {
			id : "ecs",
			tables : []
		},
		functions : [],
		segmentation : {},
		queryitems : [],
		features : [],
		plat_relation : {}
	};
	document.body.append(this.editor.dom)
	initToolbar(this);
	initWorkSpace(this);
	initAttributePanel(this);

}

Editor.prototype.setToolbar = function(toolbar) {
	this.toolbar = toolbar;
	this.add(toolbar)
}
Editor.prototype.add = function(toolbar) {
	this.editor.add(toolbar);
}
function initToolbar(editor) {
	editor.setToolbar(new Toolbar());
	var menu = new ToolBarMenu("文件");
	menu.addItem("新建", function() {
		alert(1);
	})
	menu.addItem("打开", function() {
		layer
		.open({
			type : 1 // 此处以iframe举例
			,
			title : '打开',
			area : [ '80%', '80%' ],
			shade : 0,
			maxmin : true,
			content : "<textarea id='area1' style='width:99%;height:99%'></textarea>",
			btn:['打开'],
			yes:function(index){
					editor.data=$.parseJSON($("#area1").val());
				layer.close(index);
			},
			zIndex : layer.zIndex // 重点1
			,
			success : function(layero) {
				layer.setTop(layero); // 重点2
			}
		});
	})
	menu
			.addItem(
					"保存",
					function() {
						layer
								.open({
									type : 1 // 此处以iframe举例
									,
									title : '保存',
									area : [ '80%', '80%' ],
									shade : 0,
									maxmin : true,
									content : "<textarea id='area' style='width:99%;height:99%'></textarea>"

									,
									zIndex : layer.zIndex // 重点1
									,
									success : function(layero) {
										layer.setTop(layero); // 重点2
										$.ajax({
											url : "metadata/parseToXML",
											type : "post",
											data : {
												json : JSON
														.stringify(editor.data)
											},
											dataType : "text",
											success : function(data) {
												$("#area").text(data);
											}
										})
									}
								});
					})
	menu.addItem("另存为", function() {
		alert(1);
	})
	editor.toolbar.add(menu);
}
function initWorkSpace(editor) {
	editor.workspace = new Workspace();
	editor.add(editor.workspace);
}
function initAttributePanel(editor) {
	editor.attributePanel = new AttributePanel(editor);
	editor.add(editor.attributePanel);
}