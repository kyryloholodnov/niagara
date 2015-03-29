package com.github.holodnov.graph.zoo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.requireNonNull;
import static org.apache.curator.framework.imps.CuratorFrameworkState.STARTED;

public final class ZooKeeperUtils {

    private static final Logger log = LoggerFactory.getLogger(ZooKeeperUtils.class);

    private ZooKeeperUtils() {
        throw new AssertionError("Cannot instantiate ZooKeeperUtils object");
    }

    public static String validateAndTransformPath(String path) {
        if (path == null) {
            throw new IllegalArgumentException("Input path should be not null");
        }
        if (path.isEmpty()) {
            throw new IllegalArgumentException("Input path should be not empty");
        }
        if (!path.startsWith("/")) {
            throw new IllegalArgumentException("Input path should start with '/'");
        }
        if (path.contains(" ")) {
            throw new IllegalArgumentException("Input path should not contain spaces");
        }
        while (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        if (path.isEmpty()) {
            throw new IllegalArgumentException("Input path should be not root path");
        }
        if (path.contains("//")) {
            throw new IllegalArgumentException("Input path should not contain \"//\"");
        }
        return path;
    }

    /**
     * Gets lock without acquiring.
     *
     * @param client   input client
     * @param lockPath input lock path
     * @return lock object
     */
    public static InterProcessSemaphoreMutex getLock(CuratorFramework client, String lockPath) throws ZooException {
        lockPath = validateAndTransformPath(lockPath);
        validateConnection(client);
        return new InterProcessSemaphoreMutex(client, lockPath);
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

    public static void releaseLock(InterProcessSemaphoreMutex lock) {
        requireNonNull(lock, "Input lock should be not null");
        try {
            lock.release();
        } catch (Exception ex) {
            log.warn("Unexpected exception occurred while releasing lock: ", ex);
        }
    }

    public static boolean isConnected(CuratorFramework client) {
        validateClient(client);
        CuratorFrameworkState state = client.getState();
        return state == STARTED && client.getZookeeperClient().isConnected();
    }

    /**
     * Waits until connection succeeded or timeout occurred.
     *
     * @param client input client
     * @return true if connection succeeded
     * @throws InterruptedException if interruption occurred
     */
    public static boolean blockUntilConnectedOrTimedOut(CuratorFramework client) throws InterruptedException {
        validateClient(client);
        return client.getZookeeperClient().blockUntilConnectedOrTimedOut();
    }

    /**
     * Retrieves child ZNodes for given path.
     *
     * @param client    input client
     * @param zNodePath input ZNode path
     * @return child ZNodes
     */
    public static List<String> getChildZNodes(CuratorFramework client, String zNodePath) throws ZooException {
        zNodePath = validateAndTransformPath(zNodePath);
        validateConnection(client);
        try {
            return client.getChildren().forPath(zNodePath);
        } catch (Exception ex) {
            throw new ZooException("Cannot retrieve child ZNodes, for path = " + zNodePath, ex);
        }
    }

    /**
     * Retrieves long value for given ZNode path.
     *
     * @param client    input client
     * @param zNodePath input ZNode path
     * @return long value stored in ZNode
     */
    public static long getLongFromPath(CuratorFramework client, String zNodePath) throws ZooException {
        zNodePath = validateAndTransformPath(zNodePath);
        validateConnection(client);
        try {
            byte[] bytes = client.getData().forPath(zNodePath);
            if (bytes == null || bytes.length != 8) {
                return 0;
            } else {
                ByteBuffer buffer = ByteBuffer.wrap(bytes);
                return buffer.getLong();
            }
        } catch (Exception ex) {
            throw new ZooException("Cannot retrieve long value from ZNode, for path = " + zNodePath, ex);
        }
    }

    /**
     * Deletes given ZNode with its children
     *
     * @param client    input client
     * @param zNodePath input ZNode path
     */
    public static void deletePathWithChildren(CuratorFramework client, String zNodePath) throws ZooException {
        zNodePath = validateAndTransformPath(zNodePath);
        validateConnection(client);
        try {
            client.delete().deletingChildrenIfNeeded().forPath(zNodePath);
        } catch (KeeperException.NoNodeException ignored) {
        } catch (Exception ex) {
            throw new ZooException("Exception occurred while deleting ZNode, for path = " + zNodePath, ex);
        }
    }

    /**
     * Creates or updates ZNode for given path and sets value to ZNode.
     *
     * @param client    input client
     * @param zNodePath input ZNode path
     * @param value     value that should be set
     * @return true if ZNode was created and false if ZNode already exists
     */
    public static boolean createOrUpdatePath(CuratorFramework client,
                                             String zNodePath,
                                             long value) throws ZooException {
        byte[] bytes = new byte[8];
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.putLong(value);
        return createOrUpdatePath(client, zNodePath, bytes);
    }

    /**
     * Creates ZNode for given path and sets value to ZNode.
     *
     * @param client    input client
     * @param zNodePath input ZNode path
     * @param value     value that should be set
     */
    public static void createPath(CuratorFramework client,
                                  String zNodePath,
                                  long value) throws ZooException {
        byte[] bytes = new byte[8];
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.putLong(value);
        createPath(client, zNodePath, bytes);
    }

    /**
     * Creates or updates ZNode for given path.
     *
     * @param client    input client
     * @param zNodePath input ZNode path
     * @return true if ZNode was created and false if ZNode already exists
     */
    public static boolean createOrUpdatePath(CuratorFramework client,
                                             String zNodePath) throws ZooException {
        return createOrUpdatePath(client, zNodePath, null);
    }

    /**
     * Creates ZNode for given path.
     *
     * @param client    input client
     * @param zNodePath input ZNode path
     */
    public static void createPath(CuratorFramework client,
                                  String zNodePath) throws ZooException {
        createPath(client, zNodePath, null);
    }

    /**
     * Creates ZNode for given path and sets value to ZNode if needed.
     *
     * @param client    input client
     * @param zNodePath input ZNode path
     * @param value     value that should be set
     * @return true if ZNode was created and false if ZNode already exists
     */
    private static boolean createOrUpdatePath(CuratorFramework client,
                                              String zNodePath,
                                              byte[] value) throws ZooException {
        zNodePath = validateAndTransformPath(zNodePath);
        validateConnection(client);
        Stat stat;
        try {
            stat = client.checkExists().forPath(zNodePath);
        } catch (Exception ex) {
            throw new ZooException("Cannot check if ZNode exists, for path = " + zNodePath, ex);
        }
        if (stat == null) {
            try {
                if (value != null) {
                    client.create().creatingParentsIfNeeded().forPath(zNodePath, value);
                } else {
                    client.create().creatingParentsIfNeeded().forPath(zNodePath);
                }
            } catch (Exception ex) {
                throw new ZooException("Cannot create ZNode, for path = " + zNodePath, ex);
            }
            log.debug("Path created: {}", zNodePath);
            return true;
        } else {
            if (value != null) {
                try {
                    client.setData().forPath(zNodePath, value);
                } catch (Exception ex) {
                    throw new ZooException("Cannot set data to ZNode, for path = " + zNodePath, ex);
                }
            }
            log.debug("Path already exists: {}", zNodePath);
            return false;
        }
    }

    private static void createPath(CuratorFramework client, String zNodePath, byte[] value)
            throws ZooException {
        zNodePath = validateAndTransformPath(zNodePath);
        validateConnection(client);
        try {
            if (value != null) {
                client.create().creatingParentsIfNeeded().forPath(zNodePath, value);
            } else {
                client.create().creatingParentsIfNeeded().forPath(zNodePath);
            }
        } catch (Exception ex) {
            throw new ZooException("Cannot create ZNode, for path = " + zNodePath, ex);
        }
        log.debug("Path created: {}", zNodePath);
    }

    private static void validateConnection(CuratorFramework client) throws ZooException {
        validateClient(client);
        CuratorFrameworkState state = client.getState();
        if (state != STARTED) {
            throw new ZooException("Curator client has not-started state: " + state);
        }
        if (!client.getZookeeperClient().isConnected()) {
            throw new ZooException("Curator client is not connected");
        }
    }

    private static void validateClient(CuratorFramework client) {
        requireNonNull(client, "Input client should be not null");
    }
}