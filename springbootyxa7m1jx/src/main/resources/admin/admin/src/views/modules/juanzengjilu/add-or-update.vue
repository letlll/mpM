<template>
	<div class="addEdit-block">
		<el-form
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="145px"
		>
			<template >
				<el-form-item class="input" v-if="type!='info'"  label="项目名称" prop="xiangmumingcheng" >
					<el-input v-model="ruleForm.xiangmumingcheng" placeholder="项目名称" clearable  :readonly="ro.xiangmumingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="项目名称" prop="xiangmumingcheng" >
					<el-input v-model="ruleForm.xiangmumingcheng" placeholder="项目名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="项目分类" prop="xiangmufenlei" >
					<el-input v-model="ruleForm.xiangmufenlei" placeholder="项目分类" clearable  :readonly="ro.xiangmufenlei"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="项目分类" prop="xiangmufenlei" >
					<el-input v-model="ruleForm.xiangmufenlei" placeholder="项目分类" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.tupian" label="图片" prop="tupian" >
					<file-upload
						tip="点击上传图片"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.tupian?ruleForm.tupian:''"
						@change="tupianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.tupian" label="图片" prop="tupian" >
					<img v-if="ruleForm.tupian.substring(0,4)=='http'&&ruleForm.tupian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.tupian" width="100" height="100">
					<img v-else-if="ruleForm.tupian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.tupian.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.tupian.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="发布人" prop="faburen" >
					<el-input v-model="ruleForm.faburen" placeholder="发布人" clearable  :readonly="ro.faburen"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="发布人" prop="faburen" >
					<el-input v-model="ruleForm.faburen" placeholder="发布人" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="受助账号" prop="shouzhuzhanghao" >
					<el-input v-model="ruleForm.shouzhuzhanghao" placeholder="受助账号" clearable  :readonly="ro.shouzhuzhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="受助账号" prop="shouzhuzhanghao" >
					<el-input v-model="ruleForm.shouzhuzhanghao" placeholder="受助账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="受助人" prop="shouzhuren" >
					<el-input v-model="ruleForm.shouzhuren" placeholder="受助人" clearable  :readonly="ro.shouzhuren"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="受助人" prop="shouzhuren" >
					<el-input v-model="ruleForm.shouzhuren" placeholder="受助人" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="联系方式" prop="lianxifangshi" >
					<el-input v-model="ruleForm.lianxifangshi" placeholder="联系方式" clearable  :readonly="ro.lianxifangshi"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="联系方式" prop="lianxifangshi" >
					<el-input v-model="ruleForm.lianxifangshi" placeholder="联系方式" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="捐赠账号" prop="juanzengzhanghao" >
					<el-input v-model="ruleForm.juanzengzhanghao" placeholder="捐赠账号" clearable  :readonly="ro.juanzengzhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="捐赠账号" prop="juanzengzhanghao" >
					<el-input v-model="ruleForm.juanzengzhanghao" placeholder="捐赠账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="捐赠姓名" prop="juanzengxingming" >
					<el-input v-model="ruleForm.juanzengxingming" placeholder="捐赠姓名" clearable  :readonly="ro.juanzengxingming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="捐赠姓名" prop="juanzengxingming" >
					<el-input v-model="ruleForm.juanzengxingming" placeholder="捐赠姓名" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="捐赠手机" prop="juanzengshouji" >
					<el-input v-model="ruleForm.juanzengshouji" placeholder="捐赠手机" clearable  :readonly="ro.juanzengshouji"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="捐赠手机" prop="juanzengshouji" >
					<el-input v-model="ruleForm.juanzengshouji" placeholder="捐赠手机" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="捐赠金额" prop="leijizijin" >
					<el-input-number v-model="ruleForm.leijizijin" placeholder="捐赠金额" :disabled="ro.leijizijin"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="捐赠金额" prop="leijizijin" >
					<el-input v-model="ruleForm.leijizijin" placeholder="捐赠金额" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="捐赠方式" prop="juanzengfangshi" >
					<el-input v-model="ruleForm.juanzengfangshi" placeholder="捐赠方式" clearable  :readonly="ro.juanzengfangshi"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="捐赠方式" prop="juanzengfangshi" >
					<el-input v-model="ruleForm.juanzengfangshi" placeholder="捐赠方式" readonly></el-input>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="捐赠时间" prop="juanzengshijian" >
					<el-date-picker
						format="yyyy 年 MM 月 dd 日"
						value-format="yyyy-MM-dd"
						v-model="ruleForm.juanzengshijian" 
						type="date"
						:readonly="ro.juanzengshijian"
						placeholder="捐赠时间"
					></el-date-picker> 
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.juanzengshijian" label="捐赠时间" prop="juanzengshijian" >
					<el-input v-model="ruleForm.juanzengshijian" placeholder="捐赠时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="捐赠备注" prop="juanzengbeizhu" >
					<el-input v-model="ruleForm.juanzengbeizhu" placeholder="捐赠备注" clearable  :readonly="ro.juanzengbeizhu"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="捐赠备注" prop="juanzengbeizhu" >
					<el-input v-model="ruleForm.juanzengbeizhu" placeholder="捐赠备注" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-xihuan"></span>
					提交
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					取消
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					返回
				</el-button>
			</el-form-item>
		</el-form>
    

	</div>
