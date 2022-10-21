package Paircoding;
import java.util.Random;
public class Question {
    Random r = new Random();
    String operator1;//运算符1
    int operator11 = r.nextInt(4);//运算符为4分之1，+-×÷，在创建对象时确定
    int operator22 = r.nextInt(4);//运算符为4分之1，+-×÷
    int operator33 = r.nextInt(4);//运算符为4分之1，+-×÷
    String operator2;//运算符2
    String operator3;//运算符3
    public int nummax;//自然数范围
    public int fracmax;//分母范围
    int y;//是否希望出现括号,0带，1为不带
    // int x1 = r.nextInt(2);//在希望出现括号的前提下，本题出现括号的概率50%,为0带，为1不带
    int x123 = r.nextInt(2);//括号出现在12外还是23
    int x1234 = r.nextInt(3);//括号出现在12，23，34外
    public Num num1 ;//运算数1,
    int n1;
    public void num1(){
        if(n1==0){//如果snum没调用过，调用一次，为snum成员赋值
            this.num1= new Num(nummax,fracmax);
            n1=n1+1;
        }
         //nat不是第一次调用，则nat值已有，不用重新赋值
    }
    public Num num2 ;//运算数2
    int n2;
    public void num2(){
        if(n2==0){//如果snum没调用过，调用一次，为snum成员赋值
            this.num2= new Num(nummax,fracmax);
            n2=n2+1;
        }
    }
    public Num num3;//运算数3
    int n3;
    public void num3(){
        if(n3==0){//如果snum没调用过，调用一次，为snum成员赋值
            this.num3= new Num(nummax,fracmax);
            n3=n3+1;
        }
        //nat不是第一次调用，则nat值已有，不用重新赋值

    }
    public Num num4 ;//运算数4
    int n4;
    public void num4(){
        if(n4==0){//如果snum没调用过，调用一次，为snum成员赋值
            this.num4= new Num(nummax,fracmax);
            n4=n4+1;
        }
        //nat不是第一次调用，则nat值已有，不用重新赋值
    }
    int x = r.nextInt(3);//决定运算符个数
    public String toString() {//让toString每次调用相同
        num1();
        num2();
        num3();
        num4();
        switch (operator11) {//确定第一个运算符
            case 0 -> operator1 = "+";
            case 1 -> operator1 = "-";
            case 2 -> operator1 = "×";
            case 3 -> operator1 = "÷";
        }
        switch (operator22) {//确定第2个运算符
            case 0 -> operator2 = "+";
            case 1 -> operator2 = "-";
            case 2 -> operator2 = "×";
            case 3 -> operator2 = "÷";
        }
        switch (operator33) {//确定第3个运算符
            case 0 -> operator3 = "+";
            case 1 -> operator3 = "-";
            case 2 -> operator3 = "×";
            case 3 -> operator3 = "÷";
        }
        if (x == 0) {//1个运算符，不用考虑括号
            return num1 + operator1 + num2;
        }

        if (x == 1) {//2个运算符
            if (y == 0) {//带括号

                //50%可能带
                if (x123 == 0) {//前2个数带括号
                    return "(" + num1 + operator1 + num2 + ")" + operator2 + num3;
                } else {//后2个数带括号
                    return num1 + operator1 + "(" + num2 + operator2 + num3 + ")";
                }
            }
            else {//不带括号
                return num1 + operator1 + num2 + operator2 + num3;
            }
        }
        //2个运算符
        if (x == 2) {//3个运算符
            if (y == 1) {//不带括号
                return num1 + operator1 + num2 + operator2 + num3 + operator3 + num4;
            }
            else {//带括号
                if (x1234 == 0) {////12带括号
                        return "(" + num1 + operator1 + num2 + ")" + operator2 + num3 + operator3 + num4;
                    }
                else if (x1234== 1) {//带括号23带括号
                        return num1 + operator1 + "(" + num2 + operator2 + num3 + ")" + operator3 + num4;
                    }
                else {//34带括号
                        return num1 + operator1 + num2 + operator2 + "(" + num3 + operator3 + num4 + ")";
                    }
                }

            }
        //3个运算符
        return null;
    }
    public Question(int nummax, int fracmax, int y) {//构造方法
        this.nummax = nummax;
        this.fracmax = fracmax;
        this.y = y;
    }
    public Num answer() {
        num1();
        num2();
        num3();
        num4();
        if (x == 0) {//一个运算符
            switch (operator11) {
                case 0:
                    return num1.add(num2);

                case 1:
                    return num1.minus(num2);

                case 2:
                    return num1.mult(num2);

                case 3:
                    return num1.divi(num2);
            }
        }//一个运算符
        if (x == 1) {//2个运算符,3个数
            if (y==1) {//无括号
                switch (operator11) {
                    case 0:
                        switch (operator22) {//+
                            case 0:
                                return num1.add(num2).add(num3);
                            case 1:
                                return num1.add(num2).minus(num3);
                            case 2:
                                return num2.mult(num3).add(num1);//b*c+a
                            case 3:
                                return num2.divi(num3).add(num1);//b/c+a
                        }
                    case 1:
                        switch (operator22) {//-
                            case 0:
                                return num1.minus(num2).add(num3);
                            case 1:
                                return num1.minus(num2).minus(num3);
                            case 2:
                                return num1.minus(num2.mult(num3));//a-b*c
                            case 3:
                                return num1.minus(num2.divi(num3));//a-b/c
                        }
                    case 2:
                        switch (operator22) {//×
                            case 0:
                                return num1.mult(num2).add(num3);//a*b+c
                            case 1:
                                return num1.mult(num2).minus(num3);//a*b-c
                            case 2:
                                return num1.mult(num2).mult(num3);
                            case 3:
                                return num1.mult(num2).divi(num3);
                        }
                    case 3:
                        switch (operator22) {//÷
                            case 0:
                                return num1.divi(num2).add(num3);
                            case 1:
                                return num1.divi(num2).minus(num3);
                            case 2:
                                return num1.divi(num2).mult(num3);
                            case 3:
                                return num1.divi(num2).divi(num3);
                        }
                }
            }//无括号
            else {//有括号

                if (x123 == 0) {//12有括号
                    switch (operator11) {
                        case 0://
                            switch (operator22) {//+
                                case 0:
                                    return num1.add(num2).add(num3);
                                case 1:
                                    return num1.add(num2).minus(num3);
                                case 2:
                                    return num1.add(num2).mult(num3);
                                case 3:
                                    return num1.add(num2).divi(num3);
                            }
                        case 1:
                            switch (operator22) {//-
                                case 0:
                                    return num1.minus(num2).add(num3);
                                case 1:
                                    return num1.minus(num2).minus(num3);
                                case 2:
                                    return num1.minus(num2).mult(num3);
                                case 3:
                                    return num1.minus(num2).divi(num3);
                            }
                        case 2:
                            switch (operator22) {//+
                                case 0:
                                    return num1.mult(num2).add(num3);
                                case 1:
                                    return num1.mult(num2).minus(num3);
                                case 2:
                                    return num1.mult(num2).mult(num3);
                                case 3:
                                    return num1.mult(num2).divi(num3);
                            }
                        case 3:
                            switch (operator22) {//+
                                case 0:
                                    return num1.divi(num2).add(num3);
                                case 1:
                                    return num1.divi(num2).minus(num3);
                                case 2:
                                    return num1.divi(num2).mult(num3);
                                case 3:
                                    return num1.divi(num2).divi(num3);
                            }
                    }
                }
                else {//23括号
                        switch (operator11) {
                            case 0:
                                switch (operator22) {//+
                                    case 0:
                                        return num1.add(num2.add(num3));
                                    case 1:
                                        return num1.add(num2.minus(num3));
                                    case 2:
                                        return num1.add(num2.mult(num3));//a+b*c
                                    case 3:
                                        return num1.add(num2.divi(num3));//a+b/c
                                }
                            case 1:
                                switch (operator22) {//-
                                    case 0:
                                        return num1.minus(num2.add(num3));
                                    case 1:
                                        return num1.minus(num2.minus(num3));
                                    case 2:
                                        return num1.minus(num2.mult(num3));
                                    case 3:
                                        return num1.minus(num2.divi(num3));
                                }
                            case 2:
                                switch (operator22) {//×
                                    case 0:
                                        return num1.mult(num2.add(num3));
                                    case 1:
                                        return num1.mult(num2.minus(num3));
                                    case 2:
                                        return num1.mult(num2.mult(num3));
                                    case 3:
                                        return num1.mult(num2.divi(num3));
                                }
                            case 3:
                                switch (operator22) {//÷
                                    case 0:
                                        return num1.divi(num2.add(num3));
                                    case 1:
                                        return num1.divi(num2.minus(num3));
                                    case 2:
                                        return num1.divi(num2.mult(num3));
                                    case 3:
                                        return num1.divi(num2.divi(num3));
                                }

                        }
                    }//34有括号

                }
            }
        if (x == 2) {//
            if (y == 1) {//不带括号
                switch (operator11) {
                    case 0:
                        switch (operator22) {//+
                            case 0:
                                switch (operator33) {//++
                                    case 0:
                                        return num1.add(num2).add(num3).add(num4);//a+b+c+d
                                    case 1:
                                        return num1.add(num2).add(num3).minus(num4);//a+b+c-d
                                    case 2:
                                        return num1.add(num2).add(num3.mult(num4));//a+b+c*d
                                    case 3:
                                        return num1.add(num2).add(num3.divi(num4));//a+b+c/d
                                }//++
                            case 1:
                                switch (operator33) {//+-
                                    case 0:
                                        return num1.add(num2).minus(num3).add(num4);//a+b-c+d
                                    case 1:
                                        return num1.add(num2).minus(num3).minus(num4);//a+b-c-d
                                    case 2:
                                        return num1.add(num2).minus(num3.mult(num4));//a+b-c*d
                                    case 3:
                                        return num1.add(num2).minus(num3.divi(num4));//a+b-c/d
                                }//+-
                            case 2:
                                switch (operator33) {//+×
                                    case 0:
                                        return num1.add(num2.mult(num3)).add(num4);//a+b*c+d
                                    case 1:
                                        return num1.add(num2.mult(num3)).minus(num4);//a+b*c-d
                                    case 2:
                                        return num1.add((num2.mult(num3)).mult(num4));//a+b*c/d;
                                    case 3:
                                        return num1.add((num2.mult(num3)).divi(num4));//a+b*c/d
                                }//+×
                            case 3:
                                switch (operator33) {//+÷
                                    case 0:
                                        return num1.add(num2.divi(num3)).add(num4);//a+b/c+d
                                    case 1:
                                        return num1.add(num2.divi(num3)).minus(num4);//a+b/c-d
                                    case 2:
                                        return num1.add((num2.divi(num3)).mult(num4));//a+b/c*d;
                                    case 3:
                                        return num1.add((num2.divi(num3)).divi(num4));//a+b/c/d
                                }//+×
                        }
                    case 1:
                        switch (operator22) {//-
                            case 0:
                                switch (operator33) {//-+
                                    case 0:
                                        return num1.minus(num2).add(num3).add(num4);//a-b+c+d
                                    case 1:
                                        return num1.minus(num2).add(num3).minus(num4);//a-b+c-d
                                    case 2:
                                        return num1.minus(num2).add(num3.mult(num4));//a-b+c*d
                                    case 3:
                                        return num1.minus(num2).add(num3.divi(num4));//a-b+c/d
                                }//-+
                            case 1:
                                switch (operator33) {//--
                                    case 0:
                                        return num1.minus(num2).minus(num3).add(num4);//a-b-c+d
                                    case 1:
                                        return num1.minus(num2).minus(num3).minus(num4);//a-b-c-d
                                    case 2:
                                        return num1.minus(num2).minus(num3.mult(num4));//a-b-c*d
                                    case 3:
                                        return num1.minus(num2).minus(num3.divi(num4));//a-b-c/d
                                }//--
                            case 2:
                                switch (operator33) {//+×
                                    case 0:
                                        return num1.minus(num2.mult(num3)).add(num4);//a-b*c+d
                                    case 1:
                                        return num1.minus(num2.mult(num3)).minus(num4);//a-b*c-d
                                    case 2:
                                        return num1.minus((num2.mult(num3)).mult(num4));//a-b*c/d;
                                    case 3:
                                        return num1.minus((num2.mult(num3)).divi(num4));//a-b*c/d
                                }//-×
                            case 3:
                                switch (operator33) {//-÷
                                    case 0:
                                        return num1.minus(num2.divi(num3)).add(num4);//a-b/c+d
                                    case 1:
                                        return num1.minus(num2.divi(num3)).minus(num4);//a-b/c-d
                                    case 2:
                                        return num1.minus((num2.divi(num3)).mult(num4));//a-b/c*d;
                                    case 3:
                                        return num1.minus((num2.divi(num3)).divi(num4));//a-b/c/d
                                }//-×
                        }
                    case 2:
                        switch (operator22) {//*
                            case 0:
                                switch (operator33) {//*+
                                    case 0:
                                        return num1.mult(num2).add(num3).add(num4);//a*b+c+d
                                    case 1:
                                        return num1.mult(num2).add(num3).minus(num4);//a*b+c-d
                                    case 2:
                                        return num1.mult(num2).add(num3.mult(num4));//a*b+c*d
                                    case 3:
                                        return num1.mult(num2).add(num3.divi(num4));//a*b+c/d
                                }//*+
                            case 1:
                                switch (operator33) {//--
                                    case 0:
                                        return num1.mult(num2).minus(num3).add(num4);//a*b-c+d
                                    case 1:
                                        return num1.mult(num2).minus(num3).minus(num4);//a*b-c-d
                                    case 2:
                                        return num1.mult(num2).minus(num3.mult(num4));//a*b-c*d
                                    case 3:
                                        return num1.mult(num2).minus(num3.divi(num4));//a*b-c/d
                                }//*-
                            case 2:
                                switch (operator33) {//+×
                                    case 0:
                                        return num1.mult(num2.mult(num3)).add(num4);//a*b*c+d
                                    case 1:
                                        return num1.mult(num2.mult(num3)).minus(num4);//a*b*c-d
                                    case 2:
                                        return num1.mult((num2.mult(num3)).mult(num4));//a*b*c/d;
                                    case 3:
                                        return num1.mult((num2.mult(num3)).divi(num4));//a*b*c/d
                                }//*×
                            case 3:
                                switch (operator33) {//*÷
                                    case 0:
                                        return num1.mult(num2.divi(num3)).add(num4);//a*b/c+d
                                    case 1:
                                        return num1.mult(num2.divi(num3)).minus(num4);//a*b/c-d
                                    case 2:
                                        return num1.mult((num2.divi(num3)).mult(num4));//a*b/c*d;
                                    case 3:
                                        return num1.mult((num2.divi(num3)).divi(num4));//a*b/c/d
                                }//*/
                        }
                    case 3:
                        switch (operator22) {//÷
                            case 0:
                                switch (operator33) {//÷+
                                    case 0:
                                        return num1.divi(num2).add(num3).add(num4);//a/b+c+d
                                    case 1:
                                        return num1.divi(num2).add(num3).minus(num4);//a/b+c-d
                                    case 2:
                                        return num1.divi(num2).add(num3.mult(num4));//a/b+c*d
                                    case 3:
                                        return num1.divi(num2).add(num3.divi(num4));//a/b+c/d
                                }//÷+
                            case 1:
                                switch (operator33) {//÷-
                                    case 0:
                                        return num1.divi(num2).minus(num3).add(num4);//a/b-c+d
                                    case 1:
                                        return num1.divi(num2).minus(num3).minus(num4);//a/b-c-d
                                    case 2:
                                        return num1.divi(num2).minus(num3.mult(num4));//a/b-c*d
                                    case 3:
                                        return num1.divi(num2).minus(num3.divi(num4));//a/b-c/d
                                }//÷-
                            case 2:
                                switch (operator33) {//÷×
                                    case 0:
                                        return num1.divi(num2.mult(num3)).add(num4);//a/b*c+d
                                    case 1:
                                        return num1.divi(num2.mult(num3)).minus(num4);//a/b*c-d
                                    case 2:
                                        return num1.divi((num2.mult(num3)).mult(num4));//a/b*c/d;
                                    case 3:
                                        return num1.divi((num2.mult(num3)).divi(num4));//a/b*c/d
                                }//÷×
                            case 3:
                                switch (operator33) {//*÷
                                    case 0:
                                        return num1.divi(num2.divi(num3)).add(num4);//a*b/c+d
                                    case 1:
                                        return num1.divi(num2.divi(num3)).minus(num4);//a*b/c-d
                                    case 2:
                                        return num1.divi((num2.divi(num3)).mult(num4));//a*b/c*d;
                                    case 3:
                                        return num1.divi((num2.divi(num3)).divi(num4));//a*b/c/d
                                }//÷/
                        }
                }
            }//不带括号
            else {//带括号
                if (x1234 == 0) {//12带
                    switch (operator11) {
                        case 0:
                            switch (operator22) {//+
                                case 0:
                                    switch (operator33) {//++
                                        case 0:
                                            return num1.add(num2).add(num3).add(num4);//a+b+c+d
                                        case 1:
                                            return num1.add(num2).add(num3).minus(num4);//a+b+c-d
                                        case 2:
                                            return num1.add(num2).add(num3.mult(num4));//a+b+c*d
                                        case 3:
                                            return num1.add(num2).add(num3.divi(num4));//a+b+c/d
                                    }//++
                                case 1:
                                    switch (operator33) {//+-
                                        case 0:
                                            return num1.add(num2).minus(num3).add(num4);//a+b-c+d
                                        case 1:
                                            return num1.add(num2).minus(num3).minus(num4);//a+b-c-d
                                        case 2:
                                            return num1.add(num2).minus(num3.mult(num4));//a+b-c*d
                                        case 3:
                                            return num1.add(num2).minus(num3.divi(num4));//a+b-c/d
                                    }//+-
                                case 2:
                                    switch (operator33) {//+×
                                        case 0:
                                            return num1.add(num2).mult(num3).add(num4);//(a+b)*c+d
                                        case 1:
                                            return num1.add(num2).mult(num3).minus(num4);//a+b*c-d
                                        case 2:
                                            return num1.add(num2).mult(num3.mult(num4));//a+b*c*d;
                                        case 3:
                                            return num1.add(num2).mult(num3.divi(num4));//a+b*c/d
                                    }//+×
                                case 3:
                                    switch (operator33) {//+÷
                                        case 0:
                                            return num1.add(num2).divi(num3).add(num4);//a+b/c+d
                                        case 1:
                                            return num1.add(num2).divi(num3).minus(num4);//a+b/c-d
                                        case 2:
                                            return num1.add(num2).divi(num3).mult(num4);//(a+b)/c*d;
                                        case 3:
                                            return num1.add(num2).divi(num3).divi(num4);//a+b/c/d
                                    }//+×
                            }
                        case 1:
                            switch (operator22) {//-
                                case 0:
                                    switch (operator33) {//-+
                                        case 0:
                                            return num1.minus(num2).add(num3).add(num4);//a-b+c+d
                                        case 1:
                                            return num1.minus(num2).add(num3).minus(num4);//a-b+c-d
                                        case 2:
                                            return num1.minus(num2).add(num3.mult(num4));//a-b+c*d
                                        case 3:
                                            return num1.minus(num2).add(num3.divi(num4));//a-b+c/d
                                    }//-+
                                case 1:
                                    switch (operator33) {//--
                                        case 0:
                                            return num1.minus(num2).minus(num3).add(num4);//a-b-c+d
                                        case 1:
                                            return num1.minus(num2).minus(num3).minus(num4);//a-b-c-d
                                        case 2:
                                            return num1.minus(num2).minus(num3.mult(num4));//a-b-c*d
                                        case 3:
                                            return num1.minus(num2).minus(num3.divi(num4));//a-b-c/d
                                    }//--
                                case 2:
                                    switch (operator33) {//+×
                                        case 0:
                                            return num1.minus(num2).mult(num3).add(num4);//a-b*c+d
                                        case 1:
                                            return num1.minus(num2).mult(num3).minus(num4);//a-b*c-d
                                        case 2:
                                            return num1.minus(num2).mult(num3).mult(num4);//a-b*c/d;
                                        case 3:
                                            return num1.minus(num2).mult(num3).divi(num4);//a-b*c/d
                                    }//-×
                                case 3:
                                    switch (operator33) {//-÷
                                        case 0:
                                            return num1.minus(num2).divi(num3).add(num4);//a-b/c+d
                                        case 1:
                                            return num1.minus(num2).divi(num3).minus(num4);//a-b/c-d
                                        case 2:
                                            return num1.minus(num2).divi(num3).mult(num4);//a-b/c*d;
                                        case 3:
                                            return num1.minus(num2).divi(num3).divi(num4);//a-b/c/d
                                    }//-×
                            }
                        case 2:
                            switch (operator22) {//*
                                case 0:
                                    switch (operator33) {//*+
                                        case 0:
                                            return num1.mult(num2).add(num3).add(num4);//a*b+c+d
                                        case 1:
                                            return num1.mult(num2).add(num3).minus(num4);//a*b+c-d
                                        case 2:
                                            return num1.mult(num2).add(num3.mult(num4));//a*b+c*d
                                        case 3:
                                            return num1.mult(num2).add(num3.divi(num4));//a*b+c/d
                                    }//*+
                                case 1:
                                    switch (operator33) {//--
                                        case 0:
                                            return num1.mult(num2).minus(num3).add(num4);//a*b-c+d
                                        case 1:
                                            return num1.mult(num2).minus(num3).minus(num4);//a*b-c-d
                                        case 2:
                                            return num1.mult(num2).minus(num3.mult(num4));//a*b-c*d
                                        case 3:
                                            return num1.mult(num2).minus(num3.divi(num4));//a*b-c/d
                                    }//*-
                                case 2:
                                    switch (operator33) {//+×
                                        case 0:
                                            return num1.mult(num2.mult(num3)).add(num4);//a*b*c+d
                                        case 1:
                                            return num1.mult(num2.mult(num3)).minus(num4);//a*b*c-d
                                        case 2:
                                            return num1.mult((num2.mult(num3)).mult(num4));//a*b*c/d;
                                        case 3:
                                            return num1.mult((num2.mult(num3)).divi(num4));//a*b*c/d
                                    }//*×
                                case 3:
                                    switch (operator33) {//*÷
                                        case 0:
                                            return num1.mult(num2.divi(num3)).add(num4);//a*b/c+d
                                        case 1:
                                            return num1.mult(num2.divi(num3)).minus(num4);//a*b/c-d
                                        case 2:
                                            return num1.mult((num2.divi(num3)).mult(num4));//a*b/c*d;
                                        case 3:
                                            return num1.mult((num2.divi(num3)).divi(num4));//a*b/c/d
                                    }//*/
                            }
                        case 3:
                            switch (operator22) {//÷
                                case 0:
                                    switch (operator33) {//÷+
                                        case 0:
                                            return num1.divi(num2).add(num3).add(num4);//a/b+c+d
                                        case 1:
                                            return num1.divi(num2).add(num3).minus(num4);//a/b+c-d
                                        case 2:
                                            return num1.divi(num2).add(num3.mult(num4));//a/b+c*d
                                        case 3:
                                            return num1.divi(num2).add(num3.divi(num4));//a/b+c/d
                                    }//÷+
                                case 1:
                                    switch (operator33) {//÷-
                                        case 0:
                                            return num1.divi(num2).minus(num3).add(num4);//a/b-c+d
                                        case 1:
                                            return num1.divi(num2).minus(num3).minus(num4);//a/b-c-d
                                        case 2:
                                            return num1.divi(num2).minus(num3.mult(num4));//a/b-c*d
                                        case 3:
                                            return num1.divi(num2).minus(num3.divi(num4));//a/b-c/d
                                    }//÷-
                                case 2:
                                    switch (operator33) {//÷×
                                        case 0:
                                            return num1.divi(num2.mult(num3)).add(num4);//a/b*c+d
                                        case 1:
                                            return num1.divi(num2.mult(num3)).minus(num4);//a/b*c-d
                                        case 2:
                                            return num1.divi((num2.mult(num3)).mult(num4));//a/b*c/d;
                                        case 3:
                                            return num1.divi((num2.mult(num3)).divi(num4));//a/b*c/d
                                    }//÷×
                                case 3:
                                    switch (operator33) {//*÷
                                        case 0:
                                            return num1.divi(num2.divi(num3)).add(num4);//a*b/c+d
                                        case 1:
                                            return num1.divi(num2.divi(num3)).minus(num4);//a*b/c-d
                                        case 2:
                                            return num1.divi((num2.divi(num3)).mult(num4));//a*b/c*d;
                                        case 3:
                                            return num1.divi((num2.divi(num3)).divi(num4));//a*b/c/d
                                    }//÷/
                            }
                    }
                }//12带
                if (x1234 == 1) {//23带
                    switch (operator11) {
                        case 0:
                            switch (operator22) {//+
                                case 0:
                                    switch (operator33) {//++
                                        case 0:
                                            return num1.add(num2).add(num3).add(num4);//a+b+c+d
                                        case 1:
                                            return num1.add(num2).add(num3).minus(num4);//a+b+c-d
                                        case 2:
                                            return num1.add((num2.add(num3)).mult(num4));//a+(b+c)*d
                                        case 3:
                                            return num1.add((num2.add(num3)).divi(num4));//a+b+c/d
                                    }//++
                                case 1:
                                    switch (operator33) {//+-
                                        case 0:
                                            return num1.add(num2).minus(num3).add(num4);//a+b-c+d
                                        case 1:
                                            return num1.add(num2.minus(num3)).minus(num4);//a+b-c-d
                                        case 2:
                                            return num1.add((num2.minus(num3)).mult(num4));//a+b-c*d
                                        case 3:
                                            return num1.add((num2.minus(num3)).divi(num4));//a+b-c/d
                                    }//+-
                                case 2:
                                    switch (operator33) {//+×
                                        case 0:
                                            return num1.add(num2.mult(num3)).add(num4);//a+b*c+d
                                        case 1:
                                            return num1.add(num2.mult(num3)).minus(num4);//a+b*c-d
                                        case 2:
                                            return num1.add(num2.mult(num3.mult(num4)));//a+b*c*d;
                                        case 3:
                                            return num1.add(num2.mult(num3.divi(num4)));//a+b*c/d
                                    }//+×
                                case 3:
                                    switch (operator33) {//+÷
                                        case 0:
                                            return num1.add(num2.divi(num3)).add(num4);//a+b/c+d
                                        case 1:
                                            return num1.add(num2.divi(num3)).minus(num4);//a+b/c-d
                                        case 2:
                                            return num1.add(num2.divi(num3).mult(num4));//a+b/c*d;
                                        case 3:
                                            return num1.add(num2.divi(num3).divi(num4));//a+b/c/d
                                    }//+×
                            }
                        case 1:
                            switch (operator22) {//-
                                case 0:
                                    switch (operator33) {//-+
                                        case 0:
                                            return num1.minus(num2.add(num3)).add(num4);//a-b+c+d
                                        case 1:
                                            return num1.minus(num2.add(num3)).minus(num4);//a-b+c-d
                                        case 2:
                                            return num1.minus(num2.add(num3).mult(num4));//a-b+c*d
                                        case 3:
                                            return num1.minus(num2.add(num3).divi(num4));//a-b+c/d
                                    }//-+
                                case 1:
                                    switch (operator33) {//--
                                        case 0:
                                            return num1.minus(num2.minus(num3)).add(num4);//a-b-c+d
                                        case 1:
                                            return num1.minus(num2.minus(num3)).minus(num4);//a-b-c-d
                                        case 2:
                                            return num1.minus(num2.minus(num3).mult(num4));//a-b-c*d
                                        case 3:
                                            return num1.minus(num2.minus(num3).divi(num4));//a-(b-c)/d
                                    }//--
                                case 2:
                                    switch (operator33) {//-×
                                        case 0:
                                            return num1.minus(num2.mult(num3)).add(num4);//a-b*c+d
                                        case 1:
                                            return num1.minus(num2.mult(num3)).minus(num4);//a-b*c-d
                                        case 2:
                                            return num1.minus(num2.mult(num3).mult(num4));//a-b*c/d;
                                        case 3:
                                            return num1.minus(num2.mult(num3).divi(num4));//a-b*c/d
                                    }//-×
                                case 3:
                                    switch (operator33) {//-÷
                                        case 0:
                                            return num1.minus(num2.divi(num3)).add(num4);//a-b/c+d
                                        case 1:
                                            return num1.minus(num2.divi(num3)).minus(num4);//a-b/c-d
                                        case 2:
                                            return num1.minus(num2.divi(num3).mult(num4));//a-b/c*d;
                                        case 3:
                                            return num1.minus(num2.divi(num3).divi(num4));//a-b/c/d
                                    }//-×
                            }
                        case 2:
                            switch (operator22) {//*
                                case 0:
                                    switch (operator33) {//*+
                                        case 0:
                                            return num1.mult(num2.add(num3)).add(num4);//a*(b+c)+d
                                        case 1:
                                            return num1.mult(num2.add(num3)).minus(num4);//a*b+c-d
                                        case 2:
                                            return num1.mult(num2.add(num3).mult(num4));//a*(b+c)*d
                                        case 3:
                                            return num1.mult(num2.add(num3).divi(num4));//a*(b+c)/d
                                    }//*+
                                case 1:
                                    switch (operator33) {//--
                                        case 0:
                                            return num1.mult(num2.minus(num3)).add(num4);//a*(b-c)+d
                                        case 1:
                                            return num1.mult(num2.minus(num3)).minus(num4);//a*b-c-d
                                        case 2:
                                            return num1.mult(num2.minus(num3).mult(num4));//a*b-c*d
                                        case 3:
                                            return num1.mult(num2.minus(num3).divi(num4));//a*b-c/d
                                    }//*-
                                case 2:
                                    switch (operator33) {//+×
                                        case 0:
                                            return num1.mult(num2.mult(num3)).add(num4);//a*b*c+d
                                        case 1:
                                            return num1.mult(num2.mult(num3)).minus(num4);//a*b*c-d
                                        case 2:
                                            return num1.mult((num2.mult(num3)).mult(num4));//a*b*c/d;
                                        case 3:
                                            return num1.mult((num2.mult(num3)).divi(num4));//a*b*c/d
                                    }//*×
                                case 3:
                                    switch (operator33) {//*÷
                                        case 0:
                                            return num1.mult(num2.divi(num3)).add(num4);//a*b/c+d
                                        case 1:
                                            return num1.mult(num2.divi(num3)).minus(num4);//a*b/c-d
                                        case 2:
                                            return num1.mult((num2.divi(num3)).mult(num4));//a*b/c*d;
                                        case 3:
                                            return num1.mult((num2.divi(num3)).divi(num4));//a*b/c/d
                                    }//*/
                            }
                        case 3:
                            switch (operator22) {//÷
                                case 0:
                                    switch (operator33) {//÷+
                                        case 0:
                                            return num1.divi(num2.add(num3)).add(num4);//a/b+c+d
                                        case 1:
                                            return num1.divi(num2.add(num3)).minus(num4);//a/b+c-d
                                        case 2:
                                            return num1.divi(num2.add(num3).mult(num4));//a/b+c*d
                                        case 3:
                                            return num1.divi(num2.add(num3).divi(num4));//a/b+c/d
                                    }//÷+
                                case 1:
                                    switch (operator33) {//÷-
                                        case 0:
                                            return num1.divi(num2.minus(num3)).add(num4);//a/b-c+d
                                        case 1:
                                            return num1.divi(num2.minus(num3)).minus(num4);//a/b-c-d
                                        case 2:
                                            return num1.divi(num2.minus(num3)).mult(num4);//a/b-c*d
                                        case 3:
                                            return num1.divi(num2.minus(num3)).divi(num4);//a/b-c/d
                                    }//÷-
                                case 2:
                                    switch (operator33) {//÷×
                                        case 0:
                                            return num1.divi(num2.mult(num3)).add(num4);//a/b*c+d
                                        case 1:
                                            return num1.divi(num2.mult(num3)).minus(num4);//a/b*c-d
                                        case 2:
                                            return num1.divi((num2.mult(num3)).mult(num4));//a/b*c/d;
                                        case 3:
                                            return num1.divi((num2.mult(num3)).divi(num4));//a/b*c/d
                                    }//÷×
                                case 3:
                                    switch (operator33) {///÷
                                        case 0:
                                            return num1.divi(num2.divi(num3)).add(num4);//a*b/c+d
                                        case 1:
                                            return num1.divi(num2.divi(num3)).minus(num4);//a*b/c-d
                                        case 2:
                                            return num1.divi((num2.divi(num3)).mult(num4));//a*b/c*d;
                                        case 3:
                                            return num1.divi((num2.divi(num3)).divi(num4));//a*b/c/d
                                    }//÷/
                            }

                    }
                }//23带
                if (x1234 == 2) {//34带

                    switch (operator11) {
                        case 0:
                            switch (operator22) {//+
                                case 0:
                                    switch (operator33) {//++
                                        case 0:
                                            return num1.add(num2).add(num3.add(num4));//a+b+c+d
                                        case 1:
                                            return num1.add(num2).add(num3.minus(num4));//a+b+c-d
                                        case 2:
                                            return num1.add(num2).add(num3.mult(num4));//a+b+c*d
                                        case 3:
                                            return num1.add(num2).add(num3.divi(num4));//a+b+c/d
                                    }//++
                                case 1:
                                    switch (operator33) {//+-
                                        case 0:
                                            return num1.add(num2).minus(num3.add(num4));//a+b-c+d
                                        case 1:
                                            return num1.add(num2).minus(num3.minus(num4));//a+b-c-d
                                        case 2:
                                            return num1.add(num2).minus(num3.mult(num4));//a+b-c*d
                                        case 3:
                                            return num1.add(num2).minus(num3.divi(num4));//a+b-c/d
                                    }//+-
                                case 2:
                                    switch (operator33) {//+×
                                        case 0:
                                            return num1.add(num2.mult(num3.add(num4)));//a+b*c+d
                                        case 1:
                                            return num1.add(num2.mult(num3.minus(num4)));//a+b*c-d
                                        case 2:
                                            return num1.add(num2.mult(num3.mult(num4)));//a+b*c*d;
                                        case 3:
                                            return num1.add(num2.mult(num3.divi(num4)));//a+b*c/d
                                    }//+×
                                case 3:
                                    switch (operator33) {//+÷
                                        case 0:
                                            return num1.add(num2.divi(num3.add(num4)));//a+b/c+d
                                        case 1:
                                            return num1.add(num2.divi(num3.minus(num4)));//a+b/c-d
                                        case 2:
                                            return num1.add(num2.divi(num3).mult(num4));//a+b/c*d;
                                        case 3:
                                            return num1.add(num2.divi(num3).divi(num4));//a+b/c/d
                                    }//+×
                            }
                        case 1:
                            switch (operator22) {//-
                                case 0:
                                    switch (operator33) {//-+
                                        case 0:
                                            return num1.minus(num2).add(num3.add(num4));//a-b+c+d
                                        case 1:
                                            return num1.minus(num2).add(num3.minus(num4));//a-b+c-d
                                        case 2:
                                            return num1.minus(num2.add(num3.mult(num4)));//a-b+c*d
                                        case 3:
                                            return num1.minus(num2.add(num3.divi(num4)));//a-b+c/d
                                    }//-+
                                case 1:
                                    switch (operator33) {//--
                                        case 0:
                                            return num1.minus(num2).minus(num3.add(num4));//a-b-c+d
                                        case 1:
                                            return num1.minus(num2).minus(num3.minus(num4));//a-b-c-d
                                        case 2:
                                            return num1.minus(num2.minus(num3.mult(num4)));//a-b-(c*d)
                                        case 3:
                                            return num1.minus(num2.minus(num3.divi(num4)));//a-(b-c)/d
                                    }//--
                                case 2:
                                    switch (operator33) {//-×
                                        case 0:
                                            return num1.minus(num2.mult(num3.add(num4)));//a-b*c+d
                                        case 1:
                                            return num1.minus(num2.mult(num3.minus(num4)));//a-b*c-d
                                        case 2:
                                            return num1.minus(num2.mult(num3).mult(num4));//a-b*c/d;
                                        case 3:
                                            return num1.minus(num2.mult(num3).divi(num4));//a-b*c/d
                                    }//-×
                                case 3:
                                    switch (operator33) {//-÷
                                        case 0:
                                            return num1.minus(num2.divi(num3.add(num4)));//a-b/c+d
                                        case 1:
                                            return num1.minus(num2.divi(num3.minus(num4)));//a-b/c-d
                                        case 2:
                                            return num1.minus(num2.divi(num3).mult(num4));//a-b/c*d;
                                        case 3:
                                            return num1.minus(num2.divi(num3).divi(num4));//a-b/c/d
                                    }//-×
                            }
                        case 2:
                            switch (operator22) {//*
                                case 0:
                                    switch (operator33) {//*+
                                        case 0:
                                            return num1.mult(num2).add(num3.add(num4));//a*b+(c+d)
                                        case 1:
                                            return num1.mult(num2).add(num3.minus(num4));//a*b+c-d
                                        case 2:
                                            return num1.mult(num2).add(num3.mult(num4));//a*b+(c*d)
                                        case 3:
                                            return num1.mult(num2).add(num3).divi(num4);//a*(b+c)/d
                                    }//*+
                                case 1:
                                    switch (operator33) {//--
                                        case 0:
                                            return num1.mult(num2).minus(num3.add(num4));
                                        case 1:
                                            return num1.mult(num2).minus(num3.minus(num4));//a*b-c-d
                                        case 2:
                                            return num1.mult(num2).minus(num3.mult(num4));//a*b-c*d
                                        case 3:
                                            return num1.mult(num2).minus(num3.divi(num4));//a*b-c/d
                                    }//*-
                                case 2:
                                    switch (operator33) {//+×
                                        case 0:
                                            return num1.mult(num2.mult(num3.add(num4)));//a*b*c+d
                                        case 1:
                                            return num1.mult(num2.mult(num3.minus(num4)));//a*b*c-d
                                        case 2:
                                            return num1.mult((num2.mult(num3)).mult(num4));//a*b*c/d;
                                        case 3:
                                            return num1.mult((num2.mult(num3)).divi(num4));//a*b*c/d
                                    }//*×
                                case 3:
                                    switch (operator33) {//*÷
                                        case 0:
                                            return num1.mult(num2.divi(num3.add(num4)));//a*b/c+d
                                        case 1:
                                            return num1.mult(num2.divi(num3.minus(num4)));//a*b/c-d
                                        case 2:
                                            return num1.mult((num2.divi(num3)).mult(num4));//a*b/c*d;
                                        case 3:
                                            return num1.mult((num2.divi(num3)).divi(num4));//a*b/c/d
                                    }//*/
                            }
                        case 3:
                            switch (operator22) {//÷
                                case 0:
                                    switch (operator33) {//÷+
                                        case 0:
                                            return num1.divi(num2).add(num3.add(num4));//a/b+c+d
                                        case 1:
                                            return num1.divi(num2).add(num3.minus(num4));//a/b+c-d
                                        case 2:
                                            return num1.divi(num2).add(num3.mult(num4));//a/b+c*d
                                        case 3:
                                            return num1.divi(num2).add(num3.divi(num4));//a/b+c/d
                                    }//÷+
                                case 1:
                                    switch (operator33) {//÷-
                                        case 0:
                                            return num1.divi(num2).minus(num3.add(num4));//a/b-c+d
                                        case 1:
                                            return num1.divi(num2).minus(num3.minus(num4));//a/b-c-d
                                        case 2:
                                            return num1.divi(num2).minus(num3.mult(num4));//a/b-c*d
                                        case 3:
                                            return num1.divi(num2).minus(num3.divi(num4));//a/b-c/d
                                    }//÷-
                                case 2:
                                    switch (operator33) {//÷×
                                        case 0:
                                            return num1.divi(num2.mult(num3.add(num4)));//a/b*c+d
                                        case 1:
                                            return num1.divi(num2.mult(num3.minus(num4)));//a/b*c-d
                                        case 2:
                                            return num1.divi(num2).mult(num3).mult(num4);//a/b*c/d;
                                        case 3:
                                            return num1.divi(num2).mult(num3).divi(num4);//a/b*c/d
                                    }//÷×
                                case 3:
                                    switch (operator33) {///÷
                                        case 0:
                                            return num1.divi(num2.divi(num3.add(num4)));//a*b/c+d
                                        case 1:
                                            return num1.divi(num2.divi(num3.minus(num4)));//a*b/c-d
                                        case 2:
                                            return num1.divi(num2).divi(num3.mult(num4));//a*b/c*d;
                                        case 3:
                                            return num1.divi((num2.divi(num3)).divi(num4));//a*b/c/d
                                    }//÷/
                            }

                    }
                }//34带
            }


        }
        return null;
    }


}
