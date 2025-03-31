// pages/wupinjuanzeng/detail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    baseUrl: '',
    id: '',
    detail: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // 设置baseUrl和ID
    this.setData({
      baseUrl: options.baseUrl || wx.getStorageSync('baseUrl'),
      id: options.id || ''
    });
    
    // 获取详情数据
    this.getDetail();
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

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  },

  // 获取详情数据
  getDetail: function () {
    let that = this;
    
    wx.showLoading({
      title: '加载中...'
    });
    
    wx.request({
      url: that.data.baseUrl + 'wupinjuanzeng/detail/' + that.data.id,
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        wx.hideLoading();
        if (res.data.code === 0) {
          that.setData({
            detail: res.data.data
          });
        } else {
          wx.showToast({
            title: res.data.msg || '获取数据失败',
            icon: 'none',
            duration: 2000
          });
        }
      },
      fail: function (err) {
        wx.hideLoading();
        wx.showToast({
          title: '服务器连接失败',
          icon: 'none',
          duration: 2000
        });
      }
    });
  },

  // 跳转到编辑页
  goEdit: function (e) {
    const id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: './add-or-update?id=' + id + '&baseUrl=' + this.data.baseUrl
    });
  },

  // 删除物品
  handleDelete: function () {
    let that = this;
    
    wx.showModal({
      title: '提示',
      content: '确定要删除这个物品吗？',
      success: function (res) {
        if (res.confirm) {
          wx.showLoading({
            title: '删除中...'
          });
          
          wx.request({
            url: that.data.baseUrl + 'wupinjuanzeng/delete',
            data: {
              id: that.data.id
            },
            method: 'POST',
            header: {
              'content-type': 'application/x-www-form-urlencoded'
            },
            success: function (res) {
              wx.hideLoading();
              if (res.data.code === 0) {
                wx.showToast({
                  title: '删除成功',
                  icon: 'success',
                  duration: 2000,
                  success: function () {
                    setTimeout(function () {
                      wx.navigateBack();
                    }, 2000);
                  }
                });
              } else {
                wx.showToast({
                  title: res.data.msg || '删除失败',
                  icon: 'none',
                  duration: 2000
                });
              }
            },
            fail: function (err) {
              wx.hideLoading();
              wx.showToast({
                title: '服务器连接失败',
                icon: 'none',
                duration: 2000
              });
            }
          });
        }
      }
    });
  },

  // 返回列表页
  goBack: function () {
    wx.navigateBack();
  }
})