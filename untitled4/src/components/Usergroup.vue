<template>
  <div class="app-container">
<!--    <div-->
<!--      style="box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);background:white;padding-top:20px"-->
<!--    >-->
      <div class="department-top" style="padding-left:20px">
        <el-row :gutter="20">
          <el-col :span="4">
            <el-input v-model="searchParam" placeholder="输入查询信息"></el-input>
          </el-col>
          <el-col :span="3">
            <el-button type="primary" @click="search">查询</el-button>
          </el-col>
        </el-row>
      </div>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="ID" width="95" prop="id">
      </el-table-column>
      <el-table-column label="班级"  width="60" prop="className">
      </el-table-column>
      <el-table-column label="姓名" width="60" align="center" prop="userName">
      </el-table-column>
      <el-table-column label="密码" width="60" align="center" prop="passWord">
      </el-table-column>
      <el-table-column class-name="status-col" label="身份" width="60" align="center" prop="job">
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="剩余金额" width="100">
        <template slot-scope="scope">
          <i class="el-icon-money" />
          <span>{{ scope.row.money }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="primary"  @click="up(scope.row)">修改</el-button>
          <el-button type="danger" @click="del(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!---->
    <el-dialog title="修改用户" :visible.sync="dialogTableVisible">
      <el-form :model="user">
        <el-form-item label="ID" :label-width="formLabelWidth">
          <el-input v-model="user.id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="真实姓名" :label-width="formLabelWidth">
        <el-input v-model="user.userName" autocomplete="off"></el-input>
      </el-form-item>
        <el-form-item label="用户密码" :label-width="formLabelWidth">
          <el-input v-model="user.passWord" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="班级" :label-width="formLabelWidth">
          <el-input v-model="user.className" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="金额" :label-width="formLabelWidth">
          <el-input v-model="user.money" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="身份" :label-width="formLabelWidth">
          <el-radio-group v-model="user.job">
            <el-radio label="管理员" />
            <el-radio label="学生" />
            <el-radio label="教师" />
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTableVisible = false">取 消</el-button>
        <el-button type="primary" @click="upUser">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {postRequest} from '../main'

export default {
  data () {
    return {
      searchParam: '',
      dialogTableVisible: false,
      formLabelWidth: '120px',
      list: null,
      listLoading: true,
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
  created () {
    this.fetchData()
  },
  methods: {
    fetchData () {
      var data1 = JSON.parse(localStorage.getItem('token'))
      this.listLoading = true
      postRequest('/api/public/showallU', {useId: data1.id}).then((result) => {
        console.log(result)
        if (result.data.code === 200) {
          this.list = result.data.data
          console.log(this.list)
        } else if (result.data.code === -1) {
          alert(result.data.msg)
        }
        this.listLoading = false
      }).catch(e => {
        console.log(e)
      })
    },
    search () {
      var data1 = JSON.parse(localStorage.getItem('token'))
      if (this.searchParam != null && this.searchParam !== '') {
        this.listLoading = true
        postRequest('/api/public/selectU', {searchParam: this.searchParam, useId: data1.id}).then((result) => {
          console.log(result)
          if (result.data.code === 200) {
            this.list = result.data.data
            console.log(this.list)
          } else if (result.data.code === -1) {
            alert(result.data.msg)
          }
          this.listLoading = false
        }).catch(e => {
          console.log(e)
        })
      } else {
        this.fetchData()
      }
    },
    del (row) {
      var data1 = JSON.parse(localStorage.getItem('token'))
      postRequest('/api/public/delU', {
        id: row.id,
        useId: data1.id
      }).then((result) => {
        console.log(result)
        if (result.data.code === 200) {
          console.log('删除成功！')
          this.fetchData()
        } else if (result.data.code === -1) {
          alert(result.data.msg)
        }
      }).catch(e => {
        console.log(e)
      })
    },
    up (row) {
      this.dialogTableVisible = true
      this.user.id = row.id
      this.user.userName = row.userName
      this.user.passWord = row.passWord
      this.user.money = row.money
      this.user.job = row.job
      this.user.className = row.className
    },
    upUser () {
      var data1 = JSON.parse(localStorage.getItem('token'))
      let upuser = {
        id: this.user.id,
        userName: this.user.userName,
        className: this.user.className,
        passWord: this.user.passWord,
        money: this.user.money,
        job: this.user.job
      }
      postRequest('/api/public/upU/' + data1.id, upuser).then((result) => {
        console.log(result)
        if (result.data.code === 200) {
          this.$message(`${result.data.msg}`)
          this.fetchData()
        } else if (result.data.code === -1) {
          alert(`${result.data.msg}`)
        }
      }).catch(e => {
        console.log(e)
      })
      console.log(upuser)
      this.dialogTableVisible = false
    }
  }

  // fetchData () {
  //   this.listLoading = true
  //   postRequest().then(response => {
  //     this.list = response.data.items
  //     this.listLoading = false
  //   })
  // }
}

</script>

<style scoped>
.app-container{
  width: 800px;
  margin-left: 340px;
  margin-top: 130px;
}
</style>
