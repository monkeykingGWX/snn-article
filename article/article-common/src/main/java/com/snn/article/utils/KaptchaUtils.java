package com.snn.article.utils;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-29 15:58
 */
public class KaptchaUtils {
    public static final String VERIFY_CODE = "verify_code";

    // 获取Kaptcha实例
    public static DefaultKaptcha getKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties     properties     = new Properties();
        Config         config         = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    // 获取Kaptcha实例
    public static DefaultKaptcha getKaptcha (Properties properties)
    {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    // 创建验证码
    public static void createVerifyCode (
            DefaultKaptcha defaultKaptcha,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException
    {
        byte[]                captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream       = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute(VERIFY_CODE, createText);
            // 使用生成的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    // 创建验证码
    public static void createVerifyCode (
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException
    {
        createVerifyCode(getKaptcha(), request, response);
    }

    // 验证码的校验
    public static boolean checkVerificationCode(
            String verificationCode,
            HttpServletRequest httpServletRequest
    ) {
        String verificationCodeIn = (String) httpServletRequest.getSession().getAttribute(VERIFY_CODE);
        httpServletRequest.getSession().removeAttribute(VERIFY_CODE);
        return Objects.equals(verificationCodeIn, verificationCode);
    }
}
