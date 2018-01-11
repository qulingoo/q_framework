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
Vue.component('quota',{
					template : getHtml("./quota-page.vue"),
					props : [ "quotaAttrs", 'currSubject', 'seg', 'funs' ],
					data : function() {
						return {
							attr_status : "quota-list"
						}
					},
					methods : {
						initParams : function() {
							this
									.$nextTick(function() {
										var paramJson = $.parseJSON($
												.parseJSON($("#quota-fun")
														.val()).paramJson);
										for (var i = 0; i < paramJson.length; i++) {
											var param = paramJson[i];
											var div = $("<div>");
											var span = $("<span class='key'>")
													.text(param + "");
											var select = $("<select   class='value layui-input'>").width(300).css("display","inline");
											
											
											for (var j = 0; j < this.seg.subjects.length; j++) {
												var subject = this.seg.subjects[j];
												for (var k = 0; k < subject.attributeCollections.length; k++) {
													var coll = subject.attributeCollections[k];
													for (var l = 0; l < coll.normalAttributes.length; l++) {
														var attr = coll.normalAttributes[l];
														var option = $("<option value='normal##"
																+ attr.id
																+ "'>基础属性—"+coll.display+"—"
																+ attr.display
																+ "</option>")
														select.append(option);
													}
												}
												for (var k = 0; k < subject.dycAttrs.length; k++) {
													var dyc = subject.dycAttrs[k];
													var option = $("<option value='dynamic##"
															+ dyc.id
															+ "'>动态属性—"+subject.display+"—"
															+ dyc.display
															+ "</option>")
													select.append(option);
												}
											}
											div.append(span).append(select);
											$("#quota-params").append(div);
										}
									});
						},
						addQuotaAttr : function() {
							var quota = {};
							quota.display = $("#quota-name").val();
							quota._function = $
									.parseJSON($("#quota-fun").val()).id;
							quota['group-subject'] = this.currSubject.id;
							quota.id = "model@quota_" + quota.display;
							quota.param = [];
							this.quotaAttrs.push(quota);
							$("#quota-params .key").each(
									function(i, el) {
										var param = {};
										param.name = $(el).text();
										var values = $(
												$("#quota-params .value")
														.get(i)).val().split(
												"##");
										param['attribute-type'] = values[0];
										param['attribute'] = values[1];
										quota.param.push(param);
									})
							this.attr_status = "quota-list";
						},
						deleteQuota : function(quota) {
							if (confirm("确认删除？")) {
								this.quotaAttrs.removeByValue(quota);
							}
						}
						,chnageFun:function(){
							$("#quota-params").empty().html(this.initParams())
						}
					}
				});
