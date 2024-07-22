BeanUtils.copyProperties——还是提取不到值

为什么内置的bucket类无法打印出东西,传出的时候只会传出对象而不是对象的值

- 类对象中隐私字段,并且无get方法,获取当前对象的字段值——但是会报错

```java
ArrayList<ItemVO>  itemVOS= new ArrayList<>();
        for (Item item :items){
            ItemVO itemVO = new ItemVO();
            itemVO.setEtag(item.etag());
            itemVO.setObjectName(item.objectName());
            itemVO.setLastModified(item.lastModified());
            itemVO.setOwner(item.owner());
            itemVO.setSize(item.size());
            itemVO.setStorageClass(item.storageClass());
            itemVO.setLatest(item.isLatest());
            itemVO.setVersionId(item.versionId());
//            itemVO.setUserMetadata(item.userMetadata());
            itemVO.setDir(item.isDir());
            //encoding无get方法
            Field encodingType = item.getClass().getDeclaredField("encodingType");
            encodingType.setAccessible(true);
            itemVO.setEncodingType((String) encodingType.get(item));
            
            itemVOS.add(itemVO);
        }
        return itemVOS;
```

获取文件外链——临时访问

```java
/**
 * Gets presigned URL of an object for HTTP method, expiry time and custom request parameters.
 *
 * <pre>Example:{@code
 * // Get presigned URL string to delete 'my-objectname' in 'my-bucketname' and its life time
 * // is one day.
 * String url =
 *    minioClient.getPresignedObjectUrl(
 *        GetPresignedObjectUrlArgs.builder()
 *            .method(Method.DELETE)
 *            .bucket("my-bucketname")
 *            .object("my-objectname")
 *            .expiry(24 * 60 * 60)
 *            .build());
 * System.out.println(url);
 *
 * // Get presigned URL string to upload 'my-objectname' in 'my-bucketname'
 * // with response-content-type as application/json and life time as one day.
 * Map<String, String> reqParams = new HashMap<String, String>();
 * reqParams.put("response-content-type", "application/json");
 *
 * String url =
 *    minioClient.getPresignedObjectUrl(
 *        GetPresignedObjectUrlArgs.builder()
 *            .method(Method.PUT)
 *            .bucket("my-bucketname")
 *            .object("my-objectname")
 *            .expiry(1, TimeUnit.DAYS)
 *            .extraQueryParams(reqParams)
 *            .build());
 * System.out.println(url);
 *
 * // Get presigned URL string to download 'my-objectname' in 'my-bucketname' and its life time
 * // is 2 hours.
 * String url =
 *    minioClient.getPresignedObjectUrl(
 *        GetPresignedObjectUrlArgs.builder()
 *            .method(Method.GET)
 *            .bucket("my-bucketname")
 *            .object("my-objectname")
 *            .expiry(2, TimeUnit.HOURS)
 *            .build());
 * System.out.println(url);
 * }</pre>
```

- 赋值空指针异常

![image-20240712111004853](/Users/dearfriend/Library/Application Support/typora-user-images/image-20240712111004853.png)

![image-20240712111040798](/Users/dearfriend/Library/Application Support/typora-user-images/image-20240712111040798.png)



```java
for (Result<Item> result : results) {
            Item item = result.get();
            ItemVO itemVO = new ItemVO();
            try {
                if (item != null && item.lastModified() != null) {
//                    BeanUtils.copyProperties(item, itemVO);
                    itemVO = ItemVO.itemTransform(item);
                }

            } catch (NullPointerException e) {   //LastModified会出现空指针异常
                itemVO = ItemVO.itemTransformIgnoreLastModified(item);
//                itemVO = ItemVO.itemTransform(item);         //todo:文件夹在这个地方获取不打破//LastModified会出现空指针异常——失败
//                itemVO.setLastModified(null);    //原始null值
//                BeanUtils.copyProperties(item, itemVO, "lastModified");
            }
            if (itemVO.getObjectName().endsWith("/")) {
                itemVO.setDir(true);
            }
```

- 前端传过来,参数传入List<string>(),后面直接调用出现No primary or default constructor found for interface java.util.List——List是接口()