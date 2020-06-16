import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import m from '@/components/Main'
import Usergroup from '@/components/Usergroup'
import Form from '@/components/Form'
import log from '@/components/Loggroup'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      meta: {
        requireAuth: true
      },
      component: Login
    },
    // {
    //   path: '/Usergroup',
    //   name: 'Usergroup',
    //   component: Usergroup,
    //   meta: {}
    // },
    // {
    //   path: '/Form',
    //   name: 'Form',
    //   component: Form
    // },
    // {
    //   path: '/Bar',
    //   name: 'Bar',
    //   component: Bar
    // },
    {
      path: '/',
      component: resolve => require(['../components/Home.vue'], resolve),
      meta: {
        title: '自述文件'
      },
      children: [
        {
          path: '/',
          name: 'Main',
          component: m
        },
        {
          path: '/Usergroup',
          name: 'Usergroup',
          component: Usergroup
        },
        {
          path: '/main',
          name: 'Main',
          component: m
        },
        {
          path: '/Loggroup',
          name: 'Loggroup',
          component: log
        },
        {
          path: '/Form',
          name: 'Form',
          component: Form
        }
      ]
    }
  ]
})
