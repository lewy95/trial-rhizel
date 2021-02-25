package cn.xzxy.lewy.facade;

/**
 * 门面类，提供给 Client 调用
 */
public class FacadeService {

    private QualifyService qualifyService = new QualifyService();
    private PaymentService paymentService = new PaymentService();
    private ShippingService shippingService = new ShippingService();

    // 积分兑换礼物场景
    public void exchange(GiftInfo giftInfo) {
        //资格校验通过
        if (qualifyService.isAvailable(giftInfo)) {
            //如果支付积分成功
            if (paymentService.pay(giftInfo)) {
                // 运行物流下单
                String shippingNo = shippingService.delivery(giftInfo);
                System.out.println("物流系统下单成功，物流单号是：" + shippingNo);
            }
        }
    }
}
