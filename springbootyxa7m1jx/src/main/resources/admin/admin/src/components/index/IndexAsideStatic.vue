<template>
	<div class="menu-preview">

		<div class="userinfo" :style='{"padding":"0 10px","alignItems":"center","flexDirection":"column","background":"#fff","display":"none","justifyContent":"center","height":"60px"}'>
			<el-image :style='{"width":"36px","objectFit":"cover","borderRadius":"4px","display":"block","height":"36px"}' :src="avatar?this.$base.url + avatar: require('@/assets/img/avator.png')" fit="cover"></el-image>
			<div :style='{"fontSize":"12px","lineHeight":"1","color":"#333","textAlign":"center"}'>{{this.$storage.get('adminName')}}</div>
		</div>
		<el-menu :default-active="activeMenu" :unique-opened="true" :style='{"border":0,"padding":"0","listStyle":"none","margin":"0","flexWrap":"wrap","background":"none","display":"flex","position":"relative"}' mode="horizontal" class="el-menu-horizontal-2">
		    <el-menu-item class="home" :style='{"border":"0","padding":"0","listStyle":"none","margin":"0","color":"#C1C1C1","background":"transparent","float":"left","order":"1"}' @click.native="menuHandler('')" index="/">
				<div class="el-tooltip">
					<i :style='{"verticalAlign":"middle","margin":"0 0 12px","color":"inherit","textAlign":"center","display":"block","fontSize":"15px","lineHeight":"15px"}' class="icon iconfont icon-shouye-zhihui"></i>
					<span :style='{"verticalAlign":"middle","fontSize":"14px","lineHeight":"16px","color":"inherit"}' slot="title">系统首页</span>
				</div>
			</el-menu-item>
		    <el-submenu class="user" popper-class="user" :popper-append-to-body="false" :style='{"border":"0","padding":"0","listStyle":"none","margin":"0","color":"#C1C1C1","background":"transparent","float":"left","order":"2"}' index="1">
				<template slot="title">
					<i :style='{"verticalAlign":"middle","margin":"0 0 12px","color":"inherit","textAlign":"center","display":"block","fontSize":"15px","lineHeight":"15px"}' class="icon iconfont icon-kuaijiezhifu"></i>
					<span :style='{"verticalAlign":"middle","fontSize":"14px","lineHeight":"16px","color":"inherit"}' slot="title">个人中心</span>
				</template>
				<el-menu-item index="/updatePassword" @click="menuHandler('updatePassword')">修改密码</el-menu-item>
				<el-menu-item index="/center" @click="menuHandler('center')">个人信息</el-menu-item>
		    </el-submenu>
			<template v-for="(menu,index) in menuList.backMenu" v-if="menu.child.length&&menu.child[0].tableName!='hasBoard'">
				<el-submenu v-if="menu.child.length > 1 || !horizontalIsMultiple" class="other" popper-class="other" :popper-append-to-body="false" :style='{"border":"0","padding":"0","listStyle":"none","margin":"0","color":"#C1C1C1","background":"transparent","float":"left"}' :index="index+2+''">
					<template slot="title">
						<i :style='{"verticalAlign":"middle","margin":"0 0 12px","color":"inherit","textAlign":"center","display":"block","fontSize":"15px","lineHeight":"15px"}' class="el-icon-menu" :class="icons[index]"></i>
						<span :style='{"verticalAlign":"middle","fontSize":"14px","lineHeight":"16px","color":"inherit"}' slot="title">{{menu.menu + (horizontalFlag ? '管理' : '')}}</span>
					</template>
					<el-menu-item v-for="(child,sort) in menu.child" :key="sort" :index="'/'+child.tableName" @click="menuHandler(child.tableName)">{{ child.menu }}</el-menu-item>
				</el-submenu>
				<el-menu-item v-if="menu.child.length <= 1 && horizontalIsMultiple" class="other" popper-class="other" :style='{"border":"0","padding":"0","listStyle":"none","margin":"0","color":"#C1C1C1","background":"transparent","float":"left"}' @click="menuHandler(menu.child[0].tableName)" :index="'/'+menu.child[0].tableName">
					<div class="el-tooltip">
						<i :style='{"verticalAlign":"middle","margin":"0 0 12px","color":"inherit","textAlign":"center","display":"block","fontSize":"15px","lineHeight":"15px"}' class="el-icon-menu" :class="icons[index]"></i>
						<span :style='{"verticalAlign":"middle","fontSize":"14px","lineHeight":"16px","color":"inherit"}' slot="title">{{menu.child[0].menu + (horizontalFlag ? '管理' : '')}}</span>
					</div>
				</el-menu-item>
			</template>
		</el-menu>

	</div>
