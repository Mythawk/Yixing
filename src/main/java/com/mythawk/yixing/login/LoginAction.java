package com.mythawk.yixing.login;

import com.mythawk.yixing.user.IUsersService;
import com.mythawk.yixing.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/manage")
public class LoginAction {

    @Autowired
    private IIndexService indexService;

    @Autowired
    private UserService userService;


    @Autowired
    private IUsersService usersService;

    @RequestMapping("index")
    public String index(){
        return "index";
    }
    @RequestMapping("welcome")
    public String welcome(){
        return "/manage/welcome";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public ReturnInfo dogLogin(@RequestBody LoginInfo loginInfo, HttpSession session) {
        //先验证验证码，在传到service验证密码
        ReturnInfo returnInfo = null;
        String verifyCodeValue = (String)session.getAttribute("verifyCodeValue");
        System.out.println(verifyCodeValue);
        if (verifyCodeValue != null && verifyCodeValue.equals(loginInfo.getVerity())) {
            returnInfo = indexService.doLogin(loginInfo);
            if (returnInfo.getErrCode() == 0) {
                session.setAttribute("user",returnInfo.getData());
            }
            else if (loginInfo.getPassword() == null ){
                returnInfo = new ReturnInfo(3,"验证码错误");
            }
        } else {
            returnInfo = new ReturnInfo(2,"验证码错误");
        }
        System.out.println("------------------------------------------");
        System.out.println(loginInfo.getLoginName() + "--" + loginInfo.getVerity());
        return returnInfo;
    }

    /**
     * 获取验证码
     *
     * @param response
     * @param session
     */
    @RequestMapping("/getVerifyCode")
    public void generate(HttpServletResponse response, HttpSession session) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String verifyCodeValue = drawImg(output);

        session.setAttribute("verifyCodeValue", verifyCodeValue);

        try {
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 绘画验证码
     *
     * @param output
     * @return
     */
    private String drawImg(ByteArrayOutputStream output) {
        String code = "";
        // 随机产生4个字符
        for (int i = 0; i < 4; i++) {
            code += randomChar();
        }
        int width = 100;
        int height = 25;
        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Font font = new Font("Microspft YAHEI", Font.PLAIN, 20);
        // 调用Graphics2D绘画验证码
        Graphics2D g = bi.createGraphics();
        g.setFont(font);
        Color color = new Color(82, 33, 69);
        g.setColor(color);
        g.setBackground(new Color(226, 226, 240));
        g.clearRect(0, 0, width, height);
        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(code, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = y - ascent;
        g.drawString(code, (int) x, (int) baseY);
        g.dispose();
        try {
            ImageIO.write(bi, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * 随机参数一个字符
     *
     * @return
     */
    private char randomChar() {
        Random r = new Random();
        String s = "123456789abcdefjhigkmnpqrstuvwxy";
        return s.charAt(r.nextInt(s.length()));
    }

    /**
     * 放回json测试
     */
    @RequestMapping(value = "/redata",method = RequestMethod.GET)
    @ResponseBody
    public List<User> request(){
        User user = userService.findByLoginName("my112");
        List<User> userList = new ArrayList<User>();
        for (int i = 0 ;i<9 ;i++){
            userList.add(user);
        }
        return userList;
    }


}
