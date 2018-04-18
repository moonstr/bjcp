package com.bjke.project1.view.lotterytrend;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 * Created by tiny on 2017/10/28.
 * Version:
 */

public class TrendViewData {
    private List<Integer> ballLeft = new ArrayList<>();//左侧的三个数据，最多三个。
    private Integer ballRight;//右侧需要连线的数据。

    public List<Integer> getBallLeft() {
        return ballLeft;
    }

    public void setBallLeft(List<Integer> ballLeft) {
        this.ballLeft = ballLeft;
    }

    public Integer getBallRight() {
        return ballRight;
    }

    public void setBallRight(Integer ballRight) {
        this.ballRight = ballRight;
    }

    @Override
    public String toString() {
        return "TrendViewData{" +
                "ballLeft=" + ballLeft +
                ", ballRight=" + ballRight +
                '}';
    }
}
