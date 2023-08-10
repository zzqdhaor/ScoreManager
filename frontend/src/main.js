

import { createApp } from 'vue'
import App from './App.vue'
import naive from 'naive-ui'
import {createRouter, createWebHistory} from "vue-router";
import Login from "@/components/Login.vue";
import AdminMainPage from "@/components/admin/AdminMainPage.vue";
import UserManage from "@/components/admin/UserManage.vue";
import CourseManage from "@/components/admin/CourseManage.vue";
import TeachManage from "@/components/admin/TeachManage.vue";
import TeacherMainPage from "@/components/teacher/TeacherMainPage.vue";
import RecordScore from "@/components/teacher/RecordScore.vue";
import StudentMainPage from "@/components/student/StudentMainPage.vue";
import StudentView from "@/components/student/StudentView.vue";
const app = createApp(App)
const routes = [{
    path: "/",
    redirect: "/login",
    children: [
        {
            path: "/login",
            component: Login
        },
        {
            path: "/admin",
            component: AdminMainPage,
            redirect: "/admin/user",
            children: [
                {
                    path: "user",
                    component: UserManage
                },
                {
                    path: "course",
                    component: CourseManage
                },
                {
                    path: "teach",
                    component: TeachManage
                }
            ]
        },
        {
            path: "/teacher",
            component: TeacherMainPage,
            redirect: "/teacher/record",
            children: [
                {
                    path: "record",
                    component: RecordScore
                }
            ]
        },
        {
            path: "/student",
            component: StudentMainPage,
            redirect: "/student/view",
            children: [
                {
                    path: "view",
                    component: StudentView
                }
            ]
        }
    ]
}]
const router = createRouter({history: createWebHistory(), routes})
app.use(naive)
app.use(router)
app.mount("#app")
