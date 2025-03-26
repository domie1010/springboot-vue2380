<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','user') || $check_field('add','user') || $check_field('set','user')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="用户" prop="user">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_user(form['user']) }}
							<!--<el-input id="business_name" v-model="form['user']" placeholder="请输入用户"-->
							<!--v-if="user_group === '管理员' || (form['online_reporting_id'] && $check_field('set','user')) || (!form['online_reporting_id'] && $check_field('add','user'))" :disabled="disabledObj['user_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','user')">{{form['user']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['online_reporting_id'] && $check_field('set','user')) || (!form['online_reporting_id'] && $check_field('add','user'))" id="user" v-model="form['user']" :disabled="disabledObj['user_isDisabled']">
								<el-option v-for="o in list_user_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','user')" id="user" v-model="form['user']" :disabled="true">
								<el-option v-for="o in list_user_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="user" v-model="form['user']" :disabled="disabledObj['user_isDisabled']">
							<el-option v-for="o in list_user_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','date') || $check_field('add','date') || $check_field('set','date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="日期" prop="date">
					<el-date-picker :disabled="disabledObj['date_isDisabled']" v-if="user_group === '管理员' || (form['online_reporting_id'] && $check_field('set','date')) || (!form['online_reporting_id'] && $check_field('add','date'))" id="date"
						v-model="form['date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','date')">{{form['date']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','report_content') || $check_field('add','report_content') || $check_field('set','report_content')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="举报内容" prop="report_content">
					<el-input type="textarea" id="report_content" v-model="form['report_content']" placeholder="请输入举报内容"
						v-if="user_group === '管理员' || (form['online_reporting_id'] && $check_field('set','report_content')) || (!form['online_reporting_id'] && $check_field('add','report_content'))" :disabled="disabledObj['report_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','report_content')">{{form['report_content']}}</div>
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
				field: "online_reporting_id",
				url_add: "~/api/online_reporting/add?",
				url_set: "~/api/online_reporting/set?",
				url_get_obj: "~/api/online_reporting/get_obj?",
				url_upload: "~/api/online_reporting/upload?",

				query: {
					"online_reporting_id": 0,
				},

				form: {
					"user": 0, // 用户
					"date":'', // 日期
					"report_content":'', // 举报内容
					"online_reporting_id": 0, // ID

				},
				disabledObj:{
					"user_isDisabled": false,
					"date_isDisabled": false,
					"report_content_isDisabled": false,
				},
				// 用户列表
				list_user_user: [],
				// 用户组
				group_user_user: "",
			}
		},
		methods: {
			/**
			 * 获取普通用户用户列表
			 */
			async get_list_user_user() {
                // if(this.user_group !== "管理员" && this.form["user"] === 0) {
                //     this.form["user"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=普通用户");
                if(json.result && json.result.list){
                    this.list_user_user = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取普通用户用户组
			 */
			async get_group_user_user() {
				this.form["user"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					this.group_user_user = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_user(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_user.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                for (var j = 0; j < arrForm.length; j++) {
                  if (arr[i] === arrForm[j]) {
                    if (arr[i] !== "user") {
                      _this.form[arrForm[j]] = res.result.obj[arr[i]]
                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                      break;
                    } else {
                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                    }
                  }
                }
              }
						}
					}
				});
			},
			get_user_user(id){
				var obj = this.list_user_user.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "用户":
							if(param["user"] > 0){
								param["user"] = this.user.user_id;
							}
							break;
					}
				}
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
					bl = this.$check_action('/online_reporting/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/online_reporting/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/online_reporting/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/online_reporting/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/online_reporting/view','get');
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
			this.get_list_user_user();
			this.get_group_user_user();
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
