package com.wwdy.admin.event;

import com.wwdy.admin.pojo.SliderShow;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author wwdy
 * @date 2022/3/21 11:16
 */
@Getter
public class DelSliderShowEvent extends ApplicationEvent {

    private final SliderShow sliderShow;

    public DelSliderShowEvent(SliderShow sliderShow) {
        super(sliderShow);
        this.sliderShow = sliderShow;
    }
}
