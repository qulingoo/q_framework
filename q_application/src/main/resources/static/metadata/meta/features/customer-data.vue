<div v-if="status=='init'" v-html="init()"></div>
<div v-else-if="status=='list'">
	<table class="layui-table">
		<tr>
			<td width="100px">主题</td>
			<td><select id="subject" class="layui-input"
				v-model="features[1].subject.id">
					<option v-for="subject in seg.subjects" :value="subject.id">{{subject.display}}</option>
			</select></td>
		</tr>
		<tr>
			<td width="100px">订单绑定主题</td>
			<td><select id="bind-subject" class="layui-input"
				v-model="features[1].subject.category.behavior.behaviorSubject">
					<option v-for="subject in seg.subjects" :value="subject.id">
						{{subject.display}}</option>
			</select></td>
		</tr>
	</table>
	<table class="layui-table">
	
		<thead>
			<tr>
				<th>绑定编号</th>
				<th>名称</th>
				<th>x</th>
				<th>y</th>
				<th>操作</th>
			</tr>

		</thead>
		<tbody>
			<tr v-for=" item in features[1].subject.category.behavior.item">
				<td>{{item.id}}</td>
				<td>{{item.display}}</td>
				<td>{{item.x}}</td>
				<td>{{item.y}}</td>
				<td><span class="delete" @click="del(item)"></span></td>
			</tr>
		</tbody>
	</table>

	<div class="toolbox unselect" @click="status='add'">+</div>
</div>
<div v-else-if="status=='add'">
	<table class="layui-table">
		<tr>
			<td>绑定</td>
			<td><select class="layui-input" id="queryitem">
					<option v-for="queryitem in getOtherQueryItem()"
						:value="JSON.stringify(queryitem)">{{queryitem.display}}</option>
			</select></td>
		</tr>
		<tr>
			<td>X</td>
			<td><input id="x" class="layui-input" /></td>
		</tr>
		<tr>
			<td>Y</td>
			<td><input id="y" class="layui-input" /></td>
		</tr>

		<tr>
			<td><button class="layui-btn" @click="save()">保存</button></td>
		</tr>

	</table>

</div>
