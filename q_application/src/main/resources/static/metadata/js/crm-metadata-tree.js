var setting = {
	view : {
		selectedMulti : false
	},
	edit : {
		enable : true,
		showRemoveBtn : false,
		showRenameBtn : false,
		editNameSelectAll : true
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		beforeDrag : beforeDrag,
		beforeEditName : beforeEditName,
		beforeRemove : beforeRemove,
		beforeRename : beforeRename,
		onRemove : onRemove,
		onDrop : onDrop
	}
};

var zNodes = [ {
	id : 1,
	pId : 0,
	name : "平台",
	open : true
}];
var log, className = "dark";

/* 是否允许拖拽 */
function beforeDrag(treeId, treeNodes) {
	return true;
}
/* 确认删除 */
function beforeRemove(treeId, treeNode) {
	className = (className === "dark" ? "" : "dark");

	return confirm("确定删除 '" + treeNode.name + "' 节点?");
}
/* 删除后回调函数 */
function onRemove(e, treeId, treeNode) {
	alert('success');
}
/* 被拖拽后回调函数 */
function onDrop(event, treeId, treeNodes, targetNode, moveType) {
};

/* 被修改后回调函数 */
function beforeRename(treeId, treeNode, newName) {
	if (newName.length == 0) {
		alert("Node name can not be empty.");
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		setTimeout(function() {
			zTree.editName(treeNode)
		}, 10);
		return false;
	}

	return true;
}
/* 是否允许修改 */
function beforeEditName(treeId, treeNode) {
	className = (className === "dark" ? "" : "dark");
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.selectNode(treeNode);
	return confirm("Start node '" + treeNode.name + "' editorial status?");
}

var newCount = 1;

/* 初始化tree */
$(document).ready(function() {
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	init();
	window.zTree = $.fn.zTree
	.getZTreeObj("treeDemo")
});

/* 初始化右击菜单 */
function init() {
	$
			.contextMenu({
				selector : '#treeDemo li a',
				build : function($trigger, e) {
					$trigger.click();
					return {
						items : {
							"add" : {
								name : "新增平台",
								icon : "add",
								callback : function(key, options) {
									
								}
							},
							"delete" : {
								name : "删除",
								icon : "delete",
								callback : function(key, options) {
									 
								}
							}
						}
					};
				}
			});

}
 