# Jsonp、HttpClient跨域问题

## 一、跨域

1.跨域

​	1.1 一个 Servlet 容器(tomcat)中项目调用另一个 servlet 容器(tomcat)中项目,称为跨域

2.ajax 在研发时处于安全性考虑不允许跨域请求的. 

​	2.1 解决办法1：使用 jsonp
​	2.2 解决办法2：由项目访问自己的控制器,自己的控制器访问自己的 service,在自己的service 中使用 httpclient 调用另一个项目的控制器方法.



## 二. jsonp

1. jsonp:跨域 ajax 数据请求的解决方案

2. 发展由来
  2.1 ajax 不能进行跨域请求(如果 ajax 请求的控制器返回的就是字符串六或 json 数据,不能访问
  2.2 发现可以在一个项目直接访问另一个项目的 js 文件.(标签引用还是通过 ajax 访问)
  2.3 使用 ajax 访问另一个项目的控制器,但是控制器返回的结果伪装成 js 文件. 

3. 使用 jsonp 时服务器端返回的数据格式:

  ```
  函数名(返回的数据);
  ```

4. 在客户端编写代码
  4.1 dataType: 一定要设置 jsonp
  4.2 jsonp: 传递给服务器的参数名.省略的默认 callback
  4.3 jsonCallback: 参数名对应的值,表示最终回调的函数名.省略的默认值 jquery:一堆数字
  4.4 如果直接使用 success:function()对 jsonpCallback 值没有要求. 4.5 如果单独编写了一个function,必须要求 jsonpCallback 和function 的名称相同

  ```javascript
  $(function(){
  	$("button").click(function(){
  		$.ajax({
  			url:'http://localhost:9002/demo2',
  			type:'post',
  			dataType:'jsonp',
               jsonpCallback:'ab12312321c',
               jsonp:'callback',
               success:function(data){
               alert(data+"success");
            }
        });
    })
  })
  ```

  ​

5. 在服务器端添加代码
  5.1 使用 spring 对 jackson 封装类 MappingJacksonValue

```java
@Controller
public class DemoController {
    @RequestMapping("demo3")
    @ResponseBody
    public MappingJacksonValue demo(String callback){
        People p = new People();
        p.setId(1);
        p.setName("张三");
        //把构造方法参数转换为 json 字符串并当作最终返回值函数的参数
        MappingJacksonValue mjv = new
        MappingJacksonValue(p);
        //最终返回结果中函数名
        mjv.setJsonpFunction(callback);
        return mjv;
    }
}	
```





## 三、两者区别

区别:

1. 请求发起的位置不同
  JSONP:浏览器解析ajax发起的请求
  HttpClient:java代码中模拟发起请求
2. 安全性不同
  JSONP:浏览器可以记录JSONP的全部请求过程.
  HttpClient: 因为请求是经过2次处理的,所以相对安全
3. 请求方式不同:
  JSONP:发起GET请求,一般以查询为主.
  httpClient:可以发起get/post请求.
4. 代码调用层级不同:
  JSONP:3级调用
  HttpClient:5级调用
