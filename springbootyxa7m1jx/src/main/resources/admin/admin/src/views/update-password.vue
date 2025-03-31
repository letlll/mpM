<template>
	<div :style='{"padding":"36px 22% 0 4%","margin":"0","background":"#fff"}'>
		<el-form
			:style='{"padding":"0px","borderColor":"#C4C4C4","borderStyle":"solid","flexWrap":"wrap","borderWidth":"1px 0 0 0","display":"flex"}'
			class="add-update-preview"
			ref="ruleForm"
			:rules="rules"
			:model="ruleForm"
			label-width="145px"
		>
			<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}' label="原密码" prop="password">
				<el-input type="password" v-model="ruleForm.password" ></el-input>
			</el-form-item>
			<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}' label="新密码" prop="newpassword">
				<el-input type="password" v-model="ruleForm.newpassword" ></el-input>
			</el-form-item>
			<el-form-item :style='{"padding":"0","margin":"0","flex":"1 1 50%"}' label="确认密码" prop="repassword">
				<el-input type="password" v-model="ruleForm.repassword" ></el-input>
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
export default {
	data() {
		return {
			dialogVisible: false,
			ruleForm: {},
			user: {},
			rules: {
				password: [
					{
						required: true,
						message: "密码不能为空",
						trigger: "blur"
					}
				],
				newpassword: [
					{
						required: true,
						message: "新密码不能为空",
						trigger: "blur"
					}
				],
				repassword: [
					{
						required: true,
						message: "确认密码不能为空",
						trigger: "blur"
					}
				]
			}
		};
	},
	mounted() {
		this.$http({
			url: `${this.$storage.get("sessionTable")}/session`,
			method: "get"
		}).then(({ data }) => {
			if (data && data.code === 0) {
				this.user = data.data;
			} else {
				this.$message.error(data.msg);
			}
		});
	},
	methods: {
		onLogout() {
			this.$storage.remove("Token");
			this.$router.replace({ name: "login" });
		},
		// 修改密码
		async onUpdateHandler() {
			this.$refs["ruleForm"].validate(async valid => {
				if (valid) {
					var password = "";
					if (this.user.mima) {
						password = this.user.mima;
					} else if (this.user.password) {
						password = this.user.password;
					}
					if(this.$storage.get("sessionTable")=='users'){
						if (this.ruleForm.password != password) {
							this.$message.error("原密码错误");
							return;
						}
						if (this.ruleForm.newpassword != this.ruleForm.repassword) {
							this.$message.error("两次密码输入不一致");
							return;
						}
						this.user.password = this.ruleForm.newpassword;
						this.user.mima = this.ruleForm.newpassword;
						this.$http({
							url: `${this.$storage.get("sessionTable")}/update`,
							method: "post",
							data: this.user
						}).then(({ data }) => {
							if (data && data.code === 0) {
								this.$message({
									message: "修改密码成功,下次登录系统生效",
									type: "success",
									duration: 1500,
									onClose: () => {
									}
								});
							} else {
								this.$message.error(data.msg);
							}
						});
						return false
					}
					if (this.$storage.get("sessionTable") == 'shouzhuzhe') {
					}
					var nowpassword = ''
					await this.$http({
						url: '/encrypt/des?text=' + this.ruleForm.password,
						method: 'get'
					}).then(res=>{
						if(res.data&&res.data.code==0){
							nowpassword = res.data.data
						}
					})
					if(nowpassword!=password){
						this.$message.error("原密码错误");
						return;
					}
					if (this.ruleForm.newpassword != this.ruleForm.repassword) {
						this.$message.error("两次密码输入不一致");
						return;
					}
					if (this.ruleForm.newpassword == this.ruleForm.password) {
						this.$message.error("新密码与原密码相同");
						return;
					}
					this.user.password = this.ruleForm.newpassword;
					this.user.mima = this.ruleForm.newpassword;
					this.$http({
						url: `${this.$storage.get("sessionTable")}/update`,
						method: "post",
						data: this.user
					}).then(({ data }) => {
						if (data && data.code === 0) {
							this.$message({
								message: "修改密码成功,下次登录系统生效",
								type: "success",
								duration: 1500,
								onClose: () => {
								}
							});
						} else {
							this.$message.error(data.msg);
						}
					});
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
</style>
