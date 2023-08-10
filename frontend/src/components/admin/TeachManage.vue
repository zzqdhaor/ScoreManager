<script setup>

</script>

<template>
  <n-modal title="授课学生" style="text-align: center;" v-model:show="infoDialog" preset="dialog">
    <div style="display: inline-block; text-align: left">
      <p v-for="stu in studentsInfo" key="stu">{{stu.no + " " + stu.name}}</p>
    </div>

  </n-modal>
  <n-modal style="text-align: center;" v-model:show="newCourseDialog" :title="'新建教学计划'" preset="dialog">
    <n-space vertical>
      <n-space style="margin-top: 20px">
        <div style="margin-top: 5px">教师:</div>
        <n-select style="width: 300px" v-model:value="teacherNo" :options="teachers" placeholder="请选择教师" />
      </n-space>
      <n-space>
        <div style="margin-top: 5px">课程:</div>
        <n-select style="width: 300px" v-model:value="courseId" :options="courses" placeholder="请选择课程"/>
      </n-space>
      授课对象
      <n-card style="overflow: auto; height: 200px">
        <n-space justify="center" style="margin-bottom: 10px">
          <n-button @click="selectAll(true)">全选</n-button>
          <n-button @click="selectAll(false)">全不选</n-button>
        </n-space>
        <n-checkbox-group v-model:value="selectedStudents">
          <n-space item-style="display: flex;">
            <div v-for="stu in students" key="stu">
              <n-checkbox style="width: 200px" :value="stu.no" :label="stu.no + '  ' + stu.name"/>
            </div>
          </n-space>
        </n-checkbox-group>
      </n-card>

      <n-button style="margin-top: 10px" @click="addTeach">确定</n-button>
    </n-space>

  </n-modal>
  <n-space style="margin-bottom: 10px">
    <n-button @click="newCourseDialog = true">新建教学计划</n-button>
  </n-space>
  <n-table>
    <thead>
    <tr>
      <th>教学号</th>
      <th>课程号</th>
      <th>课程名</th>
      <th>教师编号</th>
      <th>教师</th>
      <th>授课学生</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="teach in teaches" key="teach">
      <td>{{ teach.id }}</td>
      <td>{{ teach.courseId }}</td>
      <td>{{ teach.courseName }}</td>
      <td>{{ teach.teacherNo }}</td>
      <td>{{ teach.teacherName }}</td>
      <td><n-button @click="getTeachInfo(teach.courseId, teach.teacherNo)">点击查看</n-button></td>
      <td><n-button type="error" @click="removeTeach(teach.courseId, teach.teacherNo)">删除</n-button></td>
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
      teaches: [],
      newCourseDialog: false,
      courseName: '',
      teachers: [],
      courses: [],
      students: [],
      teacherNo: '',
      courseId: '',
      selectedStudents: [],
      infoDialog: false,
      studentsInfo: []
    }
  },
  mounted() {
    this.getAllTeaches()
  },
  methods: {
    selectAll(c) {
      this.selectedStudents = []
      if (c) {
        this.students.forEach(i => {
          this.selectedStudents.push(i.no)
        })
      }
    },
    removeTeach(courseId, teacherNo) {
      let that = this
      this.studentsInfo = []
      axios.post("http://localhost:8080/api/teach/remove-teach", qs.stringify({courseId, teacherNo})).then(r => {
        window.$message.success("删除成功")
        that.getAllTeaches()
      })
    },
    getTeachInfo(courseId, teacherNo) {
      let that = this
      this.studentsInfo = []
      axios.get("http://localhost:8080/api/teach/get-info?courseId" + "=" + courseId + "&teacherNo=" + teacherNo).then(r => {
        that.studentsInfo = r.data.data
        that.infoDialog = true
      })
    },
    deleteCourse(id) {
      let that = this
      axios.post("http://localhost:8080/api/course/delete-course", qs.stringify({id: id})).then(r => {
        if (r.data.status === 0) {
          window.$message.success("删除成功")
          that.getAllCourse()
        }
      })
    },
    addTeach() {
      let that = this
      axios.post("http://localhost:8080/api/teach/add-teach", {teacherNo: this.teacherNo, courseId: this.courseId, students: this.selectedStudents}).then(r => {
        if (r.data.status === 0) {
          window.$message.success("添加成功");
          that.newCourseDialog = false
          that.getAllTeaches()
        }
      })
    },
    getAllTeaches() {
      let that = this
      axios.get("http://localhost:8080/api/teach/all").then(r => {
        that.teaches = r.data.data
      })
      axios.get("http://localhost:8080/api/teach/info").then(r => {
        that.teachers = []
        that.courses = []
        r.data.data.teacher.forEach(r => {
          that.teachers.push({label: r.name, value: r.no})
        })
        console.log(that.teachers)
        r.data.data.course.forEach(r => {
          that.courses.push({label: r.name, value: r.id})
        })
      })
      axios.get("http://localhost:8080/api/teach/students").then(r => {
        that.students = r.data.data
      })
    }
  }
}
</script>
<style scoped>

</style>