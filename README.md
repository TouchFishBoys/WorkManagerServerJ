#ToDo-List
###登录
- [x] 学生/教师登录                              POST /auth/login { username:string, password:string, role: ERole }
----
###学生操作
- 学生个人信息
  - [ ] 获取学生信息(姓名,班级,密码)                 GET /student-info/:stuId
- 作业管理
  - [ ] 获取课程列表                               GET /student/:studentId/course +
  - [ ] 平时作业
    - [ ] 获取选定课程下的课题列表                   GET /course/:courseId/exp-task    /
    - [ ] 获取某课题的信息                         GET /course/:courseId/exp-task/:topicId  //
    - [ ] 上传平时作业                             POST /student/:stuId/exp-task/:topicId/file
    - [ ] 下载某个实验作业                          GET /exp-task/:expId/file        ///
  - [ ] 大作业
    - [ ] 获取上传大作业信息(包括下载链接)             GET /final-task/:finalId        ////
    - [ ] 上传大作业                               POST /group/:groupId/final-task/file
    - [ ] 下载大作业文件                            GET /final-task/:finalId/file  /////
- 课程管理
  - [ ] 获取课程列表                               GET /student/:studentId/course +
  - [ ] 选课                                     POST /student/:studentId/course/:courseId
  - [ ] 获取某课程的信息                           GET /course/:courseId   //////
----
###教师操作
- 教师个人信息
  - [ ] 获取教师信息(姓名,电话,密码等)               GET /teacher-info/:teacherId
- 学生管理
  - [ ] 获取课程列表                              GET /teacher/:teacherId/course   ++        
  - [ ] 获取某门课学生信息                         GET /student-info/:courseId
  - [ ] 导入某门课学生信息(EXCEL)                  POST /course/:courseId
- 作业管理
  - [ ] 获取课程列表                              GET /teacher/:teacherId/course   ++
  - [ ] 平时作业
    - [ ] 获取选定课程下的课题列表                  GET /course/:courseId/exp-task   /
    - [ ] 获取某课题的信息                        GET /course/:courseId/exp-task/:topicId  //
    - [ ] 某门课某课题平时作业发布                  POST /course/:courseId/exp-task/:topicId
    - [ ] 下载某个实验作业                         GET /exp-task/:expId/file      ///
    - [ ] 实验作业评分                            POST /exp-task/:expId/score
  - 大作业
    - [ ] 获取该门课已上传大作业列表                 GET /course/:courseId/final-task
    - [ ] 获取某个大作业信息(包括下载链接)            GET /final-task/:finalId     ////
    - 文档相关
      - [ ] 下载某个大作业文件                      GET /final-task/:finalId/file  /////
      - [ ] 大作业文档评分                         POST /final-task/:finalId/score
    - 答辩相关
      - [ ] 获取某门课程某学生的答辩记录表            GET /student/:stuId/course/:courseId/qa-form
      - [ ] 提交学生的答辩记录表                    POST /student/:stuId/course/:courseId/qa-form
      - [ ] 下载某学生的答辩记录表                  GET /student/:stuId/course/:courseId/qa-form/file
- 课程管理
  - [ ] 获取课程列表                              GET /teacher/:teacherId/course   ++
  - [ ] 开课                                    POST /teacher/:teacherId/course   
  - [ ] 获取某课程的信息                           GET /course/:courseId    //////
----


