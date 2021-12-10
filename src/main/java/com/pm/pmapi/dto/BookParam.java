package com.pm.pmapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: pmApi
 * @description: book parameter
 * @author: Shen Zhengyu
 * @create: 2021-12-07 14:05
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookParam {
    private Long id;
    private String lessonId;
    private MultipartFile picture;
    private String bookname;
    private String author;
    private String content;
    private String publisher;
    private String newDegree;
    private Double price;
}
