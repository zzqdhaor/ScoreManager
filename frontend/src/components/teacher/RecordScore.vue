<script setup>

</script>

<template>
  <n-space justify="center" style="width: 700px" :vertical="false">
    <p style="margin-top: 5px">课程: </p>
    <n-select style="width: 400px" placeholder="请选择任教的课程" :options="options" @update:value="(val) => {getStudentScores(val)}"></n-select>
  </n-space>
  <n-checkbox v-show="showStatistics" style="margin: 20px" label="按成绩排序" :on-update:checked="c => checkedChange(c)"></n-checkbox>
  <n-space size="large" v-show="showStatistics">
    <n-statistic label="参加人数" :value="statistics.number"/>
    <n-statistic label="平均分" v-show="statistics" :value="statistics.averageScore"/>
    <n-statistic label="最高分" v-show="statistics" :value="statistics.highestScore"/>
    <n-statistic label="及格率" v-show="statistics" :value="statistics.passPercentage">
      <template #suffix>
        %
      </template>
    </n-statistic>
    <n-statistic label="优秀率" v-show="statistics" :value="statistics.greatPercentage">
      <template #suffix>
        %
      </template>
    </n-statistic>
  </n-space>
  <n-table v-show="showStatistics">
    <thead>
    <tr>
      <th>学号</th>
      <th>姓名</th>
      <th>分数</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="info in showingInfos">
      <td>{{info.no}}</td>
      <td>{{info.name}}</td>
      <td>
        <n-input-number update-value-on-input @update:value="val => updateScore(val, courseId, info.no)" placeholder="" style="width: 100px" v-model:value="info.score"/>
      </td>
    </tr>
    </tbody>
  </n-table>
</template>
<script>
import axios from "axios";
import qs from "qs";

export default {
  name: 'record_score',
  data() {
    return {
      options: [],
      infos: [],
      showingInfos: [],
      order: false,
      courseId: 0,
      statistics: {
        number: 0,
        passPercentage: 0,
        greatPercentage: 0,
        averageScore: 0,
        highestScore: 0
      },
      showStatistics: false
    }
  },
  methods: {
    checkedChange(c) {
      if (c) {
        this.showingInfos.sort((a, b) => {
          return b.score - a.score
        })
      } else {
        this.showingInfos = this.infos.slice()
      }
    },
    updateScore(score, courseId, no) {
      axios.post("http://localhost:8080/api/teacher/update-score", qs.stringify({score: score == null ? -1 : score, courseId: courseId, studentNo: no}), {withCredentials: true}).then(r => {
        if (r.data.status !== 0) {
          window.$message.error("请注意, 修改失败, 成绩未保存! 请检查网络和登陆状态")
        }
      })
    },
    getStudentScores(id) {
      this.showStatistics = true
      this.courseId = id
      let that = this
      axios.get("http://localhost:8080/api/teacher/get-scores?courseId=" + id, {withCredentials: true}).then(r => {
        if (r.data.status === 0) {
          that.infos = r.data.data
          that.showingInfos = that.infos.slice()
          console.log(that.showingInfos)
        }
      })
      axios.get("http://localhost:8080/api/teacher/get-statistic-info?courseId=" + id).then(r =>{
        if (r.data.status === 0) {
          that.statistics = r.data.data
        } else {
          window.$message.error("获取统计信息失败!")
        }
      })
    }
  },
  mounted() {
    let that = this
    axios.get("http://localhost:8080/api/teacher/get-my-teach", {withCredentials: true}).then(r => {
      if (r.data.status === 0) {
        r.data.data.forEach(i => {
          that.options.push({
            label: i.name,
            value: i.id
          })
        })

      }
    })
  }
}
</script>
<style scoped>

</style>