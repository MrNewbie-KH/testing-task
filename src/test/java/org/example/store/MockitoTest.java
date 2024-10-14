package org.example.store;

import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.List;

public class MockitoTest {

    @Test
    public void test() {
        List list = Mockito.mock(List.class);
        Mockito.when(list.get(Matchers.anyInt())).then(answer -> {
           int index = answer.getArgumentAt(0, Integer.class);
           return "COLOR #" + index;
        });

        list.add("red");
        list.add("green");
        list.add("blue");

        System.out.println(list);

        System.out.println(list.get(0));
        System.out.println(list.get(100));

//        Mockito.verify(list).add("blue");
//        Mockito.verify(list).add("red");

        Mockito.verify(list, Mockito.times(3)).add(Matchers.any());


    }

}
