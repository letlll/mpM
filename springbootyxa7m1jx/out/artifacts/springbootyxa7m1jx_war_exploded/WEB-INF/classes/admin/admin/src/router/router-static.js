import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
	import news from '@/views/modules/news/list'
	import aboutus from '@/views/modules/aboutus/list'
	import juanzengjilu from '@/views/modules/juanzengjilu/list'
	import xiangmufenlei from '@/views/modules/xiangmufenlei/list'
	import gongyihuodong from '@/views/modules/gongyihuodong/list'
	import shouzhushenqing from '@/views/modules/shouzhushenqing/list'
	import discussjuanzengxiangmu from '@/views/modules/discussjuanzengxiangmu/list'
	import storeup from '@/views/modules/storeup/list'
	import shouzhuzhe from '@/views/modules/shouzhuzhe/list'
	import juanzengquxiang from '@/views/modules/juanzengquxiang/list'
	import huodongbaoming from '@/views/modules/huodongbaoming/list'
	import juanzengzhe from '@/views/modules/juanzengzhe/list'
	import juanzengxiangmu from '@/views/modules/juanzengxiangmu/list'
	import config from '@/views/modules/config/list'
	import zhiyuanzhe from '@/views/modules/zhiyuanzhe/list'
	import huodongleixing from '@/views/modules/huodongleixing/list'
	import newstype from '@/views/modules/newstype/list'


//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/news',
		name: '公告信息',
		component: news
	}
	,{
		path: '/aboutus',
		name: '关于我们',
		component: aboutus
	}
	,{
		path: '/juanzengjilu',
		name: '捐赠记录',
		component: juanzengjilu
	}
	,{
		path: '/xiangmufenlei',
		name: '项目分类',
		component: xiangmufenlei
	}
	,{
		path: '/gongyihuodong',
		name: '公益活动',
		component: gongyihuodong
	}
	,{
		path: '/shouzhushenqing',
		name: '受助申请',
		component: shouzhushenqing
	}
	,{
		path: '/discussjuanzengxiangmu',
		name: '捐赠项目评论',
		component: discussjuanzengxiangmu
	}
	,{
		path: '/storeup',
		name: '我的收藏',
		component: storeup
	}
	,{
		path: '/shouzhuzhe',
		name: '受助者',
		component: shouzhuzhe
	}
	,{
		path: '/juanzengquxiang',
		name: '捐赠去向',
		component: juanzengquxiang
	}
	,{
		path: '/huodongbaoming',
		name: '活动报名',
		component: huodongbaoming
	}
	,{
		path: '/juanzengzhe',
		name: '捐赠者',
		component: juanzengzhe
	}
	,{
		path: '/juanzengxiangmu',
		name: '捐赠项目',
		component: juanzengxiangmu
	}
	,{
		path: '/config',
		name: '轮播图管理',
		component: config
	}
	,{
		path: '/zhiyuanzhe',
		name: '志愿者',
		component: zhiyuanzhe
	}
	,{
		path: '/huodongleixing',
		name: '活动类型',
		component: huodongleixing
	}
	,{
		path: '/newstype',
		name: '公告信息分类',
		component: newstype
	}
	]
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
