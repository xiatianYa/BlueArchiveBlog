package com.blue.common.core.utils.udp;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


/**
 * 服务器信息查询工具类
 *
 * @author summer
 */
public class GameServerUtil {
    // 构造A2S_INFO请求
    private static final byte[] requestData = new byte[]{
            (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, // Header
            (byte) 0x54, // Type (A2S_INFO)
            'S', 'o', 'u', 'r', 'c', 'e', ' ', 'E', 'n', 'g', 'i', 'n', 'e', ' ', 'Q', 'u', 'e', 'r', 'y', '\0'
    };

    public static byte[] sendAndReceiveUDP(String ip, int port) {
        try (DatagramSocket socket = new DatagramSocket()) {
            //设置超时时间
            socket.setSoTimeout(500);
            InetAddress address = InetAddress.getByName(ip);

            // 发送请求
            DatagramPacket sendPacket = new DatagramPacket(requestData, requestData.length, address, port);
            socket.send(sendPacket);

            // 接收响应
            byte[] buffer = new byte[1024];
            sendPacket = new DatagramPacket(buffer, buffer.length);

            socket.receive(sendPacket);

            byte[] receivedData = new byte[sendPacket.getLength()];
            System.arraycopy(buffer, 0, receivedData, 0, sendPacket.getLength());

            //开始发送第二次UDP请求
            byte[] reqParama = new byte[receivedData.length - 5];
            System.arraycopy(receivedData, 5, reqParama, 0, receivedData.length - 5);

            //封装第二次请求的数据
            reqParama = concatenate(requestData, reqParama);

            //发送第二次请求
            sendPacket = new DatagramPacket(reqParama, reqParama.length, address, port);
            socket.send(sendPacket);

            sendPacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(sendPacket);

            receivedData = new byte[sendPacket.getLength()];
            System.arraycopy(buffer, 6, receivedData, 0, sendPacket.getLength());
            
            //关闭连接
            socket.close();
            return findSequence(receivedData, (byte) -38, (byte) 2);
        } catch (Exception e) {
            //返回一个byte [64,64]
            byte[] result = new byte[2];
            result[0]=64;
            result[1]=64;
            return result;
        }
    }

    /**
     * 将两个byte数组拼接起来
     *
     * @param first  第一个byte数组
     * @param second 第二个byte数组
     * @return 拼接后的byte数组
     */
    public static byte[] concatenate(byte[] first, byte[] second) {
        // 计算新数组的长度
        int totalLength = first.length + second.length;
        // 创建新数组
        byte[] result = new byte[totalLength];

        // 将第一个数组的内容复制到新数组中
        System.arraycopy(first, 0, result, 0, first.length);
        // 将第二个数组的内容追加到新数组中
        System.arraycopy(second, 0, result, first.length, second.length);

        // 返回新数组
        return result;
    }

    /**
     * 获取服务器在线人数/最大人数
     * @param array byte[]数组
     * @param first 第一个元素
     * @param second 第二个元素
     * @return byte[] [0] 在线人数 [1] 最大人数
     */
    public static byte[] findSequence(byte[] array, byte first, byte second) {
        if (array == null || array.length < 2) {
            return null;
        }

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == first && array[i + 1] == second) {
                byte[] responseBytes = new byte[2];
                responseBytes[0] = array[i+2];
                responseBytes[1] = array[i + 3];
                return responseBytes; // 返回第一个元素的下标
            }
        }

        return null; // 如果没有找到，返回-1
    }
}
