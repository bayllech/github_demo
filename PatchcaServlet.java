package com.kaishengit.web;

import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.font.FontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/patchca.png")
public class PatchcaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new SingleColorFactory(new Color(11, 32, 170)));
        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));


        RandomWordFactory wordFactory = new RandomWordFactory();
        wordFactory.setMinLength(4);
        wordFactory.setMaxLength(4);
        //wordFactory.setCharacters("0123456789中了深刻的房间啦师傅拉萨扩大就卢卡斯的flak时间的flask的距离喀什的加拉塞克地方");

        cs.setWordFactory(wordFactory);

        FontFactory factory = new FontFactory() {
            @Override
            public Font getFont(int i) {
                return new Font("微软雅黑",Font.BOLD,34);
            }
        };

        cs.setFontFactory(factory);

        //!!!!!!!!!!!!!!! 通过Response对象获取响应输出流
        OutputStream outputStream = resp.getOutputStream();
        String code = EncoderHelper.getChallangeAndWriteImage(cs,"png",outputStream);

        //将产生的验证码放入session
        HttpSession session = req.getSession();
        session.setAttribute("patchca",code);

        outputStream.flush();
        outputStream.close();


    }
}
