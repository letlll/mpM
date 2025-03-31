<template>
	<div>
		<div class="register-container">
			<el-form v-if="pageFlag=='register'" ref="ruleForm" class="rgs-form animate__animated animate__backInDown" :model="ruleForm" :rules="rules">
				<div class="rgs-form2">
					<div class="title">基于微信小程序的爱心捐赠及追踪平台的设计与实现</div>
					<el-form-item class="list-item" v-if="tableName=='zhiyuanzhe'">
						<div class="lable" :class="changeRules('zhiyuanzhanghao')?'required':''">志愿账号：</div>
						<el-input  v-model="ruleForm.zhiyuanzhanghao"  autocomplete="off" placeholder="志愿账号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='zhiyuanzhe'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='zhiyuanzhe'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='zhiyuanzhe'">
						<div class="lable" :class="changeRules('zhiyuanzhe')?'required':''">志愿者：</div>
						<el-input  v-model="ruleForm.zhiyuanzhe"  autocomplete="off" placeholder="志愿者"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='zhiyuanzhe'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="ruleForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in zhiyuanzhexingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='zhiyuanzhe'">
						<div class="lable" :class="changeRules('nianling')?'required':''">年龄：</div>
						<el-input  v-model.number="ruleForm.nianling"  autocomplete="off" placeholder="年龄"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='zhiyuanzhe'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="zhiyuanzhetouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='zhiyuanzhe'">
						<div class="lable" :class="changeRules('zhiyuanzheshouji')?'required':''">志愿者手机：</div>
						<el-input  v-model="ruleForm.zhiyuanzheshouji"  autocomplete="off" placeholder="志愿者手机"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='juanzengzhe'">
						<div class="lable" :class="changeRules('juanzengzhanghao')?'required':''">捐赠账号：</div>
						<el-input  v-model="ruleForm.juanzengzhanghao"  autocomplete="off" placeholder="捐赠账号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='juanzengzhe'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='juanzengzhe'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='juanzengzhe'">
						<div class="lable" :class="changeRules('juanzengxingming')?'required':''">捐赠姓名：</div>
						<el-input  v-model="ruleForm.juanzengxingming"  autocomplete="off" placeholder="捐赠姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='juanzengzhe'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="ruleForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in juanzengzhexingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='juanzengzhe'">
						<div class="lable" :class="changeRules('nianling')?'required':''">年龄：</div>
						<el-input  v-model.number="ruleForm.nianling"  autocomplete="off" placeholder="年龄"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='juanzengzhe'">
						<div class="lable" :class="changeRules('juanzengshouji')?'required':''">捐赠手机：</div>
						<el-input  v-model="ruleForm.juanzengshouji"  autocomplete="off" placeholder="捐赠手机"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='juanzengzhe'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="juanzengzhetouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='shouzhuzhe'">
						<div class="lable" :class="changeRules('shouzhuzhanghao')?'required':''">受助账号：</div>
						<el-input  v-model="ruleForm.shouzhuzhanghao"  autocomplete="off" placeholder="受助账号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='shouzhuzhe'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='shouzhuzhe'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='shouzhuzhe'">
						<div class="lable" :class="changeRules('shouzhuren')?'required':''">受助人：</div>
						<el-input  v-model="ruleForm.shouzhuren"  autocomplete="off" placeholder="受助人"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='shouzhuzhe'">
						<div class="lable" :class="changeRules('lianxifangshi')?'required':''">联系方式：</div>
						<el-input  v-model="ruleForm.lianxifangshi"  autocomplete="off" placeholder="联系方式"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='shouzhuzhe'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="shouzhuzhetouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='shouzhuzhe'">
						<div class="lable" :class="changeRules('fenlei')?'required':''">分类：</div>
						<el-select v-model="ruleForm.fenlei" placeholder="请选择分类" >
							<el-option
								v-for="(item,index) in shouzhuzhefenleiOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<button type="button" class="r-btn" @click="login()">注册</button>
						</div>
						<div class="register-btn2">
							<div class="r-login" @click="close()">已有账号，直接登录</div>
						</div>
					</div>
				</div>
				<div class="idea-box1"></div>
			</el-form>
		</div>
	</div>
