// pages/wupinjuanzeng/add-or-update.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    baseUrl: '',
    id: '',
    ruleForm: {
      wupinmingcheng: '',
      wupinleixing: '',
      shuliang: '',
      tupian: '',
      wupinmiaoshu: '',
      juanzengxingming: '',
      juanzenglianxidianhua: '',
      qiwangjuanzengfangshi: ''
    },
    wupinleixingOptions: ['生活用品', '学习用品', '电子设备', '衣物', '食品', '其他'],
    wupinleixingIndex: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // 设置baseUrl
    this.setData({
      baseUrl: options.baseUrl || wx.getStorageSync('baseUrl'),
      id: options.id || ''
    });
    
    // 如果是编辑模式，获取物品详情
    if (this.data.id) {
      this.getDetail();
    } else {
      // 添加模式，设置默认的捐赠账号和姓名
      let userInfo = wx.getStorageSync('userInfo');
      let username = wx.getStorageSync('username');
      let realname = wx.getStorageSync('realname');

      // 设置捐赠账号和姓名
      this.setData({
        'ruleForm.juanzengzhanghao': username,
        'ruleForm.juanzengxingming': realname || userInfo.juanzengxingming || ''
      });
    }
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

  // 获取物品详情
  getDetail: function() {
    let that = this;
    
    wx.request({
      url: that.data.baseUrl + 'wupinjuanzeng/detail/' + that.data.id,
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function(res) {
        if (res.data.code === 0) {
          that.setData({
            ruleForm: res.data.data
          });
          
          // 设置物品类型的索引
          let index = that.data.wupinleixingOptions.findIndex(item => item === that.data.ruleForm.wupinleixing);
          if (index !== -1) {
            that.setData({
              wupinleixingIndex: index
            });
          }
        } else {
          wx.showToast({
            title: res.data.msg || '获取数据失败',
            icon: 'none',
            duration: 2000
          });
        }
      },
      fail: function(err) {
        wx.showToast({
          title: '服务器连接失败',
          icon: 'none',
          duration: 2000
        });
      }
    });
  },

  // 输入框变化
  inputChange: function(e) {
    let field = e.currentTarget.dataset.field;
    let value = e.detail.value;
    this.setData({
      ['ruleForm.' + field]: value
    });
  },

  // 物品类型选择变化
  wupinleixingChange: function(e) {
    this.setData({
      wupinleixingIndex: e.detail.value,
      'ruleForm.wupinleixing': this.data.wupinleixingOptions[e.detail.value]
    });
  },

  // 上传图片
  tupianChoose: function() {
    let that = this;
    
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success: function(res) {
        wx.showLoading({
          title: '上传中...'
        });
        
        // 如果是在本地开发环境，模拟上传成功
        let baseUrl = that.data.baseUrl;
        if (!baseUrl || baseUrl.includes('localhost')) {
          // 本地开发环境，模拟上传成功
          wx.hideLoading();
          wx.showToast({
            title: '图片已选择',
            icon: 'success'
          });
          return;
        }
        
        wx.uploadFile({
          url: that.data.baseUrl + 'file/upload',
          filePath: res.tempFilePaths[0],
          name: 'file',
          success: function(uploadRes) {
            wx.hideLoading();
            try {
              let data = JSON.parse(uploadRes.data);
              if (data.code === 0) {
                that.setData({
                  'ruleForm.tupian': data.file
                });
              } else {
                wx.showToast({
                  title: data.msg || '上传失败',
                  icon: 'none'
                });
              }
            } catch (e) {
              wx.showToast({
                title: '上传失败',
                icon: 'none'
              });
            }
          },
          fail: function() {
            wx.hideLoading();
            wx.showToast({
              title: '上传失败',
              icon: 'none'
            });
          }
        });
      }
    });
  },

  // 提交表单
  submitForm: function() {
    // 表单验证
    if (!this.data.ruleForm.wupinmingcheng) {
      wx.showToast({
        title: '请输入物品名称',
        icon: 'none'
      });
      return;
    }
    
    if (!this.data.ruleForm.wupinleixing) {
      wx.showToast({
        title: '请选择物品类型',
        icon: 'none'
      });
      return;
    }
    
    let that = this;
    let url = 'wupinjuanzeng/save';
    if (this.data.id) {
      url = 'wupinjuanzeng/update';
    }
    
    wx.showLoading({
      title: '提交中...'
    });
    
    wx.request({
      url: that.data.baseUrl + url,
      data: that.data.ruleForm,
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function(res) {
        wx.hideLoading();
        if (res.data.code === 0) {
          wx.showToast({
            title: '提交成功',
            icon: 'success',
            duration: 2000,
            success: function() {
              setTimeout(function() {
                wx.navigateBack();
              }, 2000);
            }
          });
        } else {
          wx.showToast({
            title: res.data.msg || '提交失败',
            icon: 'none',
            duration: 2000
          });
        }
      },
      fail: function(err) {
        wx.hideLoading();
        wx.showToast({
          title: '服务器连接失败',
          icon: 'none',
          duration: 2000
        });
      }
    });
  },

  // 重置表单
  resetForm: function() {
    if (this.data.id) {
      this.getDetail();
    } else {
      this.setData({
        ruleForm: {
          wupinmingcheng: '',
          wupinleixing: '',
          shuliang: '',
          tupian: '',
          wupinmiaoshu: '',
          juanzengxingming: '',
          juanzenglianxidianhua: '',
          qiwangjuanzengfangshi: ''
        },
        wupinleixingIndex: 0
      });
    }
  },

  // 返回列表页
  goBack: function() {
    wx.navigateBack();
  }
})