<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','region') || $check_field('add','region') || $check_field('set','region')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="地区" prop="region">
					<el-input id="region" v-model="form['region']" placeholder="请输入地区"
							  v-if="user_group === '管理员' || (form['epidemic_data_id'] && $check_field('set','region')) || (!form['epidemic_data_id'] && $check_field('add','region'))" :disabled="disabledObj['region_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','region')">{{form['region']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_infected_persons') || $check_field('add','number_of_infected_persons') || $check_field('set','number_of_infected_persons')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="感染人数" prop="number_of_infected_persons">
					<el-input id="number_of_infected_persons" v-model="form['number_of_infected_persons']" placeholder="请输入感染人数"
							  v-if="user_group === '管理员' || (form['epidemic_data_id'] && $check_field('set','number_of_infected_persons')) || (!form['epidemic_data_id'] && $check_field('add','number_of_infected_persons'))" :disabled="disabledObj['number_of_infected_persons_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','number_of_infected_persons')">{{form['number_of_infected_persons']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_close_contacts') || $check_field('add','number_of_close_contacts') || $check_field('set','number_of_close_contacts')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="密接人数" prop="number_of_close_contacts">
					<el-input id="number_of_close_contacts" v-model="form['number_of_close_contacts']" placeholder="请输入密接人数"
							  v-if="user_group === '管理员' || (form['epidemic_data_id'] && $check_field('set','number_of_close_contacts')) || (!form['epidemic_data_id'] && $check_field('add','number_of_close_contacts'))" :disabled="disabledObj['number_of_close_contacts_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','number_of_close_contacts')">{{form['number_of_close_contacts']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','statistician') || $check_field('add','statistician') || $check_field('set','statistician')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="统计人" prop="statistician">
					<el-input id="statistician" v-model="form['statistician']" placeholder="请输入统计人"
							  v-if="user_group === '管理员' || (form['epidemic_data_id'] && $check_field('set','statistician')) || (!form['epidemic_data_id'] && $check_field('add','statistician'))" :disabled="disabledObj['statistician_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','statistician')">{{form['statistician']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','remarks') || $check_field('add','remarks') || $check_field('set','remarks')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="备注" prop="remarks">
					<el-input id="remarks" v-model="form['remarks']" placeholder="请输入备注"
							  v-if="user_group === '管理员' || (form['epidemic_data_id'] && $check_field('set','remarks')) || (!form['epidemic_data_id'] && $check_field('add','remarks'))" :disabled="disabledObj['remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','remarks')">{{form['remarks']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','date') || $check_field('add','date') || $check_field('set','date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="日期" prop="date">
					<el-date-picker :disabled="disabledObj['date_isDisabled']" v-if="user_group === '管理员' || (form['epidemic_data_id'] && $check_field('set','date')) || (!form['epidemic_data_id'] && $check_field('add','date'))" id="date"
						v-model="form['date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','date')">{{form['date']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "epidemic_data_id",
				url_add: "~/api/epidemic_data/add?",
				url_set: "~/api/epidemic_data/set?",
				url_get_obj: "~/api/epidemic_data/get_obj?",
				url_upload: "~/api/epidemic_data/upload?",

				query: {
					"epidemic_data_id": 0,
				},

				form: {
					"region":'', // 地区
					"number_of_infected_persons":'', // 感染人数
					"number_of_close_contacts":'', // 密接人数
					"statistician":'', // 统计人
					"remarks":'', // 备注
					"date":'', // 日期
					"epidemic_data_id": 0, // ID

				},
				disabledObj:{
					"region_isDisabled": false,
					"number_of_infected_persons_isDisabled": false,
					"number_of_close_contacts_isDisabled": false,
					"statistician_isDisabled": false,
					"remarks_isDisabled": false,
					"date_isDisabled": false,
				},
			}
		},
		methods: {

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
        if (this.form["date"].indexOf("-")===-1){
          this.form["date"] = this.$toTime(parseInt(this.form["date"]),"yyyy-MM-dd")
        }
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
        if(this.form["date"]=="0000-00-00"){
          this.form["date"] = null;
        }
				if(parseInt(this.form["date"]) > 9999){
					this.form["date"] = this.$toTime(parseInt(this.form["date"]),"yyyy-MM-dd")
				}
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/epidemic_data/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/epidemic_data/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/epidemic_data/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/epidemic_data/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/epidemic_data/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
		},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
