var DrawingBoard = function(id, width, height) {
	this.dx = 100;
	this.dy = height / 2;
	this.init(id, width, height);
	this.rootOpen = false;
}
DrawingBoard.prototype.init = function(id, width, height) {
	var that=this;
	var r = Raphael(id, width, height);
	this.board = r;
	var dx = this.dx;
	var dy = this.dy;
	r.text(dx, dy, "元数据").attr({
		"font-size" : "24px"
	});
	this.root = r.rect(dx - 50, dy - 20, 100, 40);
	this.root.attr({
		"fill" : "#ccc",
		"fill-opacity" : 0.1
	});
	this.root.click(function() {
		console.info(JSON.stringify(editor.data,null,"\t"))
	})
	that.initItem();
}

DrawingBoard.prototype.initItem = function() {
	var r = this.board;
	var dx = this.dx;
	var dy = this.dy;
	  r.text(dx + 100 + 50, dy - dy / 5 * 4, "物理表").attr({
		"font-size" : "20px"
	});
	var database = r.rect(dx + 100, dy - dy / 5 * 4 - 20, 100, 40);
	r.text(dx + 100 + 50, dy - dy / 5 * 2, "函数").attr({
		"font-size" : "20px"
	});
	var functions = r.rect(dx + 100, dy - dy / 5 * 2 - 20, 100, 40);
	r.text(dx + 100 + 50, dy , "平台").attr({
		"font-size" : "20px"
	});
	var seg = r.rect(dx + 100, dy - 20, 100, 40);
	r.text(dx + 100 + 50, dy+ dy / 5 * 2  , "查询条件").attr({
		"font-size" : "20px"
	});
	var queryitems = r.rect(dx + 100, dy + dy / 5 * 2 - 20, 100, 40);
	r.text(dx + 100 + 50, dy+ dy / 5 * 4  , "模块").attr({
		"font-size" : "20px"
	});
	var features = r.rect(dx + 100, dy + dy / 5 * 4 - 20, 100, 40);
	r.connection(this.root, database, "#000");
	r.connection(this.root, functions, "#000");
	r.connection(this.root, queryitems, "#000");
	r.connection(this.root, seg, "#000");
	r.connection(this.root, features, "#000");
	database.attr({
		"fill" : "#ccc",
		"fill-opacity" : 0.1
	}).click(function(){
		$("#attr-frame").attr("src","meta/database.html")
	})
	functions.attr({
		"fill" : "#ccc",
		"fill-opacity" : 0.1
	}).click(function(){
		$("#attr-frame").attr("src","meta/functions.html")
	})
	seg.attr({
		"fill" : "#ccc",
		"fill-opacity" : 0.1
	}).click(function(){
		$("#attr-frame").attr("src","meta/segmentation.html")
	})
	queryitems.attr({
		"fill" : "#ccc",
		"fill-opacity" : 0.1
	}).click(function(){
		$("#attr-frame").attr("src","meta/queryitems.html")
	})

	features.attr({
		"fill" : "#ccc",
		"fill-opacity" : 0.1
	}).click(function(){
		$("#attr-frame").attr("src","meta/features.html")
	})
}
