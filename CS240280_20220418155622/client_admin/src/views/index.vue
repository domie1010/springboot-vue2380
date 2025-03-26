<template>
	<div class="page_root" id="root_index">
		<div class="warp">
			<div class="container-fluid">
				<el-row>
					<el-col :span="4">
						<mm_label bg_color="bg_purple" icon="el-icon-user-solid" :url="url_user_count" unit="人"
								  title="用户数量"></mm_label>
					</el-col>
				</el-row>

				<el-row>
					<el-col v-if="user_group == '管理员' || $check_figure('/user_status_information/table')" :span="8">
						<div class="card chart">
							<newBarChart v-if="bar_obj_user_status_information.values.length > 0" id="bar_obj_user_status_information" :vm="bar_obj_user_status_information" :title="'用户状态信息统计'">
							</newBarChart>
							<div v-if="!bar_obj_user_status_information.values.length">用户状态信息没有符合条件的数据</div>
						</div>
					</el-col>
					<el-col v-if="user_group == '管理员' || $check_figure('/information_reporting/table')" :span="8">
						<div class="card chart">
							<newLineChart v-if="line_obj_information_reporting.values.length > 0" id="line_obj_information_reporting" :vm="line_obj_information_reporting" :title="'上报信息情况统计'">
							</newLineChart>
							<div v-if="!line_obj_information_reporting.values.length">上报信息情况没有符合条件的数据</div>
						</div>
					</el-col>
					<el-col v-if="user_group == '管理员' || $check_figure('/epidemic_data/table')" :span="8">
						<div class="card chart">
							<newBarChart v-if="bar_obj_epidemic_data.values.length > 0" id="bar_obj_epidemic_data" :vm="bar_obj_epidemic_data" :title="'疫情数据统计'">
							</newBarChart>
							<div v-if="!bar_obj_epidemic_data.values.length">疫情数据没有符合条件的数据</div>
						</div>
					</el-col>
				</el-row>
			</div>
		</div>
	</div>
