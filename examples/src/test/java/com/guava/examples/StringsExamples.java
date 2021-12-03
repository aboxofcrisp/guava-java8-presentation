package com.guava.examples;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StringsExamples {

    // join 操作
  public void  StringsJoinTest(){
        List<String> words = Lists.newArrayList("123","456","789",null);

        //不使用guava
        StringBuilder sb = new StringBuilder();
        for(String word : words){
            if(word==null){
                sb.append("default");
            }else {
                sb.append(word);
            }
            sb.append(",");
        }
        if(sb.length()>1){
            sb.deleteCharAt(sb.length()-1);
        }
        System.out.println(sb.toString());

        //使用guava
        System.out.println(Joiner.on(",").useForNull("default").join(words));
        System.out.println(Joiner.on(",").skipNulls().join(words));
        System.out.println(Joiner.on("-").skipNulls().join(words));

        Map<String, String> data = ImmutableMap.of("a", "1", "b", "2");
        System.out.println(Joiner.on(",").withKeyValueSeparator("-").join(data));
        //output:a-1,b-2

        Map<String, Integer> data2 = ImmutableMap.of("a", 1, "b", 2);
        System.out.println(Joiner.on(",").withKeyValueSeparator("-").join(data2));
        //output:a-1,b-2
    }

    // split 操作
    public void  StringsSplitTest(){
        System.out.println("------------- part 1 ---------------");
        Splitter.on(",").omitEmptyStrings().splitToList("123,456,789,,23").forEach(a->{
            System.out.println(a);
        });
        System.out.println("------------- part 2 ---------------");
        Splitter.on(",").limit(2).splitToList("123,456,789,,23").forEach(a->{
            System.out.println(a);
        });
        System.out.println("------------- part 3 ---------------");
        Splitter.on(",").trimResults().splitToList("12 3, 456 ,789,,23").forEach(a->{
            System.out.println(a+"|");
        });

        System.out.println("------------- part 4 ---------------");
        Map<String,String> map = Splitter.on(",").withKeyValueSeparator("-").split("1-2,3-5");
        System.out.println(map);

    }

    // char match
    public void  StringsCharTest() {

        System.out.println(CharMatcher.inRange('0','9').retainFrom("asfds12312fds444"));
        //12312444
        System.out.println(CharMatcher.inRange('0','9').removeFrom("asfds12312fds444"));
        //asfdsfds
       //  System.out.println(CharMatcher.inRange('0','9').or(CharMatcher.whitespace()).retainFrom("as fds123 12 fds444"));
        // 123 12 444
    }

    // null
    public void  StringsNullTest() {
        Optional<Integer> possible = Optional.of(5);
        possible.isPresent(); // returns true
        possible.get();  // returns 5

    }


    public static void main(String[] args) {
        StringsExamples stringsExamples = new StringsExamples();
      //   stringsExamples.StringsJoinTest();
        stringsExamples.StringsSplitTest();
        System.out.println("------------- StringsCharTest ---------------");
        stringsExamples.StringsCharTest();

    }
}
