<script setup>

</script>

<template>
  <div style="height: 100%; text-align: center">
    <n-card style="display: inline-block; margin-top: 20vh; width: 400px; height: 400px" title="成绩管理系统">
      <img height="80" src="/p.png"><br/>
      统一认证登录<br/>
      <n-input style="margin-top: 20px" v-model:value="no" placeholder="学工号"></n-input>
      <n-input style="margin-top: 20px" type="password" v-model:value="password" placeholder="密码"></n-input>
      <n-button @click="login" style="margin-top: 40px">登录</n-button>
    </n-card>
  </div>

</template>
<script>
import axios from "axios";
import qs from "qs";
import {useMessage} from "naive-ui";

export default {
  name: 'login',
  data() {
    return {
      no: '',
      password: ''
    }
  },
  setup() {
    window.$message = useMessage()
  },
  methods: {
    login() {
      window.$message.info("登录中")
      let that = this
      axios.post("http://localhost:8080/api/login", qs.stringify({no: this.no, password: this.password})).then(r => {
        if (r.data.status === 0) {
          window.$message.success("登录成功")
          sessionStorage.setItem("name", r.data.data.split('/')[1])
          if (r.data.data.split('/')[0] === "0") {
            that.$router.replace("/admin")
          } else if (r.data.data.split('/')[0] === "1") {
            that.$router.replace("/teacher")
          } else if (r.data.data.split('/')[0] === "2") {
            that.$router.replace("/student")
          }
        } else window.$message.error("登录失败")
      }).catch(() => {
        window.$message.error("网络错误")
      })
    },
  }
}
</script>
<style scoped>
</style>