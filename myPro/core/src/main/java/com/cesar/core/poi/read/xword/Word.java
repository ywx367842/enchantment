package com.cesar.core.poi.read.xword;

import lombok.Data;

import java.util.List;

/**
 * @ClassName Word
 * @Description: TODO
 * @Author movit
 * @Date 2020/10/14
 * @Version V1.0
 **/
@Data
public class Word {
    private List<String> directory;
    private List<Div> word;
}