</template>

<script>
import menu from '@/utils/menu'
export default {
	data() {
		return {
			menuList: [],
			dynamicMenuRoutes: [],
			role: '',
			user: null,
			avatar:'',
			icons: [
				'el-icon-s-cooperation',
				'el-icon-s-order',
				'el-icon-s-platform',
				'el-icon-s-fold',
				'el-icon-s-unfold',
				'el-icon-s-operation',
				'el-icon-s-promotion',
				'el-icon-s-release',
				'el-icon-s-ticket',
				'el-icon-s-management',
				'el-icon-s-open',
				'el-icon-s-shop',
				'el-icon-s-marketing',
				'el-icon-s-flag',
				'el-icon-s-comment',
				'el-icon-s-finance',
				'el-icon-s-claim',
				'el-icon-s-custom',
				'el-icon-s-opportunity',
				'el-icon-s-data',
				'el-icon-s-check',
				'el-icon-s-grid',
				'el-icon-menu',
				'el-icon-chat-dot-square',
				'el-icon-message',
				'el-icon-postcard',
				'el-icon-position',
				'el-icon-microphone',
				'el-icon-close-notification',
				'el-icon-bangzhu',
				'el-icon-time',
				'el-icon-odometer',
				'el-icon-crop',
				'el-icon-aim',
				'el-icon-switch-button',
				'el-icon-full-screen',
				'el-icon-copy-document',
				'el-icon-mic',
				'el-icon-stopwatch',
			],
			menulistBorderBottom: {},
			horizontalFlag: false,
			horizontalIsMultiple: true,
		}
	},
	computed: {
		activeMenu() {
			const route = this.$route
			const {
				meta,
				path
			} = route
			// if st path, the sidebar will highlight the path you sete
			if (meta.activeMenu) {
				return meta.activeMenu
			}
			return path
		}
	},
	watch:{
		avatar(){
			this.$forceUpdate()
		},
	},
	mounted() {
		const menus = menu.list()
		if(menus) {
			this.menuList = menus
		} else {
			let params = {
				page: 1,
				limit: 1,
				sort: 'id',
			}
			
			this.$http({
				url: "menu/list",
				method: "get",
				params: params
			}).then(({
				data
			}) => {
				if (data && data.code === 0) {
					this.menuList = JSON.parse(data.data.list[0].menujson);
					this.$storage.set("menus", this.menuList);
				}
			})
		}
		this.role = this.$storage.get('role')
		
		for(let i=0;i<this.menuList.length;i++) {
			if(this.menuList[i].roleName == this.role) {
				this.menuList = this.menuList[i];
				break;
			}
		}
		this.styleChange()
		
		let sessionTable = this.$storage.get("sessionTable")
		this.$http({
			url: sessionTable + '/session',
			method: "get"
		}).then(({
			data
		}) => {
			if (data && data.code === 0) {
				if(sessionTable == 'zhiyuanzhe') {
					this.avatar = data.data.touxiang
				}
				if(sessionTable == 'juanzengzhe') {
					this.avatar = data.data.touxiang
				}
				if(sessionTable == 'shouzhuzhe') {
					this.avatar = data.data.touxiang
				}
				if(sessionTable=='users') {
					this.avatar = data.data.image
				}
				this.user = data.data;
			} else {
				let message = this.$message
				message.error(data.msg);
			}
		});
	},
	created(){
		this.icons.sort(()=>{
			return (0.5-Math.random())
		})
	},
	methods: {
		collapse() {
		  this.isCollapse = !this.isCollapse
		  this.$emit('oncollapsechange', this.isCollapse)
		},
		styleChange() {
			this.$nextTick(() => {
												document.querySelectorAll('.el-menu-horizontal-demo .el-submenu .el-menu').forEach(el => {
				  el.removeAttribute('style')
				  const icon = {"border":"none"}
				  Object.keys(icon).forEach((key) => {
				    el.style[key] = icon[key]
				  })
				})
							})
		},
		menuHandler(name) {
			let router = this.$router
			name = '/'+name
			router.push(name)
		},
	}
}
</script>
<style lang="scss" scoped>
	.menu-preview {
		.el-scrollbar {
			height: 100%;
	
			& /deep/ .scrollbar-wrapper {
				overflow-x: hidden;
			}
		
		}
		// --- 横向 ---
		.el-menu-horizontal-demo {
			.el-submenu:first-of-type /deep/ .el-submenu__title .el-submenu__icon-arrow {
				display: none;
			}
		}
	  
		.el-menu-horizontal-demo>.el-menu-item {
			border: 0;
			cursor: pointer;
			padding: 0 20px;
			color: #909399;
			white-space: nowrap;
			font-size: 14px;
			line-height: 60px;
			list-style: none;
			height: 60px;
		}
		
		.el-menu-horizontal-demo>.el-menu-item:hover {
			color: #fff;
			background: blue;
		}
	  
		.el-menu-horizontal-demo .el-submenu {
			padding: 0;
			margin: 0;
			float: left;
			list-style: none;
		}
	  
		.el-menu-horizontal-demo .el-submenu /deep/ .el-submenu__title {
			border: 0;
			cursor: pointer;
			padding: 0 20px;
			color: #909399;
			white-space: nowrap;
			font-size: 14px;
			line-height: 60px;
			list-style: none;
			height: 60px;
		}
	  
		.el-menu-horizontal-demo .el-submenu /deep/ .el-submenu__title:hover {
			color: #fff;
			background: blue;
		}
	  
		.el-menu-horizontal-demo .el-submenu /deep/ .el-submenu__title .el-submenu__icon-arrow {
			margin: -3px 0 0 8px;
			color: inherit;
			vertical-align: middle;
			font-size: 12px;
			position: static;
		}
	  
		// .el-menu-horizontal-demo .el-submenu /deep/ .el-menu {
// 			// 	border: none;
// 			// }
	  
		.el-menu-horizontal-demo .el-submenu /deep/ .el-menu .el-menu-item {
			padding: 0 40px;
			color: #666;
			background: #fff;
			line-height: 50px;
			height: 50px;
		}
	  
		.el-menu-horizontal-demo .el-submenu /deep/ .el-menu .el-menu-item:hover {
			padding: 0 40px;
			color: #fff;
			background: red;
			line-height: 50px;
			height: 50px;
		}
	  
		.el-menu-horizontal-demo .el-submenu /deep/ .el-menu .el-menu-item.is-active {
			padding: 0 40px;
			color: #fff;
			background: blue;
			line-height: 50px;
			height: 50px;
		}
		// --- 横向 ---
	}
	// 横向 样式二
	.el-menu-horizontal-2>.el-menu-item.other>.el-tooltip {
		border: 0;
		cursor: pointer;
		padding: 0 20px;
		margin: 5px;
		color: #C1C1C1;
		white-space: nowrap;
		display: flex;
		font-size: 14px;
		line-height: 50px;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		position: relative;
		list-style: none;
		height: 50px;
	}
	
	.el-menu-horizontal-2>.el-menu-item.other>.el-tooltip:hover {
		border: 0;
		border-radius: 5px;
		color: #fff;
		background: #3EA751;
	}
	
	.el-menu-horizontal-2>.el-menu-item.other.is-active>.el-tooltip {
		border: 0;
		color: #3EA751;
		background: #263746;
	}
	
	.el-menu-horizontal-2 .el-submenu.other /deep/ .el-submenu__title {
		border: 0;
		cursor: pointer;
		padding: 0 20px;
		margin: 5px;
		color: #C1C1C1;
		white-space: nowrap;
		display: flex;
		font-size: 14px;
		line-height: 50px;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		position: relative;
		list-style: none;
		height: 50px;
	}
	
	.el-menu-horizontal-2 .el-submenu.other /deep/ .el-submenu__title:hover {
		border: 0;
		border-radius: 5px;
		color: #fff;
		background: #3EA751;
	}
	
	.el-menu-horizontal-2 .el-submenu.other /deep/ .el-submenu__title .iconfont {
		margin: 0 0 12px;
		color: inherit;
		display: block;
		vertical-align: middle;
		font-size: 15px;
		line-height: 15px;
		text-align: center;
	}
	
	.el-menu-horizontal-2 .el-submenu.other /deep/ .el-submenu__title .el-submenu__icon-arrow {
		margin: -3px 0 0 8px;
		color: inherit;
		display: none;
		vertical-align: middle;
		font-size: 12px;
		line-height: 16px;
		position: static;
	}
	
	.el-menu-horizontal-2  /deep/.el-menu--horizontal.other .el-menu {
		border: none;
		border-radius: 2px;
		padding: 5px 0;
		box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
		margin: 5px 0 0 0;
		z-index: 999;
		background: #FFF;
		position: relative;
		list-style: none;
		min-width: 200px;
	}
	
	.el-menu-horizontal-2 .el-menu--horizontal.other .el-menu .el-menu-item {
		padding: 0 10px;
		color: #A5A5A5;
		background: #fff;
		line-height: 36px;
		min-width: 200px;
		height: 36px;
	}
	
	.el-menu-horizontal-2 .el-menu--horizontal.other .el-menu .el-menu-item:hover {
		border-radius: 5px;
		color: #fff;
		background: #3EA751;
	}
	
	.el-menu-horizontal-2 .el-menu--horizontal.other .el-menu .el-menu-item.is-active {
		color: #3EA751;
		background: #fff;
	}

	// 横向 样式二-首页
	.el-menu-horizontal-2>.el-menu-item.home>.el-tooltip {
		border: 0;
		cursor: pointer;
		padding: 0 20px;
		margin: 5px;
		color: #C1C1C1;
		white-space: nowrap;
		display: flex;
		font-size: 14px;
		line-height: 50px;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		position: relative;
		list-style: none;
		height: 50px;
	}
	
	.el-menu-horizontal-2>.el-menu-item.home>.el-tooltip:hover {
		border: 0;
		border-radius: 5px;
		color: #fff;
		background: #3EA751;
	}
	
	.el-menu-horizontal-2>.el-menu-item.home.is-active>.el-tooltip {
		border: 0;
		color: #3EA751;
		background: #263746;
	}
	
	.el-menu-horizontal-2 .el-submenu.home /deep/ .el-submenu__title {
		border: 0;
		cursor: pointer;
		padding: 0 20px;
		margin: 5px;
		color: #C1C1C1;
		white-space: nowrap;
		display: flex;
		font-size: 14px;
		line-height: 50px;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		position: relative;
		list-style: none;
		height: 50px;
	}
	
	.el-menu-horizontal-2 .el-submenu.home /deep/ .el-submenu__title:hover {
		border: 0;
		border-radius: 5px;
		color: #fff;
		background: #3EA751;
	}
	
	.el-menu-horizontal-2 .el-submenu.home /deep/ .el-submenu__title .iconfont {
		margin: 0 0 12px;
		color: inherit;
		display: block;
		vertical-align: middle;
		font-size: 15px;
		line-height: 15px;
		text-align: center;
	}
	
	.el-menu-horizontal-2 .el-submenu.home /deep/ .el-submenu__title .el-submenu__icon-arrow {
		margin: -3px 0 0 8px;
		color: inherit;
		display: none;
		vertical-align: middle;
		font-size: 12px;
		line-height: 16px;
		position: static;
	}
	
	.el-menu-horizontal-2 /deep/.el-menu--horizontal.home .el-menu {
		border: none;
		border-radius: 2px;
		padding: 5px 0;
		box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
		margin: 5px 0 0 0;
		z-index: 999;
		background: #FFF;
		position: relative;
		list-style: none;
		min-width: 200px;
	}
	
	.el-menu-horizontal-2 .el-menu--horizontal.home .el-menu .el-menu-item {
		padding: 0 10px;
		color: #A5A5A5;
		background: #fff;
		line-height: 36px;
		min-width: 200px;
		height: 36px;
	}
	
	.el-menu-horizontal-2 .el-menu--horizontal.home .el-menu .el-menu-item:hover {
		border-radius: 5px;
		color: #fff;
		background: #3EA751;
	}
	
	.el-menu-horizontal-2 .el-menu--horizontal.home .el-menu .el-menu-item.is-active {
		color: #3EA751;
		background: #fff;
	}
	// 横向 样式二-个人中心
	.el-menu-horizontal-2>.el-menu-item.user>.el-tooltip {
		border: 0;
		cursor: pointer;
		padding: 0 20px;
		margin: 5px;
		color: #C1C1C1;
		white-space: nowrap;
		display: flex;
		font-size: 14px;
		line-height: 50px;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		position: relative;
		list-style: none;
		height: 50px;
	}
	
	.el-menu-horizontal-2>.el-menu-item.user>.el-tooltip:hover {
		border: 0;
		border-radius: 5px;
		color: #fff;
		background: #3EA751;
	}
	
	.el-menu-horizontal-2>.el-menu-item.user.is-active>.el-tooltip {
		border: 0;
		color: #3EA751;
		background: #263746;
	}
	
	.el-menu-horizontal-2 .el-submenu.user /deep/ .el-submenu__title {
		border: 0;
		cursor: pointer;
		padding: 0 20px;
		margin: 5px;
		color: #C1C1C1;
		white-space: nowrap;
		display: flex;
		font-size: 14px;
		line-height: 50px;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		position: relative;
		list-style: none;
		height: 50px;
	}
	
	.el-menu-horizontal-2 .el-submenu.user /deep/ .el-submenu__title:hover {
		border: 0;
		border-radius: 5px;
		color: #fff;
		background: #3EA751;
	}
	
	.el-menu-horizontal-2 .el-submenu.user /deep/ .el-submenu__title .iconfont {
		margin: 0 0 12px;
		color: inherit;
		display: block;
		vertical-align: middle;
		font-size: 15px;
		line-height: 15px;
		text-align: center;
	}
	
	.el-menu-horizontal-2 .el-submenu.user /deep/ .el-submenu__title .el-submenu__icon-arrow {
		margin: -3px 0 0 8px;
		color: inherit;
		display: none;
		vertical-align: middle;
		font-size: 12px;
		line-height: 16px;
		position: static;
	}
	
	.el-menu-horizontal-2 /deep/.el-menu--horizontal.user .el-menu {
		border: none;
		border-radius: 2px;
		padding: 5px 0;
		box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
		margin: 5px 0 0 0;
		z-index: 999;
		background: #FFF;
		position: relative;
		list-style: none;
		min-width: 200px;
	}
	
	.el-menu-horizontal-2 .el-menu--horizontal.user .el-menu .el-menu-item {
		padding: 0 10px;
		color: #A5A5A5;
		background: #fff;
		line-height: 36px;
		min-width: 200px;
		height: 36px;
	}
	
	.el-menu-horizontal-2 .el-menu--horizontal.user .el-menu .el-menu-item:hover {
		border-radius: 5px;
		color: #fff;
		background: #3EA751;
	}
	
	.el-menu-horizontal-2 .el-menu--horizontal.user .el-menu .el-menu-item.is-active {
		color: #3EA751;
		background: #fff;
	}
</style>