</template>
<script>
	import { 
		isNumber,
	} from "@/utils/validate";
	export default {
		data() {
			var validateNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isNumber(value)) {
					callback(new Error("请输入数字"));
				} else {
					callback();
				}
			};
			return {
				id: '',
				type: '',
			
			
				ro:{
					xiangmumingcheng : false,
					xiangmufenlei : false,
					tupian : false,
					faburen : false,
					shouzhuzhanghao : false,
					shouzhuren : false,
					lianxifangshi : false,
					juanzengzhanghao : false,
					juanzengxingming : false,
					juanzengshouji : false,
					leijizijin : false,
					juanzengfangshi : false,
					juanzengshijian : false,
					juanzengbeizhu : false,
				},
			
				ruleForm: {
					xiangmumingcheng: '',
					xiangmufenlei: '',
					tupian: '',
					faburen: '',
					shouzhuzhanghao: '',
					shouzhuren: '',
					lianxifangshi: '',
					juanzengzhanghao: '',
					juanzengxingming: '',
					juanzengshouji: '',
					leijizijin: '',
					juanzengfangshi: '银行转账',
					juanzengshijian: '',
					juanzengbeizhu: '',
				},

				rules: {
					xiangmumingcheng: [
					],
					xiangmufenlei: [
					],
					tupian: [
					],
					faburen: [
					],
					shouzhuzhanghao: [
					],
					shouzhuren: [
					],
					lianxifangshi: [
					],
					juanzengzhanghao: [
					],
					juanzengxingming: [
					],
					juanzengshouji: [
					],
					leijizijin: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					juanzengfangshi: [
					],
					juanzengshijian: [
					],
					juanzengbeizhu: [
					],
				},
			};
		},
		props: ["parent"],
		computed: {



		},
		components: {
		},
		created() {
			this.ruleForm.juanzengshijian = this.getCurDate()
		},
		methods: {
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(id,type) {
				if (id) {
					this.id = id;
					this.type = type;
				}
				if(this.type=='info'||this.type=='else'||this.type=='msg'){
					this.info(id);
				}else if(this.type=='logistics'){
					for(let x in this.ro) {
						this.ro[x] = true
					}
					this.logistics=false;
					this.info(id);
				}else if(this.type=='cross'){
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o=='xiangmumingcheng'){
							this.ruleForm.xiangmumingcheng = obj[o];
							this.ro.xiangmumingcheng = true;
							continue;
						}
						if(o=='xiangmufenlei'){
							this.ruleForm.xiangmufenlei = obj[o];
							this.ro.xiangmufenlei = true;
							continue;
						}
						if(o=='tupian'){
							this.ruleForm.tupian = obj[o];
							this.ro.tupian = true;
							continue;
						}
						if(o=='faburen'){
							this.ruleForm.faburen = obj[o];
							this.ro.faburen = true;
							continue;
						}
						if(o=='shouzhuzhanghao'){
							this.ruleForm.shouzhuzhanghao = obj[o];
							this.ro.shouzhuzhanghao = true;
							continue;
						}
						if(o=='shouzhuren'){
							this.ruleForm.shouzhuren = obj[o];
							this.ro.shouzhuren = true;
							continue;
						}
						if(o=='lianxifangshi'){
							this.ruleForm.lianxifangshi = obj[o];
							this.ro.lianxifangshi = true;
							continue;
						}
						if(o=='juanzengzhanghao'){
							this.ruleForm.juanzengzhanghao = obj[o];
							this.ro.juanzengzhanghao = true;
							continue;
						}
						if(o=='juanzengxingming'){
							this.ruleForm.juanzengxingming = obj[o];
							this.ro.juanzengxingming = true;
							continue;
						}
						if(o=='juanzengshouji'){
							this.ruleForm.juanzengshouji = obj[o];
							this.ro.juanzengshouji = true;
							continue;
						}
						if(o=='leijizijin'){
							this.ruleForm.leijizijin = obj[o];
							this.ro.leijizijin = true;
							continue;
						}
						if(o=='juanzengfangshi'){
							this.ruleForm.juanzengfangshi = obj[o];
							this.ro.juanzengfangshi = true;
							continue;
						}
						if(o=='juanzengshijian'){
							this.ruleForm.juanzengshijian = obj[o];
							this.ro.juanzengshijian = true;
							continue;
						}
						if(o=='juanzengbeizhu'){
							this.ruleForm.juanzengbeizhu = obj[o];
							this.ro.juanzengbeizhu = true;
							continue;
						}
					}
					this.ruleForm.leijizijin = 0
					this.ro.leijizijin = false;
					this.ruleForm.juanzengfangshi = '银行转账'; 				}
				// 获取用户信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(((json.juanzengzhanghao!=''&&json.juanzengzhanghao) || json.juanzengzhanghao==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.juanzengzhanghao = json.juanzengzhanghao
							this.ro.juanzengzhanghao = true;
						}
						if(((json.juanzengxingming!=''&&json.juanzengxingming) || json.juanzengxingming==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.juanzengxingming = json.juanzengxingming
							this.ro.juanzengxingming = true;
						}
						if(((json.juanzengshouji!=''&&json.juanzengshouji) || json.juanzengshouji==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.juanzengshouji = json.juanzengshouji
							this.ro.juanzengshouji = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `juanzengjilu/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.tupian!=null) {
						this.ruleForm.tupian = this.ruleForm.tupian.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
					var table = this.$storage.getObj('crossTable');
					if(objcross!=null) {
						if(!this.ruleForm.leijizijin){
							this.$message.error("捐赠金额不能为空");
							return
						}
						objcross.leijizijin = parseFloat(objcross.leijizijin) + parseFloat(this.ruleForm.leijizijin)
											}
					if(!this.ruleForm.id) {
						delete this.ruleForm.userid
					}
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							if(this.type=='cross'){
								var statusColumnName = this.$storage.get('statusColumnName');
								var statusColumnValue = this.$storage.get('statusColumnValue');
								if(statusColumnName!='') {
									var obj = this.$storage.getObj('crossObj');
									if(statusColumnName && !statusColumnName.startsWith("[")) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = this.$storage.get('crossTable');
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: obj
										}).then(({ data }) => {});
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: objcross
										}).then(({ data }) => {});
									}
								}
							}
							
							await this.$http({
								url: `juanzengjilu/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.juanzengjiluCrossAddOrUpdateFlag = false;
											this.parent.search();
											this.parent.contentStyleChange();
										}
									});
									var table = this.$storage.get('crossTable');
									await this.$http({
										url: `${table}/update`,
										method: "post",
										data: objcross
									}).then(({ data }) => {});
								} else {
									this.$message.error(data.msg);
								}
							});
						}
					});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.parent.showFlag = true;
				this.parent.addOrUpdateFlag = false;
				this.parent.juanzengjiluCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			tupianUploadChange(fileUrls) {
				this.ruleForm.tupian = fileUrls;
			},
		}
	};
