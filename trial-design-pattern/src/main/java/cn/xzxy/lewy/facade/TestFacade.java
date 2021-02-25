package cn.xzxy.lewy.facade;

public class TestFacade {

    public static void main(String[] args) {
        FacadeService facadeService = new FacadeService();
        GiftInfo giftInfo = new GiftInfo("《纳什均衡》");
        facadeService.exchange(giftInfo);
    }

    // 校验《纳什均衡》积分通过,库存通过。
    // 扣减《纳什均衡》 积分成功
    // 《纳什均衡》进入物流系统
    // 物流系统下单成功，物流单号是：666
}