Vue.component('dyc', {
	template : getHtml("./dyc-page.vue"),
	props : [ "dycAttrs", 'currSubject', 'seg' ],
	data : function() {
		return {
			attr_status : "dyc-list"
		}
	},
	methods : {
		changeSubject : function() {
			var attrColls = $.parseJSON($("#param-attr-subject").val());
			var attrSelect = $("#param-attr").empty();
			$.each(attrColls, function(i, coll) {
				$.each(coll.normalAttributes, function(i, attr) {
					var option = $("<option>").attr("value", attr.id).text(
							attr.display).attr("coll", coll.id);
					attrSelect.attr("coll",coll.id).append(option);
				})
			})
		},
		initMainAttr : function() {
			this.$nextTick(function() {

				var colls = this.currSubject.attributeCollections;
				$.each(colls, function(i, coll) {
					$.print(coll)
					$.each(coll.normalAttributes, function(j, attr) {
						var option = $("<option>").attr("value", attr.id).text(
								attr.display);
						$("#main-attr").attr("coll",coll.id).append(option);
					
					})
				})

			})
		},
		addDycAttr : function() {
			var dyc = {};
			dyc.display = $("#name").val();
			dyc.id = "model@_dynamic_" + dyc.display;
			dyc['default-attribute'] = $("#main-attr").val();
			dyc.dynamic = {};
			dyc.dynamic['param-attribute-collection'] = $("#param-attr").attr(
					"coll");
			dyc.dynamic['attribute-type'] = "normal";
			dyc['attribute-type'] = "normal";
			dyc.dynamic['attribute'] = $("#param-attr").val();
			this.currSubject.dycAttrs.push(dyc);
			this.attr_status = "dyc-list";
		},
		deleteDyc : function(dyc) {
			if (confirm("确认删除？")) {
				this.dycAttrs.removeByValue(dyc);
			}
		}
	}
})
layui.use('element', function() {
	var $ = layui.jquery, element = layui.element;
});
Array.prototype.removeByValue = function(val) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == val) {
			this.splice(i, 1);
			break;
		}
	}
}
var vueparam = {
	el : "#functions",
	data : {
		seg : window.parent.editor.data.segmentation,
		tables : window.parent.editor.data.database.tables,
		funs : window.parent.editor.data.functions,
		status : "seg-list",
		attr_status : 'nomal-list'
	},
	methods : {
		addSubject : function() {
			var subject = {};
			subject.id = $("#name").val();
			subject.display = $("#name").val();
			subject.attributeCollections = [];
			subject.functionAttributes = [];
			subject.composeAttributes = [];
			subject.dycAttrs = [];
			subject.quotaAttrs = [];
			subject.subjectdoc = {};
			if (!this.seg.subjects) {
				this.seg.subjects = [];
			}
			this.seg.subjects.push(subject);
			this.status = "seg-list";
		},
		returnToEditSubject : function(subject) {
			this.currSubject = subject;
			this.status = 'edit-subject'
		},
		returnToSubject : function(subject) {
			this.currSubject = subject;
			this.status = 'subject-info'
		},
		delSubject : function(subject) {
			if (confirm("确认删除？")) {
				this.seg.subjects.removeByValue(subject);
			}

		},
		addAttrColl : function() {
			var coll = {};
			coll.code = $("#code").val();
			coll.display = $("#name").val();
			coll.table = "db@" + coll.code;
			coll.id = "model@" + coll.code;
			coll.normalAttributes = [];
			coll.ismaster = true;
			coll.refsubject = true;
			this.currSubject.attributeCollections.push(coll);
			this.status = 'subject-info'
		},
		returnEditAttrColl : function(attrColl) {
			this.currAttrColl = attrColl;
			this.status = "edit-attrColl";
			this.$nextTick(function() {
				$("#name").val(attrColl.display);
				$("#code").val(attrColl.code);
			})
		},
		editAttrColl : function() {
			var coll = {};
			coll.code = $("#code").val();
			coll.display = $("#name").val();
			coll.table = "db@" + coll.code;
			coll.id = "model@" + coll.code;
			this.$set(this.currAttrColl, "code", coll.code);
			this.$set(this.currAttrColl, "display", coll.display);
			this.$set(this.currAttrColl, "id", coll.id);
			this.$set(this.currAttrColl, "table", coll.table);
			this.status = 'subject-info';
		},
		delAttrColl : function(attrColl) {
			if (confirm("确认删除？")) {
				this.currSubject.attributeCollections.removeByValue(attrColl);
			}
		},
		returnToAttrList : function(attrColl) {
			this.currAttrColl = attrColl;
			this.attr_status = 'nomal-attr';
		},
		returnAddNomalAttr : function() {
			for (var i = 0; i < this.tables.length; i++) {
				var table = this.tables[i];
				if (table.code == this.currAttrColl.code) {
					if (!table.fields) {
						table.fields = [];
					}
					this.currFields = table.fields;
					break;
				}
			}
			this.nomalAttrFilter();
			if (this.array.length == 0) {
				alert("已经没有可以添加的属性");
			} else {

				this.status = 'add-nomal-attr';
			}
		},
		nomalAttrFilter : function() {
			var array = [];
			var nomals = this.currAttrColl.normalAttributes;
			var fields = this.currFields;
			for (var i = 0; i < fields.length; i++) {
				var field = fields[i];
				var flag = true;
				for (var j = 0; i < nomals.length; j++) {
					var nomal = nomals[j];
					if (field.id == nomal.field) {
						flag = false;
						break;
					}
				}
				if (flag) {
					array.push(field);
				}
			}
			this.array = array;
		},
		addNomalAttr : function() {
			var that = this;
			$("input[name='choose-field']:checked").each(function(i, elt) {
				var field = $.parseJSON($(elt).val());
				var nomalAttr = {};
				nomalAttr.display = field.comment;
				nomalAttr.field = field.id;
				nomalAttr.id = field.id.replace("db@", "model@");
				nomalAttr.ismaster = field.iskey;
				nomalAttr.refsubject = field.iskey;
				that.currAttrColl.normalAttributes.push(nomalAttr);
			});
			this.status = 'subject-info';
		},
		deleteNomalAttr : function(attr) {
			if (confirm("确认删除？")) {
				this.currAttrColl.normalAttributes.removeByValue(attr);
			}
		},
		buildFunParam : function() {
			var params = $.parseJSON($("#funs>option:checked").attr("params"));
			var that = this;
			var div = $("<div>");
			$
					.each(
							params,
							function(i, param) {
								var item = $("<div>");
								div.append(item);
								var span = $("<span>").text(param + ":");
								var select = $("<select id='fun-table'>")
										.width(150);
								$.each(that.currSubject.attributeCollections,
										function(i, table) {
											var op = $("<option>").text(
													table.display).data(
													"table", table);
											$(select).append(op);
										})

								var select1 = $("<select class='fun-field'>")
										.width(150);
								if (that.currSubject.attributeCollections.length > 0) {
									$
											.each(
													that.currSubject.attributeCollections[0].normalAttributes,
													function(i, field) {
														var op = $("<option>")
																.text(
																		field.display)
																.attr(
																		"value",
																		field.id);
														$(select1).append(op);
													})
								}
								select.change(function() {
									var table = $("#fun-table>option:checked")
											.data("table");
									select1.empty();
									$.each(table.normalAttributes, function(i,
											field) {
										var op = $("<option>").text(
												field.display).attr("value",
												field.id);
										select1.append(op);
									})
								})
								item.append(span).append(select)
										.append(select1);

							})
			$("#paramsDiv").empty().append(div);

		},
		addFunAttr : function() {
			var funAttr = {};
			funAttr.id = "model@fun_" + $("#name").val();
			funAttr.display = $("#name").val();
			funAttr.attribute = $("#funs").val();
			funAttr.param = [];
			var params = $.parseJSON($("#funs>option:checked").attr("params"));

			$(".fun-field").each(function(i, el) {
				var param = {
					name : params[i],
					attributeType : "nomal",
					attribute : $(el).val()
				}
				funAttr.param.push(param);
			})
			this.currSubject.functionAttributes.push(funAttr);
			this.attr_status = 'fun-list'
		},
		delFunAttr : function(fun) {
			if (confirm("确认删除？")) {
				this.currSubject.functionAttributes.removeByValue(fun);
			}
		},
		addCompMapInput : function() {
			var div = $("<div>");
			var key = $("<input class='key'>").width(100).height(35);
			var value = $("<select class='value'>").width(150).height(35);
			var colls = this.currSubject.attributeCollections
			for (var i = 0; i < colls.length; i++) {
				var attrs = colls[i].normalAttributes;
				for (var j = 0; j < attrs.length; j++) {
					var attr = attrs[j];
					value.append("<option value='" + attr.id + "'>"
							+ attr.display + "</option>");
				}
			}
			div.append(key).append(":").append(value);
			$("#mapDiv").append(div);
		},
		addCompAttr : function() {
			var comp = {};
			comp.display = $("#name").val();
			comp.id = "model@comp_" + comp.display;
			comp.mapping = [];
			$("#mapDiv .key").each(function(i, el) {
				var mapping = {};
				mapping.name = $(el).val();
				mapping.attributeType = "normal";
				mapping.attribute = $($("#mapDiv .value").get(i)).val();
				comp.mapping.push(mapping);
			})
			this.currSubject.composeAttributes.push(comp);
			this.attr_status = 'comp-list';

		},
		delCompAttr : function(comp) {
			if (confirm("确认删除？")) {
				this.currSubject.composeAttributes.removeByValue(comp);
			}
		},
		getHtml : function() {
			return "{{seg.id}}"
		}
		
	}
}

new Vue(vueparam);
