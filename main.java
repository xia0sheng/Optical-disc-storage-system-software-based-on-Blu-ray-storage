import java.util.Scanner;

public class UserLoginModule {
    private String username;
    private String password;

    public UserLoginModule(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login() {
        Scanner input = new Scanner(System.in);

        // Step 1: Receive username and password from user
        System.out.println("请输入用户名：");
        String inputUsername = input.nextLine();

        System.out.println("请输入密码：");
        String inputPassword = input.nextLine();

        // Step 2: Check if the username and password are correct
        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            System.out.println("登录成功");
            return true;
        } else {
            System.out.println("用户名或密码错误");
            return false;
        }
    }
}
import java.util.Scanner;

public class UserManagementModule {
    private String username;
    private String password;

    public UserManagementModule(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void showMenu() {
        Scanner input = new Scanner(System.in);

        // Step 1: Display user menu
        System.out.println("欢迎 " + username + " 使用系统");
        System.out.println("1. 切换用户账户");
        System.out.println("2. 修改信息门户账号密码");
        System.out.println("3. 申请容量扩充");
        System.out.println("4. 注销");

        // Step 2: Receive user input
        System.out.println("请输入选项：");
        int choice = input.nextInt();

        // Step 3: Process user input
        switch (choice) {
            case 1:
                System.out.println("切换用户账户");
                break;
            case 2:
                System.out.println("修改信息门户账号密码");
                break;
            case 3:
                System.out.println("申请容量扩充");
                break;
            case 4:
                System.out.println("注销");
                break;
            default:
                System.out.println("无效选项");
                break;
        }

        input.close();
    }
}

import java.util.ArrayList;
import java.util.List;

public class FileManagementModule {
    private List<String> files;

    public FileManagementModule() {
        files = new ArrayList<String>();
    }

    public void uploadFile(String filename) {
        files.add(filename);
        System.out.println("文件 " + filename + " 上传成功");
    }

    public void showFiles() {
        System.out.println("所有文件：");
        for (String file : files) {
            System.out.println(file);
        }
    }

    public void showFilesByType(String type) {
        System.out.println(type + " 类型的文件：");
        for (String file : files) {
            if (file.endsWith("." + type)) {
                System.out.println(file);
            }
        }
    }
}

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUploadModule {
    private List<String> files;

    public FileUploadModule() {
        files = new ArrayList<String>();
    }

    public void uploadFile(String filename) {
        File file = new File(filename);

        // Step 1: Check if the file exists
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }

        // Step 2: Check if the file is too large
        if (file.length() > 1024 * 1024 * 10) {
            System.out.println("文件太大，需要采用多线程上传");
            // TODO: Implement multi-threaded file upload
            return;
        }

        // Step 3: Check if the file already exists on the server
        if (files.contains(filename)) {
            System.out.println("文件已经存在");
            return;
        }

        // Step 4: Upload the file
        files.add(filename);
        System.out.println("文件 " + filename + " 上传成功");
    }
}
import java.io.File;

public class FileDownloadModule {
    public void downloadFile(String filename, String savePath) {
        File file = new File(filename);

        // Step 1: Check if the file exists
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }

        // Step 2: Download the file
        // TODO: Implement file download

        System.out.println("文件 " + filename + " 下载成功，已保存到 " + savePath);
    }
}

import java.util.ArrayList;
import java.util.List;

public class FileSharingModule {
    private List<String> files;
    private boolean hasPermission;

    public FileSharingModule(boolean hasPermission) {
        files = new ArrayList<String>();
        this.hasPermission = hasPermission;
    }

    public void shareFile(String filename, String department) {
        // Step 1: Check if the user has permission to share files
        if (!hasPermission) {
            System.out.println("您没有分享文件的权限");
            return;
        }

        // Step 2: Check if the file exists
        if (!files.contains(filename)) {
            System.out.println("文件不存在");
            return;
        }

        // Step 3: Check if the file is private
        // TODO: Implement private file check

        // Step 4: Share the file
        System.out.println("文件 " + filename + " 已分享到 " + department + " 部门");
    }
}
import java.io.File;

public class FileDeletionModule {
    public void deleteFile(String filename) {
        File file = new File(filename);

        // Step 1: Check if the file exists
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }

