package com.practice.testfileupload.annotations;

import com.practice.testfileupload.enmus.FileType;

import java.lang.annotation.*;

/**
 * @author cube.li
 * @date 2021/6/25 20:19
 * @description 文件校验
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface FileCheck {

    /**
     * 校验不通过提示信息
     *
     * @return
     */
    String message() default "不支持的文件格式";

    /*
    校验方式
     */
    CheckType type() default CheckType.SUFFIX;

    /**
     * 支持的文件后缀
     *
     * @return
     */
    String[] supportedSuffixes() default {};

    /**
     * 支持的文件类型
     *
     * @return
     */
    FileType[] supportedFileTypes() default {};

    enum CheckType {
        /**
         * 仅校验后缀
         */
        SUFFIX,
        /**
         * 校验文件头(魔数)
         */
        MAGIC_NUMBER
    }
}