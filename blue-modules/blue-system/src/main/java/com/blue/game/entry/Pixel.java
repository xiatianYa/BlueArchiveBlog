package com.blue.game.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pixel {
    //像素的颜色
    private String color;
    //修改时间
    private Date updateTime;
}