        // Step 2: Delete the file
        if (file.delete()) {
            System.out.println("文件 " + filename + " 删除成功");
            // TODO: Update database information
        } else {
            System.out.println("文件 " + filename + " 删除失败");
        }
    }
}

import java.net.Socket;

public class ConnectionManager {
    private Socket socket;

    public ConnectionManager(String host, int port) {
        try {
            socket = new Socket(host, port);
            System.out.println("连接成功");
        } catch (Exception e) {
            System.out.println("连接失败");
        }
    }

    public void closeConnection() {
        try {
            socket.close();
            System.out.println("连接已关闭");
        } catch (Exception e) {
            System.out.println("关闭连接失败");
        }
    }
}
import java.util.HashMap;

public class LRUCache<K, V> {
    private int capacity;
    private HashMap<K, Node> map;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<K, Node>();
        head = new Node(null, null);
        tail = new Node(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key) {
        Node node = map.get(key);
        if (node == null) {
            return null;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(K key, V value) {
        Node node = map.get(key);
        if (node == null) {
            node = new Node(key, value);
            map.put(key, node);
            addToHead(node);
            if (map.size() > capacity) {
                Node removed = removeTail();
                map.remove(removed.key);
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private Node removeTail() {
        Node node = tail.prev;
        removeNode(node);
        return node;
    }

    private class Node {
        K key;
        V value;
        Node prev;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

import java.util.HashMap;

public class LIRSCache<K, V> {
    private int capacity;
    private HashMap<K, Node> map;
    private Node head;
    private Node tail;
    private int recencyThreshold;
    private int recencyCount;

    public LIRSCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<K, Node>();
        head = new Node(null, null);
        tail = new Node(null, null);
        head.next = tail;
        tail.prev = head;
        recencyThreshold = 1;
        recencyCount = 0;
    }

    public V get(K key) {
        Node node = map.get(key);
        if (node == null) {
            return null;
        }
        moveToHead(node);
        node.recency = ++recencyCount;
        return node.value;
    }

    public void put(K key, V value) {
        Node node = map.get(key);
        if (node == null) {
            node = new Node(key, value);
            map.put(key, node);
            addToHead(node);
            node.recency = ++recencyCount;
            if (map.size() > capacity) {
                Node removed = removeTail();
                map.remove(removed.key);
                if (removed.recency > recencyThreshold) {
                    recencyThreshold = removed.recency;
                }
            }
        } else {
            node.value = value;
            moveToHead(node);
            node.recency = ++recencyCount;
        }
        if (recencyCount == Integer.MAX_VALUE) {
            resetRecency();
        } else if (recencyCount % capacity == 0) {
            adjustRecencyThreshold();
        }
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private Node removeTail() {
        Node node = tail.prev;
        removeNode(node);
        return node;
    }

    private void resetRecency() {
        for (Node node = head.next; node != tail; node = node.next) {
            node.recency = 0;
        }
        recencyCount = 0;
        recencyThreshold = 1;
    }

    private void adjustRecencyThreshold() {
        int minRecency = Integer.MAX_VALUE;
        for (Node node = head.next; node != tail; node = node.next) {
            if (node.recency < minRecency) {
                minRecency = node.recency;
            }
        }
        recencyThreshold = minRecency;
    }

    private class Node {
        K key;
        V value;
        Node prev;
        Node next;
        int recency;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.recency = 0;
        }
    }
}

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TypeAndDiskLRUCache<K, V> {
    private int capacity;
    private Map<K, Node> map;
    private LinkedList<Node>[] diskArray;
    private int diskCount;
    private int recencyCount;

    public TypeAndDiskLRUCache(int capacity, int diskCount) {
        this.capacity = capacity;
        this.diskCount = diskCount;
        map = new HashMap<>();
        diskArray = new LinkedList[diskCount];
        for (int i = 0; i < diskCount; i++) {
            diskArray[i] = new LinkedList<>();
        }
        recencyCount = 0;
    }

    public V get(K key) {
        Node node = map.get(key);
        if (node == null) {
            return null;
        }
        moveToHead(node);
        node.recency = ++recencyCount;
        return node.value;
    }

    public void put(K key, V value, int type) {
        Node node = map.get(key);
        if (node == null) {
            node = new Node(key, value, type);
            map.put(key, node);
            addToHead(node);
            node.recency = ++recencyCount;
            if (map.size() > capacity) {
                Node removed = removeTail();
                map.remove(removed.key);
                diskArray[removed.type].remove(removed);
            }
        } else {
            node.value = value;
            moveToHead(node);
            node.recency = ++recencyCount;
        }
        if (recencyCount == Integer.MAX_VALUE) {
            resetRecency();
        } else if (recencyCount % capacity == 0) {
            adjustDiskThreshold();
        }
    }

    private void addToHead(Node node) {
        diskArray[node.type].addFirst(node);
    }

    private void removeNode(Node node) {
        diskArray[node.type].remove(node);
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private Node removeTail() {
        Node node = null;
        for (int i = 0; i < diskCount; i++) {
            if (!diskArray[i].isEmpty()) {
                node = diskArray[i].removeLast();
                break;
            }
        }
        return node;
    }

    private void resetRecency() {
        for (Node node : map.values()) {
            node.recency = 0;
        }
        recencyCount = 0;
    }

    private void adjustDiskThreshold() {
        int minRecency = Integer.MAX_VALUE;
        for (Node node : map.values()) {
            if (node.recency < minRecency) {
                minRecency = node.recency;
            }
        }
        for (int i = 0; i < diskCount; i++) {
            diskArray[i].clear();
        }
        for (Node node : map.values()) {
            if (node.recency >= minRecency) {
                diskArray[node.type].addFirst(node);
            }
        }
    }

    private class Node {
        K key;
        V value;
        int type;
        int recency;

        public Node(K key, V value, int type) {
            this.key = key;
            this.value = value;
            this.type = type;
            this.recency = 0;
        }
    }
}

public class Cache<K, V> {
    private final int diskCount;
    private final Map<K, Node> map;
    private final LinkedList<Node>[] diskArray;

    public Cache(int diskCount) {
        this.diskCount = diskCount;
        this.map = new HashMap<>();
        this.diskArray = new LinkedList[diskCount];
        for (int i = 0; i < diskCount; i++) {
            diskArray[i] = new LinkedList<>();
        }
    }

    public void put(K key, V value, int type) {
        Node node = map.get(key);
        if (node == null) {
            node = new Node(key, value, type);
            map.put(key, node);
        } else {
            node.value = value;
            node.type = type;
        }
        node.recency = Integer.MAX_VALUE;
        for (LinkedList<Node> list : diskArray) {
            if (!list.isEmpty() && list.getFirst().type == type) {
                node.recency = Math.min(node.recency, list.getFirst().recency);
            }
        }
        node.recency++;
        diskArray[type].addFirst(node);
        if (diskArray[type].size() > 10) {
            Node removed = diskArray[type].removeLast();
            map.remove(removed.key);
        }
    }

    public V get(K key) {
        Node node = map.get(key);
        if (node == null) {
            return null;
        }
        node.recency = Integer.MAX_VALUE;
        for (LinkedList<Node> list : diskArray) {
            if (!list.isEmpty() && list.getFirst().type == node.type) {
                node.recency = Math.min(node.recency, list.getFirst().recency);
            }
        }
        node.recency++;
        diskArray[node.type].remove(node);
        diskArray[node.type].addFirst(node);
        return node.value;
    }

    private void rebalance() {
        int minRecency = Integer.MAX_VALUE;
        for (Node node : map.values()) {
            if (node.recency < minRecency) {
                minRecency = node.recency;
            }
        }
        for (int i = 0; i < diskCount; i++) {
            diskArray[i].clear();
        }
        for (Node node : map.values()) {
            if (node.recency >= minRecency) {
                diskArray[node.type].addFirst(node);
            }
        }
    }

    private class Node {
        K key;
        V value;
        int type;
        int recency;

        public Node(K key, V value, int type) {
            this.key = key;
            this.value = value;
            this.type = type;
            this.recency = 0;
        }
    }
}

import java.util.HashMap;

public class Cache<K, V> {
    private HashMap<K, CacheEntry<V>> cacheMap = new HashMap<>();

    public V get(K key) {
        CacheEntry<V> entry = cacheMap.get(key);
        if (entry == null) {
            return null;
        }
        if (entry.isExpired()) {
            cacheMap.remove(key);
            return null;
        }
        return entry.getValue();
    }

    public void put(K key, V value, long ttl) {
        CacheEntry<V> entry = new CacheEntry<>(value, ttl);
        cacheMap.put(key, entry);
    }

    private class CacheEntry<T> {
        private T value;
        private long expirationTime;

        public CacheEntry(T value, long ttl) {
            this.value = value;
            this.expirationTime = System.currentTimeMillis() + ttl;
        }

        public T getValue() {
            return value;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() > expirationTime;
        }
    }
}
import java.util.HashMap;

public class UserManagement {
    private HashMap<String, ApplyEntry> applyMap = new HashMap<>();

    public boolean applyForSpace(String userID) {
        ApplyEntry apply = findApplyByUser(userID);
        if (apply != null) {
            if (apply.status == ApplyStatus.PENDING) {
                return false;
            } else if (apply.status == ApplyStatus.APPROVED) {
                apply.status = ApplyStatus.PENDING;
            }
        } else {
            apply = new ApplyEntry(userID);
            applyMap.put(userID, apply);
        }
        return true;
    }

    private ApplyEntry findApplyByUser(String userID) {
        return applyMap.get(userID);
    }

    private class ApplyEntry {
        private String userID;
        private ApplyStatus status;

        public ApplyEntry(String userID) {
            this.userID = userID;
            this.status = ApplyStatus.PENDING;
        }
    }

    private enum ApplyStatus {
        PENDING,
        APPROVED,
        REJECTED
    }
}
public class FileManager {
    private Map<String, String> fileMap = new HashMap<>();
    private Map<String, List<String>> departmentMap = new HashMap<>();

    public String upLoadFileU(HttpServletRequest request) {
        String actionId = request.getParameter("actionId");
        String breakpoint = request.getParameter("breakpoint");
        String tempfilePath = request.getParameter("tempfilePath");
        writeToTheCDCACHE(tempfilePath);
        Map<String, String> result = new HashMap<>();
        result.put("status", "success");
        return sendJSONToClient(result);
    }

    public void downLoadFile(HttpServletResponse response, String userID, String fileID) {
        if (checkDownLoadFile(userID, fileID)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileMap.get(fileID));
            if (response.getStatus() == 206) {
                skip(response.getOutputStream(), response.getOutputStream().size());
            }
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    public void shareFileToDepartment(String fileID, String userID) {
        List<String> departmentList = new ArrayList<>();
        Department userDepartment = findDepartmentByUserID(userID);
        if (userDepartment.getLevel() == 1) {
            departmentList.add(userDepartment.getDepartmentID());
            for (Department department : findAllDepartment()) {
                if (department.getLevel() == 1) {
                    departmentList.add(department.getDepartmentID());
                } else if (department.getParentDepartmentID().equals(userDepartment.getDepartmentID())) {
                    departmentList.add(department.getDepartmentID());
                }
            }
        } else {
            Department parentDepartment = findDepartmentByID(userDepartment.getParentDepartmentID());
            departmentList.add(parentDepartment.getDepartmentID());
            for (Department department : findAllDepartment()) {
                if (department.getParentDepartmentID().equals(parentDepartment.getDepartmentID())) {
                    departmentList.add(department.getDepartmentID());
                }
            }
        }
        departmentMap.put(fileID, departmentList);
    }

    public void deleteFile(String fileID) {
        fileMap.remove(fileID);
        departmentMap.remove(fileID);
        Map<String, String> result = new HashMap<>();
        result.put("status", "success");
        sendJSONToClient(result);
    }

    private void writeToTheCDCACHE(String tempfilePath) {
        // write file to cache
    }

    private boolean checkDownLoadFile(String userID, String fileID) {
        // check user permission
        return true;
    }

    private Department findDepartmentByUserID(String userID) {
        // find department by user ID
        return new Department();
    }

    private Department findDepartmentByID(String departmentID) {
        // find department by ID
        return new Department();
    }

    private List<Department> findAllDepartment() {
        // find all departments
        return new ArrayList<>();
    }

    private void skip(OutputStream outputStream, long size) {
        // skip bytes
    }

    private String sendJSONToClient(Map<String, String> result) {
        // send JSON to client
        return "";
    }
}
function sendRequest() {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/api/data", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            // do something with the response object
        }
    };
    var data = {
        name: "John",
        age: 30
    };
    xhr.send(JSON.stringify(data));
}
