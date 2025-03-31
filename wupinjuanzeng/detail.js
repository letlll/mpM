Page({
  data: {
    id: '',
    baseUrl: '',
    detail: {}
  },
  
  onLoad: function(options) {
    this.setData({
      baseUrl: getApp().globalData.baseUrl
    });
    
    if (options.id) {
      this.setData({
        id: options.id
      });
      this.getDetail();
    }
  },
  
  getStatusClass: function(status) {
    if(status === '通过') {
      return 'status-pass';
    } else if(status === '未通过') {
      return 'status-reject';
    } else {
      return 'status-pending';
    }
  },
  
  getDetail: function() {
    wx.request({
      url: wx.getStorageSync('baseUrl') + 'wupinjuanzeng/detail/' + this.data.id,
      success: (res) => {
        if(res.data.code == 0) {
          this.setData({
            detail: res.data.data
          });
        }
      }
    });
  },
  
  onEditTap: function() {
    wx.navigateTo({
      url: './add-or-update?id=' + this.data.id
    });
  },
  
  onDeleteTap: function() {
    wx.showModal({
      title: '提示',
      content: '确认取消此次捐赠？',
      success: (res) => {
        if (res.confirm) {
          wx.request({
            url: wx.getStorageSync('baseUrl') + 'wupinjuanzeng/delete',
            method: 'POST',
            data: JSON.stringify([this.data.id]),
            success: (res) => {
              if(res.data.code == 0) {
                wx.showToast({
                  title: '操作成功',
                  icon: 'success',
                  duration: 2000,
                  success: () => {
                    setTimeout(() => {
                      wx.navigateBack();
                    }, 2000);
                  }
                });
              }
            }
          });
        }
      }
    });
  }
}); 