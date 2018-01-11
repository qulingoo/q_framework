<div v-if="attr_status=='dyc-list'">
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
			<tr v-for="dyc in dycAttrs">
				<td>{{dyc.id}}</td>
				<td>{{dyc.display}}</td>
				<td><div>主属性:{{dyc['default-attribute']}}</div>
				<div>关联属性:{{dyc.dynamic['attribute']}}</div></td>
				<td><span class="delete" @click="deleteDyc(dyc)"></span></td>
			</tr>
		</tbody>
	</table>
	<div class="back unselect" @click="status='seg-list'"><</div>
	<div class="toolbox unselect" @click="attr_status='dyc-add'">+</div>
</div>
<div v-else-if="attr_status=='dyc-add'">
	<table style="height: 130px; width: 80%" align="center">
		<tr>
			<td>名称</td>
			<td><input id="name" class="layui-input" /></td>
		</tr>
		<tr>
			<td>主属性</td>
			<td><select id="main-attr" class="layui-input" v-html="initMainAttr()">
					 
			</select></td>
		</tr>
		<tr>
			<td>引用属性</td>
			<td><select id="param-attr-subject" class="layui-input"
				@change="changeSubject()">
					<option></option>
					<option :value="JSON.stringify(subject.attributeCollections)"
						v-for="subject in seg.subjects">{{subject.display}}</option>
			</select>
			<select id="param-attr" class="layui-input"></select></td>
		</tr>

		<tr>
			<td colspan="2" style="text-align: center;">
				<button class="layui-btn" @click="addDycAttr">添加</button>
			</td>
		</tr>
	</table>
</div>