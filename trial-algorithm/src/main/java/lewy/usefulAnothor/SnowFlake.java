package lewy.usefulAnothor;

import java.util.Random;

/**
 * 雪花算法 ID生成类
 * 四大组成部分：
 * 固定bit(0): 1 bit
 * 时间戳: 41 bit
 * 机器ID: 10bit 一般来说机房号5位机器号5位，本质还是表示机器数目最多2^10=1024
 * 序列号: 12 bit
 *
 * 返回的是Long型ID，可以根据业务情况
 */
public class SnowFlake {

    Random random = new Random();

    private long dataCenterId; // 即机房号，一般占用
    private long workerId; // 机器号
    private long sequence; // 序列号

    public SnowFlake(long workerId, long dataCenterId, long sequence) {
        // sanity check for workerId 机器号检查
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenterId can't be greater than %d or less than 0", maxDataCenterId));
        }
        System.out.printf("worker starting. timestamp left shift %d, dataCenterId bits %d, worker id bits %d, sequence bits %d, workerId %d\n",
                timestampLeftShift, dataCenterIdBits, workerIdBits, sequenceBits, workerId);

        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.sequence = sequence;
    }

    // 起始时间戳，用于用当前时间戳减去这个时间戳，算出偏移量
    // 可以设置也可以不设置
    private long initTimeStamp = 1288834974657L;

    private long workerIdBits = 5L; // workerId占用的位数：5
    private long dataCenterIdBits = 5L; //dataCenterId占用的位数：5
    private long maxWorkerId = ~(-1L << workerIdBits); // workerId可以使用的最大数值：31  2^5-1
    private long maxDataCenterId = ~(-1L << dataCenterIdBits); // dataCenterId可以使用的最大数值：31  2^5-1
    private long sequenceBits = 12L; // 序列号位数：12
    private long sequenceMask = ~(-1L << sequenceBits); // 序列号最大值4095

    // 偏移量
    private long workerIdShift = sequenceBits; // 机器号偏移12位
    private long dataCenterIdShift = sequenceBits + workerIdBits; // 机房号偏移12位
    private long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits; // 12+5+5=22 时间戳偏移22位

    private long lastTimestamp = -1L;

    public long getWorkerId() {
        return workerId;
    }

    public long getDatacenterId() {
        return dataCenterId;
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * get snowFlake Id
     * @return id
     */
    public synchronized long nextId() {
        long timeStamp = timeGen();

        /**
         * 时钟判断，避免服务器时钟进行回拨
         * 时钟问题是雪花算法的一大缺陷，如果服务器上的时钟进行回溯，那么以后产生的ID是可能出现重复的
         * 因此需要控制时间戳在
         */
        if (timeStamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timeStamp));
        }

        // 如果当前时间戳和上一次请求的时间戳在统一毫秒内，序列号加一
        if (lastTimestamp == timeStamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                // sequence为 0 表示这一毫秒请求量超过4095，那么自旋等待下一毫秒
                sequence = random.nextInt(100);
                timeStamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 若是新的一毫秒，那么从一个[0, 100)的随机数开始
            // 之所以不是每次都从0开始，是因为防止低并发时获取的序列号都类似，如果用序列号作为分片键，可能导致数据倾斜
            sequence = random.nextInt(100);
        }

        lastTimestamp = timeStamp;
        return ((timeStamp - initTimeStamp) << timestampLeftShift) |
                (dataCenterId << dataCenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    // 如果当前时钟时间戳 未达到最后
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    //---------------测试---------------
    public static void main(String[] args) {
        SnowFlake worker = new SnowFlake(1, 1, 1);
        for (int i = 0; i < 30; i++) {
            System.out.println(worker.nextId());
        }
    }
}
