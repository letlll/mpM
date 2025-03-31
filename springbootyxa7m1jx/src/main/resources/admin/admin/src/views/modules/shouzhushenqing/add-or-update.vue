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
				<el-form-item class="date" v-if="type!='info'" label="申请时间" prop="shenqingshijian" >
					<el-date-picker
						format="yyyy 年 MM 月 dd 日"
						value-format="yyyy-MM-dd"
						v-model="ruleForm.shenqingshijian" 
						type="date"
						:readonly="ro.shenqingshijian"
						placeholder="申请时间"
					></el-date-picker> 
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.shenqingshijian" label="申请时间" prop="shenqingshijian" >
					<el-input v-model="ruleForm.shenqingshijian" placeholder="申请时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="申请标题" prop="shenqingbiaoti" >
					<el-input v-model="ruleForm.shenqingbiaoti" placeholder="申请标题" clearable  :readonly="ro.shenqingbiaoti"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="申请标题" prop="shenqingbiaoti" >
					<el-input v-model="ruleForm.shenqingbiaoti" placeholder="申请标题" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info'&& !ro.shenqingwenjian" label="申请文件" prop="shenqingwenjian" >
					<file-upload
						tip="点击上传申请文件"
						action="file/upload"
						:limit="1"
						:type="3"
						:multiple="true"
						:fileUrls="ruleForm.shenqingwenjian?ruleForm.shenqingwenjian:''"
						@change="shenqingwenjianUploadChange"
					></file-upload>
				</el-form-item>  
				<el-form-item v-else-if="ruleForm.shenqingwenjian" label="申请文件" prop="shenqingwenjian" >
					<el-button class="downBtn" type="text" size="small" @click="download($base.url+ruleForm.shenqingwenjian)">
						<span class="icon iconfont icon-xiazai6"></span>
						下载
					</el-button>
				</el-form-item>
				<el-form-item v-else-if="!ruleForm.shenqingwenjian" label="申请文件" prop="shenqingwenjian" >
					<el-button class="unBtn" type="text" size="small">
						<span class="icon iconfont icon-xihuan"></span>
						暂无
					</el-button>
				</el-form-item>
			</template>
			<el-form-item v-if="type!='info'"  label="申请内容" prop="shenqingneirong" >
				<editor 
					style="min-width: 200px; max-width: 600px;"
					v-model="ruleForm.shenqingneirong" 
					class="editor"
					myQuillEditor="shenqingneirong"
					action="file/upload">
				</editor>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.shenqingneirong" label="申请内容" prop="shenqingneirong" >
				<span class="text ql-snow ql-editor" v-html="ruleForm.shenqingneirong"></span>
			</el-form-item>
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
	export default {
		data() {
			return {
				id: '',
				type: '',
			
			
				ro:{
					shouzhuzhanghao : false,
					shouzhuren : false,
					lianxifangshi : false,
					shenqingshijian : false,
					shenqingbiaoti : false,
					shenqingneirong : false,
					shenqingwenjian : false,
					sfsh : false,
					shhf : false,
				},
			
				ruleForm: {
					shouzhuzhanghao: '',
					shouzhuren: '',
					lianxifangshi: '',
					shenqingshijian: '',
					shenqingbiaoti: '',
					shenqingneirong: '',
					shenqingwenjian: '',
					shhf: '',
				},

				rules: {
					shouzhuzhanghao: [
					],
					shouzhuren: [
					],
					lianxifangshi: [
					],
					shenqingshijian: [
					],
					shenqingbiaoti: [
						{ required: true, message: '申请标题不能为空', trigger: 'blur' },
					],
					shenqingneirong: [
					],
					shenqingwenjian: [
					],
					sfsh: [
					],
					shhf: [
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
			this.ruleForm.shenqingshijian = this.getCurDate()
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
						if(o=='shenqingshijian'){
							this.ruleForm.shenqingshijian = obj[o];
							this.ro.shenqingshijian = true;
							continue;
						}
						if(o=='shenqingbiaoti'){
							this.ruleForm.shenqingbiaoti = obj[o];
							this.ro.shenqingbiaoti = true;
							continue;
						}
						if(o=='shenqingneirong'){
							this.ruleForm.shenqingneirong = obj[o];
							this.ro.shenqingneirong = true;
							continue;
						}
						if(o=='shenqingwenjian'){
							this.ruleForm.shenqingwenjian = obj[o];
							this.ro.shenqingwenjian = true;
							continue;
						}
					}
				}
				// 获取用户信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(((json.shouzhuzhanghao!=''&&json.shouzhuzhanghao) || json.shouzhuzhanghao==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.shouzhuzhanghao = json.shouzhuzhanghao
							this.ro.shouzhuzhanghao = true;
						}
						if(((json.shouzhuren!=''&&json.shouzhuren) || json.shouzhuren==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.shouzhuren = json.shouzhuren
							this.ro.shouzhuren = true;
						}
						if(((json.lianxifangshi!=''&&json.lianxifangshi) || json.lianxifangshi==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.lianxifangshi = json.lianxifangshi
							this.ro.lianxifangshi = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `shouzhushenqing/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
						this.ruleForm.shenqingneirong = this.ruleForm.shenqingneirong.replace(reg,'../../../springbootyxa7m1jx/upload');
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.shenqingwenjian!=null) {
						this.ruleForm.shenqingwenjian = this.ruleForm.shenqingwenjian.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
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
									}
								}
							}
							
							await this.$http({
								url: `shouzhushenqing/${!this.ruleForm.id ? "save" : "update"}`,
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
											this.parent.shouzhushenqingCrossAddOrUpdateFlag = false;
											this.parent.search();
											this.parent.contentStyleChange();
										}
									});
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
				this.parent.shouzhushenqingCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			shenqingwenjianUploadChange(fileUrls) {
				this.ruleForm.shenqingwenjian = fileUrls;
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
