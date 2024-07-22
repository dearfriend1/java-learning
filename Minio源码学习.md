# Minio源码学习





## 创建客户端

```java
MinioClient.builder()
```

参数

| 参数                                 | 作用       | 注释       |
| :----------------------------------- | ---------- | ---------- |
| .endpoint("")                        | 服务器地址 |            |
| .credentials(public-key, secret-key) | 指定身份   | (可不添加) |

## 桶相关操作

### 创建桶MakeBucketArgs

参数

| 参数        | 作用                         | 注释 |
| ----------- | ---------------------------- | ---- |
| .bucket     | 指定桶名                     | 必须 |
| .regin      | 地区                         |      |
| .objectLock | 保护对象免受删除或修改的功能 |      |



### 删除桶RemoveBucketArgs 

### 桶存在  BucketExistsArgs 

### 对象、桶信息ListObjectsArgs

列出对象信息（可选）以及bucket的版本。



## 对象相关操作

### 对象信息StatObjectArgs

### 从流中将数据上传到 MinIO 对象存储 PutObjectArgs

### 从文件中将数据上传到 MinIO 对象存储 UploadObjectArgs 

### 复制对象CopyObjectArgs

### 恢复历史版本对象 RestoreObjectArgs

这个功能实际就是创建版本(恢复历史/当前版本作为最新版本)

```java
/**
   * Restores an object.
   *
   * <pre>Example:{@code
   * // Restore object.
   * minioClient.restoreObject(
   *     RestoreObjectArgs.builder()
   *         .bucket("my-bucketname")
   *         .object("my-objectname")
   *         .request(new RestoreRequest(null, null, null, null, null, null))
   *         .build());
   *
   * // Restore versioned object.
   * minioClient.restoreObject(
   *     RestoreObjectArgs.builder()
   *         .bucket("my-bucketname")
   *         .object("my-versioned-objectname")
   *         .versionId("my-versionid")
   *         .request(new RestoreRequest(null, null, null, null, null, null))
   *         .build());
   * }</pre>
   *
   * @param args {@link RestoreObjectArgs} object.
   * @throws ErrorResponseException thrown to indicate S3 service returned an error response.
   * @throws InsufficientDataException thrown to indicate not enough data available in InputStream.
   * @throws InternalException thrown to indicate internal library error.
   * @throws InvalidKeyException thrown to indicate missing of HMAC SHA-256 library.
   * @throws InvalidResponseException thrown to indicate S3 service returned invalid or no error
   *     response.
   * @throws IOException thrown to indicate I/O error on S3 operation.
   * @throws NoSuchAlgorithmException thrown to indicate missing of MD5 or SHA-256 digest library.
   * @throws XmlParserException thrown to indicate XML parsing error.
   */
```



### 副本组合ComposeObjectArgs

通过使用服务器端副本组合来自不同源对象的数据来创建对象。

### 删除对象RemoveObjectArgs

### 指定列表删除RemoveObjectsArgs 

缓慢删除多个对象。需要迭代返回的Iterable以执行删除。

### 获取对象内容GetObjectArgs

从SSE-C加密对象的偏移量到长度获取数据。使用后必须关闭返回的InputStream以释放网络资源。

### 下载对象 DownloadObjectArgs

### 预签名链接GetPresignedObjectUrlArgs

获取HTTP方法、过期时间和自定义请求参数的对象的预签名URL。

### 对象、桶信息ListObjectsArgs

列出对象信息（可选）以及bucket的版本。

### 单次上传多个对象 UploadSnowballObjectsArgs

在一次put调用中放置多个对象。这是通过创建可选择压缩的中间TAR文件来完成的，该文件上传到S3服务。

### 

## 桶版本功能配置信息

### 设置桶版本SetBucketVersioningArgs

默认没有versionId,要想查看versionId需要

```java
.includeVersions(true)
```



```java
    /**
     * 设置桶的版本控制功能
     *
     */
    public static void setVersionConfig(String bucketName, Boolean enable) throws Exception {
        VersioningConfiguration.Status status;
        if (enable == null) return;   //不传只就默认不打开,打开后只能关闭或者暂停
        if (enable) {
            status = VersioningConfiguration.Status.ENABLED;
        } else {
            status = VersioningConfiguration.Status.SUSPENDED;
        }

        VersioningConfiguration versioningConfiguration = new VersioningConfiguration(status, null);
        minioClient.setBucketVersioning(SetBucketVersioningArgs.builder().bucket(bucketName).config(versioningConfiguration).build());
    }
```



