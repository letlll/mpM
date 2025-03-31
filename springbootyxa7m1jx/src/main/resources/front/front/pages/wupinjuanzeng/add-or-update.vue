<template>
	<view class="container">
		<form class="form">
			<view class="cu-form-group">
				<view class="title">物品名称</view>
				<input placeholder="请输入物品名称" v-model="ruleForm.wupinmingcheng"></input>
			</view>

			<view class="cu-form-group">
				<view class="title">物品类型</view>
				<picker @change="wupinleixingChange" :value="wupinleixingIndex" :range="wupinleixingOptions">
					<view class="picker">
						{{ruleForm.wupinleixing || '请选择物品类型'}}
					</view>
				</picker>
			</view>

			<view class="cu-form-group">
				<view class="title">物品数量</view>
				<input type="number" placeholder="请输入物品数量" v-model="ruleForm.shuliang" @input="shuliangInput"></input>
			</view>

			<view class="cu-form-group">
				<view class="title">捐赠时间</view>
				<picker mode="date" v-model="ruleForm.juanzengshijian" start="2020-01-01" end="2030-12-31" @change="timeChange">
					<view class="picker">
						{{ruleForm.juanzengshijian || '请选择捐赠时间'}}
					</view>
				</picker>
			</view>

			<view class="cu-form-group">
				<view class="title">物品图片</view>
				<view class="upload-img" @tap="tupianChoose">
					<image :src="ruleForm.tupian" mode="aspectFill" v-if="ruleForm.tupian"></image>
					<view class="upload-btn" v-else>
						<text class="cuIcon-cameraadd"></text>
					</view>
				</view>
			</view>

			<view class="btn-submit">
				<button class="cu-btn block bg-blue margin-tb-sm lg" @tap="onSubmit">提交</button>
			</view>
		</form>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				ruleForm: {
					wupinmingcheng: '',
					wupinleixing: '',
					shuliang: 1,
					juanzengzhanghao: '',
					juanzengxingming: '',
					tupian: '',
					juanzengshijian: '',
					zhuangtai: '待审核'
				},
				wupinleixingOptions: ['衣物', '书籍', '食品', '其他'],
				wupinleixingIndex: 0,
				user: {},
				today: new Date().toISOString().split('T')[0],
				id: null
			};
		},
		onLoad(options) {
			// 获取用户信息
			let userInfo = uni.getStorageSync('userInfo');
			if (userInfo) {
				this.ruleForm.juanzengzhanghao = userInfo.zhanghao;
				this.ruleForm.juanzengxingming = userInfo.xingming;
				this.user = userInfo;
			}
			
			// 如果是编辑模式
			if(options.id){
				this.id = options.id;
				this.getDetail();
			}
		},
		methods: {
			// 获取详情
			async getDetail() {
				let res = await this.$api.info('wupinjuanzeng', this.id);
				if(res.code == 0) {
					this.ruleForm = res.data;
					// 设置物品类型索引
					this.wupinleixingIndex = this.wupinleixingOptions.indexOf(this.ruleForm.wupinleixing);
					if(this.wupinleixingIndex == -1) {
						this.wupinleixingIndex = 0;
					}
				}
			},
			// 物品类型选择
			wupinleixingChange(e) {
				this.ruleForm.wupinleixing = this.wupinleixingOptions[e.detail.value];
				this.wupinleixingIndex = e.detail.value;
			},
			
			// 数量输入
			shuliangInput(e) {
				this.ruleForm.shuliang = parseInt(e.detail.value) || 1;
			},
			
			// 上传图片
			tupianChoose() {
				uni.chooseImage({
					count: 1,
					sizeType: ['original', 'compressed'],
					sourceType: ['album', 'camera'],
					success: (res) => {
						uni.uploadFile({
							url: uni.getStorageSync('baseUrl') + 'file/upload',
							filePath: res.tempFilePaths[0],
							name: 'file',
							success: (res) => {
								let data = JSON.parse(res.data);
								if (data.code == 0) {
									this.ruleForm.tupian = data.file;
								}
							}
						});
					}
				});
			},
			
			// 捐赠时间选择
			timeChange(e) {
				this.ruleForm.juanzengshijian = e.detail.value;
			},
			
			// 提交表单
			onSubmit() {
				if (!this.ruleForm.wupinmingcheng) {
					uni.showToast({
						title: '请输入物品名称',
						icon: 'none'
					});
					return;
				}
				if (!this.ruleForm.wupinleixing) {
					uni.showToast({
						title: '请选择物品类型',
						icon: 'none'
					});
					return;
				}
				if (!this.ruleForm.juanzengshijian) {
					uni.showToast({
						title: '请选择捐赠时间',
						icon: 'none'
					});
					return;
				}
				
				let url = 'wupinjuanzeng/save';
				if(this.id) {
					url = 'wupinjuanzeng/update';
				}
				
				uni.request({
					url: uni.getStorageSync('baseUrl') + url,
					method: 'POST',
					data: this.ruleForm,
					success: (res) => {
						if (res.data.code == 0) {
							uni.showToast({
								title: this.id ? '修改成功' : '提交成功',
								icon: 'success',
								duration: 2000,
								success: function() {
									setTimeout(function() {
										uni.navigateBack();
									}, 2000);
								}
							});
						} else {
							uni.showToast({
								title: res.data.msg,
								icon: 'none'
							});
						}
					}
				});
			}
		}
	}
</script>

<style lang="scss" scoped>
.container {
  padding: 20rpx;
}

.form {
  width: 100%;
}

.cu-form-group {
  background-color: #ffffff;
  padding: 1rpx 30rpx;
  display: flex;
  align-items: center;
  min-height: 100rpx;
  justify-content: space-between;
}

.cu-form-group .title {
  text-align: justify;
  padding-right: 30rpx;
  font-size: 30rpx;
  position: relative;
  height: 60rpx;
  line-height: 60rpx;
}

.cu-form-group input {
  flex: 1;
  font-size: 30rpx;
  color: #555;
  padding-right: 20rpx;
}

.cu-form-group .picker {
  flex: 1;
  padding-right: 40rpx;
  overflow: hidden;
  position: relative;
  font-size: 30rpx;
  color: #555;
}

.upload-img {
  width: 200rpx;
  height: 200rpx;
  position: relative;
  margin-right: 20rpx;
}

.upload-img image {
  width: 100%;
  height: 100%;
  border-radius: 8rpx;
}

.upload-btn {
  width: 100%;
  height: 100%;
  border: 1px dashed #ddd;
  border-radius: 8rpx;
  display: flex;
  align-items: center;
  justify-content: center;
}

.upload-btn text {
  font-size: 60rpx;
  color: #888;
}

.btn-submit {
  padding: 40rpx;
}

.btn-submit button {
  width: 100%;
  height: 80rpx;
  line-height: 80rpx;
  font-size: 32rpx;
  border-radius: 40rpx;
}
</style> 