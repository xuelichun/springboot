package com.bonc.forkjoin;

import com.bonc.test.User;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 10:11 2018/12/15.
 * @Modified By:
 */
public class ForkJoinTest {

    public static void main(String[] args) {
        //System.out.println("合并结果"+getList());
    }
    public static List<User> getList(){
        List<User> result=new ArrayList<>();
        List<User> list=new ArrayList<>();

        for(int i=0;i<18;i++){
            User user=new User();
            user.setId(i);
            list.add(user);
        }
        System.out.println("原始数据"+list);

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<List<User>> task = pool.submit(new AnalysisDetailTask(list));
       // ForkJoinTask<List<User>> task2 = pool.submit(new AnalysisDetailTask(list));

        try {
            //result=task.get();
            result.addAll(task.get());
            //result.addAll(task2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static  class AnalysisDetailTask extends RecursiveTask<List<User>>  {

        /**
         * 分界标识
         */
        private static final int LOOP = 10;

        private  List<User> list;

        @Override
        protected List<User> compute() {
            List<User> userList=Lists.newLinkedList();
            int len=list.size();
            if(len<=LOOP){
                processData(userList);
            }else{
                // 定义任务集合
                List<AnalysisDetailTask> tasks = Lists.newArrayList();
                // 分解任务
                for (int i = 0; i < len; i += LOOP) {
                    int end = (i + LOOP) > len ? len : (i + LOOP);
                    //TODO fork和invokeAll invokeAll效率高
                    //对于fork/join模式，假如pool里面线程数量是固定的，
                    // 那么调用子任务的fork方法相当于A先分工给B，然后A当监工不干活，B去完成A交代的任务。
                    // 所以上面的模式相当于浪费了一个线程。那么如果使用invokeAll相当于A分工给B后，A和B都去完成工作。
                    // 这样缩短了执行的时间。
                   /* AnalysisDetailTask ad=new AnalysisDetailTask(list.subList(i,end));
                    ad.fork();
                    tasks.add(ad);*/

                    tasks.add(new AnalysisDetailTask(list.subList(i,end)));
                }

                // 执行任务
                invokeAll(tasks);

                try {
                    // 合并执行结果
                    for (AnalysisDetailTask task : tasks) {
                        userList.addAll(task.get());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            return  userList;
        }

        public AnalysisDetailTask(List<User> list) {
            this.list = list;
        }

        private  void  processData(List<User> userList){
            for (User user : list) {
                userList.add(user);
            }
            System.out.println("分解后"+userList);
        }
    }


}
