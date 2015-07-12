package com.github.holodnov.graph.zoo;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.System.getProperty;
import static java.util.Objects.requireNonNull;
import static org.apache.curator.framework.CuratorFrameworkFactory.newClient;
import static org.apache.curator.framework.imps.CuratorFrameworkState.STARTED;
import static org.apache.curator.utils.PathUtils.validatePath;

/**
 * @author Kyrylo Holodnov
 */
public class ZooClient implements DisposableBean {

    private static final Logger log = LoggerFactory.getLogger(ZooClient.class);

    private CuratorFramework zooClient;

    public ZooClient(String connectString) throws Exception {
        RetryPolicy retryPolicy = new RetryNTimes(25, 1000);
        zooClient = newClient(connectString, retryPolicy);
        zooClient.start();
        if (!blockUntilConnectedOrTimedOut()) {
            throw new Exception("Cannot connect to ZooKeeper (connection timed out)");
        }
    }

    public ZooClient() throws Exception {
        this(getProperty("zookeeper.connect.string", "localhost:2181"));
    }

    public CuratorFramework getZooClient() {
        return zooClient;
    }

    @Override
    public void destroy() {
        zooClient.close();
    }

    public void sync(String zNodePath) throws ZooException, NoZooNodeException {
        zNodePath = validatePath(zNodePath);
        validateConnection();
        try {
            zooClient.sync().forPath(zNodePath);
        } catch (KeeperException.NoNodeException ex) {
            throw new NoZooNodeException("No ZNode exists, for path = " + zNodePath);
        } catch (Exception ex) {
            throw new ZooException("Exception occurred while syncing ZNode, for path = " + zNodePath, ex);
        }
    }

    /**
     * Creates or updates ZNode for given path.
     *
     * @param zNodePath input ZNode path
     * @return true if ZNode was created and false if ZNode already exists
     */
    public boolean createOrUpdatePath(String zNodePath) throws ZooException {
        return createOrUpdatePath(zNodePath, null);
    }

    /**
     * Creates or updates ZNode for given path and sets long value to ZNode.
     *
     * @param zNodePath input ZNode path
     * @param value     value that should be set
     * @return true if ZNode was created and false if ZNode already exists
     */
    public boolean createOrUpdatePath(String zNodePath,
                                      long value) throws ZooException {
        byte[] bytes = new byte[8];
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.putLong(value);
        return createOrUpdatePath(zNodePath, bytes);
    }

    /**
     * Creates or updates ZNode for given path and sets double value to ZNode.
     *
     * @param zNodePath input ZNode path
     * @param value     value that should be set
     * @return true if ZNode was created and false if ZNode already exists
     */
    public boolean createOrUpdatePath(String zNodePath,
                                      double value) throws ZooException {
        byte[] bytes = new byte[8];
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.putDouble(value);
        return createOrUpdatePath(zNodePath, bytes);
    }

    /**
     * Creates ZNode for given path.
     *
     * @param zNodePath input ZNode path
     */
    public void createPath(String zNodePath) throws ZooException {
        createPath(zNodePath, null);
    }

    /**
     * Creates ZNode for given path and sets long value to ZNode.
     *
     * @param zNodePath input ZNode path
     * @param value     value that should be set
     */
    public void createPath(String zNodePath,
                           long value) throws ZooException {
        byte[] bytes = new byte[8];
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.putLong(value);
        createPath(zNodePath, bytes);
    }

    /**
     * Creates ZNode for given path and sets double value to ZNode.
     *
     * @param zNodePath input ZNode path
     * @param value     value that should be set
     */
    public void createPath(String zNodePath,
                           double value) throws ZooException {
        byte[] bytes = new byte[8];
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.putDouble(value);
        createPath(zNodePath, bytes);
    }

