package cn.xzxy.lewy.facade;

public class PaymentService {

    public boolean pay(GiftInfo giftInfo) {
        System.out.println("扣减" + giftInfo.getName() + " 积分成功");
        return true;
    }
}
