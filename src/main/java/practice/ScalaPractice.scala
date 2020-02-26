
object HelloWorld {
  def  main(args: Array[String]): Unit ={
    val input = "2020-01-21 00:59:48,005 [ServerBizExecutor#2986] [requestid:43f34e4d-3ba6-11ea-9c89-b58c86b16a6c] [sessionid:4b534c47-0083-46a8-1f90-157953959123] [userid:18793014] [optid:19256444] [methodname:com.baidu.fengchao.puppet.function.custom.CustomToolBarFunction.queryToolBarInfo] [params:(18793014,19256444)] [optfrom:1;optcontext:3;tokenid:null;tokentype:null;] [start:2020-01-21 00:59:47,892] [end:2020-01-21 00:59:48,005] [using:112ms] [ip:10.151.88.18] [status:200]"
    val pattern = """(\S+ \S+),\d+ \[(\S+)\] \[requestid:(\S+)\] \[sessionid:(\S+)\] \[userid:(\d+)\] \[optid:(\d+)\] \[methodname:(\S+)\] \[params:(\S+)\] \[optfrom:(\d+);optcontext:(\d+);tokenid:(\S+);tokentype:(\S+);] \[start:(\S+ \S+),\d+\] \[end:(\S+ \S+),\d+\] \[using:(\S+)ms\] \[ip:(\d+.\d+.\d+.\d+)\] \[status:(\d+)\]""".r
    val regRes = pattern.findFirstMatchIn(input)
    regRes.get.subgroups.foreach(s => print(s+"\n"))
  }
}