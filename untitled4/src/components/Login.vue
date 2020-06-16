<template>
  <div class="login-wrap">
    <div class="ms-login">
      <div class="ms-title">后台管理系统</div>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="ms-content">
        <el-form-item prop="userid">
          <el-input v-model="ruleForm.userid" placeholder="userid">
            <el-button slot="prepend" icon="el-icon-lx-people"></el-button>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" placeholder="password" v-model="ruleForm.password" @keyup.enter.native="submitForm('ruleForm')">
            <el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
          </el-input>
        </el-form-item>
        <div class="login-btn">
          <el-button type="primary" @click="login">登录</el-button>
        </div>
        <p class="login-tips">Tips : 用户名和密码随便填。</p>
      </el-form>
    </div>
  </div>
</template>
<script>
import {postRequest} from '../main'

export default {
  data: function () {
    return {
      ruleForm: {
        userid: '1',
        password: '456'
      },
      rules: {
        userid: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    login () {
      postRequest('/api/public/login', {
        userid: this.ruleForm.userid,
        password: this.ruleForm.password
      }).then((result) => {
        console.log(result)
        //    console.log(result.data.msg);
        if (result.data.code === 200) {
          localStorage.setItem('token', JSON.stringify(result.data.data))
          this.$router.push({path: '/Usergroup'})
        } else if (result.data.code === -1) {
          alert(result.data.msg)
        }
      }).catch(e => {
        console.log(e)
      })
    }
  }

}
</script>

<style scoped>
  .login-wrap{
    position: relative;
    width:100%;
    height:100%;
    background-image: url(../assets/img/login-bg.jpg);
    background-size: 100%;
  }
  .ms-title{
    width:100%;
    line-height: 50px;
    text-align: center;
    font-size:20px;
    color: #ff6cb7;
    border-bottom: 1px solid #ddd;
  }
  .ms-login{
    position: absolute;
    left:50%;
    top:50%;
    width:350px;
    margin:0px 0 0 -100px;
    border-radius: 5px;
    background: rgba(255,255,255, 0.3);
    overflow: hidden;
  }
  .ms-content{
    padding: 30px 30px;
  }
  .login-btn{
    text-align: center;
  }
  .login-btn button{
    width:100%;
    height:36px;
    margin-bottom: 10px;
  }
  .login-tips{
    font-size:12px;
    line-height:30px;
    color:#fff;
  }
</style>
