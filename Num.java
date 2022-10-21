package Paircoding;

import java.util.Random;

//运算数
public class Num {
    Random r = new Random();//控制自然数或分数
    public  int nummax;//自然数最大值
    public int framax;//分母最大值
    int x = r.nextInt(2);//在创建对象时自动确定为分数还是自然数,0是分数，1是自然数
    int n1=0;//用来控制次数
    int n2=0;
    int n3=0;
    int nat;  //如果为自然数，不为0
    int mnum=1;//分母不为0
    int snum;//分子，分子小于分母，不为0
   public Num(){}//无参构造
    public int nat(){
        if(n1==0){//如果snum没调用过，调用一次，为snum成员赋值
            int xx;
            xx=r.nextInt(this.nummax)+1;//在范围内
            this.nat=xx;
            n1=n1+1;
            return nat;
        }
       else //nat不是第一次调用，则nat值已有，不用重新赋值
       return nat;
    }
    public int snum(){
        if(n2==0){//如果snum没调用过，调用一次，为snum成员赋值
            int xx;
            xx=r.nextInt(this.mnum)+1;//在范围内
            this.snum=xx;
            n2=n2+1;
            return snum;
        }
        else //snum不是第一次调用，则snum值已有，不用重新赋值
            return snum;
    }
    public int mnum(){
        if(n3==0){//如果nat没调用过，调用一次，为nat成员赋值
            int xx;
           //在范围内
                mnum=r.nextInt(framax)+1;

            n3=n3+1;
            return mnum;
        }
        else //nat不是第一次调用，则nat值已有，不用重新赋值
            return mnum;
    }
    public Num(int nummax,int framax){
       this.nummax=nummax;
       this.framax=framax;

    }
    public Num( int nat) {//自然数构造方法
        this.x = 1;
        this.nat = nat;
        this.n1=1;
        this.n2=1;
        this.n3=1;
    }//自然数构造方法，暂时没用*/
    public Num(int x, int snum, int mnum) {//分数构造方法
        this.x = x;
        this.mnum = mnum;
        this.snum = snum;
        this.n1=1;
        this.n2=1;
        this.n3=1;
    }//分数构造方法,暂时没用
    @Override
    public String toString() {
        this.nat();//调用一次取值方法，值就确定了
        this.mnum();
        this.snum();
        if (x == 0) {//则为分数
            int z=0;
            int n=mnum;
            int m;//15
            //5
            int c = snum; //c为余数
            while(c != 0) {
                m = n; //让原来除数作为被除数
                n = c; //让原来余数作为除数
                c = m %n;
            }
            if(mnum/n==1){//如果分母为1
                return snum/n+"";
            }
            if(snum/n==0) {//如果分子为0
                return 0 + "";
            }
            return snum/n + "/" + mnum/n;
        } else {//则为自然数
            return nat+"";
        }

    }

    public Num add(Num num) {//加法
        if(num==null){
            return this;
        }
        nat();//调用一次取值方法，值就确定了
        snum();
        mnum();
        if (num.x == 1 && this.x == 1) {//两个数都是自然数,仍为自然数
            return new Num( this.nat + num.nat);
        }
        if (num.x == 0 && this.x == 1) {//一个为自然数，加数为分数，结果为分数
            return new Num(0,this.nat*num.mnum+num.snum,num.mnum);
        }
        if (num.x == 1 && this.x == 0) {//一个为自然数，加数为分数，结果为分数
            return new Num(0,this.mnum*num.nat+this.snum,this.mnum);
        }
        else {//都为分数，结果为分数
            return new Num(0,this.snum*num.mnum+num.snum*this.mnum,num.mnum*this.mnum);
        }

    }
    public Num minus(Num num) {//减法
       if(num==null){
           return this;
       }
        nat();//调用一次取值方法，值就确定了
        snum();
        mnum();
        if (num.x == 1 && this.x == 1) {//两个数都是自然数,仍为自然数
            return new Num( this.nat - num.nat);
        }
        if (num.x == 0 && this.x == 1) {//一个为自然数，减数为分数，结果为分数
            return new Num(0,this.nat*num.mnum-num.snum,num.mnum);
        }
        if (num.x == 1 && this.x == 0) {//一个为自然数，加数为分数，结果为分数
            return new Num(0,-this.mnum*num.nat+this.snum,this.mnum);
        }
        else {//都为分数，结果为分数
            return new Num(0,this.snum*num.mnum-num.snum*this.mnum,num.mnum*this.mnum);
        }

    }
    public Num mult(Num num) {//乘法
        if(num==null){
            return this;
        }
        nat();//调用一次取值方法，值就确定了
        snum();
        mnum();
        if (num.x == 1 && this.x == 1) {//两个数都是自然数,仍为自然数
            return new Num( this.nat * num.nat);
        }
        if (num.x == 0 && this.x == 1) {//一个为自然数，乘数为分数，结果为分数
            return new Num(0,this.nat*num.snum,num.mnum);
        }
        if (num.x == 1 && this.x == 0) {//一个为自然数，乘数为分数，结果为分数
            return new Num(0,this.snum*num.nat,this.mnum);
        }
        else   {//都为分数，结果为分数
            return new Num(0,this.snum*num.snum,num.mnum*this.mnum);
        }

    }
    public Num divi(Num num) {//除法
        if(num==null){
            return this;
        }
        nat();//调用一次取值方法，值就确定了
        snum();
        mnum();

       if(num.x==1) {//如果除数为自然数
           return this.mult(new Num(0, 1, num.nat));//返回乘法
       }
       else{//除数为分数
           return this.mult(new Num(0,num.mnum,num.snum));
       }
    }
}
