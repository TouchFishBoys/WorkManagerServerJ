#ToDo-List
###登录
- [x] 学生/教师登录           POST  /auth/login { username:string, password:string, role: ERole }
- [ ] 修改密码               PATCH /auth/password
----
###学生操作   
学生信息从 Token 中获取
- 学生个人信息
  - [x] 获取学生信息(姓名,班级)                  GET /student/:stuId?selectedColumn=[]
- 作业管理
  - 平时作业 (TopicController)
    - [x] 获取题目信息                         GET  /topic/:topicId
    - [ ] 上传平时作业                         POST /topic/:topicId/file
  - 大作业 (FinalWorkController)
    - [x] 获取大作业信息                       GET  /final-work/:teamId
    - [ ] 上传大作业文件                       POST /final-work/:teamId/file
- 课程管理
  - [x] 获取已选课程列表                       GET /course
  - [x] 获取某课程的信息                       GET /course/:courseId
  - [x] 获取课程题目列表                       GET /course/:courseId/topic
----
###教师操作
- 教师个人信息
  - [ ] 获取教师信息             GET /teacher/:teacherId
- 学生管理
  - [ ] 导入学生信息             POST  /course/:courseId/student?courseName=
  - [ ] 获取学生信息             GET   /course/:courseId/student
- 作业管理
  - 平时作业
    - [x] 获取题目列表           GET  /course/:courseId/topic //同上
    - [x] 获取题目信息           GET  /topic/:topicId //同上
    - [x] 发布平时作业           POST /course/:courseId/topic
    - [ ] 平时作业信息           GET  /topic/:topicId/:studentId //生成图表
    - [ ] 下载平时作业           GET  /topic/:topicId/:studentId/file
    - [ ] 平时作业评分           POST /topic/:topicId/:stidentId/score
  - 大作业
    - [ ] 获取大作业列表         GET  /course/:courseId/final-work
    - [ ] 获取大作业信息         GET  /final-work/:finalId  
    - [x] 设置大作业分数         POST /final-work/:finalId/score
    - 文件相关
      - [ ] 下载大作业文件       GET /final-work/:finalId/file
    - 文档相关
      - [ ] 下载大作业文档       GET  /final-work/:finalId/document
      - [ ] 大作业文档评分       POST /final-work/:finalId/document/score
    - 答辩相关
      - [ ] 获取小组的成员       GET  /team/:teamId/student
      - [ ] 获取答辩记录表       GET  /qa-table/:courseId/:studentId      //Json
      - [x] 下载答辩记录表       GET  /qa-table/:courseId/:studentId/file //Word
      - [ ] 提交答辩记录表       POST /qa-table/:courseId/:studentId
- 课程管理
  - [ ] 获取某课程信息           GET /course/:courseId
----
###存储目录结构
/storage
  - :courseId
    - normal
      - :topicId
        - :studentId
    - final
      - :teamId
        - document
        - file
        - qa-table.docx