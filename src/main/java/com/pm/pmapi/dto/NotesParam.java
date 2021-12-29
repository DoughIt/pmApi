package com.pm.pmapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: pmApi
 * @description:
 * @author: Shen Zhengyu
 * @create: 2021-12-07 14:07
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotesParam {
    private Long lessonId;
    private String name;
    private String coverPercentage;
    private Double price;
    private String content;
    private String filename;
}
