<template>
	<div class="diy_edit page_health_information_reporting" id="health_information_reporting_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
					<div v-if="$check_field('set','user') || $check_field('add','user') || $check_field('get','user')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>用户:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_user" :disabled="disabledObj['user_isDisabled']" v-model="form['user']" v-if="(form['user'] && $check_field('set','user')) || (!form['user'] && $check_field('add','user'))" >
								<option v-for="o in list_user_user" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','user')">{{ form['user'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','full_name') || $check_field('add','full_name') || $check_field('get','full_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>姓名:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_full_name" v-model="form['full_name']" placeholder="请输入姓名" v-if="(form['full_name'] && $check_field('set','full_name')) || (!form['full_name'] && $check_field('add','full_name'))"  :disabled="disabledObj['full_name_isDisabled']"/>
							<span v-else-if="$check_field('get','full_name')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','id') || $check_field('add','id') || $check_field('get','id')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>身份证:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_id" v-model="form['id']" placeholder="请输入身份证" v-if="(form['id'] && $check_field('set','id')) || (!form['id'] && $check_field('add','id'))"  :disabled="disabledObj['id_isDisabled']"/>
							<span v-else-if="$check_field('get','id')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','temperature') || $check_field('add','temperature') || $check_field('get','temperature')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>体温:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_temperature" v-model="form['temperature']" placeholder="请输入体温" v-if="(form['temperature'] && $check_field('set','temperature')) || (!form['temperature'] && $check_field('add','temperature'))"  :disabled="disabledObj['temperature_isDisabled']"/>
							<span v-else-if="$check_field('get','temperature')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','cough') || $check_field('add','cough') || $check_field('get','cough')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>是否咳嗽:
							</span>
						</div>
						<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_cough" v-model="form['cough']" v-if="(form['cough'] && $check_field('set','cough')) || (!form['cough'] && $check_field('add','cough'))" >
								<option v-for="o in list_cough" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','cough')">{{ form['cough'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','is_it_hot') || $check_field('add','is_it_hot') || $check_field('get','is_it_hot')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>是否发热:
							</span>
						</div>
						<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_is_it_hot" v-model="form['is_it_hot']" v-if="(form['is_it_hot'] && $check_field('set','is_it_hot')) || (!form['is_it_hot'] && $check_field('add','is_it_hot'))" >
								<option v-for="o in list_is_it_hot" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','is_it_hot')">{{ form['is_it_hot'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','is_it_weak') || $check_field('add','is_it_weak') || $check_field('get','is_it_weak')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>是否乏力:
							</span>
						</div>
						<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_is_it_weak" v-model="form['is_it_weak']" v-if="(form['is_it_weak'] && $check_field('set','is_it_weak')) || (!form['is_it_weak'] && $check_field('add','is_it_weak'))" >
								<option v-for="o in list_is_it_weak" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','is_it_weak')">{{ form['is_it_weak'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','is_it_difficult_to_breathe') || $check_field('add','is_it_difficult_to_breathe') || $check_field('get','is_it_difficult_to_breathe')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>是否呼吸困难:
							</span>
						</div>
						<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_is_it_difficult_to_breathe" v-model="form['is_it_difficult_to_breathe']" v-if="(form['is_it_difficult_to_breathe'] && $check_field('set','is_it_difficult_to_breathe')) || (!form['is_it_difficult_to_breathe'] && $check_field('add','is_it_difficult_to_breathe'))" >
								<option v-for="o in list_is_it_difficult_to_breathe" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','is_it_difficult_to_breathe')">{{ form['is_it_difficult_to_breathe'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','date') || $check_field('add','date') || $check_field('get','date')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>日期:
							</span>
						</div>
						<!-- 日期 -->
						<div class="diy_field diy_date">
							<input type="date" :disabled="disabledObj['date_isDisabled']" id="form_date" v-model="form['date']" placeholder="请输入日期" v-if="(form['date'] && $check_field('set','date')) || (!form['date'] && $check_field('add','date'))" />
							<span v-else-if="$check_field('get','date')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','physical_symptoms') || $check_field('add','physical_symptoms') || $check_field('get','physical_symptoms')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>身体症状:
							</span>
						</div>
						<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_physical_symptoms" v-model="form['physical_symptoms']" v-if="(form['physical_symptoms'] && $check_field('set','physical_symptoms')) || (!form['physical_symptoms'] && $check_field('add','physical_symptoms'))" :disabled="disabledObj['physical_symptoms_isDisabled']" />
							<span v-else-if="$check_field('get','physical_symptoms')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="user_group === '管理员' || $check_examine()" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核状态:
							</span>
						</div>
						<div class="diy_field diy_select" v-if="$check_action('/health_information_reporting/edit','examine')">
							<!--<span> {{ form['examine_state'] }} </span>-->
							<select v-model="form['examine_state']">
								<option value="未审核">
									未审核
								</option>
								<option value="已通过">
									已通过
								</option>
								<option value="未通过">
									未通过
								</option>
							</select>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_state'] }}
							</span>
						</div>
					</div>
					<div v-if="user_group === '管理员' || $check_examine()" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核回复:
							</span>
						</div>
						<div class="diy_field diy_desc" v-if="$check_action('/health_information_reporting/edit','examine')">
							<textarea v-model="form['examine_reply']"></textarea>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_reply'] }}
							</span>
						</div>
					</div>
				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/health_information_reporting/get_obj?",
				url_add: "~/api/health_information_reporting/add?",
				url_set: "~/api/health_information_reporting/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"user": 0,
					"full_name": "",
					"id": "",
					"temperature": "",
					"cough": "",
					"is_it_hot": "",
					"is_it_weak": "",
					"is_it_difficult_to_breathe": "",
					"date": "",
					"physical_symptoms": "",
					"health_information_reporting_id": 0,
				},

				obj: {
					"user": 0, // 用户
					"full_name":'', // 姓名
					"id":'', // 身份证
					"temperature":'', // 体温
					"cough":'', // 是否咳嗽
					"is_it_hot":'', // 是否发热
					"is_it_weak":'', // 是否乏力
					"is_it_difficult_to_breathe":'', // 是否呼吸困难
					"date": new Date().getTime(),
					"physical_symptoms":'', // 身体症状
					"examine_state": "未审核",
					"examine_reply": "",
					"health_information_reporting_id": 0,
				},

				// 表单字段
				form: {
					"user": 0, // 用户
					"full_name":'', // 姓名
					"id":'', // 身份证
					"temperature":'', // 体温
					"cough":'', // 是否咳嗽
					"is_it_hot":'', // 是否发热
					"is_it_weak":'', // 是否乏力
					"is_it_difficult_to_breathe":'', // 是否呼吸困难
					"date": new Date().getTime(),
					"physical_symptoms":'', // 身体症状
					"examine_state": "未审核",
					"examine_reply": "",
					"health_information_reporting_id": 0,
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
				//是否咳嗽选项列表
				list_cough: ['是','否'],
				//是否发热选项列表
				list_is_it_hot: ['是','否'],
				//是否乏力选项列表
				list_is_it_weak: ['是','否'],
				//是否呼吸困难选项列表
				list_is_it_difficult_to_breathe: ['是','否'],

				// ID字段
				field: "health_information_reporting_id",
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
			async get_user_session_user(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["user"] = user_id
								_this.disabledObj['user' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
                  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                    for (var j = 0; j < arrForm.length; j++) {
                      if (arr[i] === arrForm[j]) {
                        if (arr[i] !== "user") {
                          _this.form[arrForm[j]] = res.result.obj[arr[i]]
                          _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                          break;
                        }
                      }
                    }
                  }
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/health_information_reporting/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
        //   this.obj = $.push(this.obj ,form);
				// 	this.form = $.push(this.form ,form);
				// }
				// var arr = []
				// for (let key in form) {
				// 	arr.push(key)
				// }
				// for (var i=0;i<arr.length;i++){
				// 	this.disabledObj[arr[i] + '_isDisabled'] = true
				// }
        if (form) {
          var arr = []
          for (let key in form) {
            arr.push(key)
          }
          var arrForm = []
          for (let key in this.form) {
            arrForm.push(key)
          }
          for (var i=0;i<arr.length;i++){
            if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
              for (var j = 0; j < arrForm.length; j++) {
                if (arrForm[j] === arr[i]) {
                  this.form[arrForm[j]] = form[arr[i]]
                  this.obj[arrForm[j]] = form[arr[i]]
                  this.disabledObj[arrForm[j] + '_isDisabled'] = true
                  break;
                }
              }
            }
          }
        }
        if (JSON.stringify(this.form["date"]).indexOf("-")===-1) {
          this.form["date"] = this.$toTime(parseInt(this.form["date"]), "yyyy-MM-dd")
        }

        $.db.del("form");
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				// var form = $.db.get("form");
				// var obj = Object.assign({} ,form ,this.obj);
				// if (obj) {
        //   delete(obj.examine_state)
        //   delete(obj.examine_reply)
				// 	this.obj = $.push(this.obj ,obj);
				// }
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
				// 	this.form = $.push(this.form ,form);
				// }
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_user_session_user();
			this.get_list_user_user();
		},
	}
</script>

<style>
</style>
