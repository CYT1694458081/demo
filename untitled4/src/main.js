// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
Vue.config.productionTip = false
Vue.use(ElementUI)
/* eslint-disable no-new */
export const postRequest = (url, params) => {
  return axios({
    method: 'post',
    url: url,
    data: params,
    dataType: 'json',
    transformRequest: [function (data) {
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}
export const getRequest = (url, data) => {
  return axios({
    method: 'get',
    params: data,
    url: url
  })
}
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
