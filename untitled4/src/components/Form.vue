<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="ID">
        <el-input v-model="form.id" />
      </el-form-item>
      <el-form-item label="真实姓名">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="班级">
        <el-input v-model="form.className" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password" />
      </el-form-item>
      <el-form-item label="出生年月">
        <el-col :span="11">
          <el-date-picker v-model="form.date1" type="date" placeholder="Pick a date" style="width: 100%;" />
        </el-col>
      </el-form-item>
      <el-form-item label="身份">
        <el-radio-group v-model="form.resource">
          <el-radio label="管理员" />
          <el-radio label="学生" />
          <el-radio label="教师" />
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">注册</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import {postRequest} from '../main'
export default {
  data () {
    return {
      form: {
        id: '',
        name: '',
        className: '',
        password: '',
        date1: '',
        resource: '学生'
      },
      user: {
        id: '',
        userName: '',
        className: '',
        passWord: '',
        money: '',
        job: ''
      }
    }
  },
  methods: {

    onCancel () {
      this.$message({
        message: '取消了!',
        type: 'warning'
      })
      this.form.id = ''
      this.form.name = ''
      this.form.className = ''
      this.form.password = ''
      this.form.resource = '学生'
    },
    onSubmit: function () {
      let user = {
        id: this.form.id,
        userName: this.form.name,
        className: this.form.className,
        passWord: this.form.password,
        money: 100,
        job: this.form.resource
      }
      var data1 = JSON.parse(localStorage.getItem('token'))
      postRequest('/api/public/addU/' + data1.id, user).then((result) => {
        if (result.data.code === 0) {
          alert(result.data.msg)
        } else if (result.data.code === 200) {
          console.log(result)
          this.$message('注册了!')
        }
      }).catch(e => {
        console.log(e)
      })
    }
  }
}
</script>

<style scoped>
  .line{
    text-align: center;
  }
  .app-container{
    width: 800px;
    margin-left: 340px;
    margin-top: 130px;
  }
</style>
