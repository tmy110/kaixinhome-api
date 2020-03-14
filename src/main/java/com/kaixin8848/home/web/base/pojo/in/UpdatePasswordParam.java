package com.kaixin8848.home.web.base.pojo.in;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "UpdatePasswordParam", description = "修改密码参数")
@Data
public class UpdatePasswordParam {


    @ApiModelProperty(value = "验证码")
    private String verificationCode;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 密码
     */
    @ApiModelProperty(value = "新密码")
    private String password;


}
