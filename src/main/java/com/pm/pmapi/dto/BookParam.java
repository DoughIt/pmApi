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
    private Long lessonId;
    private String name;
    private String author;
    private String content;
    private String publisher;
    private String newDegree;
    private String filename;
    private Double price;
}
