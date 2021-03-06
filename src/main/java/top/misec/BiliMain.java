package top.misec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import top.misec.login.Verify;
import top.misec.task.DailyTask;
import top.misec.task.ServerPush;


/**
 * @author Junzhou Liu
 * @create 2020/10/11 2:29
 */
public class BiliMain {

    static Logger logger = (Logger) LogManager.getLogger(BiliMain.class.getName());
    public static String serverMsgPush = null;

    public static void main(String[] args) {

        if (args.length < 3) {
            logger.info("-----任务启动失败-----");
            logger.warn("Cooikes参数缺失，请检查是否在Github Secrets中配置Cooikes参数");
        }
        //读取环境变量
        Verify.verifyInit(args[0], args[1], args[2]);
        serverMsgPush = args[3];
        //每日任务65经验
        logger.debug("-----任务启动-----");
        DailyTask dailyTask = new DailyTask();
        dailyTask.doDailyTask();

        if (serverMsgPush != null) {
            ServerPush serverPush = new ServerPush(serverMsgPush);
            serverPush.pushMsg("BILIBILIHELPER已完成所有任务", "自动推送");
        }
    }

}
