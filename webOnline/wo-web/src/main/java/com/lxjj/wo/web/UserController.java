package com.lxjj.wo.web;

import com.lxjj.wo.common.entity.ResponseResult;
import com.lxjj.wo.entity.User;
import com.lxjj.wo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@Api(tags = "用户接口")
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    @ApiOperation(value = "用户登录", notes = "账号+密码方式登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "账号", required = true, dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string"),
            @ApiImplicitParam(name = "encrypted", value = "是否已加密，true：是，false：否，默认为false", defaultValue = "false", dataType = "boolean")
    })
    public ResponseResult<User> login(
            @RequestParam String userCode,
            @RequestParam String password,
            @RequestParam(required = false, defaultValue = "false") Boolean encrypted
    ) {
        if (!encrypted) {
            try{
                password = DigestUtils.md5DigestAsHex((password).getBytes("utf-8"));
            }catch (UnsupportedEncodingException e){

            }
        }
        Subject subject = SecurityUtils.getSubject();
        // 此处的密码应该是按照后台的加密规则加密过的，不应该传输明文密码
        UsernamePasswordToken token = new UsernamePasswordToken(userCode, password);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return ResponseResult.failure(e.getMessage());
        } catch (LockedAccountException e) {
            return ResponseResult.failure(e.getMessage());
        } catch (DisabledAccountException e) {
            return ResponseResult.failure(e.getMessage());
        }
        User user = (User) subject.getPrincipal();
        subject.getSession().setAttribute("user", user);
        return ResponseResult.success(user);

    }
}
