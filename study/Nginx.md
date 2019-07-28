# Nginx和VSFTPD相关知识点

# 一，VSFTPD简介

1.Linux的组件(一款软件)，安装到Linux后通过java代码(FtpClient)实现文件上传

2.VSFTPD基于FTP协议

3.为什么要使用VSFTPD

​       3.1之前实现文件上传是怎么实现

​                              ![](pic/nginx1.png)

​       3.2使用VSFTPD后优化

​              3.2.1如果希望在客户端直接访问图片服务器中的图片，由于VSFTPD是基于FTP协议的，客户端浏览器是需通过http协议访问图片

​                     3.2.1.1解决办法：使用Nginx进行反向代理

 ![](pic/nginx2.png)

 

4.可以使用格式在浏览器中访问到对应的图片(不推荐这么使用)

​       4.1在谷歌浏览器中直接访问到ftpuser目录

4.2在IE中访问的是linux的根目录

```html
ftp://ftpuser:ftpuser@10.51.37.64
```

 

# 二，FtpClient

1.java技术，使用FtpClient使用java代码上传文件到vsftpd服务端

2.代码示例

```java
public class Test {
	public static void main(String[] args) throws IOException {
		FTPClient ftp = new FTPClient();
		ftp.connect("10.51.37.64", 21);
		ftp.login("ftpuser", "ftpuser");
		//设置文件类型
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		//存储时名称
		InputStream is = new FileInputStream("F:/umr.jpg");
		ftp.storeFile("umr.jpg", is);
		ftp.logout();
	}
}
```

3.使用web项目结合FtpClient完成

 ![](pic/nginx3.png)



# 三，反向代理和正向代理

1.正向代理服务器（例如vpn）

​       1.1客户端知道最终要访问的服务器地址

2.反向代理

​       2.1客户端只知道代理服务器地址，而不知道真实访问的服务器地址

 ![](pic/nginx4.png)

 

## 四，负载均衡

[Nginx如何实现负载均衡，Nginx负载均衡的几种方式](https://blog.csdn.net/x1021333506/article/details/80975462)