package com.spring.study;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

/**
 * @author wangxia
 * @date 2019/7/19 15:42
 * @Description:
 */
public class ZookeeperStudy {

    public static void main(String[] args) {
        try{
            ZooKeeper zooKeeper=new ZooKeeper("127.0.0.1:2181", 10000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("watch--------:"+watchedEvent.getPath());
                }
            });
            //添加
//            zooKeeper.create("/wangxia2","hello".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            //查看
            for(String path:zooKeeper.getChildren("/",false)){
                System.out.println("路径:"+path);
            }
            //查看值
            System.out.println("/wangxia2对应值："+new String(zooKeeper.getData("/wangxia2",true,new Stat())));
            //修改节点   -1就是修改最新版本
            zooKeeper.setData("/wangxia2","reset".getBytes(),-1);
            System.out.println("/wangxia2对应值："+new String(zooKeeper.getData("/wangxia2",true,new Stat())));
            //删除节点
            zooKeeper.delete("/wangxia2",-1);
            for(String path:zooKeeper.getChildren("/",false)){
                System.out.println("路径:"+path);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
