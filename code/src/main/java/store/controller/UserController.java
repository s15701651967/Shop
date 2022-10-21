package com.bistu.store.controller;

import com.bistu.store.controller.ex.*;
import com.bistu.store.entity.BaseEntity;
import com.bistu.store.entity.User;
import com.bistu.store.entity.UserPurse;
import com.bistu.store.service.IUserPurseService;
import com.bistu.store.service.IUserService;
import com.bistu.store.service.ex.InsertException;
import com.bistu.store.service.ex.UsernameDuplicateException;
import com.bistu.store.util.JsonResult;
import com.bistu.store.vo.PriorityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController//等价于@Controller+@ResponseBody
//@Controller//当前类交给Spring管理
@RequestMapping("/user")//什么样的请求可以被拦截到此类当中

public class UserController extends BaseController {//在控制层抽离一个父类在父类中统一处理关于异常的相关操作，编写一个BaseController类，统一处理异常
    @Autowired//spring可以自动帮你把bean里面引用的对象的setter/getter方法省略

    private IUserService userService;//UserController依赖于业务层接口

    @Autowired
    private IUserPurseService userPurseService;

    /**
     *1.接收数据方式：请求处理方法的参数列表设置为pojo类型来接收前端数据，
     * SpringBoot会将前端的URL地址中的参数名和pojo类的属性名进行比较，若两个名称相同，将值注入到pojo类中对应的属性上
     */

    @RequestMapping("reg")//什么样的请求可以被拦截到此类当中/user/reg
    //@ResponseBody//将JSON数据写入响应包中    此方法的响应结果以JSON格式进行数据的相应给到前端
    public JsonResult<Void> reg(User user){
        //创建响应结果对象
        JsonResult<Void> result=new JsonResult<>();
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    /**
     * 约定大于配置开发思想完成，省略大量的配置甚至注解的编写
     *2.接收数据方式：请求处理方法的参数列表设置为非自己封装的pojo类型如String类型来接收前端数据，
     * SpringBoot会直接将请求的参数名和方法的参数名直接进行比较，若名称相同则自动完成值的依赖注入
     */

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        //创建响应结果对象
        User data=userService.login(username,password);
        //向session对象中完成数据的绑定（session为全局的）
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());

