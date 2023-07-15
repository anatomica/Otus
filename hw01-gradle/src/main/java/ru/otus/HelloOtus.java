package ru.otus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class HelloOtus {

    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<HelloOtus> entities = new ArrayList<>();

        int min = 0, max = 100;
        for (int i = min; i < max; i++) {
            entities.add(HelloOtusBuilder.aHelloOtus().withNumber(i).build());
        }

        ImmutableList<HelloOtus> immutableList = entities.stream().collect(ImmutableList.toImmutableList());

        System.out.println(mapper.writeValueAsString(immutableList));
    }

    private static final class HelloOtusBuilder {

        private Integer number;

        private HelloOtusBuilder() {
        }

        public static HelloOtusBuilder aHelloOtus() {
            return new HelloOtusBuilder();
        }

        public HelloOtusBuilder withNumber(Integer number) {
            this.number = number;
            return this;
        }

        public HelloOtus build() {
            HelloOtus helloOtus = new HelloOtus();
            helloOtus.setNumber(number);
            return helloOtus;
        }
    }
}