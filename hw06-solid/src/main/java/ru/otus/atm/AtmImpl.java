package ru.otus.atm;

import ru.otus.exceptions.NotEnoughMoneyException;
import ru.otus.money.Money;
import ru.otus.money.MoneyType;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.emptyMap;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.deleteWhitespace;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class AtmImpl implements Atm {

    private static Map<MoneyType, List<Money>> TOTAL_MONEY;

    private static Map<MoneyType, List<Money>> TEMP_MONEY;

    public AtmImpl() {
        TOTAL_MONEY = new HashMap<>();
        TEMP_MONEY = new HashMap<>();
    }

    @Override
    public void addMoney(String moniesStr) {
        Map<MoneyType, List<Money>> newMoney = getValues(moniesStr);
        TOTAL_MONEY.putAll(newMoney);
    }

    @Override
    public List<Money> getMoney(String monies) {
        if (isNotBlank(monies)) {
            TEMP_MONEY.clear();
            int needMonies = Integer.parseInt(monies);
            final int[] needMoney = {Integer.parseInt(monies)};
            List<Money> moneyList = new ArrayList<>();

            List<Integer> types = Arrays.stream(MoneyType.values()).map(MoneyType::getValue)
                    .sorted(comparingInt(Integer::intValue).reversed()).toList();

            recalculation(needMoney, moneyList, types);
            return getMonies(needMonies, moneyList);
        }

        return null;
    }

    @Override
    public List<Money> getTotal() {
        List<Money> moneyList = new ArrayList<>();
        Arrays.stream(MoneyType.values()).forEach(type -> {
            List<Money> thisMoneyList = new ArrayList<>(TOTAL_MONEY.getOrDefault(type, new ArrayList<>()));
            if (isNotEmpty(thisMoneyList)) {
                moneyList.addAll(thisMoneyList);
                TOTAL_MONEY.remove(type);
            }
        });
        return moneyList;
    }

    private void recalculation(int[] needMoney, List<Money> moneyList, List<Integer> types) {
        types.forEach(type -> {
            while (needMoney[0] > 0 && needMoney[0] >= type) {
                MoneyType thisType = MoneyType.of(type);
                List<Money> thisMoneyList = new ArrayList<>(TOTAL_MONEY.getOrDefault(thisType, new ArrayList<>()));
                if (isNotEmpty(thisMoneyList)) {
                    moneyList.add(thisMoneyList.get(0));
                    thisMoneyList.remove(0);
                    TEMP_MONEY.put(thisType, thisMoneyList);
                    needMoney[0] -= type;
                } else {
                    break;
                }
            }
        });
    }

    private List<Money> getMonies(int needMonies, List<Money> moneyList) {
        int totalHaveMonies = moneyList.stream().mapToInt(Money::getValue).sum();
        if (needMonies == totalHaveMonies) {
            TEMP_MONEY.forEach((key, values) -> {
                List<Money> tempList = new ArrayList<>(TOTAL_MONEY.getOrDefault(key, new ArrayList<>()));
                values.forEach(value -> tempList.remove(0));
                TOTAL_MONEY.put(key, tempList);
            });
            return moneyList;
        }

        throw new NotEnoughMoneyException("There are no necessary banknotes in the ATM!");
    }

    private Map<MoneyType, List<Money>> getValues(String moneysStr) {
        if (isNotBlank(moneysStr)) {
            List<Integer> intValues = Stream.of(deleteWhitespace(moneysStr).split(",")).map(Integer::parseInt).toList();
            return intValues.stream().collect(groupingBy(MoneyType::of, collectingAndThen(toList(), moneys ->
                    moneys.stream().map(m -> Money.builder().value(m).build()).toList())));
        }
        return emptyMap();
    }

}