        //获取session中绑定的数据
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));

        return new JsonResult<User>(OK,data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("change_password_admin")
    public JsonResult<Void> changePasswordByAdmin(Integer uid, String oldPassword,String newPassword,HttpSession session){
        //Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data= userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
        //user对象中有四部分数据 username、phone、email、gender
        //uid数据需要再次封装到user对象中
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return new JsonResult<>(OK);
    }

    @RequestMapping("change_info_admin")
    public JsonResult<Void> changeInfoByAdmin(Integer uid, User user,HttpSession session){
        //user对象中有四部分数据 username、phone、email、gender
        //uid数据需要再次封装到user对象中
//        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return new JsonResult<>(OK);
    }

    /**设置上传文件的最大值*/
    public static final int AVATAR_MAX_SIZE=10*1024*1024;

    /**限制上传文件的类型*/
    public static final List<String> AVATAR_TYPE=new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    /**
     * MultipartFile接口是由springMVC提供的接口，该接口为我们包装了获取文件类型的数据（file任何类型的文件)，
     * SpringBoot整合了SpringMVC，只需在处理请求的方法参数列表上声明一个参数类型为MultipartFile的参数，
     * 然后SpringBoot自动将传递给服务的文件数据赋值给该参数
     * @RequestParam表示请求中的参数，将请求中的参数注入到请求处理方法的某个参数上，若名称不一致，则用@RequestParam注解标记和映射
     *
     *
     * @param session
     * @param file
     * @return
     */
    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session,
                                           @RequestParam("file") MultipartFile file){
        //将表单当中的哪个值传递给MultipartFile中的file,解决表单当中的参数名与MultipartFile的变量的参数名不一致
        //解决前后端不一致的问题

        //判断文件是否为NULL
        if(file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if(file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件大小超出限制");
        }
        //判断文件类型是否符合规定的后缀类型
        String contentType=file.getContentType();
        //若集合包含某个元素则返回值true
        if(!AVATAR_TYPE.contains(contentType)){
            throw new FileTypeException("文件类型不支持");
        }

        //上传的文件.../upload/文件/png
        String parent= session.getServletContext().getRealPath("upload");//获取上下文的upload绝对路径
//        String parent =  "D:/SpringWorkSpace/store/src/upload";
        //File对象指向该路径，判断File是否存在
        File dir=new File(parent);
        if(!dir.exists())//检测目录是否存在，若不存在进入if
        {
            dir.mkdirs();//创建当前的目录
        }

        //获取到该文件名称，用UUID工具来生成一个新的字符串作为文件名
        String originalFilename=file.getOriginalFilename();
        System.out.println("OriginalFilename="+originalFilename);
        //获取后缀
        int index=originalFilename.lastIndexOf(".");
        String suffix=originalFilename.substring(index);
        //随机生成前缀  并拼接前后缀
        String filename=UUID.randomUUID().toString().toUpperCase()
                + suffix;//随机生成前缀，全大写

        //指定创建的对象放在哪里 在dir目录结构下创建filename文件
        File dest=new File(dir,filename);  //dest此时为一个空文件
        //方法传入的参数file中的数据写入到该空文件dest中
        try {
            file.transferTo(dest);//将数据写入 将file文件中的数据写入到dest文件中，前提是两文件后缀一致
        }catch (FileStateException e){
            throw new FileStateException("文件状态异常");
        }
        catch (IOException e) {
            throw new FileUploadException("文件读写异常");
        }

        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        //返回头像的路径 相对路径 /upload/test.png
        String avatar="/upload/"+filename;
        userService.changeAvatar(uid,avatar,username);
        //返回用户头像的路径给前端界面，将来用于头像展示使用
        return new JsonResult<>(OK,avatar);
    }

    @RequestMapping("check_all_users")
    public JsonResult<List<User>> checkAllUsers(){
        List<User> data=userService.checkAllUsers();
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("{uid}/pass_user")
    public JsonResult<Void> passUserByUid(@PathVariable("uid") Integer uid){
        userService.passUserByUid(uid);
        return new JsonResult<>(OK);
    }

    @RequestMapping("{uid}/reject_user")
    public JsonResult<Void> rejectUserByUid(@PathVariable("uid")Integer uid){
        userService.rejectUserByUid(uid);
        return new JsonResult<>(OK);
    }

    @RequestMapping("addMoney")
    public JsonResult<Void> addMoneyByUid(Integer addAmount ,HttpSession session){

        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.addMoney(uid,addAmount,username,new Date());
        System.out.println("充值金额："+addAmount);
        return new JsonResult<>(OK);
    }

    @RequestMapping("adminAddMoney")
    public JsonResult<Void> adminAddMoneyByUid(Integer addAmount ,Integer uid){
        userService.addMoney(uid,addAmount,"管理员",new Date());
        System.out.println("充值金额："+addAmount);
        return new JsonResult<>(OK);
    }

    @RequestMapping("businessAddMoney")
    public JsonResult<Void> businessAddMoney(Integer prices ,Integer businessId){
        userService.addMoney(businessId,prices,"系统",new Date());
        System.out.println("商家充值金额："+prices);
        return new JsonResult<>(OK);
    }

    @RequestMapping("userRefund")
    public JsonResult<Void> userRefund(Integer addAmount ,Integer uid){
        userService.addMoney(uid,addAmount,"商家",new Date());
        System.out.println("充值金额："+addAmount);
        return new JsonResult<>(OK);
    }

    @RequestMapping("subMoney")
    public JsonResult<Void> subMoneyByUid(Integer subAmount ,HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.subMoney(uid,subAmount,username,new Date());
        System.out.println("扣除金额："+subAmount);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_money")
    public JsonResult<User> getMoney(HttpSession session){
        User data= userService.getMoney(getuidFromSession(session));
        data.setUsername(getUsernameFromSession(session));
        data.setModifiedUser(getUsernameFromSession(session));
        data.setModifiedTime(new Date());
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("get_aMoney")
    public JsonResult<User> adminGetMoney(Integer uid){
        User data= userService.getMoney(uid);
        User user1= userService.getByUid(uid);
        data.setUsername(user1.getUsername());
        data.setModifiedUser("管理员");
        data.setModifiedTime(new Date());
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("get_bMoney")
    public JsonResult<User> getBMoney(Integer businessId){
        User data= userService.getMoney(businessId);
        data.setModifiedUser("系统");
        data.setModifiedTime(new Date());
        return new JsonResult<>(OK,data);
    }

    /**
     * 用户充值记录
     * @param user
     * @return
     */
    @RequestMapping("purse_insert")
    public JsonResult<Void> recordInsert(UserPurse user){
        //创建响应结果对象
        JsonResult<Void> result=new JsonResult<>();
        userPurseService.insert(user);
        return new JsonResult<>(OK);
    }

    /**
     * 用户扣款记录
     * @param user
     * @return
     */
    @RequestMapping("purse_inserts")
    public JsonResult<Void> recordInserts(UserPurse user){
        //创建响应结果对象
        JsonResult<Void> result=new JsonResult<>();
        userPurseService.inserts(user);
        return new JsonResult<>(OK);
    }

    @RequestMapping("record")
    public JsonResult<List<UserPurse>> recordGet(HttpSession session){
        List<UserPurse> data =userPurseService.getByUid(getuidFromSession(session));
        return new JsonResult<List<UserPurse>>(OK,data);
    }


    @RequestMapping("{uid}/addLevel")
    public JsonResult<Void> addLevel(@PathVariable("uid") Integer uid,HttpSession session){

        userService.addLevel(uid,"管理员",new Date());
        System.out.println("增加等级");
        return new JsonResult<>(OK);
    }

    @RequestMapping("{uid}/subLevel")
    public JsonResult<Void> subLevel(@PathVariable("uid") Integer uid,HttpSession session){

        userService.subLevel(uid,"管理员",new Date());
        System.out.println("减少等级");
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_level")
    public JsonResult<List<User>> getLevel(){
        List<User> data= userService.getLevel();
        return new JsonResult<>(OK,data);

    }

    @RequestMapping("get_levelByBid")
    public JsonResult<User> getBidLevel(Integer businessId){
        User data= userService.getBidLevel(businessId);
        return new JsonResult<>(OK,data);

    }

    @RequestMapping("get_bLevel")
    public JsonResult<PriorityVO> getLevelByBid(Integer businessId){
        PriorityVO data= userService.getLevelByBid(businessId);
        return new JsonResult<>(OK,data);

    }


    @RequestMapping("addPoint")
    public JsonResult<Void> addPoint(Integer point ,HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.addPoint(uid,point,username,new Date());
        System.out.println("增加积分："+point);
        return new JsonResult<>(OK);
    }

    @RequestMapping("subPoint")
    public JsonResult<Void> subPoint(Integer point ,HttpSession session){

        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.subPoint(uid,point,username,new Date());
        System.out.println("扣除积分："+point);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_point")
    public JsonResult<User> getPoint(HttpSession session){
        User data= userService.getPoint(getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }
}

