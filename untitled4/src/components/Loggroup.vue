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
      <el-table-column label="用户ID" width="60" align="center" prop="userId">
      </el-table-column>
      <el-table-column label="操作" width="60" align="center" prop="todo">
      </el-table-column>
      <el-table-column label="时间" width="60" align="center" prop="date">
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="danger" @click="del(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!---->
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
      log: {
        id: '',
        userId: '',
        todo: '',
        date: ''
      }
    }
  },
  created () {
    this.fetchData()
  },
  methods: {
    fetchData () {
      this.listLoading = true
      postRequest('/api/public/showallL', {}).then((result) => {
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
      if (this.searchParam != null && this.searchParam !== '') {
        this.listLoading = true
        postRequest('/api/public/selectL', {searchParam: this.searchParam}).then((result) => {
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
      postRequest('/api/public/delL', {
        id: row.id
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
    }
    // up (row) {
    //   this.dialogTableVisible = true
    //   this.log.id = row.id
    //   this.log.userId = row.userId
    //   this.log.todo = row.todo
    //   this.log.date = row.date
    // }
  }
}
</script>

<style scoped>
  .app-container {
    width: 800px;
    margin-left: 340px;
    margin-top: 130px;
  }
</style>
