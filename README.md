学生相关：
```
GET /meta/student 学生信息元数据
GET /student/:stuid?selectedColumn=[] 获取学生信息
POST /student/:courseId 导入学生 (EXCEL)
```
平时作业相关：
```
GET /student/:stuid/course/:courseId/normalWork 学生完成的平时作业列表（某课程下）
POST /student/:stuId/course/:courseId/normalWork/:questionId 上传平时作业
GET /student/:stuid/course/:courseId/normalWork/:questionId 某次平时作业信息（下载链接，完成时间等）
GET /sudent/:stuId/course/:courseId/normalWork/:questionId/file 下载某次平时作业
```
答辩相关：
```
GET /student/:stuId/course/:courseId/document 获取学生（stuId）某课程（courseId）的答辩记录的信息（完成时间等）
POST /student/:stuId/course/:courseId/document 提交学生的答辩
GET /student/:stuId/course/:courseId/document/file 下载答辩记录
```
----
老师相关：
```
GET /meta/teacher 教师信息元数据
GET /teacher/:teacherId?selectedColumn=[] 获取教师信息
GET /teacher/:teacherId/course 获取某个老师开设的课程列表*
```
---
大作业相关：
```
POST /group/:groupId/finalWork 上传大作业
GET /group/:groupId/finalWork 获取大作业信息（下载链接，完成时间等）
GET /group/:groupId/finalWork/file 大作业文件下载
```
