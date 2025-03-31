Page({
  data: {
    baseUrl: '',
    list: [],
    userid: '',
    user: {}
  },

  onLoad: function(options) {
    this.setData({
      baseUrl: getApp().globalData.baseUrl
    });
    
    // 获取用户信息
    let userInfo = wx.getStorageSync('userInfo');
    if (userInfo) {
      this.setData({
        user: userInfo
      });
    }
    
    // 获取userid
    if (options.userid) {
      this.setData({
        userid: options.userid
      });
    }
    
    this.getList();
  },
  
  onShow: function() {
    this.getList();
  },

  // 获取捐赠记录列表
  getList: function() {
    let params = {
      page: 1,
      limit: 10
    };
    
    if (this.data.user.zhanghao) {
      params.juanzengzhanghao = this.data.user.zhanghao;
    }
    
    wx.request({
      url: wx.getStorageSync('baseUrl') + 'wupinjuanzeng/page',
      data: params,
      success: (res) => {
        if (res.data.code == 0) {
          this.setData({
            list: res.data.data.list
          });
        }
      }
    });
  },
  
  // 获取状态样式类
  getStatusClass: function(status) {
    if(status === '通过') {
      return 'status-pass';
    } else if(status === '未通过') {
      return 'status-reject';
    } else {
      return 'status-pending';
    }
  },
  
  // 查看详情
  onDetailTap: function(e) {
    wx.navigateTo({
      url: './detail?id=' + e.currentTarget.dataset.id
    });
  },
  
  // 新增捐赠
  onAddTap: function() {
    wx.navigateTo({
      url: './add-or-update'
    });
  }
}); 