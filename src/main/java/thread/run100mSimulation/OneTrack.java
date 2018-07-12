package thread.run100mSimulation;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;


/**
 * 这是第一个比赛程序。
 * @author yinwenjie
 *
 */
public class OneTrack {

    private static final String[] PLAYERNAMES = new String[]{"白银圣斗士","黄金圣斗士"
        ,"青铜圣斗士","神斗士","冥斗士","哈迪斯","龟仙人","孙悟空","孙悟饭","贝吉塔","孙悟天"};

    /**
     * 报名队列（非线程安全）
     */
    private List<Player> signupPlayers = new LinkedList<Player>();

    /**
     * 初赛结果队列（有排序功能，且线程安全）
     */
    private PriorityBlockingQueue<Player> preliminaries = new PriorityBlockingQueue<Player>();

    /**
     * 决赛结果队列（有排序功能，且线程安全）
     */
    private PriorityBlockingQueue<Player> finals = new PriorityBlockingQueue<Player>();

    public void track() {
        /*
         * 赛跑分为以下几个阶段进行；
         * 
         * 1、报名
         * 2、初赛，10名选手，分成两组，每组5名选手。
         * 分两次进行初赛（因为场地只有5条赛道，只有拿到进场许可的才能使用赛道，进行比赛）
         * 
         * 3、决赛：初赛结果将被写入到一个队列中进行排序，只有成绩最好的前五名选手，可以参加决赛。
         * 
         * 4、决赛结果的前三名将分别作为冠亚季军被公布出来
         * */

        //1、================报名
        // 这就是跑道，需求上说了只有5条跑道，所以只有5个permits。
        Semaphore runway = new Semaphore(5);
        this.signupPlayers.clear();
        for(int index = 0 ; index < OneTrack.PLAYERNAMES.length ; ) {
            Player player = new Player(OneTrack.PLAYERNAMES[index], ++index , runway);
            this.signupPlayers.add(player);
        }

        //2、================进行初赛
        // 这是裁判
        ExecutorService refereeService = Executors.newFixedThreadPool(5);
        for (final Player player : this.signupPlayers) {
            Future<Result> future = null;
            future = refereeService.submit(player);
            new FutureThread(future, player, this.preliminaries).start();
        }
        //! 只有当PLAYERNAMES.length位选手的成绩都产生了，才能进入决赛，这很重要
        synchronized (this.preliminaries) {
            while(this.preliminaries.size() < OneTrack.PLAYERNAMES.length) {
                try {
                    this.preliminaries.wait();
                } catch(InterruptedException e) {
                    e.printStackTrace(System.out);
                }
            }
        }

        // 3、============决赛(只有初赛结果的前5名可以参见)
        for(int index = 0 ; index < 5 ; index++) {
            Player player = this.preliminaries.poll();
            Future<Result> future = null;
            future = refereeService.submit(player);
            new FutureThread(future, player, this.finals).start();
        }
        //! 只有当5位选手的决赛成绩都产生了，才能到下一步：公布成绩
        synchronized (this.finals) {
            while(this.finals.size() < 5) {
                try {
                    this.finals.wait();
                } catch(InterruptedException e) {
                    e.printStackTrace(System.out);
                }
            }
        }

        // 4、============公布决赛成绩（前三名）
        for(int index = 0 ; index < 3 ; index++) {
            Player player = this.finals.poll();
            switch (index) {
            case 0:
                System.out.println("第一名："  + player.getName() + "[" + player.getNumber() + "]，成绩：" + player.getResult().getTime() + "秒");
                break;
            case 1:
                System.out.println("第二名："  + player.getName() + "[" + player.getNumber() + "]，成绩：" + player.getResult().getTime() + "秒");
                break;
            case 2:
                System.out.println("第三名："  + player.getName() + "[" + player.getNumber() + "]，成绩：" + player.getResult().getTime() + "秒");
                break;
            default:
                break;
            }
        }
    }

    public static void main(String[] args) throws RuntimeException {
        new OneTrack().track();
    }

    
    
    /**
     * 这是计分线程，是为了保证产生比赛结果后，在计入PriorityBlockingQueue
     * 这样才有排列成绩的依据
     * @author yinwenjie
     *
     */
    private class FutureThread extends Thread {

        /**
         * 选手跑步任务（Player）的执行状态对象
         */
        private Future<Result> future;

        /**
         * 跑步成绩出来后，需要操作的队列
         * （要将对应的选手加入到队列，以便依据成绩进行排序）
         */
        private PriorityBlockingQueue<Player> achievementQueue;

        /**
         * 当前进行跑步的选手
         */
        private Player player;

        public FutureThread(Future<Result> future , Player player , PriorityBlockingQueue<Player> achievementQueue) {
            this.future = future;
            this.player = player;
            this.achievementQueue = achievementQueue;
        }

        /* (non-Javadoc)
         * @see java.lang.Thread#run()
         */
        @Override
        public void run() {
            // 如果条件成立，最有可能的就是选手在比赛过程中，
            // 由于某种原因退赛了！
            if(this.future == null) {
                System.out.println("选手退赛，计分为0");
            } else {
                try {
                    // 如果选手没有跑完，FutureThread将阻塞在这里
                    // 当然出现跑步过程中退赛，就会抛出异常
                    this.future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            // 运行到这里，就说明这个选手跑完了（或者退赛了）
            // 无论什么情况，都计入队列，然后通知主线程
            this.achievementQueue.put(this.player);
            synchronized (this.achievementQueue) {
                this.achievementQueue.notify();
            }
        }
    }
}