    /**
     * Creates or updates ZNode for given path and sets value to ZNode if needed.
     *
     * @param zNodePath input ZNode path
     * @param value     value that should be set
     * @return true if ZNode was created and false if ZNode already exists
     */
    public boolean createOrUpdatePath(String zNodePath,
                                      byte[] value) throws ZooException {
        zNodePath = validatePath(zNodePath);
        validateConnection();
        Stat stat;
        try {
            stat = zooClient.checkExists().forPath(zNodePath);
        } catch (Exception ex) {
            throw new ZooException("Cannot check if ZNode exists, for path = " + zNodePath, ex);
        }
        if (stat == null) {
            try {
                if (value != null) {
                    zooClient.create().creatingParentsIfNeeded().forPath(zNodePath, value);
                } else {
                    zooClient.create().creatingParentsIfNeeded().forPath(zNodePath);
                }
            } catch (Exception ex) {
                throw new ZooException("Cannot create ZNode, for path = " + zNodePath, ex);
            }
            log.debug("Path created: {}", zNodePath);
            return true;
        } else {
            if (value != null) {
                try {
                    zooClient.setData().forPath(zNodePath, value);
                } catch (Exception ex) {
                    throw new ZooException("Cannot set data to ZNode, for path = " + zNodePath, ex);
                }
            }
            log.debug("Path already exists: {}", zNodePath);
            return false;
        }
    }

    /**
     * Creates ZNode for given path and sets value to ZNode if needed.
     *
     * @param zNodePath input ZNode path
     * @param value     value that should be set
     */
    public void createPath(String zNodePath, byte[] value) throws ZooException {
        zNodePath = validatePath(zNodePath);
        validateConnection();
        try {
            if (value != null) {
                zooClient.create().creatingParentsIfNeeded().forPath(zNodePath, value);
            } else {
                zooClient.create().creatingParentsIfNeeded().forPath(zNodePath);
            }
        } catch (Exception ex) {
            throw new ZooException("Cannot create ZNode, for path = " + zNodePath, ex);
        }
        log.debug("Path created: {}", zNodePath);
    }

    /**
     * Deletes given ZNode with its children
     *
     * @param zNodePath input ZNode path
     */
    public void deletePathWithChildren(String zNodePath) throws ZooException {
        zNodePath = validatePath(zNodePath);
        validateConnection();
        try {
            zooClient.delete().deletingChildrenIfNeeded().forPath(zNodePath);
        } catch (KeeperException.NoNodeException ignored) {
        } catch (Exception ex) {
            throw new ZooException("Exception occurred while deleting ZNode, for path = " + zNodePath, ex);
        }
    }

    /**
     * Retrieves long value for given ZNode path.
     *
     * @param zNodePath input ZNode path
     * @return long value stored in ZNode
     */
    public long getLongFromPath(String zNodePath) throws ZooException, NoZooNodeException {
        byte[] bytes = getBytesFromPath(zNodePath);
        if (bytes == null || bytes.length != 8) {
            return 0;
        } else {
            ByteBuffer buffer = ByteBuffer.wrap(bytes);
            return buffer.getLong();
        }
    }

    /**
     * Retrieves double value for given ZNode path.
     *
     * @param zNodePath input ZNode path
     * @return double value stored in ZNode
     */
    public double getDoubleFromPath(String zNodePath) throws ZooException, NoZooNodeException {
        byte[] bytes = getBytesFromPath(zNodePath);
        if (bytes == null || bytes.length != 8) {
            return 0.0;
        } else {
            ByteBuffer buffer = ByteBuffer.wrap(bytes);
            return buffer.getDouble();
        }
    }

    /**
     * Retrieves byte array for given ZNode path.
     *
     * @param zNodePath input ZNode path
     * @return byte array stored in ZNode
     */
    public byte[] getBytesFromPath(String zNodePath) throws ZooException, NoZooNodeException {
        zNodePath = validatePath(zNodePath);
        validateConnection();
        try {
            return zooClient.getData().forPath(zNodePath);
        } catch (KeeperException.NoNodeException ex) {
            throw new NoZooNodeException("No ZNode exists, for path = " + zNodePath);
        } catch (Exception ex) {
            throw new ZooException("Cannot retrieve long value from ZNode, for path = " + zNodePath, ex);
        }
    }

    /**
     * Puts long value for given ZNode path.
     *
     * @param zNodePath input ZNode path
     * @param value     input value to put
     */
    public void putLongToPath(String zNodePath, long value) throws ZooException, NoZooNodeException {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(value);
        putBytesToPath(zNodePath, buffer.array());
    }

    /**
     * Puts double value for given ZNode path.
     *
     * @param zNodePath input ZNode path
     * @param value     input value to put
     */
    public void putDoubleToPath(String zNodePath, double value) throws ZooException, NoZooNodeException {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putDouble(value);
        putBytesToPath(zNodePath, buffer.array());
    }

