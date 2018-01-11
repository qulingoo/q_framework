function getModule(url) {
	var html = $.ajax({
		url : url,
		async : false,
		dataType : "html",
		global : false,
		scriptCharset : 'utf-8'
	}).responseText;
	return html;
}