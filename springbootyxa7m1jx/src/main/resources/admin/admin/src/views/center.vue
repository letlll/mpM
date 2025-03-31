<template>
	<div :style='{"padding":"0","fontSize":"14px"}'>
		<el-form
			:style='{"padding":"0px","borderColor":"#C4C4C4","borderStyle":"solid","flexWrap":"wrap","borderWidth":"1px 0 0 0","display":"flex"}'
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			label-width="145px"
		>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}'   v-if="flag=='zhiyuanzhe'"  label="志愿账号" prop="zhiyuanzhanghao">
					<el-input v-model="ruleForm.zhiyuanzhanghao" readonly						placeholder="志愿账号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}'   v-if="flag=='zhiyuanzhe'"  label="志愿者" prop="zhiyuanzhe">
					<el-input v-model="ruleForm.zhiyuanzhe" 						placeholder="志愿者" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}' v-if="flag=='zhiyuanzhe'"  label="性别" prop="xingbie">
					<el-select v-model="ruleForm.xingbie"  placeholder="请选择性别">
						<el-option
							v-for="(item,index) in zhiyuanzhexingbieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}'   v-if="flag=='zhiyuanzhe'"  label="年龄" prop="nianling">
					<el-input v-model="ruleForm.nianling" 						placeholder="年龄" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}' v-if="flag=='zhiyuanzhe'" label="头像" prop="touxiang">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
						@change="zhiyuanzhetouxiangUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}'   v-if="flag=='zhiyuanzhe'"  label="志愿者手机" prop="zhiyuanzheshouji">
					<el-input v-model="ruleForm.zhiyuanzheshouji" 						placeholder="志愿者手机" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}'   v-if="flag=='juanzengzhe'"  label="捐赠账号" prop="juanzengzhanghao">
					<el-input v-model="ruleForm.juanzengzhanghao" readonly						placeholder="捐赠账号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}'   v-if="flag=='juanzengzhe'"  label="捐赠姓名" prop="juanzengxingming">
					<el-input v-model="ruleForm.juanzengxingming" 						placeholder="捐赠姓名" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}' v-if="flag=='juanzengzhe'"  label="性别" prop="xingbie">
					<el-select v-model="ruleForm.xingbie"  placeholder="请选择性别">
						<el-option
							v-for="(item,index) in juanzengzhexingbieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}'   v-if="flag=='juanzengzhe'"  label="年龄" prop="nianling">
					<el-input v-model="ruleForm.nianling" 						placeholder="年龄" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}'   v-if="flag=='juanzengzhe'"  label="捐赠手机" prop="juanzengshouji">
					<el-input v-model="ruleForm.juanzengshouji" 						placeholder="捐赠手机" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}' v-if="flag=='juanzengzhe'" label="头像" prop="touxiang">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
						@change="juanzengzhetouxiangUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}'   v-if="flag=='shouzhuzhe'"  label="受助账号" prop="shouzhuzhanghao">
					<el-input v-model="ruleForm.shouzhuzhanghao" readonly						placeholder="受助账号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}'   v-if="flag=='shouzhuzhe'"  label="受助人" prop="shouzhuren">
					<el-input v-model="ruleForm.shouzhuren" 						placeholder="受助人" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}'   v-if="flag=='shouzhuzhe'"  label="联系方式" prop="lianxifangshi">
					<el-input v-model="ruleForm.lianxifangshi" 						placeholder="联系方式" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}' v-if="flag=='shouzhuzhe'" label="头像" prop="touxiang">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
						@change="shouzhuzhetouxiangUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}' v-if="flag=='shouzhuzhe'"  label="分类" prop="fenlei">
					<el-select v-model="ruleForm.fenlei"  placeholder="请选择分类">
						<el-option
							v-for="(item,index) in shouzhuzhefenleiOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}' v-if="flag=='users'" label="用户名" prop="username">
					<el-input v-model="ruleForm.username" placeholder="用户名"></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}' v-if="flag=='users'" label="头像" prop="image">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="1"
						:multiple="false"
						:fileUrls="ruleForm.image?ruleForm.image:''"
						@change="usersimageUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"padding":"0","margin":"20px 0 0 -145px","flex":"1 1 100%"}'>
					<el-button class="btn3" :style='{"border":"1px solid #3EA751","cursor":"pointer","padding":"0 10px","margin":"0 10px 0 0","color":"#3EA751","borderRadius":"4px","background":"transparent","width":"auto","fontSize":"16px","minWidth":"110px","height":"40px"}' type="primary" @click="onUpdateHandler">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
						提交
					</el-button>
				</el-form-item>
		</el-form>
	</div>
</template>
<script>
// 校验引入
import { 
	isIntNumer,
	isMobile,
} from "@/utils/validate";