    /**
     * Puts byte array for given ZNode path.
     *
     * @param zNodePath input ZNode path
     * @param bytes     input byte array to put
     */
    public void putBytesToPath(String zNodePath, byte[] bytes) throws ZooException, NoZooNodeException {
        zNodePath = validatePath(zNodePath);
        validateConnection();
        try {
            try {
                zooClient.setData().forPath(zNodePath, bytes);
            } catch (KeeperException.NoNodeException ex) {
                throw new NoZooNodeException("No ZNode exists, for path = " + zNodePath);
            }
        } catch (Exception ex) {
            throw new ZooException("Cannot retrieve long value from ZNode, for path = " + zNodePath, ex);
        }
    }

    /**
     * Gets lock without acquiring.
     *
     * @param lockPath input lock path
     * @return lock object
     */
    public InterProcessSemaphoreMutex getLock(String lockPath) throws ZooException {
        lockPath = validatePath(lockPath);
        return new InterProcessSemaphoreMutex(zooClient, lockPath);
    }

    /**
     * Retrieves child ZNodes for given path.
     *
     * @param zNodePath input ZNode path
     * @param fullPath  to return full path or not
     * @return child ZNodes
     */
    public List<String> getChildZNodes(String zNodePath, boolean fullPath) throws ZooException {
        zNodePath = validatePath(zNodePath);
        validateConnection();
        try {
            List<String> children = zooClient.getChildren().forPath(zNodePath);
            if (!fullPath || children == null || children.isEmpty()) {
                return children;
            }
            List<String> fullPathChildren = new ArrayList<>(children.size());
            for (String child : children) {
                fullPathChildren.add(zNodePath + "/" + child);
            }
            return fullPathChildren;
        } catch (KeeperException.NoNodeException ignored) {
            return null;
        } catch (Exception ex) {
            throw new ZooException("Cannot retrieve child ZNodes, for path = " + zNodePath, ex);
        }
    }

    /**
     * Checks if ZNode exists.
     *
     * @param zNodePath input ZNode path
     * @return true if ZNode exists, false otherwise
     */
    public boolean exists(String zNodePath) throws ZooException {
        zNodePath = validatePath(zNodePath);
        validateConnection();
        try {
            Stat stat = zooClient.checkExists().forPath(zNodePath);
            return stat != null;
        } catch (Exception ex) {
            throw new ZooException("Cannot check if ZNode exists, for path = " + zNodePath, ex);
        }
    }

    /**
     * Waits until connection succeeded or timeout occurred.
     *
     * @return true if connection succeeded
     * @throws InterruptedException if interruption occurred
     */
    public boolean blockUntilConnectedOrTimedOut() throws InterruptedException {
        return zooClient.getZookeeperClient().blockUntilConnectedOrTimedOut();
    }

    public boolean isConnected() {
        CuratorFrameworkState state = zooClient.getState();
        return state == STARTED && zooClient.getZookeeperClient().isConnected();
    }

    public void close() {
        zooClient.close();
    }

    /**
     * Releases given lock.
     *
     * @param lock input lock
     */
    public static void releaseLock(InterProcessSemaphoreMutex lock) {
        requireNonNull(lock, "Input lock should be not null");
        try {
            lock.release();
        } catch (Exception ex) {
            log.warn("Unexpected exception occurred while releasing lock: ", ex);
        }
    }

    /**
     * Acquires given lock.
     *
     * @param lock input lock
     * @param time time to wait
     * @param unit time unit
     * @return true if acquired and false if timeout occurred
     */
    public static boolean acquireLock(InterProcessSemaphoreMutex lock, long time, TimeUnit unit) throws ZooException {
        requireNonNull(lock, "Input lock should be not null");
        try {
            return lock.acquire(time, unit);
        } catch (Exception ex) {
            throw new ZooException("Exception occurred while acquiring lock", ex);
        }
    }

    private void validateConnection() throws ZooException {
        CuratorFrameworkState state = zooClient.getState();
        if (state != STARTED) {
            throw new ZooException("Curator client has not-started state: " + state);
        }
        if (!zooClient.getZookeeperClient().isConnected()) {
            throw new ZooException("Curator client is not connected");
        }
    }
}
