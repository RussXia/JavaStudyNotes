package com.xzy.shiyanlou;

import java.io.IOException;
import java.util.*;

/**
 * Created by RuzzZZ on 2017/5/17.
 */
public class TestReverse {

    static String results = "";

    public static void main(String[] args) throws IOException {

//        FileOperate fileOperate = new FileOperate();
//        String str = fileOperate.readFile("/Users/RuzzZZ/PersonalWorkspace/IdeaWorkspace/Git/JavaStudyNotes/src/main/java/com/xzy/test/words.txt");
//        String[] strArr = str.split(" ");
//        List<String> strList = Arrays.asList(strArr);
        List<String> strList = new ArrayList<>();
        strList.add("123");
        strList.add("234");
        strList.add("345");
        ReverseList<String> reverseList = new ReverseList(strList);
        Iterable<String> iterable = reverseList.reversed();
        Iterator<String> iterator = iterable.iterator();
        String result = "";
        while(iterator.hasNext()){
            result += iterator.next() + " ";
        }
        System.out.println(result);
//        fileOperate.outFile("/Users/RuzzZZ/PersonalWorkspace/IdeaWorkspace/Git/JavaStudyNotes/src/main/java/com/xzy/test/words.txt",result);
    }
}
