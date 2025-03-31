<template>
	<view class="content">
		<view class="container">
			<view class="detail-card">
				<view class="detail-header">
					<view class="detail-title">{{detail.wupinmingcheng}}</view>
					<view :class="['detail-status', getStatusClass(detail.zhuangtai)]">{{detail.zhuangtai}}</view>
				</view>
				
				<view class="detail-image" v-if="detail.tupian">
					<image :src="baseUrl + detail.tupian" mode="aspectFill"></image>
				</view>
				
				<view class="detail-info">
					<view class="info-item">
						<view class="info-label">物品类型</view>
						<view class="info-value">{{detail.wupinleixing}}</view>
					</view>
					<view class="info-item">
						<view class="info-label">捐赠数量</view>
						<view class="info-value">{{detail.shuliang}}</view>
					</view>
					<view class="info-item">
						<view class="info-label">捐赠账号</view>
						<view class="info-value">{{detail.juanzengzhanghao}}</view>
					</view>
					<view class="info-item">
						<view class="info-label">捐赠姓名</view>
						<view class="info-value">{{detail.juanzengxingming}}</view>
					</view>
					<view class="info-item">
						<view class="info-label">捐赠时间</view>
						<view class="info-value">{{detail.juanzengshijian}}</view>
					</view>
				</view>
				
				<view class="btn-group">
					<button class="btn-primary" v-if="detail.zhuangtai === '待审核'" @tap="onEditTap">编辑</button>
					<button class="btn-danger" v-if="detail.zhuangtai === '待审核'" @tap="onDeleteTap">取消捐赠</button>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				id: '',
				baseUrl: '',
				detail: {}
			}
		},
		onLoad(options) {
			this.baseUrl = this.$base.url;
			if (options.id) {
				this.id = options.id;
				this.getDetail();
			}
		},
		methods: {
			getStatusClass(status) {
				if(status === '通过') {
					return 'status-pass';
				} else if(status === '未通过') {
					return 'status-reject';
				} else {
					return 'status-pending';
				}
			},
			async getDetail() {
				let res = await this.$api.info('wupinjuanzeng', this.id);
				this.detail = res.data;
			},
			onEditTap() {
				this.$utils.jump(`./add-or-update?id=${this.id}`);
			},
			onDeleteTap() {
				uni.showModal({
					title: '提示',
					content: '确认取消此次捐赠？',
					success: async (res) => {
						if (res.confirm) {
							await this.$api.del('wupinjuanzeng', JSON.stringify([this.id]));
							uni.showToast({
								title: '操作成功',
								icon: 'success',
								duration: 2000,
								success: () => {
									setTimeout(() => {
										uni.navigateBack();
									}, 2000);
								}
							});
						}
					}
				});
			}
		}
	}
</script>

<style lang="scss" scoped>
.content {
	min-height: 100vh;
	background-color: #f8f8f8;
}
.container {
	padding: 20rpx;
}
.detail-card {
	background-color: #ffffff;
	border-radius: 16rpx;
	padding: 30rpx;
	box-shadow: 0 2rpx 12rpx rgba(0, 0, 0, 0.05);
}
.detail-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	border-bottom: 1px solid #f0f0f0;
	padding-bottom: 20rpx;
	margin-bottom: 30rpx;
}
.detail-title {
	font-size: 36rpx;
	font-weight: bold;
	color: #333;
}
.detail-status {
	font-size: 24rpx;
	padding: 6rpx 16rpx;
	border-radius: 20rpx;
}
.status-pass {
	background-color: #e8f4d9;
	color: #5cb85c;
}
.status-reject {
	background-color: #f8d7da;
	color: #dc3545;
}
.status-pending {
	background-color: #fff3cd;
	color: #ffc107;
}
.detail-image {
	width: 100%;
	height: 360rpx;
	margin-bottom: 30rpx;
	border-radius: 12rpx;
	overflow: hidden;
}
.detail-image image {
	width: 100%;
	height: 100%;
	object-fit: cover;
}
.detail-info {
	margin-bottom: 40rpx;
}
.info-item {
	display: flex;
	margin-bottom: 20rpx;
	line-height: 1.6;
}
.info-label {
	width: 180rpx;
	font-size: 28rpx;
	color: #999;
}
.info-value {
	flex: 1;
	font-size: 28rpx;
	color: #333;
}
.btn-group {
	display: flex;
	justify-content: space-between;
	padding-top: 30rpx;
	border-top: 1px solid #f0f0f0;
}
.btn-primary, .btn-danger {
	width: 46%;
	height: 80rpx;
	line-height: 80rpx;
	font-size: 28rpx;
	border-radius: 40rpx;
	color: #fff;
}
.btn-primary {
	background-color: #3498DB;
}
.btn-danger {
	background-color: #e74c3c;
}
</style> 