export default {
	data() {
		return {
			ruleForm: {},
			flag: '',
			usersFlag: false,
			zhiyuanzhexingbieOptions: [],
			juanzengzhexingbieOptions: [],
			shouzhuzhefenleiOptions: [],
		};
	},
	mounted() {
		var table = this.$storage.get("sessionTable");
		this.flag = table;
		this.$http({
			url: `${this.$storage.get("sessionTable")}/session`,
			method: "get"
		}).then(({ data }) => {
			if (data && data.code === 0) {
				this.ruleForm = data.data;
			} else {
				this.$message.error(data.msg);
			}
		});
		this.zhiyuanzhexingbieOptions = "男,女".split(',')
		this.juanzengzhexingbieOptions = "男,女".split(',')
		this.shouzhuzhefenleiOptions = "个人,组织".split(',')
	},
	methods: {
		zhiyuanzhetouxiangUploadChange(fileUrls) {
			this.ruleForm.touxiang = fileUrls;
		},
		juanzengzhetouxiangUploadChange(fileUrls) {
			this.ruleForm.touxiang = fileUrls;
		},
		shouzhuzhetouxiangUploadChange(fileUrls) {
			this.ruleForm.touxiang = fileUrls;
		},
		usersimageUploadChange(fileUrls) {
			this.ruleForm.image = fileUrls;
		},
		onUpdateHandler() {
			if((!this.ruleForm.zhiyuanzhanghao)&& 'zhiyuanzhe'==this.flag){
				this.$message.error('志愿账号不能为空');
				return
			}


			if((!this.ruleForm.mima)&& 'zhiyuanzhe'==this.flag){
				this.$message.error('密码不能为空');
				return
			}







			if( 'zhiyuanzhe' ==this.flag && this.ruleForm.nianling&&(!isIntNumer(this.ruleForm.nianling))){
				this.$message.error(`年龄应输入整数`);
				return
			}


			if(this.ruleForm.touxiang!=null) {
				this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
			}


			if( 'zhiyuanzhe' ==this.flag && this.ruleForm.zhiyuanzheshouji&&(!isMobile(this.ruleForm.zhiyuanzheshouji))){
				this.$message.error(`志愿者手机应输入手机格式`);
				return
			}
			if((!this.ruleForm.juanzengzhanghao)&& 'juanzengzhe'==this.flag){
				this.$message.error('捐赠账号不能为空');
				return
			}


			if((!this.ruleForm.mima)&& 'juanzengzhe'==this.flag){
				this.$message.error('密码不能为空');
				return
			}







			if( 'juanzengzhe' ==this.flag && this.ruleForm.nianling&&(!isIntNumer(this.ruleForm.nianling))){
				this.$message.error(`年龄应输入整数`);
				return
			}


			if( 'juanzengzhe' ==this.flag && this.ruleForm.juanzengshouji&&(!isMobile(this.ruleForm.juanzengshouji))){
				this.$message.error(`捐赠手机应输入手机格式`);
				return
			}


			if(this.ruleForm.touxiang!=null) {
				this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
			}
			if((!this.ruleForm.shouzhuzhanghao)&& 'shouzhuzhe'==this.flag){
				this.$message.error('受助账号不能为空');
				return
			}


			if((!this.ruleForm.mima)&& 'shouzhuzhe'==this.flag){
				this.$message.error('密码不能为空');
				return
			}

			if((!this.ruleForm.shouzhuren)&& 'shouzhuzhe'==this.flag){
				this.$message.error('受助人不能为空');
				return
			}




			if( 'shouzhuzhe' ==this.flag && this.ruleForm.lianxifangshi&&(!isMobile(this.ruleForm.lianxifangshi))){
				this.$message.error(`联系方式应输入手机格式`);
				return
			}


			if(this.ruleForm.touxiang!=null) {
				this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
			}
			if((!this.ruleForm.fenlei)&& 'shouzhuzhe'==this.flag){
				this.$message.error('分类不能为空');
				return
			}


			if('users'==this.flag && this.ruleForm.username.trim().length<1) {
				this.$message.error(`用户名不能为空`);
				return	
			}
			if(this.flag=='users'){
				this.ruleForm.image = this.ruleForm.image.replace(new RegExp(this.$base.url,"g"),"")
			}
			this.$http({
				url: `${this.$storage.get("sessionTable")}/update`,
				method: "post",
				data: this.ruleForm
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "修改信息成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							if(this.flag=='users'){
								this.$storage.set('headportrait',this.ruleForm.image)
							}
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
	.el-date-editor.el-input {
		width: auto;
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
	
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
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
	
	.add-update-preview .btn3 {
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
			}
	
	.add-update-preview .btn3:hover {
				opacity: 0.8;
			}
	
	.editor>.avatar-uploader {
		line-height: 0;
		height: 0;
	}
</style>
