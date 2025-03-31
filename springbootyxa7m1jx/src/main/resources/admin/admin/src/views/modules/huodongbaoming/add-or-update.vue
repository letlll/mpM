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
				<el-form-item class="input" v-if="type!='info'"  label="活动标题" prop="huodongbiaoti" >
					<el-input v-model="ruleForm.huodongbiaoti" placeholder="活动标题" clearable  :readonly="ro.huodongbiaoti"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="活动标题" prop="huodongbiaoti" >
					<el-input v-model="ruleForm.huodongbiaoti" placeholder="活动标题" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="活动类型" prop="huodongleixing" >
					<el-input v-model="ruleForm.huodongleixing" placeholder="活动类型" clearable  :readonly="ro.huodongleixing"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="活动类型" prop="huodongleixing" >
					<el-input v-model="ruleForm.huodongleixing" placeholder="活动类型" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.fengmian" label="封面" prop="fengmian" >
					<file-upload
						tip="点击上传封面"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.fengmian?ruleForm.fengmian:''"
						@change="fengmianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.fengmian" label="封面" prop="fengmian" >
					<img v-if="ruleForm.fengmian.substring(0,4)=='http'&&ruleForm.fengmian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.fengmian" width="100" height="100">
					<img v-else-if="ruleForm.fengmian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.fengmian.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.fengmian.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="开始时间" prop="kaishishijian" >
					<el-input v-model="ruleForm.kaishishijian" placeholder="开始时间" clearable  :readonly="ro.kaishishijian"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="开始时间" prop="kaishishijian" >
					<el-input v-model="ruleForm.kaishishijian" placeholder="开始时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="活动地址" prop="huodongdizhi" >
					<el-input v-model="ruleForm.huodongdizhi" placeholder="活动地址" clearable  :readonly="ro.huodongdizhi"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="活动地址" prop="huodongdizhi" >
					<el-input v-model="ruleForm.huodongdizhi" placeholder="活动地址" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="举办方" prop="jubanfang" >
					<el-input v-model="ruleForm.jubanfang" placeholder="举办方" clearable  :readonly="ro.jubanfang"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="举办方" prop="jubanfang" >
					<el-input v-model="ruleForm.jubanfang" placeholder="举办方" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="志愿账号" prop="zhiyuanzhanghao" >
					<el-input v-model="ruleForm.zhiyuanzhanghao" placeholder="志愿账号" clearable  :readonly="ro.zhiyuanzhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="志愿账号" prop="zhiyuanzhanghao" >
					<el-input v-model="ruleForm.zhiyuanzhanghao" placeholder="志愿账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="志愿者" prop="zhiyuanzhe" >
					<el-input v-model="ruleForm.zhiyuanzhe" placeholder="志愿者" clearable  :readonly="ro.zhiyuanzhe"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="志愿者" prop="zhiyuanzhe" >
					<el-input v-model="ruleForm.zhiyuanzhe" placeholder="志愿者" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="志愿者手机" prop="zhiyuanzheshouji" >
					<el-input v-model="ruleForm.zhiyuanzheshouji" placeholder="志愿者手机" clearable  :readonly="ro.zhiyuanzheshouji"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="志愿者手机" prop="zhiyuanzheshouji" >
					<el-input v-model="ruleForm.zhiyuanzheshouji" placeholder="志愿者手机" readonly></el-input>
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
	export default {
		data() {
			return {
				id: '',
				type: '',
			
			
				ro:{
					huodongbiaoti : false,
					huodongleixing : false,
					fengmian : false,
					kaishishijian : false,
					huodongdizhi : false,
					jubanfang : false,
					zhiyuanzhanghao : false,
					zhiyuanzhe : false,
					zhiyuanzheshouji : false,
				},
			
				ruleForm: {
					huodongbiaoti: '',
					huodongleixing: '',
					fengmian: '',
					kaishishijian: '',
					huodongdizhi: '',
					jubanfang: '',
					zhiyuanzhanghao: '',
					zhiyuanzhe: '',
					zhiyuanzheshouji: '',
				},

				rules: {
					huodongbiaoti: [
					],
					huodongleixing: [
					],
					fengmian: [
					],
					kaishishijian: [
					],
					huodongdizhi: [
					],
					jubanfang: [
					],
					zhiyuanzhanghao: [
					],
					zhiyuanzhe: [
					],
					zhiyuanzheshouji: [
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
						if(o=='huodongbiaoti'){
							this.ruleForm.huodongbiaoti = obj[o];
							this.ro.huodongbiaoti = true;
							continue;
						}
						if(o=='huodongleixing'){
							this.ruleForm.huodongleixing = obj[o];
							this.ro.huodongleixing = true;
							continue;
						}
						if(o=='fengmian'){
							this.ruleForm.fengmian = obj[o];
							this.ro.fengmian = true;
							continue;
						}
						if(o=='kaishishijian'){
							this.ruleForm.kaishishijian = obj[o];
							this.ro.kaishishijian = true;
							continue;
						}
						if(o=='huodongdizhi'){
							this.ruleForm.huodongdizhi = obj[o];
							this.ro.huodongdizhi = true;
							continue;
						}
						if(o=='jubanfang'){
							this.ruleForm.jubanfang = obj[o];
							this.ro.jubanfang = true;
							continue;
						}
						if(o=='zhiyuanzhanghao'){
							this.ruleForm.zhiyuanzhanghao = obj[o];
							this.ro.zhiyuanzhanghao = true;
							continue;
						}
						if(o=='zhiyuanzhe'){
							this.ruleForm.zhiyuanzhe = obj[o];
							this.ro.zhiyuanzhe = true;
							continue;
						}
						if(o=='zhiyuanzheshouji'){
							this.ruleForm.zhiyuanzheshouji = obj[o];
							this.ro.zhiyuanzheshouji = true;
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
						if(((json.zhiyuanzhanghao!=''&&json.zhiyuanzhanghao) || json.zhiyuanzhanghao==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.zhiyuanzhanghao = json.zhiyuanzhanghao
							this.ro.zhiyuanzhanghao = true;
						}
						if(((json.zhiyuanzhe!=''&&json.zhiyuanzhe) || json.zhiyuanzhe==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.zhiyuanzhe = json.zhiyuanzhe
							this.ro.zhiyuanzhe = true;
						}
						if(((json.zhiyuanzheshouji!=''&&json.zhiyuanzheshouji) || json.zhiyuanzheshouji==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.zhiyuanzheshouji = json.zhiyuanzheshouji
							this.ro.zhiyuanzheshouji = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `huodongbaoming/info/${id}`,
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
					if(this.ruleForm.fengmian!=null) {
						this.ruleForm.fengmian = this.ruleForm.fengmian.replace(new RegExp(this.$base.url,"g"),"");
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
								url: `huodongbaoming/${!this.ruleForm.id ? "save" : "update"}`,
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
											this.parent.huodongbaomingCrossAddOrUpdateFlag = false;
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
				this.parent.huodongbaomingCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			fengmianUploadChange(fileUrls) {
				this.ruleForm.fengmian = fileUrls;
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
