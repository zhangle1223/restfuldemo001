package com.offcn.controller;

import com.offcn.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("user")
@Api(tags = "用户处理接口")
public class UserController {

    //创建一个全局属性集合，当成数据库
    private List<User> list= Collections.synchronizedList(new ArrayList<>());

    //新增用户
    @PostMapping("/")
    @ApiOperation(tags = "新增用户",notes = "新增",value = "new")
    @ApiImplicitParam(name = "user",dataType = "User",value = "用户对象",required = true)
    public String add(@RequestBody User user){
        try {
            list.add(user);
            return "add-success";
        } catch (Exception e) {
            e.printStackTrace();
            return "add-fail";
        }
    }

    //查询全部用户
    @GetMapping("/")
    @ApiOperation(tags = "查询全部用户",notes = "查询",value = "query")
    public List<User> findAll(){
        return list;
    }

    //根据用户的id查询指定用户
    @GetMapping("/{id}")
    @ApiOperation(tags = "根据用户的id查询指定用户",notes = "查询指定用户",value = "查询指定用户")
    @ApiImplicitParam(name = "id",dataType = "Long",value = "用户id",required = true)
    public User findOne(@PathVariable("id") Long id){
        for (User user : list) {
            if (user.getId().longValue()==id.longValue()){
                return user;
            }
        }
        return null;
    }

    //根据用户的id更新用户
    @PutMapping("/{id}")
    @ApiOperation(tags = "根据用户的id更新用户",notes = "更新用户",value = "更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",dataType = "Long",value = "用户id",required = true),
            @ApiImplicitParam(name = "user",dataType = "User",value = "用户详细实体user",required = true)
    })
    public String update(@PathVariable("id") Long id,User user){
        for (User user1 : list) {
            if (user1.getId().longValue()==id.longValue()){
                user1.setName(user.getName());
                user1.setAge(user.getAge());
                return "update-success";
            }
        }
        return "update-fail";
    }

    //根据id删除用户数据
    @DeleteMapping("/{id}")
    @ApiOperation(tags = "根据id删除用户数据",notes = "删除用户数据",value = "删除用户数据")
    @ApiImplicitParam(name = "id",dataType = "Long",value = "用户id",required = true)
    public String delete(@PathVariable("id") Long id){
        try {
            list.remove(findOne(id));
            return "delete-success";
        } catch (Exception e) {
            e.printStackTrace();
            return "delete-fail";
        }
    }

}
