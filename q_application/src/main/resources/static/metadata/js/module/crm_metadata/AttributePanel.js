var AttributePanel=function(editor){
	UI.Element.call( this );
	this.editor=editor;
	this.dom=document.createElement("div");
	this.setClass("attribute-panel");
	this.setId("AttributePanel");
	this.initDataBasePanel();
}
AttributePanel.prototype= Object.create( UI.Element.prototype );

AttributePanel.prototype.initDataBasePanel=function(){
	this.clear();
	var content=$("<iframe id='attr-frame'>").addClass("attr-frame");
	$(this.dom).append(content);
	 
	 
	
}

function createAttriItem(label,id,value){
	var item1=new UI.Div().setClass("attr-item");
	var span=new UI.Span().setTextContent(label);
	var input=new UI.Input(value).setId(id);
	item1.add(span,input);
	return item1;
}