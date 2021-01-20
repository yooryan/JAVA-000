## 1、作业
### 2、去掉内存Queue，设计自定义Queue，实现消息确认和消费offset 
    1）自定义内存Message数组模拟Queue。
    2）使用指针记录当前消息写入位置。
        
### 3、拆分broker和client(包括producer和consumer)
    1）将Queue保存到web server端 
    2）设计消息读写API接口，确认接口，提交offset接口