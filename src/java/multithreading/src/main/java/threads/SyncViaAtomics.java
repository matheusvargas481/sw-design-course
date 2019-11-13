package threads;

import java.util.concurrent.atomic.AtomicInteger;

public class SyncViaAtomics {

    static class GlobalSafeAtomicCounter{
        private AtomicInteger counter = new AtomicInteger(0);
        public  Integer getCount(){ return counter.get(); }
        public  Integer inc(){ return counter.incrementAndGet(); }
    }

    public static void main(String[] args) throws Exception{
        GlobalSafeAtomicCounter counter = new GlobalSafeAtomicCounter();
        Runnable code = new Runnable(){
            @Override
            public void run() { 
                for(int i=1;i<=10;i++){
                    System.out.println("Tread " + Thread.currentThread().getName() + " = " + 
                    counter.inc());     
                }
            }
        };
        Thread t1 = new Thread(code,"t1");
        Thread t2 = new Thread(code,"t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();        
    }
}