// pages/wupinjuanzeng/add-or-update.js
Page({
  data: {
    ruleForm: {
      wupinmingcheng: '',
      wupinleixing: '',
      shuliang: 1,
      juanzengzhanghao: '',
      juanzengxingming: '',
      tupian: '',
      juanzengshijian: '',
      zhuangtai: '待审核'
    },
    wupinleixingOptions: ['衣物', '书籍', '食品', '其他'],
    user: {},
    today: new Date().toISOString().split('T')[0]
  },

  onLoad: function(options) {
    // 获取用户信息
    let userInfo = wx.getStorageSync('userInfo');
    if (userInfo) {
      this.setData({
        'ruleForm.juanzengzhanghao': userInfo.zhanghao,
        'ruleForm.juanzengxingming': userInfo.xingming,
        user: userInfo
      });
    }
  },

  // 物品类型选择
  wupinleixingChange: function(e) {
    this.setData({
      'ruleForm.wupinleixing': this.data.wupinleixingOptions[e.detail.value]
    });
  },

  // 数量输入
  shuliangInput: function(e) {
    this.setData({
      'ruleForm.shuliang': parseInt(e.detail.value) || 1
    });
  },

  // 上传图片
  tupianChoose: function() {
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success: (res) => {
        wx.uploadFile({
          url: wx.getStorageSync('baseUrl') + 'file/upload',
          filePath: res.tempFilePaths[0],
          name: 'file',
          success: (res) => {
            let data = JSON.parse(res.data);
            if (data.code == 0) {
              this.setData({
                'ruleForm.tupian': data.file
              });
            }
          }
        });
      }
    });
  },

  // 捐赠时间选择
  timeChange: function(e) {
    this.setData({
      'ruleForm.juanzengshijian': e.detail.value
    });
  },

  // 提交表单
  onSubmit: function() {
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
    if (!this.data.ruleForm.juanzengshijian) {
      wx.showToast({
        title: '请选择捐赠时间',
        icon: 'none'
      });
      return;
    }
    wx.request({
      url: wx.getStorageSync('baseUrl') + 'wupinjuanzeng/save',
      method: 'POST',
      data: this.data.ruleForm,
      success: (res) => {
        if (res.data.code == 0) {
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
            title: res.data.msg,
            icon: 'none'
          });
        }
      }
    });
  }
});