</script>
<style lang="scss" scoped>
	.addEdit-block {
		padding: 36px 22% 0 4%;
		margin: 0;
		background: #fff;
	}
	.add-update-preview {
		padding: 0px;
		display: flex;
		border-color: #C4C4C4;
		border-width: 1px 0 0 0;
		border-style: solid;
		flex-wrap: wrap;
	}
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	.add-update-preview /deep/ .el-form-item {
		padding: 0;
		margin: 0;
		flex: 1 1 50%;
	}
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
		padding: 0;
		margin: 0;
		color: #fff;
		font-weight: 600;
		display: flex;
		font-size: 16px;
		border-color: #C4C4C4;
		background-color: #3EA751;
		width: 145px;
		justify-content: center;
		border-width: 0 0 1px 0;
		align-items: center;
		border-style: solid;
		text-align: center;
		height: 70px;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: 145px;
	}
	.add-update-preview .el-form-item span.text {
		padding: 0 10px;
		color: #333;
		background: none;
		font-weight: 500;
		display: inline-block;
		width: 100%;
		font-size: 16px;
		border-color: #C4C4C4;
		border-width: 0 1px 1px 0;
		line-height: 70px;
		border-style: solid;
		height: 70px;
	}
	
	.add-update-preview .el-input {
		width: 100%;
	}
	.add-update-preview .el-input /deep/ .el-input__inner {
		border-radius: 0px;
		padding: 0 12px;
		color: #000;
		width: 100%;
		font-size: 16px;
		border-color: #C4C4C4;
		border-width: 0 1px 1px 0;
		border-style: solid;
		height: 70px;
	}
	.add-update-preview .el-input /deep/ .el-input__inner[readonly="readonly"] {
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #9B9B9B;
		background: none;
		width: 100%;
		font-size: 16px;
		border-color: #C4C4C4;
		border-width: 0 1px 1px 0;
		border-style: solid;
		height: 70px;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
		border-radius: 0px;
		padding: 0 12px;
		color: #000;
		width: 100%;
		font-size: 16px;
		border-color: #C4C4C4;
		border-width: 0 1px 1px 0;
		border-style: solid;
		height: 70px;
	}
	.add-update-preview .el-input-number /deep/ .is-disabled .el-input__inner {
		text-align: left;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #9B9B9B;
		background: none;
		width: 100%;
		font-size: 16px;
		border-color: #C4C4C4;
		border-width: 0 1px 1px 0;
		border-style: solid;
		height: 70px;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	.add-update-preview .el-select {
		width: 100%;
	}
	.add-update-preview .el-select /deep/ .el-input__inner {
		border-radius: 0px;
		padding: 0 12px;
		color: #000;
		width: 100%;
		font-size: 16px;
		border-color: #C4C4C4;
		border-width: 0 1px 1px 0;
		border-style: solid;
		height: 70px;
	}
	.add-update-preview .el-select /deep/ .is-disabled .el-input__inner {
		border: 0px solid #ccc;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #9B9B9B;
		background: none;
		width: 100%;
		font-size: 16px;
		border-color: #C4C4C4;
		border-width: 0 1px 1px 0;
		border-style: solid;
		height: 70px;
	}
	.add-update-preview .el-date-editor {
		width: 100%;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #000;
		width: 100%;
		font-size: 16px;
		border-color: #C4C4C4;
		border-width: 0 1px 1px 0;
		border-style: solid;
		height: 70px;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
		border: 0px solid #ccc;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #9B9B9B;
		background: none;
		width: 100%;
		font-size: 16px;
		border-color: #C4C4C4;
		border-width: 0 1px 1px 0;
		border-style: solid;
		height: 70px;
	}
	.add-update-preview .viewBtn {
		cursor: pointer;
		padding: 0 15px;
		margin: 0;
		color: #666;
		font-size: 15px;
		border-color: #C4C4C4;
		line-height: 70px;
		border-radius: 0px;
		background: #fff;
		width: 100%;
		border-width: 0 1px 1px 0;
		border-style: solid;
		height: 70px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .downBtn {
		border: 0px solid #ccc;
		cursor: pointer;
		padding: 0 15px;
		margin: 0;
		color: #666;
		font-size: 15px;
		border-color: #C4C4C4;
		line-height: 70px;
		border-radius: 0px;
		background: #fff;
		width: 100%;
		border-width: 0 1px 1px 0;
		border-style: solid;
		height: 70px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .unBtn {
		cursor: not-allowed;
		padding: 0 0px;
		margin: 0;
		color: #999;
		font-size: 16px;
		border-color: #C4C4C4;
		line-height: 70px;
		border-radius: 4px;
		outline: none;
		background: none;
		width: 100%;
		border-width: 0 1px 1px 0;
		border-style: solid;
		height: 70px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 34px;
		}
	}
	.add-update-preview .unBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
		border: 0px solid #ccc;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 70px;
		text-align: center;
		height: 70px;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
		border: 0px solid #ccc;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 70px;
		text-align: center;
		height: 70px;
	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
		border: 0px solid #ccc;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 70px;
		text-align: center;
		height: 70px;
	}
	.add-update-preview /deep/ .el-upload__tip {
		color: #666;
		display: none;
		font-size: 15px;
	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
		border-radius: 0px;
		padding: 0 12px;
		color: #000;
		width: 100%;
		font-size: 16px;
		border-color: #C4C4C4;
		border-width: 0 1px 1px 0;
		border-style: solid;
		height: 70px;
	}
	.add-update-preview .el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
				border: 0;
				cursor: not-allowed;
				padding: 12px;
				color: #666;
				font-size: 14px;
				border-color: #C4C4C4;
				border-radius: 0px;
				background: none;
				width: 100%;
				border-width: 0 1px 1px 0;
				border-style: solid;
				min-width: 400px;
				height: 70px;
			}
	.add-update-preview .el-form-item.btn {
		padding: 0;
		margin: 20px 0 0 -145px;
		flex: 1 1 100%;
		.btn1 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #3EA751;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn1:hover {
			opacity: 0.8;
		}
		.btn2 {
			border: 1px solid #3EA751;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #3EA751;
			background: transparent;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 34px;
			}
		}
		.btn2:hover {
			opacity: 0.8;
		}
		.btn3 {
			border: 1px solid #3EA751;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #3EA751;
			background: transparent;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn3:hover {
			opacity: 0.8;
		}
		.btn4 {
			border: 1px solid #3EA751;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #3EA751;
			background: transparent;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn4:hover {
			opacity: 0.8;
		}
		.btn5 {
			border: 1px solid #3EA751;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #3EA751;
			background: transparent;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn5:hover {
			opacity: 0.8;
		}
	}
</style>
