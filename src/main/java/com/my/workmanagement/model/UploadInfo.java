package com.my.workmanagement.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel(description = "处理后上传文件的信息")
@Data
@NoArgsConstructor
public class UploadInfo implements Serializable {
    private boolean success;
    private boolean exists;
}