package com.kaishengit.servlet;

import org.patchca.color.SingleColorFactory;
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
    private static final long serialVersionUID = 1450380513870002760L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取验证码服务，需导入patcha包
        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        //字体颜色
        cs.setColorFactory(new SingleColorFactory(new Color(38, 38, 255)));
        //曲线波纹，颜色会与字体颜色相同
       // cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));

        //设置验证码属性
        RandomWordFactory wordFactory = new RandomWordFactory();
        wordFactory.setMinLength(4);
        wordFactory.setMaxLength(4);
        wordFactory.setCharacters("0123456789");

        //将验证码属性保存
        cs.setWordFactory(wordFactory);

        //设置验证码字体样式
        FontFactory fontFactory = new FontFactory() {
            @Override
            public Font getFont(int i) {
                return new Font("微软雅黑",Font.BOLD,40);
            }
        };
        //将验证码字体样式保存
        cs.setFontFactory(fontFactory);

        //通过response对象获取响应输出流
        OutputStream outputStream = resp.getOutputStream();
        String code = EncoderHelper.getChallangeAndWriteImage(cs, "png", outputStream);

        //将产生的验证码放入的session中
        HttpSession session = req.getSession();
        session.setAttribute("patchca",code);

        outputStream.flush();
        outputStream.close();



    }
}
