<script setup>

</script>

<template>
  <n-card style="width: 150px; text-align: center; margin: 10px">
    <n-statistic label="您的总学分是" tabular-nums>
      <n-number-animation :precision="1" ref="numberAnimationInstRef" :from="0" :to="totalCourseScore"/>
      <template #suffix>
        分
      </template>
    </n-statistic>
  </n-card>
  <n-table style="margin: 10px">
    <thead>
    <tr>
      <th>科目</th>
      <th>成绩</th>
      <th>任课教师</th>
      <th>学分</th>
      <th>学时</th>
      <th>绩点</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="i in scores" key="i">
      <td>{{i.courseName}}</td>
      <td :style="'color: '+ (i.score > 60 ? 'black':'red')">{{i.score}}</td>
      <td>{{i.teacherName}}</td>
      <td>{{i.score < 60 ? '0' : i.courseScore}}</td>
      <td>{{i.courseTime}}</td>
      <td>{{i.score <= 60 ? 0: (i.score - 60) / 10}}</td>
    </tr>
    </tbody>
  </n-table>
</template>
<script>
import axios from "axios";
import {ref} from "vue";

export default {
  name: 'student_view',
  data() {
    return {
      scores: [],
      totalCourseScore: 0
    }
  },
  setup() {
    const numberAnimationInstRef = ref(0);
    return{
      numberAnimationInstRef
    }
  },
  mounted() {
    let that = this
    axios.get("http://localhost:8080/api/student/scores", {withCredentials: true}).then(r => {
      if (r.data.status === 0) {
        that.scores = r.data.data
        that.scores.forEach(p => {
          if (p.score >= 60)
            that.totalCourseScore += p.courseScore
        })
      }
    })
  }
}
</script>
<style scoped>

</style>