<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','user') || $check_field('add','user') || $check_field('set','user')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="用户" prop="user">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_user(form['user']) }}
							<!--<el-input id="business_name" v-model="form['user']" placeholder="请输入用户"-->
							<!--v-if="user_group === '管理员' || (form['health_information_reporting_id'] && $check_field('set','user')) || (!form['health_information_reporting_id'] && $check_field('add','user'))" :disabled="disabledObj['user_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','user')">{{form['user']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['health_information_reporting_id'] && $check_field('set','user')) || (!form['health_information_reporting_id'] && $check_field('add','user'))" id="user" v-model="form['user']" :disabled="disabledObj['user_isDisabled']">
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
			<el-col v-if="user_group === '管理员' || $check_field('get','full_name') || $check_field('add','full_name') || $check_field('set','full_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="姓名" prop="full_name">
					<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"
							  v-if="user_group === '管理员' || (form['health_information_reporting_id'] && $check_field('set','full_name')) || (!form['health_information_reporting_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','id') || $check_field('add','id') || $check_field('set','id')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="身份证" prop="id">
					<el-input id="id" v-model="form['id']" placeholder="请输入身份证"
							  v-if="user_group === '管理员' || (form['health_information_reporting_id'] && $check_field('set','id')) || (!form['health_information_reporting_id'] && $check_field('add','id'))" :disabled="disabledObj['id_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','id')">{{form['id']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','temperature') || $check_field('add','temperature') || $check_field('set','temperature')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="体温" prop="temperature">
					<el-input id="temperature" v-model="form['temperature']" placeholder="请输入体温"
							  v-if="user_group === '管理员' || (form['health_information_reporting_id'] && $check_field('set','temperature')) || (!form['health_information_reporting_id'] && $check_field('add','temperature'))" :disabled="disabledObj['temperature_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','temperature')">{{form['temperature']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cough') || $check_field('add','cough') || $check_field('set','cough')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="是否咳嗽" prop="cough">
					<el-select id="cough" v-model="form['cough']"
						v-if="user_group === '管理员' || (form['health_information_reporting_id'] && $check_field('set','cough')) || (!form['health_information_reporting_id'] && $check_field('add','cough'))">
						<el-option v-for="o in list_cough" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','cough')">{{form['cough']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','is_it_hot') || $check_field('add','is_it_hot') || $check_field('set','is_it_hot')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="是否发热" prop="is_it_hot">
					<el-select id="is_it_hot" v-model="form['is_it_hot']"
						v-if="user_group === '管理员' || (form['health_information_reporting_id'] && $check_field('set','is_it_hot')) || (!form['health_information_reporting_id'] && $check_field('add','is_it_hot'))">
						<el-option v-for="o in list_is_it_hot" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','is_it_hot')">{{form['is_it_hot']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','is_it_weak') || $check_field('add','is_it_weak') || $check_field('set','is_it_weak')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="是否乏力" prop="is_it_weak">
					<el-select id="is_it_weak" v-model="form['is_it_weak']"
						v-if="user_group === '管理员' || (form['health_information_reporting_id'] && $check_field('set','is_it_weak')) || (!form['health_information_reporting_id'] && $check_field('add','is_it_weak'))">
						<el-option v-for="o in list_is_it_weak" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','is_it_weak')">{{form['is_it_weak']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','is_it_difficult_to_breathe') || $check_field('add','is_it_difficult_to_breathe') || $check_field('set','is_it_difficult_to_breathe')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="是否呼吸困难" prop="is_it_difficult_to_breathe">
					<el-select id="is_it_difficult_to_breathe" v-model="form['is_it_difficult_to_breathe']"
						v-if="user_group === '管理员' || (form['health_information_reporting_id'] && $check_field('set','is_it_difficult_to_breathe')) || (!form['health_information_reporting_id'] && $check_field('add','is_it_difficult_to_breathe'))">
						<el-option v-for="o in list_is_it_difficult_to_breathe" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','is_it_difficult_to_breathe')">{{form['is_it_difficult_to_breathe']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','date') || $check_field('add','date') || $check_field('set','date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="日期" prop="date">
					<el-date-picker :disabled="disabledObj['date_isDisabled']" v-if="user_group === '管理员' || (form['health_information_reporting_id'] && $check_field('set','date')) || (!form['health_information_reporting_id'] && $check_field('add','date'))" id="date"
						v-model="form['date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','date')">{{form['date']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','physical_symptoms') || $check_field('add','physical_symptoms') || $check_field('set','physical_symptoms')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="身体症状" prop="physical_symptoms">
					<el-input type="textarea" id="physical_symptoms" v-model="form['physical_symptoms']" placeholder="请输入身体症状"
						v-if="user_group === '管理员' || (form['health_information_reporting_id'] && $check_field('set','physical_symptoms')) || (!form['health_information_reporting_id'] && $check_field('add','physical_symptoms'))" :disabled="disabledObj['physical_symptoms_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','physical_symptoms')">{{form['physical_symptoms']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="审核状态" prop="examine_state">
					<el-select id="examine_state" v-model="form['examine_state']"
						v-if="user_group === '管理员' || (form['examine_state'] && $check_examine()) || (!form['examine_state'] && $check_examine())">
						<el-option key="未审核" label="未审核" value="未审核"></el-option>
						<el-option key="已通过" label="已通过" value="已通过"></el-option>
						<el-option key="未通过" label="未通过" value="未通过"></el-option>
					</el-select>
					<div v-else>{{form["examine_state"]}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="审核回复" prop="examine_reply">
					<el-input id="examine_reply" v-model="form['examine_reply']" placeholder="请输入审核回复"
						v-if="user_group === '管理员' || (form['examine_reply'] && $check_examine()) || (!form['examine_reply'] && $check_examine())"></el-input>
					<div v-else>{{form["examine_reply"]}}</div>
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
				field: "health_information_reporting_id",
				url_add: "~/api/health_information_reporting/add?",
				url_set: "~/api/health_information_reporting/set?",
				url_get_obj: "~/api/health_information_reporting/get_obj?",
				url_upload: "~/api/health_information_reporting/upload?",

				query: {
					"health_information_reporting_id": 0,
				},

				form: {
					"user": 0, // 用户
					"full_name":'', // 姓名
					"id":'', // 身份证
					"temperature":'', // 体温
					"cough":'', // 是否咳嗽
					"is_it_hot":'', // 是否发热
					"is_it_weak":'', // 是否乏力
					"is_it_difficult_to_breathe":'', // 是否呼吸困难
					"date":'', // 日期
					"physical_symptoms":'', // 身体症状
					"examine_state": "未审核",
					"examine_reply": "",
					"health_information_reporting_id": 0, // ID

				},
				disabledObj:{
					"user_isDisabled": false,
					"full_name_isDisabled": false,
					"id_isDisabled": false,
					"temperature_isDisabled": false,
					"cough_isDisabled": false,
					"is_it_hot_isDisabled": false,
					"is_it_weak_isDisabled": false,
					"is_it_difficult_to_breathe_isDisabled": false,
					"date_isDisabled": false,
					"physical_symptoms_isDisabled": false,
				},
				// 用户列表
				list_user_user: [],
				// 用户组
				group_user_user: "",
				//是否咳嗽选项列表
				list_cough: ['是','否'],
				//是否发热选项列表
				list_is_it_hot: ['是','否'],
				//是否乏力选项列表
				list_is_it_weak: ['是','否'],
				//是否呼吸困难选项列表
				list_is_it_difficult_to_breathe: ['是','否'],
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
					bl = this.$check_action('/health_information_reporting/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/health_information_reporting/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/health_information_reporting/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/health_information_reporting/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/health_information_reporting/view','get');
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