</template>

<script>
	import 'animate.css'
export default {
	data() {
		return {
			ruleForm: {
			},
			forgetForm: {},
            pageFlag : '',
			tableName:"",
			rules: {},
            zhiyuanzhexingbieOptions: [],
            juanzengzhexingbieOptions: [],
            shouzhuzhefenleiOptions: [],
		};
	},
	mounted(){
		this.pageFlag = this.$route.query.pageFlag
		if(this.$route.query.pageFlag=='register'){
			
			let table = this.$storage.get("loginTable");
			this.tableName = table;
			if(this.tableName=='zhiyuanzhe'){
				this.ruleForm = {
					zhiyuanzhanghao: '',
					mima: '',
					zhiyuanzhe: '',
					xingbie: '',
					nianling: '',
					touxiang: '',
					zhiyuanzheshouji: '',
				}
			}
			if(this.tableName=='juanzengzhe'){
				this.ruleForm = {
					juanzengzhanghao: '',
					mima: '',
					juanzengxingming: '',
					xingbie: '',
					nianling: '',
					juanzengshouji: '',
					touxiang: '',
				}
			}
			if(this.tableName=='shouzhuzhe'){
				this.ruleForm = {
					shouzhuzhanghao: '',
					mima: '',
					shouzhuren: '',
					lianxifangshi: '',
					touxiang: '',
					fenlei: '',
				}
			}
			if ('zhiyuanzhe' == this.tableName) {
				this.rules.zhiyuanzhanghao = [{ required: true, message: '请输入志愿账号', trigger: 'blur' }]
			}
			if ('zhiyuanzhe' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('juanzengzhe' == this.tableName) {
				this.rules.juanzengzhanghao = [{ required: true, message: '请输入捐赠账号', trigger: 'blur' }]
			}
			if ('juanzengzhe' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('shouzhuzhe' == this.tableName) {
				this.rules.shouzhuzhanghao = [{ required: true, message: '请输入受助账号', trigger: 'blur' }]
			}
			if ('shouzhuzhe' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('shouzhuzhe' == this.tableName) {
				this.rules.shouzhuren = [{ required: true, message: '请输入受助人', trigger: 'blur' }]
			}
			if ('shouzhuzhe' == this.tableName) {
				this.rules.fenlei = [{ required: true, message: '请输入分类', trigger: 'blur' }]
			}
			this.zhiyuanzhexingbieOptions = "男,女".split(',')
			this.juanzengzhexingbieOptions = "男,女".split(',')
			this.shouzhuzhefenleiOptions = "个人,组织".split(',')
		}
	},
	created() {
	},
	destroyed() {
		  	},
	methods: {
		changeRules(name){
			if(this.rules[name]){
				return true
			}
			return false
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
		close(){
			this.$router.push({ path: "/login" });
		},
        zhiyuanzhetouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },
        juanzengzhetouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },
        shouzhuzhetouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },

        // 多级联动参数


		// 注册
		login() {
			var url=this.tableName+"/register";
			if((!this.ruleForm.zhiyuanzhanghao) && `zhiyuanzhe` == this.tableName){
				this.$message.error(`志愿账号不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `zhiyuanzhe` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `zhiyuanzhe` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if(`zhiyuanzhe` == this.tableName && this.ruleForm.nianling &&(!this.$validate.isIntNumer(this.ruleForm.nianling))){
				this.$message.error(`年龄应输入整数`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			if(`zhiyuanzhe` == this.tableName && this.ruleForm.zhiyuanzheshouji &&(!this.$validate.isMobile(this.ruleForm.zhiyuanzheshouji))){
				this.$message.error(`志愿者手机应输入手机格式`);
				return
			}
			if((!this.ruleForm.juanzengzhanghao) && `juanzengzhe` == this.tableName){
				this.$message.error(`捐赠账号不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `juanzengzhe` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `juanzengzhe` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if(`juanzengzhe` == this.tableName && this.ruleForm.nianling &&(!this.$validate.isIntNumer(this.ruleForm.nianling))){
				this.$message.error(`年龄应输入整数`);
				return
			}
			if(`juanzengzhe` == this.tableName && this.ruleForm.juanzengshouji &&(!this.$validate.isMobile(this.ruleForm.juanzengshouji))){
				this.$message.error(`捐赠手机应输入手机格式`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			if((!this.ruleForm.shouzhuzhanghao) && `shouzhuzhe` == this.tableName){
				this.$message.error(`受助账号不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `shouzhuzhe` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `shouzhuzhe` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if((!this.ruleForm.shouzhuren) && `shouzhuzhe` == this.tableName){
				this.$message.error(`受助人不能为空`);
				return
			}
			if(`shouzhuzhe` == this.tableName && this.ruleForm.lianxifangshi &&(!this.$validate.isMobile(this.ruleForm.lianxifangshi))){
				this.$message.error(`联系方式应输入手机格式`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			if((!this.ruleForm.fenlei) && `shouzhuzhe` == this.tableName){
				this.$message.error(`分类不能为空`);
				return
			}
			this.$http({
				url: url,
				method: "post",
				data:this.ruleForm
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "注册成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							this.$router.replace({ path: "/login" });
						}
					});
				} else {
					this.$message.error(data.msg);
				}
			});
		}
	}
};
</script>

<style lang="scss" scoped>
.register-container {
	position: relative;
	background: url(http://codegen.caihongy.cn/20240916/dd67d94380174efcb9f62e87ba814b17.webp);
	background-repeat: no-repeat;
	background-size: cover;
	background: url(http://codegen.caihongy.cn/20240916/dd67d94380174efcb9f62e87ba814b17.webp);
	display: flex;
	width: 100%;
	min-height: 100vh;
	justify-content: center;
	align-items: center;
	background-position: center center;
	.rgs-form {
		.rgs-form2 {
		width: 100%;
		}
		padding: 0;
		margin:  20px auto;
		z-index: 1000;
		display: flex;
		flex-wrap: wrap;
		border-radius: 30px;
		box-shadow: none;
		flex-direction: column;
		background: #fff;
		width: 760px;
		align-items: flex-start;
		position: relative;
		height: auto;
		.title {
			border-radius: 30px 30px 0 0;
			padding: 0 40px;
			margin: 0 0 160px 0;
			color: #fff;
			background: #263746;
			font-weight: 600;
			display: flex;
			width: 100%;
			font-size: 28px;
			line-height: 90px;
			justify-content: center;
			align-items: center;
		}
		.list-item {
			padding: 0;
			margin: 0 auto 40px auto;
			width: 500px;
			border-color: #333;
			border-width: 0 0 1px 0;
			position: relative;
			border-style: solid;
			/deep/ .el-form-item__content {
			}
			.lable {
				color: #333333;
				display: inline-block;
				width: 100px;
				font-size: 14px;
				line-height: 40px;
				text-align: center;
			}
			.el-input {
				display: inline-block;
				width: 380px;
			}
			.el-input /deep/ .el-input__inner {
				border: none;
				border-radius: 4px;
				padding: 0 10px;
				color: #333;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			.el-input /deep/ .el-input__inner:focus {
				border: none;
				border-radius: 4px;
				padding: 0 10px;
				color: #333;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			.el-input-number {
				display: inline-block;
				width: 380px;
			}
			.el-input-number /deep/ .el-input__inner {
				text-align: center;
				border: none;
				border-radius: 4px;
				padding: 0 10px;
				color: #333;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			.el-input-number /deep/ .el-input__inner:focus {
				border: none;
				border-radius: 4px;
				padding: 0 10px;
				color: #333;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			.el-input-number /deep/ .el-input-number__decrease {
				display: none;
			}
			.el-input-number /deep/ .el-input-number__increase {
				display: none;
			}
			.el-select {
				display: inline-block;
				width: 380px;
			}
			.el-select /deep/ .el-input__inner {
				border: none;
				border-radius: 4px;
				padding: 0 10px;
				color: #333;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			.el-select /deep/ .el-input__inner:focus {
				border: none;
				border-radius: 4px;
				padding: 0 10px;
				color: #333;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			.el-date-editor {
				display: inline-block;
				width: 380px;
			}
			.el-date-editor /deep/ .el-input__inner {
				border: none;
				border-radius: 4px;
				padding: 0 10px 0 30px;
				color: #333;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			.el-date-editor /deep/ .el-input__inner:focus {
				border: none;
				border-radius: 4px;
				padding: 0 10px 0 30px;
				color: #333;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			.el-date-editor.el-input {
				width: 100%;
			}
			/deep/ .el-upload--picture-card {
				background: transparent;
				border: 0;
				border-radius: 0;
				width: auto;
				height: auto;
				line-height: initial;
				vertical-align: middle;
			}
			/deep/ .upload .upload-img {
				border: 1px solid #efeff7;
				cursor: pointer;
				border-radius: 0px;
				color: #999;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload-list .el-upload-list__item {
				border: 1px solid #efeff7;
				cursor: pointer;
				border-radius: 0px;
				color: #999;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload .el-icon-plus {
				border: 1px solid #efeff7;
				cursor: pointer;
				border-radius: 0px;
				color: #999;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload__tip {
				color: #666;
				font-size: 15px;
			}
			/deep/ .el-input__inner::placeholder {
				color: #999;
				font-size: 14px;
			}
			.required {
				position: relative;
			}
			.required::after{
				color: red;
				left: 0;
				position: inherit;
				content: "*";
				order: -1;
			}
			.editor {
				background: #fff;
				width: 100%;
				height: auto;
			}
			.editor>.avatar-uploader {
				line-height: 0;
				height: 0;
			}
		}
		.list-item.email {
			input {
				border: none;
				border-radius: 0;
				padding: 0 10px;
				margin: 0;
				outline: none;
				color: #333;
				background: #fff;
				flex: 1;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			input:focus {
				border: none;
				border-radius: 0;
				padding: 0 10px;
				margin: 0;
				outline: none;
				color: #333;
				background: #fff;
				flex: 1;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			input::placeholder {
				color: #999;
				font-size: 14px;
			}
			button {
				border: 0px solid #efeff7;
				cursor: pointer;
				border-radius: 0 4px 4px 0;
				padding: 0;
				margin: 1px 0 0;
				color: #333;
				background: #0d6efd20;
				width: 150px;
				font-size: 15px;
				height: 38px;
			}
			button:hover {
				opacity: 0.8;
			}
		}
		.register-btn {
			flex-direction: column;
			display: flex;
			width: 100%;
		}
		.register-btn1 {
			width: 100%;
			order: 2;
		}
		.register-btn2 {
			margin: 0 auto;
			width: 500px;
		}
		.r-btn {
			border: 0px solid rgba(0, 0, 0, 1);
			cursor: pointer;
			border-radius: 0px  0px  30px  30px;
			padding: 0;
			margin: 0;
			color: #fff;
			background: #3EA751;
			font-weight: 600;
			width: 100%;
			font-size: 24px;
			min-width: 68px;
			height: 90px;
		}
		.r-btn:hover {
			border: none;
			opacity: 0.8;
		}
		.r-login {
			cursor: pointer;
			padding: 0;
			color: #666;
			display: inline-block;
			text-decoration: underline;
			width: 100%;
			font-size: 15px;
			line-height: 40px;
			text-align: right;
		}
		.r-login:hover {
			opacity: 1;
		}
	}
	.idea-box1 {
		top: 120px;
		left: 335px;
		background: url("http://codegen.caihongy.cn/20240916/53083c2e86a24378876d7685620b8bb7.png") center center / cover no-repeat;
		font-weight: 600;
		width: 90px;
		font-size: 20px;
		position: absolute;
		height: 90px;
	}
}
	
	::-webkit-scrollbar {
	  display: none;
	}
</style>
