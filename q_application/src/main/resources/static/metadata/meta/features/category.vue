<div v-if="status=='category-list'" :init="init()">
	<table class="layui-table">
		<tr>
			<td width="100px">主题</td>
			<td><select id="subject"  class="layui-input">
					<option v-for="subject in seg.subjects">
						{{subject.display}}</option>
			</select></td>
		</tr>
		
	</table>

	<div v-for="category in features[0].subject.category">

		<div class="table-card unselect">
			<div class="comment">{{category.display}}</div>
			<div class="code">{{category.id}}</div>
			<div class="more" @click="returnToCategory(category)">>></div>
			<div class="display" @click="returnToEditCategory(category)">目录</div>
			<div class="close" @click="delCategory(category)">x</div>
		</div>
	</div>
	<div class="toolbox unselect" @click="status='category-add'">+</div>
</div>
<div v-else-if="status=='category-queryitem-list'" :init="init()">
	<div>
		<table class="layui-table">
			<thead>
				<tr>
					<th>名称</th>
					<th>查询条件编号</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="queryitem in  currCategory.queryitems">
					<td>{{queryitem.display}}</td>
					<td>{{queryitem.id}}</td>
				</tr>
			</tbody>
			<tbody>
			<tr>
			<td colspan="2">
				分组条件
			</td>
			</tr>
				<tr v-for="groupby in  currCategory.groupbys">
					<td>{{groupby.attribute}}</td>
					<td>{{groupby.dic}}</td>
				</tr>
			</tbody>
		</table>
		<div class="back unselect" @click="status='category-list'"><</div>
	</div>
</div>
<div v-else-if="status=='category-add'">
	<table class="layui-table">
		<tr>
			<td width="100px">目录名称</td>
			<td><input class="layui-input" id="category-name" /></td>
		</tr>
		<tr>
			<td width="100px">目录类型</td>
			<td><select class="layui-input" id="category-type"
				@change="changeSelector">
					<option value="1">普通查询</option>
					<option value="2">分组查询</option>
			</select></td>
		</tr>
		<tbody v-if="selector=='2'">
			<tr>
				<td>分组字段
					<button @click="addGroupSelect">+</button>
				</td>
				<td><div id="group-select"></div></td>
			</tr>
		</tbody>
		<tr>
			<td width="100px">查询条件</td>
			<td>
				<div class="unadd">
					<div v-for="queryitem in queryItemFilter()">
						<input type="checkbox" :value="JSON.stringify(queryitem)">{{queryitem.display}}
					</div>
				</div>
				<div class="btns">
					<button @click="addQueryItem">>></button>
					<br />
					<button @click="delQueryItem"><<</button>
				</div>
				<div class="added"></div>

			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;"><button
					@click="saveCategory">添加</button></td>
		</tr>
	</table>
</div>
<div v-else-if="status=='category-edit'">
	<table class="layui-table">
		<tr>
			<td width="100px">目录名称</td>
			<td><input class="layui-input" id="category-name" /></td>
		</tr>
		<tr>
			<td width="100px">目录类型</td>
			<td><select class="layui-input" id="category-type"
				@change="changeSelector">
					<option value="1">普通查询</option>
					<option value="2">分组查询</option>
			</select></td>
		</tr>
		<tbody v-if="selector=='2'">
			<tr>
				<td>分组字段
					<button @click="addGroupSelect">+</button>
				</td>
				<td><div id="group-select"></div></td>
			</tr>
		</tbody>
		<tr>
			<td width="100px">查询条件</td>
			<td>
				<div class="unadd">
					<div v-for="queryitem in queryItemFilter()">
						<input type="checkbox" :value="JSON.stringify(queryitem)">{{queryitem.display}}
					</div>
				</div>
				<div class="btns">
					<button @click="addQueryItem">>></button>
					<br />
					<button @click="delQueryItem"><<</button>
				</div>
				<div class="added">
					<div v-for="queryitem in currCategory.queryitems">
						<input type="checkbox" :value="JSON.stringify(queryitem)">{{queryitem.display}}
					</div>
				</div>

			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;"><button
					@click="editCategory">修改</button></td>
		</tr>
	</table>
</div>