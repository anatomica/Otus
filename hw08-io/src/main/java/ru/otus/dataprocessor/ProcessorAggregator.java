package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        // группирует выходящий список по name, при этом суммирует поля value
        Map<String, Double> groupedMap = data.stream()
                .sorted(comparing(Measurement::name))
                .collect(groupingBy(Measurement::name,
                        collectingAndThen(toList(), pos -> pos.stream()
                                .sorted(comparing(Measurement::name))
                                .mapToDouble(Measurement::value).sum())));

        // сортирует map() по ключу
        return groupedMap.entrySet().stream()
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> newValue, TreeMap::new));
    }

}
