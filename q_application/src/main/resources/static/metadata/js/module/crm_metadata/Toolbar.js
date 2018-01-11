var Toolbar=function(){
	UI.Element.call( this );
	this.dom= document.createElement("div");
	this.setClass("toolbar");
}
Toolbar.prototype= Object.create( UI.Element.prototype );
Toolbar.prototype.addToolBarMenu=function(barMenu){
	 this.add(barMenu);
}
var ToolBarMenu=function(title){
	UI.Element.call( this );
	this.dom=document.createElement("div");
	this.setClass("toolbar-menu");
	this.title=new UI.Div();
	this.title.setClass("title");
	var that=this;
	this.title.setTextContent(title);
	this.add(this.title);
	this.toolbarMenuItems=new UI.Div();
	this.toolbarMenuItems.setClass("toolbar-menu-items");
	this.add(this.toolbarMenuItems);
}
ToolBarMenu.prototype= Object.create( UI.Element.prototype );
ToolBarMenu.prototype.addItem=function(name,action){
	var btn=new UI.Button(name);
	btn.onClick(action);
	btn.setClass("toolbar-menu-item");
	this.toolbarMenuItems.add(btn);
}
 