### 获取桶版本GetBucketVersioningArgs



## 上传策略

### 上传权限策略PostPolicy

获取对象的PostPolicy的表单数据，以使用POST方法上传其数据。



## 默认对象保留策略

### 设置默认对象保留策略SetObjectLockConfigurationArgs

### 删除默认对象保留策略DeleteObjectLockConfigurationArgs

### 获取默认对象保留策略GetObjectLockConfigurationArgs

## 保留策略配置

### 对象设置保留配置 SetObjectRetentionArgs

### 对象设置获取保留配置 GetObjectRetentionArgs



## 对象合法保留

### 启用对对象的合法保留 EnableObjectLegalHoldArgs

防止对象在指定时间内被删除或修改。合法保留是一种特殊的保留模式，它优先于所有其他保留规则。

### 禁用对对象的合法保留 DisableObjectLegalHoldArgs

### 是否合法保留对象 IsObjectLegalHoldEnabledArgs



## 桶策略

### 获取桶策略配置GetBucketPolicyArgs

### 设置桶策略配置SetBucketPolicyArgs

### 删除桶策略配置DeleteBucketPolicyArgs



## 桶生命周期

### 设置桶生命周期SetBucketLifecycleArgs

设置桶的生命周期配置可以帮助管理对象的存储，通过自动执行如过期删除等操作来优化存储成本

### 删除桶生命周期DeleteBucketLifecycleArgs

### 获取桶生命周期 GetBucketLifecycleArgs 



## 桶通知配置

### 获取桶通知配置 GetBucketNotificationArgs

### 设置桶通知配置 SetBucketNotificationArgs

### 删除桶通知配置 DeleteBucketNotificationArgs



## 桶复制配置

### 获取桶复制配置GetBucketReplicationArgs

### 设置桶复制配置SetBucketReplicationArgs

### 删除桶复制配置DeleteBucketReplicationArgs



## 监听桶通知事件

### 监听bucket的对象前缀和后缀的事件 ListenBucketNotificationArgs 

返回的可关闭迭代器是延迟评估的，因此需要迭代以获取新记录，并且必须与try with resource一起使用，以释放网络资源下方的资源。



## SQL表达式


### 通过SQL表达式选择对象的内容 SelectObjectContentArgs



## 桶加密配置

### 设置bucket的加密配置SetBucketEncryptionArgs 

### 获取bucket的加密配置GetBucketEncryptionArgs 

### 删除bucket的加密配置DeleteBucketEncryptionArgs 



## 桶标签

### 获取桶标签 GetBucketTagsArgs 

### 设置桶标签 SetBucketTagsArgs 

### 删除桶标签 DeleteBucketTagsArgs 



## 对象标签

### 获取对象标签 GetObjectTagsArgs 

### 设置对象标签 SetObjectTagsArgs 

### 删除对象标签 DeleteObjectTagsArgs 



## 请求超时
### 设置超时事件
设置HTTP连接、写入和读取超时。值0表示没有超时，否则值必须介于1和整数之间。转换为毫秒时的MAX_VALUE。

```
setTimeout
```

## 响应相关参数

### 对象信息StatObjectResponse

```java
  //class GenericResponse
  private Headers headers;   	//响应头部
  private String bucket;			//存储桶名
  private String region;			//存储桶所在区域(地区)
  private String object;			//存储对象名称
  
	//class StatObjectResponse extends GenericResponse
  private String etag;															//对象的实体标签 (ETag)。ETag 是一个唯一标识符
  private long size;																//对象的大小，以字节为单位
  private ZonedDateTime lastModified;								//对象的最后修改时间
  private RetentionMode retentionMode;							//对象的保留模式
  private ZonedDateTime retentionRetainUntilDate;		//对象的保留截止日期
  private LegalHold legalHold;											//对象的合法保留状态
  private boolean deleteMarker;											//删除标记(非实际删除)
  private Map<String, String> userMetadata;         //用户自定义的元数据(相当于自定义字段)
```

## 相关字段

### 基础参数



### 存储桶BucketArgs







## 返回数据

ITERM默认无versionID

![image-20240718094838882](/Users/dearfriend/Library/Application Support/typora-user-images/image-20240718094838882.png)
