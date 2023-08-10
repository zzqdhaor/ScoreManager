<script setup>

</script>

<template>
  <n-modal style="text-align: center;" v-model:show="newCourseDialog" :title="'新建课程'" preset="dialog">
    <n-input style="margin-top: 10px; margin-bottom: 10px; width: 214px" v-model:value="courseName" placeholder="课程名"/>
    <n-space justify="center">
      <div style="display: inline-block">
        <p>学分:</p>
        <n-input-number :min="1" button-placement="both" style="display: inline-block; width: 100px" v-model:value="score"/>
      </div>
      <div style="display: inline-block;">
        <p>学时:</p>
        <n-input-number :min="1" button-placement="both" style="display: inline-block; width: 100px" v-model:value="time"/>
      </div>
    </n-space>
    <br/>
    <n-button style="margin-top: 10px" @click="addCourse">确定</n-button>
  </n-modal>
  <n-space style="margin-bottom: 10px">
    <n-button @click="newCourseDialog = true">新建课程</n-button>
  </n-space>
  <n-table>
    <thead>
    <tr>
      <th>课程号</th>
      <th>课程名</th>
      <th>学分</th>
      <th>学时</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="course in courses" key="course">
      <td>{{ course.id }}</td>
      <td>{{ course.name }}</td>
      <td>{{ course.courseScore }}</td>
      <td>{{ course.courseTime }}</td>
      <td><n-button type="warning" @click="deleteCourse(course.id)">删除</n-button></td>
    </tr>
    </tbody>
  </n-table>

</template>
<script>
import axios from "axios";
import qs from "qs";

export default {
  name: 'course_manager',
  data() {
    return {
      courses: [],
      newCourseDialog: false,
      courseName: '',
      score: 1,
      time: 1
    }
  },
  mounted() {
    this.getAllCourse();
  },
  methods: {
    deleteCourse(id) {
      let that = this
      axios.post("http://localhost:8080/api/course/delete-course", qs.stringify({id: id})).then(r => {
        if (r.data.status === 0) {
          window.$message.success("删除成功")
          that.getAllCourse()
        }
      })
    },
    addCourse() {
      let that = this
      axios.post("http://localhost:8080/api/course/add-course", qs.stringify({name: this.courseName, score: this.score, time: this.time})).then(r => {
        if (r.data.status === 0) {
          window.$message.success("添加成功");
          that.newCourseDialog = false
          that.getAllCourse()
        }
      })
    },
    getAllCourse() {
      let that = this
      axios.get("http://localhost:8080/api/course/all").then(r => {
        that.courses = r.data.data
      })
    }
  }
}
</script>
<style scoped>

</style>