</template>
<script>
	import mixin from "@/mixins/page.js";
	import pieChart from "@/components/charts/pie_chart";
	import barChart from "@/components/charts/bar_chart";
	import newBarChart from "@/components/charts/new_bar_chart";
	import lineChart from "@/components/charts/line_chart";
	import newLineChart from "@/components/charts/new_line_chart";
	import mm_label from "@/components/mm_label.vue";
	export default {
		mixins: [mixin],
		name: "Home",
		components: {
			pieChart,
			barChart,
			newBarChart,
			lineChart,
			newLineChart,
			mm_label
		},
		data() {
			return {
				isAdmin: false,
				recognitionType: "",
				activeName: "third",
				bar_obj_user_status_information: {
					names:[],
					xAxis: [],
					values:[]
				},
				line_obj_information_reporting:{
					names:[],
					xAxis: [],
					values:[]
				},
				bar_obj_epidemic_data: {
					names:[],
					xAxis: [],
					values:[]
				},
				url_user_count: "~/api/user/count?",
			};
		},
		created() {
			this.getUserInfo();
			// 执行用户状态信息数据获取
			this.get_list_user_status_information();
			// 执行上报信息情况数据获取
			this.get_list_information_reporting();
			// 执行疫情数据数据获取
			this.get_list_epidemic_data();
			
			
		},
		mounted() {},
		methods: {
			async get_nickname(list,flag){
				if (flag) {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i],
								null,
								(json) => {
									if (json.result) {
										list[i] = json.result.obj.nickname;
									}
								});
					}
				}else {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i].name,
								null,
								(json) => {
									if (json.result) {
										list[i].name = json.result.obj.nickname;
									}
								});
					}
				}
			},
			// 获取用户状态信息统计柱状图
			async get_list_user_status_information() {
				let name_list = [];
				let query_str = "";
				let group_by_value = "date";
				let flag = false;
				let date_flag = "日期"
				name_list.push("健康人数");
				query_str = query_str+"number_of_healthy_people"+","
				name_list.push("不适人数");
				query_str = query_str+"number_of_discomfort"+","
				this.bar_obj_user_status_information.names = name_list
				query_str = query_str.substr(0,query_str.length-1);
				let data = {};
				await this.$get(
						"~/api/user_status_information/bar_group?field="+query_str+"&groupby="+group_by_value,
						data,
						(json) => {
							if (json.result) {
								let xAxis = [];
								let values = [];
								json.result.list.map((o) => {
									if (date_flag === "日期") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd"));
									}else if (date_flag === "时间") {
										xAxis.push(this.$toTime(o[0] ,"hh:mm:ss"));
									}else if (date_flag === "日长") {
                    xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd hh:mm:ss"));
                  }else {
										xAxis.push(o[0]);
									}
									values.push(o.splice(1))
								});
								this.bar_obj_user_status_information.xAxis = xAxis;
								this.bar_obj_user_status_information.values = values;
							}
							if (flag){
								this.get_nickname(this.bar_obj_user_status_information.xAxis,true);
							}
						});
			},
			// 获取上报信息情况统计折线图
			async get_list_information_reporting() {
				let group_by_value = "information_type";
				let data = {};
				let flag = false;
				await this.$get(
					"~/api/information_reporting/get_list?groupby="+group_by_value,data,
					(json) => {
						if (json.result) {
							let list = json.result.list;
							let name_list = [];
							for (let i=0;i<list.length;i++){
								name_list.push(list[i].information_type);
							}
							this.line_obj_information_reporting.names = name_list;
							this.get_list_information_reporting_sub("information_type",flag);
						}
				});
			},
			async get_list_information_reporting_sub(v1,names_flag) {
				let data = {};
				let flag = false;
				await this.$get(
					"~/api/information_reporting/get_list?groupby=date",data,
					(json) => {
						if (json.result) {
							let list = json.result.list;
							let xAxis_list = [];
							for (let i=0;i<list.length;i++){
								xAxis_list.push(this.$toTime(list[i].date ,"yyyy-MM-dd"));
							}
							this.line_obj_information_reporting.xAxis = xAxis_list;
							this.get_list_information_reporting_sub_sub(v1,"date",names_flag,flag);
						}
				});
			},
			async get_list_information_reporting_sub_sub(v1,v2,names_flag,xAxis_flag) {
				let data_str = "{\""+v1+"\":\"\",\""+v2+"\":\"\"}";
				let data = JSON.parse(data_str);
				for (let i=0;i<this.line_obj_information_reporting.xAxis.length;i++){
					let list = []
					for (let j=0;j<this.line_obj_information_reporting.names.length;j++){
						data[v2] = this.line_obj_information_reporting.xAxis[i];
						data[v1] = this.line_obj_information_reporting.names[j];
						await this.$get(
								"~/api/information_reporting/sum?field=number",
								data,
								(json) => {
									if (json.result) {
										list[j] = json.result;
									}else {
										list[j] = 0;
									}
								});
					}
					this.line_obj_information_reporting.values.push(list)
				}
				if (names_flag){
					this.get_nickname(this.line_obj_information_reporting.names,true);
				}
				if (xAxis_flag){
					this.get_nickname(this.line_obj_information_reporting.xAxis,true);
				}
			},
			// 获取疫情数据统计柱状图
			async get_list_epidemic_data() {
				let name_list = [];
				let query_str = "";
				let group_by_value = "region";
				let flag = false;
				let date_flag = "其他"
				name_list.push("感染人数");
				query_str = query_str+"number_of_infected_persons"+","
				name_list.push("密接人数");
				query_str = query_str+"number_of_close_contacts"+","
				this.bar_obj_epidemic_data.names = name_list
				query_str = query_str.substr(0,query_str.length-1);
				let data = {};
				await this.$get(
						"~/api/epidemic_data/bar_group?field="+query_str+"&groupby="+group_by_value,
						data,
						(json) => {
							if (json.result) {
								let xAxis = [];
								let values = [];
								json.result.list.map((o) => {
									if (date_flag === "日期") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd"));
									}else if (date_flag === "时间") {
										xAxis.push(this.$toTime(o[0] ,"hh:mm:ss"));
									}else if (date_flag === "日长") {
                    xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd hh:mm:ss"));
                  }else {
										xAxis.push(o[0]);
									}
									values.push(o.splice(1))
								});
								this.bar_obj_epidemic_data.xAxis = xAxis;
								this.bar_obj_epidemic_data.values = values;
							}
							if (flag){
								this.get_nickname(this.bar_obj_epidemic_data.xAxis,true);
							}
						});
			},
				getUserInfo(){
					let userGroup = window.localStorage.getItem('user_group');
						if(userGroup){
						let _userGroup =JSON.parse(userGroup);
						let _info = JSON.parse(_userGroup.value);
						this.isAdmin = _info["user_group"] == "管理员" ? true : false;
					}
			}

		},
		computed:{
			recognitionHeight(){
				if(this.recognitionType === "face"){
					return "1070px"
				}else{
					return "1180px"
				}
			},
			recognitionUrl(){
					if(this.recognitionType === "face"){
					return "https://www.sk-ai.com/Experience/face-compare"
				}else{
					return "https://www.sk-ai.com/Experience/recognition?type="
				}
			}
		}
	};
</script>

<style scoped="scoped">
	.chart {
		display: block;
		width: 100%;
		height: 400px;
		padding: 1rem;
		position: relative;
	}

	.el-col {
		padding: 0.5rem;
	}

	.card {
		overflow: hidden;
	}
	
	.iframe_box ,.iframe_box_change{
		width: 100%;
		height: 1180px;
		position: relative;
		margin-top: 25px;
	}
	.iframe_box_change{
		height: 580px;
		padding-top: 50px;
	}
.iframe_box	.iframe_box_content, .iframe_box_change .iframe_box_content{
	width: 100%;
	height: 100%;
}
.iframe_box_top{
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100px;
	font-size: 25px;
	line-height: 100px;
	background: #fff;
	z-index: 99999999;
	padding-left: 50px;
}
</style>
