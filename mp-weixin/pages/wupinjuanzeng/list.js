// pages/wupinjuanzeng/list.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    baseUrl: '',
    dataList: [],
    page: 1,
    limit: 10,
    total: 0,
    loading: false,
    finished: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // 设置baseUrl
    const baseUrl = wx.getStorageSync('baseUrl');
    this.setData({
      baseUrl: baseUrl
    });
    
    // 获取列表数据
    this.getList();
    
    // 监听页面刷新
    wx.startPullDownRefresh();
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    // 页面显示时刷新数据
    this.setData({
      page: 1,
      dataList: [],
      finished: false
    });
    this.getList();
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {
    // 下拉刷新
    this.setData({
      page: 1,
      dataList: [],
      finished: false
    });
    this.getList();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {
    // 上拉加载更多
    if (!this.data.finished) {
      this.getList();
    }
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  },

  // 获取列表数据
  getList: function () {
    if (this.data.loading || this.data.finished) return;
    
    let that = this;
    that.setData({
      loading: true
    });
    
    wx.request({
      url: that.data.baseUrl + 'wupinjuanzeng/page',
      data: {
        page: that.data.page,
        limit: that.data.limit
      },
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        wx.stopPullDownRefresh();
        if (res.data.code === 0) {
          const list = res.data.data.list;
          const total = res.data.data.total;
          
          if (list.length > 0) {
            that.setData({
              dataList: [...that.data.dataList, ...list],
              page: that.data.page + 1,
              total: total,
              loading: false,
              finished: that.data.dataList.length + list.length >= total
            });
          } else {
            that.setData({
              loading: false,
              finished: true
            });
          }
        } else {
          that.setData({
            loading: false
          });
          wx.showToast({
            title: res.data.msg || '获取数据失败',
            icon: 'none',
            duration: 2000
          });
        }
      },
      fail: function (err) {
        wx.stopPullDownRefresh();
        that.setData({
          loading: false
        });
        wx.showToast({
          title: '服务器连接失败',
          icon: 'none',
          duration: 2000
        });
      }
    });
  },

  // 跳转到详情页
  goDetail: function (e) {
    const id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: './detail?id=' + id + '&baseUrl=' + this.data.baseUrl
    });
  },

  // 跳转到添加页
  goAdd: function () {
    wx.navigateTo({
      url: './add-or-update?baseUrl=' + this.data.baseUrl
    });
  }
})