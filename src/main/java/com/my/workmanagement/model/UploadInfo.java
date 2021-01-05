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
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "上传结果")
    private String result;

    @ApiModelProperty(value = "初始文件名")
    private String beginFileName;

    @ApiModelProperty(value = "最终上传文件名")
    private String lastFileName;

    @ApiModelProperty(value = "文件类型")
    private String FileType;

    @ApiModelProperty(value = "文件大小")
    private String fileSize;

    @ApiModelProperty(value = "文件上传的地址")
    private String uploadUrl;

}