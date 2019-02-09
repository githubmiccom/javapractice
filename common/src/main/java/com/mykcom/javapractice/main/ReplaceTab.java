package com.mykcom.javapractice.main;


import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ReplaceTab {

    @FunctionalInterface
    private interface Replacer{
        String replace();
    }

    private String replaceTemplate(String str, int spaceCnt, Replacer replacer){
        checkArguments(str, spaceCnt);
        return replacer.replace();
    }

    public String replaceTabToSpaces(String str, int spaceCnt)
            throws IllegalArgumentException{
        return replaceTemplate(str, spaceCnt, new Replacer(){
            public String replace(){
                String alter = buildSpaces(spaceCnt);
                return str.replace("\t", alter);
            }
        });
    }

    public String replaceHaedTabToSpace(String str, int spaceCnt)
            throws IllegalArgumentException{
        return replaceTemplate(str, spaceCnt, ()-> {
            String alter = buildSpaces(spaceCnt);
            String[] lines = str.split("\\r?\\n");
            List<String> processed = new ArrayList<>();
            for (String line : lines) {
                if ('\t' == line.charAt(0)) {
                    processed.add(alter + line.substring(1));
                } else {
                    processed.add(line);
                }
            }
            return processed.stream().collect(Collectors.joining());
        });
    }

    public String replaceSpacesToTab(String str, int spaceCnt){
        return replaceTemplate(str, spaceCnt, ()->{
            String spaces = buildSpaces(spaceCnt);
            return str.replaceAll(spaces, "\t");
        });
    }

    public String replaceHeadSpacesToTab(String str, int spaceCnt){
        return replaceTemplate(str, spaceCnt, new Replacer(){
            public String replace() {
                Pattern pattern = Pattern.compile("(^[\\s]{4})(.*$)");
                String []lines = str.split("\\r?\\n");
                List<String> processed = new ArrayList<>();
                for(String line : lines){
                    Matcher m = pattern.matcher(line);
                    if(m.matches()){
                        System.out.println("Matched "+ line);
                        for(int i = 0; i < m.groupCount(); ++i){
                            System.out.println(m.group(i));
                        }
                        System.out.println("Matched "+ line + "end");
                        processed.add("\t"+ m.group(2));
                    }else{
                        processed.add(line);
                    }
                }
                return processed.stream().collect(Collectors.joining());
            }
        });
    }

    private void checkArguments(String str, int spaceCnt) throws IllegalArgumentException{

        if(1 > spaceCnt ){
            throw new IllegalArgumentException("spaceCnt should be greater than 0");
        }

        if(null == str){
            throw new IllegalArgumentException("str is null");
        }
    }

    private String buildSpaces(int spaceCnt) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < spaceCnt; ++i){
            sb.append(" ");
        }
        return sb.toString();
    }
}
