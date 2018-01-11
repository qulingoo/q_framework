var Workspace=function(){
	UI.Element.call( this );
	this.dom=document.createElement("div");
	this.setClass("workspace");
	this.setId("workspace");
}
Workspace.prototype= Object.create( UI.Element.prototype );

