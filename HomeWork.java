package Paircoding;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomeWork
{
    public static void main(String[] args) throws IOException {
        //文字提示


        Scanner sc=new Scanner(System.in);
        System.out.println("请输入题目数量");
        int n= sc.nextInt();//题目数量
        System.out.println("请输入题目中自然数最大值");
        int n1= sc.nextInt();//题目数量中自然数最大值
        System.out.println("请输入题目分数分母最大值");
        int n2= sc.nextInt();//题目数量分数分母最大值
        System.out.println("题目中是否带括号，是请输入0，否请输入1");
        int n3= sc.nextInt();//题目是否带括号
        List<Question> que=new ArrayList<>();//创建存储题目的集合
        for (int i = 0; i <n ; i++) {
            Question qu=new Question(n1,n2,n3);

           que.add(qu);
           // System.out.println(qu);因为只能调用一次toString,所以这里不打印
        }
        sc.close();
        writeE(que);//创建问题文件
        writeD(que);
        writeG(n);

}

    @Test
private static void writeG(int n) throws IOException {
        BufferedWriter bw3=new BufferedWriter(new FileWriter("Grade.txt"));//创建文件
        bw3.write("Correct:"+n+"(");
        bw3.flush();
        for (int i = 0; i < n-1; i++) {
            bw3.write((i+1)+",");
            bw3.flush();
        }
        bw3.write(n+")");
        bw3.flush();
        bw3.newLine();
        bw3.write("Wrong:0");
        bw3.flush();
        bw3.newLine();
        bw3.write("Repeat:0");
        bw3.flush();
        bw3.newLine();
        bw3.write("RepeatDetail:0");
        bw3.close();
    }
    private static void writeD(List<Question> que) throws IOException {//创建答案文件
        BufferedWriter bw2=new BufferedWriter(new FileWriter("Answers.txt"));//缓冲流
        int j=1;
        for (Question ques:que) {
            bw2.write(j+"."+ques.answer());
            j++;
            bw2.newLine();
            bw2.flush();
        }
        bw2.close();
    }

        private static void writeE(List<Question> que) throws IOException {//创建问题文件

        BufferedWriter bw1=new BufferedWriter(new FileWriter("Exercises.txt"));
        int i=1;
        for (Question ques:que
             ) {bw1.write(+i+"."+ques.toString()+"=");//tostring调用一次
            i++;
            bw1.newLine();
            bw1.flush();
        }
    bw1.close();
    }
}