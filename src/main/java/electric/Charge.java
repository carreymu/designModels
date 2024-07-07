package electric;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Charge {
    public static void main(String[] args) {
        testPeriods();
    }

    public static void testPeriods(){
        // 创建时段配置
        Set<TouPeriodDTO> periods = new HashSet<>();
        periods.add(new TouPeriodDTO(TouState.OFF_PEAK, LocalTime.of(0, 0), LocalTime.of(6, 0)));
        periods.add(new TouPeriodDTO(TouState.SHOULDER, LocalTime.of(6, 0), LocalTime.of(8, 0)));
        periods.add(new TouPeriodDTO(TouState.PEAK, LocalTime.of(8, 0), LocalTime.of(12, 0)));
        periods.add(new TouPeriodDTO(TouState.CRITICAL_PEAK, LocalTime.of(12, 0), LocalTime.of(14, 0)));
        periods.add(new TouPeriodDTO(TouState.PEAK, LocalTime.of(14, 0), LocalTime.of(15, 0)));
        periods.add(new TouPeriodDTO(TouState.SHOULDER, LocalTime.of(15, 0), LocalTime.of(18, 0)));
        periods.add(new TouPeriodDTO(TouState.PEAK, LocalTime.of(18, 0), LocalTime.of(21, 0)));
        periods.add(new TouPeriodDTO(TouState.SHOULDER, LocalTime.of(21, 0), LocalTime.of(22, 0)));
        periods.add(new TouPeriodDTO(TouState.OFF_PEAK, LocalTime.of(22, 0), LocalTime.of(23, 59)));

        // 创建电价配置
        Set<TouPriceDTO> prices = new HashSet<>();
        prices.add(new TouPriceDTO(TouState.OFF_PEAK, 0.31));
        prices.add(new TouPriceDTO(TouState.SHOULDER, 0.76));
        prices.add(new TouPriceDTO(TouState.PEAK, 0.92));
        prices.add(new TouPriceDTO(TouState.CRITICAL_PEAK, 1.20));

        // 创建每日峰谷时段与电价配置
        TouDTO touDTO = new TouDTO(LocalDate.now(), periods, prices);

        // 创建设备参数
        DeviceSpecDTO deviceSpecDTO = new DeviceSpecDTO(100.0, 50.0);

        // 创建调度上下文
        DispatchContext context = new DispatchContext(touDTO, deviceSpecDTO);

        // 获取单日最优充放电指令列表
        List<DispatchCommand> commands = DispatchScheduler.dispatch(context);

        // 打印结果
        commands.forEach(System.out::println);
    }
}
