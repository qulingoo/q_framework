<div v-if="attr_status=='quota-list'">
	<table class="layui-table">
		<thead>
			<tr>
				<th>id</th>
				<th>名称</th>
				<th>参数</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<tr v-for="quota in quotaAttrs">
				<td>{{quota.id}}</td>
				<td>{{quota.display}}</td>
				<td><div v-for="param in quota.param">{{param.name}}:{{param.attribute}}</div></td>
				<td><span class="delete" @click="deleteQuota(quota)"></span></td>
			</tr>
		</tbody>
	</table>
	<div class="back unselect" @click="status='seg-list'"><</div>
	<div class="toolbox unselect" @click="attr_status='quota-add'">+</div>
</div>
<div v-else-if="attr_status=='quota-add'">
	<table style="height: 130px; width: 80%" align="center">
		<tr>
			<td>名称</td>
			<td><input id="quota-name" class="layui-input" /></td>
		</tr>
		<tr>
			<td>函数</td>
			<td><select id="quota-fun" class="layui-input" @change="chnageFun">
				<option v-for="fun in funs" :value="JSON.stringify(fun)">{{fun.name}}</option>
			</select></td>
		</tr>
		<tr>
			<td> </td>
			<td><div id="quota-params" v-html="initParams()"></div></td>
		</tr>
		
		<tr>
			<td colspan="2" style="text-align: center;">
				<button class="layui-btn" @click="addQuotaAttr">添加</button>
			</td>
		</tr>
	</table>
</div>