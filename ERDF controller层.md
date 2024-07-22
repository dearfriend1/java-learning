# ERDF controller层

## 基本结构

- 包名 control
- 类名 xxxControl
- 注解(@Controller+@ResponseBody或者@ResetController)

## 请求方法

## get

@RequestParam 从 URL 查询参数或表单数据中提取数据。

## post

简单参数无法解析,要使用@RequestBody注解,使用Json

- 不能使用@RequestParam注解

- #### <u>***@RequestBody+类类型——Json***</u>

- ### <u>***不注解+包装类——表单***</u>

- 不注解+类类型——类字段表单——**参数对象无法接受到值**



## 参数注解

@RequestParam 从 **URL 查询参数**或**表单数据**中提取数据。

@RequestBody 从**请求体**中提取数据。

