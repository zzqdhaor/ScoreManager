<script setup>

</script>

<template>
  <n-modal v-model:show="changePassDialog" preset="dialog" title="修改密码">
    <n-space vertical>
      <n-input type="password" v-model:value="oldPass" placeholder="旧密码"></n-input>
      <n-input type="password" v-model:value="newPass" placeholder="新密码"></n-input>
      <n-input type="password" v-model:value="confirmPass" placeholder="确认新密码"></n-input>
      <n-button @click="changePass">确认</n-button>
    </n-space>
  </n-modal>
  <n-layout position="absolute">
    <n-layout-header style="height: 64px" bordered>
      <img style="position: absolute; top: 7px; left: 20px" height="54" src="/p.png">
      <p style="margin-left: 120px; font-size: 20px; margin-top: 20px">
        成绩管理系统学生端
      </p>
      <div style="position:absolute; right: 30px; top: 30px">
        你好, {{name}}
        <n-a style="color: grey" @click="logout">退出</n-a>
        <n-a style="color: grey; margin-left: 5px" @click="changePassDialog = true">修改密码</n-a>
      </div>
    </n-layout-header>
    <n-layout has-sider position="absolute" style="top: 64px; bottom: 64px">
      <n-layout-sider bordered content-style="padding: 4px;">
        <n-menu :options="menuOptions"></n-menu>
      </n-layout-sider>
      <n-layout content-style="padding: 24px;">
        <router-view/>
      </n-layout>
    </n-layout>
    <n-layout-footer
        bordered
        position="absolute"
        style="height: 64px; padding: 24px; text-align: center">
      ScoreManager Developed By ZQ
    </n-layout-footer>
  </n-layout>
</template>
<script>
import {h} from "vue";
import {RouterLink} from "vue-router";
import {useMessage} from "naive-ui";
import axios from "axios";
import qs from "qs";
export default {
  name: "student_page",
  data() {
    return {
      oldPass: '',
      newPass: '',
      confirmPass: '',
      changePassDialog: false
    }
  },
  methods: {
    changePass() {
      if (this.newPass !== this.confirmPass) {
        window.$message.error("两次密码不一致")
        return
      }
      if (this.newPass === '') {
        window.$message.error("密码不能为空")
      }
      let that = this
      axios.post("http://localhost:8080/api/login/change-password", qs.stringify({oldPass: this.oldPass, newPass: this.newPass}, {withCredentials: true})).then(r => {
        if (r.data.status === 0) {
          window.$message.success("密码已更改")
          that.changePassDialog = false
        } else {
          window.$message.error("密码修改失败, 原密码错误")
        }
      })
    },
    logout() {
      sessionStorage.clear()
      this.$router.replace('/login')
    }
  },
  setup() {
    console.log(sessionStorage.getItem("name"))
    let name = sessionStorage.getItem("name")
    window.$message = useMessage()
    let menuOptions = [
      {
        label: () => h(RouterLink, {'to': '/student/view'}, () => "我的成绩"),
        key: "user"
      },
    ]
    return {
      menuOptions: menuOptions,
      name: name
    }
  }
}

</script>
<style scoped>

</style>