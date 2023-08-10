# ScoreManager
使用SpringBoot, Vue, Naive-UI开发的,集成了Mybatis, SpringCache的成绩管理系统.
# 项目介绍
本系统分为三个角色, 管理员, 教师, 学生.
### 管理员
管理员可以添加用户, 删除用户, 无条件重置用户密码, 添加课程, 指定某位老师教学某课程(教学计划).
### 教师
教师可以查看自己所教学的学生成绩, 修改成绩, 查看学生平均分, 及格率, 优秀率等.
### 学生
学生可以查看自己的成绩, 学分, 绩点, 学时等.
# 项目实现:
1. 使用 Spring Interceptor 实现自定义鉴权登录逻辑.
2. 使用 ThreadLocal 简化登录逻辑, 不必每次使用 HttpServletRequest传参.
3. 使用 MyBatis 框架中 for-each 标签实现多个数据插入, 提高了效率.
4. 使用 SQL 触发器实现存在约束的删除.
5. 实现了用户查询的逻辑分页.
6. 实现了用户信息的缓存, 若信息没有变化, 则再次访问时将会从缓存访问.
7. 使用全局异常处理器拦截异常并返回错误信息.
# 项目截图
<img src="https://github.com/zzqdhaor/ScoreManager/blob/main/screenshots/score%20(1).png" width="800"/>
<img src="https://github.com/zzqdhaor/ScoreManager/blob/main/screenshots/score%20(2).png" width="800"/>
<img src="https://github.com/zzqdhaor/ScoreManager/blob/main/screenshots/score%20(3).png" width="800"/>
<img src="https://github.com/zzqdhaor/ScoreManager/blob/main/screenshots/score%20(4).png" width="800"/>
<img src="https://github.com/zzqdhaor/ScoreManager/blob/main/screenshots/score%20(5).png" width="800"/>
<img src="https://github.com/zzqdhaor/ScoreManager/blob/main/screenshots/score%20(6).png" width="800"/>
<img src="https://github.com/zzqdhaor/ScoreManager/blob/main/screenshots/score%20(7).png" width="800"/>
<img src="https://github.com/zzqdhaor/ScoreManager/blob/main/screenshots/score%20(8).png" width="800"/>
<img src="https://github.com/zzqdhaor/ScoreManager/blob/main/screenshots/score%20(9).png" width="800"/>
