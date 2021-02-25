package cn.xzxy.lewy.facade;

public class ShippingService {

    public String delivery(GiftInfo giftInfo) {
        System.out.println(giftInfo.getName() + "进入物流系统");
        return "666";
    }
}
