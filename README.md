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
    - [x] 上传平时作业                         POST /topic/:topicId/file
    - [ ] 获取平时作业                         GET  /topic/:stuId?selectedColumn=
  - 大作业 (FinalWorkController)
    - [x] 获取大作业信息                       GET  /final-work/:teamId
    - [x] 上传大作业文件                       POST /final-work/:teamId/file
- 课程管理
  - [x] 获取已选课程列表                       GET /course
  - [x] 获取某课程的信息                       GET /course/:courseId
  - [x] 获取课程题目列表                       GET /course/:courseId/topic
  - [ ] 创建小组                               post/team/:studentId/:courseId?teamName
  - [ ] 加入小组                               post/team/:studentId/:courseId/:teamId
  - [ ] 获取小组信息                           get /team/:courseId/course
----
###教师操作
- 教师个人信息
  - [x] 获取教师信息             GET /teacher/:teacherId
- 学生管理
  - [ ] 导入学生信息 POST /course/:courseId/student?courseName=
  - [x] 获取学生信息 GET /course/:courseId/student // 同学生
- 作业管理
  - 平时作业
    - [x] 获取题目列表           GET  /course/:courseId/topic //同上
    - [x] 获取题目信息 GET /topic/:topicId //同上
    - [x] 发布平时作业 POST /course/:courseId/topic
    - [x] 下载平时作业 GET /topic/:topicId/:studentId/file
    - [x] 平时作业评分           POST /topic/:topicId/:stidentId/score
  - 大作业
    - [ ] 获取大作业列表 GET /course/:courseId/final-work
    - [x] 获取大作业信息 GET /team/:teamId //同上
    - [x] 设置大作业分数 POST /final-work/:finalId/score
    - 文件相关
      - [x] 下载大作业文件       GET /final-work/:finalId/file
    - 文档相关
      - [x] 下载大作业文档       GET  /final-work/:finalId/document
      - [x] 大作业文档评分       POST /final-work/:finalId/document/score
    - 答辩相关
      - [x] 获取小组的成员       GET  /team/:teamId/student
      - [x] 获取答辩记录表       GET  /qa-table/:courseId/:studentId      //Json //TODO
      - [x] 下载答辩记录表       GET  /qa-table/:courseId/:studentId/file //Word
      - [x] 提交答辩记录表       POST /qa-table/:courseId/:studentId
- 课程管理
  - [x] 获取某课程信息           GET /course/:courseId
----
###存储目录结构
/storage
  - :courseId
    - normal
      - :topicId
        - :studentNum.zip
    - final
      - :teamId
        - document.docx
        - file.war //大作业文件
        - qa-table
          - :studentNum.docx    //答辩记录