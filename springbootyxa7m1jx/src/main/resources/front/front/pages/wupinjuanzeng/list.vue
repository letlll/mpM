<template>
	<view class="content">
		<view class="container">
			<view class="list">
				<view v-if="list.length>0" class="list-item" v-for="(item,index) in list" :key="index" @tap="onDetailTap(item)">
					<view class="list-item-header">
						<view class="list-item-title">{{item.wupinmingcheng}}</view>
						<view :class="['list-item-status', getStatusClass(item.zhuangtai)]">{{item.zhuangtai}}</view>
					</view>
					<view class="list-item-content">
						<image class="list-item-image" :src="baseUrl + item.tupian" v-if="item.tupian" mode="aspectFill"></image>
						<view class="list-item-info">
							<view class="list-item-info-item">
								<text class="label">物品类型：</text>
								<text>{{item.wupinleixing}}</text>
							</view>
							<view class="list-item-info-item">
								<text class="label">捐赠数量：</text>
								<text>{{item.shuliang}}</text>
							</view>
							<view class="list-item-info-item">
								<text class="label">捐赠时间：</text>
								<text>{{item.juanzengshijian}}</text>
							</view>
						</view>
					</view>
				</view>
				<view v-if="list.length==0" class="empty">
					<image src="../../static/empty.png" mode="widthFix"></image>
					<view class="empty-text">暂无物品捐赠记录</view>
				</view>
			</view>
			
			<view class="add-btn" @tap="onAddTap">
				<text class="cuIcon-add"></text>
				<text>新增捐赠</text>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userid: '',
				baseUrl: '',
				list: [],
				mescroll: null,
				downOption: {
					auto: false
				},
				upOption: {
					noMoreSize: 3,
					textNoMore: '没有更多了'
				},
				hasNext: true,
				user: {}
			};
		},
		async onLoad(options) {
			this.baseUrl = this.$base.url;
			if(options.userid) {
				this.userid = options.userid;
			} else {
				this.user = await this.$api.session('juanzengzhe');
				if(this.user.id) {
					this.userid = this.user.id;
				}
			}
			this.getList();
		},
		onShow() {
			// 重新加载数据
			this.getList();
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
			// 获取捐赠记录列表
			async getList() {
				let params = {
					page: 1,
					limit: 10,
					juanzengzhanghao: this.userid ? this.user.juanzengzhanghao : ''
				};
				let res = await this.$api.list('wupinjuanzeng', params);
				this.list = res.data.list;
			},
			// 查看详情
			onDetailTap(item) {
				this.$utils.jump(`./detail?id=${item.id}`);
			},
			// 新增捐赠
			onAddTap() {
				this.$utils.jump('./add-or-update');
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
.list {
	margin-bottom: 80rpx;
}
.list-item {
	background-color: #ffffff;
	border-radius: 10rpx;
	padding: 20rpx;
	margin-bottom: 20rpx;
	box-shadow: 0 2rpx 12rpx rgba(0, 0, 0, 0.05);
}
.list-item-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	border-bottom: 1px solid #f0f0f0;
	padding-bottom: 15rpx;
	margin-bottom: 15rpx;
}
.list-item-title {
	font-size: 32rpx;
	font-weight: bold;
}
.list-item-status {
	font-size: 24rpx;
	padding: 4rpx 12rpx;
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
.list-item-content {
	display: flex;
}
.list-item-image {
	width: 180rpx;
	height: 180rpx;
	border-radius: 10rpx;
	margin-right: 20rpx;
	flex-shrink: 0;
}
.list-item-info {
	flex: 1;
}
.list-item-info-item {
	font-size: 28rpx;
	color: #666;
	line-height: 1.8;
}
.label {
	color: #999;
}
.empty {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	padding: 100rpx 0;
}
.empty image {
	width: 240rpx;
	margin-bottom: 30rpx;
}
.empty-text {
	font-size: 28rpx;
	color: #999;
}
.add-btn {
	position: fixed;
	bottom: 30rpx;
	right: 30rpx;
	background-color: #3498DB;
	color: #ffffff;
	width: 200rpx;
	height: 80rpx;
	border-radius: 40rpx;
	display: flex;
	align-items: center;
	justify-content: center;
	box-shadow: 0 4rpx 16rpx rgba(0, 0, 0, 0.1);
}
.add-btn text {
	margin: 0 6rpx;
}
</style> 