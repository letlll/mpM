<template>
	<div class="home-content">
		<!-- title -->
		<div id="home-title" class="home-title animate__animated">
			<div class="titles" >
				{{this.$project.projectName}}
			</div>
		</div>
		<!-- statis -->
		<div class="statis-box">
			<div id="statis1" class="statis1 animate__animated" v-if="isAuth('juanzengzhe','首页总数')">
				<div class="left">
					<span class="icon iconfont icon-zhangjie8"></span>
				</div>
				<div class="right">
					<div class="num">{{juanzengzheCount}}</div>
					<div class="name">捐赠者总数</div>
				</div>
			</div>
			<div id="statis2" class="statis2 animate__animated" v-if="isAuth('shouzhuzhe','首页总数')">
				<div class="left">
					<span class="icon iconfont icon-chujia3"></span>
				</div>
				<div class="right">
					<div class="num">{{shouzhuzheCount}}</div>
					<div class="name">受助者总数</div>
				</div>
			</div>
		</div>
		<!-- statis -->
	
		<!-- echarts -->
	</div>
</template>
<script>
import 'animate.css'
//0
import router from '@/router/router-static'
import * as echarts from 'echarts'
export default {
	data() {
		return {
			juanzengzheCount: 0,
			shouzhuzheCount: 0,
		};
	},
	mounted(){
		this.init();
		this.getjuanzengzheCount();
		this.getshouzhuzheCount();
		window.addEventListener('scroll', this.handleScroll)
		setTimeout(()=>{
			this.handleScroll()
		},100)
	},
	computed: {
		avatar(){
			return this.$storage.get('headportrait')?this.$storage.get('headportrait'):''
		},
	},
	methods:{
		handleScroll() {
			let arr = [
				{id:'home-title',css:'animate__fadeInUp'},
				{id:'statis1',css:'animate__fadeInDown'},
				{id:'statis2',css:'animate__fadeInDown'},
			]
			
			for (let i in arr) {
				let doc = document.getElementById(arr[i].id)
				if (doc) {
					let top = doc.offsetTop
					let win_top = window.innerHeight + window.pageYOffset
					// console.log(top,win_top)
					if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
						// console.log(doc)
						doc.classList.add(arr[i].css)
					}
				}
			}
		},
		init(){
			if(this.$storage.get('Token')){
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code != 0) {
						router.push({ name: 'login' })
					}
				});
			}else{
				router.push({ name: 'login' })
			}
		},
		getjuanzengzheCount() {
			this.$http({
				url: `juanzengzhe/count`,
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code == 0) {
					this.juanzengzheCount = data.data
				}
			})
		},
		getshouzhuzheCount() {
			this.$http({
				url: `shouzhuzhe/count`,
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code == 0) {
					this.shouzhuzheCount = data.data
				}
			})
		},
	}
};
</script>
<style lang="scss" scoped>
	.home-content {
		padding: 0;
		display: flex;
		justify-content: flex-start;
		flex-wrap: wrap;
		.home-title {
			border-radius: 5px;
			padding: 10px 0;
			box-shadow: 0 0px 0px rgba(0,0,0,.3);
			margin: 10px 0;
			display: none;
			width: 100%;
			justify-content: center;
			align-items: center;
			transition: 0.3s;
			.titles {
				padding: 0 0 0 12px;
				color: #333;
				font-size: 24px;
				line-height: 44px;
			}
		}
		.home-title:hover {
			transform: translate3d(0, 0px, 0);
			z-index: 1;
			background: rgba(255,255,255,.12);
		}
		.statis-box {
			padding: 0;
			margin: 0 auto;
			display: flex;
			width: calc(100% - 0px);
			justify-content: center;
			align-items: center;
			.statis1 {
				border: 0px solid #ccc;
				border-radius: 10px;
				padding: 10px;
				margin: 0 10px 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					border-radius: 100%;
					background: #e9f0f9;
					display: none;
					width: 66px;
					justify-content: center;
					align-items: center;
					height: 66px;
					.iconfont {
						color: #2582f3;
						font-size: 40px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					align-items: center;
					.num {
						margin: 0 0 4px;
						color: #0F1330;
						font-weight: bold;
						font-size: 30px;
						line-height: 30px;
						height: 30px;
					}
					.name {
						margin: 0;
						color: #6E7079;
						font-size: 12px;
						line-height: 12px;
						height: 12px;
						order: 1;
					}
				}
			}
			.statis1:hover {
			}
			.statis2 {
				border: 0px solid #ccc;
				border-radius: 10px;
				padding: 10px;
				margin: 0 10px 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					border-radius: 100%;
					background: #fcece1;
					display: none;
					width: 60px;
					justify-content: center;
					align-items: center;
					height: 60px;
					.iconfont {
						color: #f56c17;
						font-size: 40px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					align-items: center;
					.num {
						margin: 0 0 4px 0;
						color: #0F1330;
						font-weight: bold;
						font-size: 30px;
						line-height: 30px;
						height: 30px;
					}
					.name {
						margin: 0;
						color: #6E7079;
						font-size: 12px;
						line-height: 12px;
						height: 12px;
						order: 1;
					}
				}
			}
			.statis2:hover {
			}
			.statis3 {
				border: 0px solid #ccc;
				border-radius: 10px;
				box-shadow: 0 0px 0px rgba(0,0,0,.3);
				padding: 10px;
				margin: 0 10px 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					border-radius: 100%;
					background: #e7fbfd;
					display: none;
					width: 60px;
					justify-content: center;
					align-items: center;
					height: 60px;
					.iconfont {
						color: #21c2f6;
						font-size: 40px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					align-items: center;
					.num {
						margin: 0 0 4px 0;
						color: #0F1330;
						font-weight: bold;
						font-size: 30px;
						line-height: 30px;
						height: 30px;
					}
					.name {
						margin: 0;
						color: #6E7079;
						font-size: 12px;
						line-height: 12px;
						height: 12px;
						order: 1;
					}
				}
			}
			.statis3:hover {
				z-index: 1;
			}
			.statis4 {
				border: 0px solid #ccc;
				border-radius: 10px;
				box-shadow: 0 0px 0px rgba(0,0,0,.3);
				padding: 10px;
				margin: 0 10px 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					border-radius: 100%;
					background: #e0ffe9;
					display: none;
					width: 60px;
					justify-content: center;
					align-items: center;
					height: 60px;
					.iconfont {
						color: #45d45a;
						font-size: 40px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					align-items: center;
					.num {
						margin: 0 0 4px 0;
						color: #0F1330;
						font-weight: bold;
						font-size: 30px;
						line-height: 30px;
						height: 30px;
					}
					.name {
						margin: 0;
						color: #6E7079;
						font-size: 12px;
						line-height: 12px;
						height: 12px;
						order: 1;
					}
				}
			}
			.statis4:hover {
			}
			.statis5 {
				border: 0px solid #ccc;
				border-radius: 10px;
				box-shadow: 0 0px 0px rgba(0,0,0,.3);
				padding: 10px;
				margin: 0 10px 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					border-radius: 100%;
					background: #fbe8db;
					display: none;
					width: 60px;
					justify-content: center;
					align-items: center;
					height: 60px;
					.iconfont {
						color: #d26515;
						font-size: 40px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					align-items: center;
					.num {
						margin: 0 0 4px 0;
						color: #0F1330;
						font-weight: bold;
						font-size: 30px;
						line-height: 30px;
						height: 30px;
					}
					.name {
						margin: 0;
						color: #6E7079;
						font-size: 12px;
						line-height: 12px;
						height: 12px;
						order: 1;
					}
				}
			}
			.statis5:hover {
			}
		}
	}
	
	.echarts-flag-2 {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
		padding: 10px 20px;
		background: rebeccapurple;
	
		&>div {
			width: 32%;
			height: 300px;
			margin: 10px 0;
			background: rgba(255,255,255,.1);
			border-radius: 8px;
			padding: 10px 20px;
		}
	}
	.animate__animated {
		animation-fill-mode: none;
	}
</style>
