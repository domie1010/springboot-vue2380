<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="疫情新闻"
				source_table="article"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/ordinary_users/list', 'get')"
				:list="result_ordinary_users_full_name"
				title="普通用户姓名"
				source_table="ordinary_users"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/health_information_reporting/list', 'get')"
				:list="result_health_information_reporting_full_name"
				title="健康信息上报姓名"
				source_table="health_information_reporting"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/health_information_reporting/list', 'get')"
				:list="result_health_information_reporting_date"
				title="健康信息上报日期"
				source_table="health_information_reporting"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/travel_information_reporting/list', 'get')"
				:list="result_travel_information_reporting_full_name"
				title="行程信息上报姓名"
				source_table="travel_information_reporting"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/travel_information_reporting/list', 'get')"
				:list="result_travel_information_reporting_date"
				title="行程信息上报日期"
				source_table="travel_information_reporting"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/online_reporting/list', 'get')"
				:list="result_online_reporting_date"
				title="在线举报日期"
				source_table="online_reporting"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/online_message/list', 'get')"
				:list="result_online_message_message_type"
				title="在线留言留言类型"
				source_table="online_message"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/online_message/list', 'get')"
				:list="result_online_message_message_date"
				title="在线留言留言日期"
				source_table="online_message"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/user_status_information/list', 'get')"
				:list="result_user_status_information_date"
				title="用户状态信息日期"
				source_table="user_status_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/information_reporting/list', 'get')"
				:list="result_information_reporting_information_type"
				title="上报信息情况信息类型"
				source_table="information_reporting"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/information_reporting/list', 'get')"
				:list="result_information_reporting_date"
				title="上报信息情况日期"
				source_table="information_reporting"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/epidemic_data/list', 'get')"
				:list="result_epidemic_data_region"
				title="疫情数据地区"
				source_table="epidemic_data"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/epidemic_data/list', 'get')"
				:list="result_epidemic_data_date"
				title="疫情数据日期"
				source_table="epidemic_data"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
			"result_ordinary_users_full_name":[],
			"result_health_information_reporting_full_name":[],
			"result_health_information_reporting_date":[],
			"result_travel_information_reporting_full_name":[],
			"result_travel_information_reporting_date":[],
			"result_online_reporting_date":[],
			"result_online_message_message_type":[],
			"result_online_message_message_date":[],
			"result_user_status_information_date":[],
			"result_information_reporting_information_type":[],
			"result_information_reporting_date":[],
			"result_epidemic_data_region":[],
			"result_epidemic_data_date":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取full_name
	 */
	get_ordinary_users_full_name(){
		this.$get("~/api/ordinary_users/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_ordinary_users_full_name = json.result.list;
			result_ordinary_users_full_name.map(o => o.title = o['full_name'])
	  			this.result_ordinary_users_full_name = result_ordinary_users_full_name
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_health_information_reporting_full_name(){
		this.$get("~/api/health_information_reporting/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_health_information_reporting_full_name = json.result.list;
			result_health_information_reporting_full_name.map(o => o.title = o['full_name'])
	  			this.result_health_information_reporting_full_name = result_health_information_reporting_full_name
		 	}
		});
	},
	/**
	 * 获取date
	 */
	get_health_information_reporting_date(){
		this.$get("~/api/health_information_reporting/get_list?like=0", { page: 1, size: 10, "date": this.query.word }, (json) => {
		  if (json.result) {
			var result_health_information_reporting_date = json.result.list;
			result_health_information_reporting_date.map(o => o.title = o['date'])
	  			this.result_health_information_reporting_date = result_health_information_reporting_date
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_travel_information_reporting_full_name(){
		this.$get("~/api/travel_information_reporting/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_travel_information_reporting_full_name = json.result.list;
			result_travel_information_reporting_full_name.map(o => o.title = o['full_name'])
	  			this.result_travel_information_reporting_full_name = result_travel_information_reporting_full_name
		 	}
		});
	},
	/**
	 * 获取date
	 */
	get_travel_information_reporting_date(){
		this.$get("~/api/travel_information_reporting/get_list?like=0", { page: 1, size: 10, "date": this.query.word }, (json) => {
		  if (json.result) {
			var result_travel_information_reporting_date = json.result.list;
			result_travel_information_reporting_date.map(o => o.title = o['date'])
	  			this.result_travel_information_reporting_date = result_travel_information_reporting_date
		 	}
		});
	},
	/**
	 * 获取date
	 */
	get_online_reporting_date(){
		this.$get("~/api/online_reporting/get_list?like=0", { page: 1, size: 10, "date": this.query.word }, (json) => {
		  if (json.result) {
			var result_online_reporting_date = json.result.list;
			result_online_reporting_date.map(o => o.title = o['date'])
	  			this.result_online_reporting_date = result_online_reporting_date
		 	}
		});
	},
	/**
	 * 获取message_type
	 */
	get_online_message_message_type(){
		this.$get("~/api/online_message/get_list?like=0", { page: 1, size: 10, "message_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_online_message_message_type = json.result.list;
			result_online_message_message_type.map(o => o.title = o['message_type'])
	  			this.result_online_message_message_type = result_online_message_message_type
		 	}
		});
	},
	/**
	 * 获取message_date
	 */
	get_online_message_message_date(){
		this.$get("~/api/online_message/get_list?like=0", { page: 1, size: 10, "message_date": this.query.word }, (json) => {
		  if (json.result) {
			var result_online_message_message_date = json.result.list;
			result_online_message_message_date.map(o => o.title = o['message_date'])
	  			this.result_online_message_message_date = result_online_message_message_date
		 	}
		});
	},
	/**
	 * 获取date
	 */
	get_user_status_information_date(){
		this.$get("~/api/user_status_information/get_list?like=0", { page: 1, size: 10, "date": this.query.word }, (json) => {
		  if (json.result) {
			var result_user_status_information_date = json.result.list;
			result_user_status_information_date.map(o => o.title = o['date'])
	  			this.result_user_status_information_date = result_user_status_information_date
		 	}
		});
	},
	/**
	 * 获取information_type
	 */
	get_information_reporting_information_type(){
		this.$get("~/api/information_reporting/get_list?like=0", { page: 1, size: 10, "information_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_information_reporting_information_type = json.result.list;
			result_information_reporting_information_type.map(o => o.title = o['information_type'])
	  			this.result_information_reporting_information_type = result_information_reporting_information_type
		 	}
		});
	},
	/**
	 * 获取date
	 */
	get_information_reporting_date(){
		this.$get("~/api/information_reporting/get_list?like=0", { page: 1, size: 10, "date": this.query.word }, (json) => {
		  if (json.result) {
			var result_information_reporting_date = json.result.list;
			result_information_reporting_date.map(o => o.title = o['date'])
	  			this.result_information_reporting_date = result_information_reporting_date
		 	}
		});
	},
	/**
	 * 获取region
	 */
	get_epidemic_data_region(){
		this.$get("~/api/epidemic_data/get_list?like=0", { page: 1, size: 10, "region": this.query.word }, (json) => {
		  if (json.result) {
			var result_epidemic_data_region = json.result.list;
			result_epidemic_data_region.map(o => o.title = o['region'])
	  			this.result_epidemic_data_region = result_epidemic_data_region
		 	}
		});
	},
	/**
	 * 获取date
	 */
	get_epidemic_data_date(){
		this.$get("~/api/epidemic_data/get_list?like=0", { page: 1, size: 10, "date": this.query.word }, (json) => {
		  if (json.result) {
			var result_epidemic_data_date = json.result.list;
			result_epidemic_data_date.map(o => o.title = o['date'])
	  			this.result_epidemic_data_date = result_epidemic_data_date
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
		this.get_ordinary_users_full_name();
		this.get_health_information_reporting_full_name();
		this.get_health_information_reporting_date();
		this.get_travel_information_reporting_full_name();
		this.get_travel_information_reporting_date();
		this.get_online_reporting_date();
		this.get_online_message_message_type();
		this.get_online_message_message_date();
		this.get_user_status_information_date();
		this.get_information_reporting_information_type();
		this.get_information_reporting_date();
		this.get_epidemic_data_region();
		this.get_epidemic_data_date();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_ordinary_users_full_name();
	  this.get_health_information_reporting_full_name();
	  this.get_health_information_reporting_date();
	  this.get_travel_information_reporting_full_name();
	  this.get_travel_information_reporting_date();
	  this.get_online_reporting_date();
	  this.get_online_message_message_type();
	  this.get_online_message_message_date();
	  this.get_user_status_information_date();
	  this.get_information_reporting_information_type();
	  this.get_information_reporting_date();
	  this.get_epidemic_data_region();
	  this.get_epidemic_data_date();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
