#羽毛球场收费系统
##输入
{⽤户ID} {预订⽇期 yyyy-MM-dd} {预订时间段 HH:mm~HH:mm} {场地} {取消标记} ，
如 U123 2016-06-02 20:00~22:00 A C ，代表⽤户U123取消其在2016年06⽉02⽇晚上20:00到
22:00在场地A的预订，其中取消标记C代表Cancel

*输入的格式有部分正则表达式没有判断完全*


##输出
收⼊汇总
---
场地:A
2016-06-02 09:00~10:00 违约⾦ 15元  
2016-06-02 10:00~12:00 60元   
2016-06-03 20:00~22:00 120元  
⼩计：195元

##功能
预定羽毛球场，  
统计收入