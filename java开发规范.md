# java开发规范

## 不使用外键

避免外键的使用,不进行表的强关联,仅仅增加逻辑上的关联字段,在业务层保证其逻辑关联

## select例子

```java
 public Response<T> select(Long id) {
        Set<String> fields = CollectUtil.set();
        fields.add("t.*");
        List<String> joins = new ArrayList();
        Iterator var4 = this.attributes().iterator();

        while(true) {
            Attribute attribute;
            List children;
            do {
                do {
                    do {
                        while(true) {
                            do {
                                if (!var4.hasNext()) {
                                    String sql;
                                    if (joins.size() == 0) {
                                        sql = String.format("SELECT %s FROM %s t WHERE t.id = %s", String.join(", ", fields), BaseUtil.table(this.dto()), id);
                                    } else {
                                        List<String> joins = (List)joins.stream().distinct().collect(Collectors.toList());
                                        sql = String.format("SELECT %s FROM %s t %s WHERE t.id = %s", String.join(", ", fields), BaseUtil.table(this.dto()), String.join(" ", joins), id);
                                    }

                                    Map map = this.baseDAO.select(sql);
                                    if (map == null) {
                                        return Response.message("NO DATA");
                                    }

                                    Iterator var14 = this.attributes().iterator();

                                    while(true) {
                                        while(true) {
                                            Attribute attribute;
                                            do {
                                                if (!var14.hasNext()) {
                                                    if (map.get("remark") instanceof String) {
                                                        map.put("remark", JSON.parseObject((String)map.get("remark"), Map.class));
                                                    }

                                                    return Response.success(BeanUtil.copy(map, this.dto()));
                                                }

                                                attribute = (Attribute)var14.next();
                                            } while(attribute.getField() == null);

                                            if (attribute.getIsRemark() == 0) {
                                                if (AttributeTypeEnumerate.DTO.getK().equals(attribute.getType()) && map.get(attribute.getField()) != null) {
                                                    Map m = CollectUtil.map();
                                                    m.put(StringUtil.hump2underline(attribute.getJoinAttribute()), map.get(attribute.getField()));
                                                    if (attribute.getJoinTable() != null) {
                                                        Iterator var9 = this.children(attribute.getAttribute(), false).iterator();

                                                        while(var9.hasNext()) {
                                                            Attribute child = (Attribute)var9.next();
                                                            m.put(StringUtil.hump2underline(child.getAttribute().split("\\.")[1]), map.get(String.format("%s_%s", attribute.getAttribute(), StringUtil.hump2underline(child.getAttribute().split("\\.")[1]))));
                                                        }
                                                    }

                                                    map.put(attribute.getAttribute(), m);
                                                }

                                                if (attribute.getIsDictionary() == 2 && map.get(attribute.getField()) != null) {
                                                    map.put(attribute.getField(), JSON.parse((String)map.get(attribute.getField())));
                                                }
                                            } else if (attribute.getIsRemark() == 1) {
                                            }
                                        }
                                    }
                                }

                                attribute = (Attribute)var4.next();
                            } while(attribute.getField() == null);

                            if (attribute.getIsRemark() == 0) {
                                break;
                            }

                            if (attribute.getIsRemark() == 1) {
                            }
                        }
                    } while(!AttributeTypeEnumerate.DTO.getK().equals(attribute.getType()));
                } while(attribute.getJoinTable() == null);

                children = this.children(attribute.getAttribute(), false);
            } while(children.size() == 0);

            Iterator var7 = children.iterator();

            while(var7.hasNext()) {
                Attribute child = (Attribute)var7.next();
                fields.add(String.format("%s.%s %s_%s", attribute.getAttribute(), StringUtil.hump2underline(child.getAttribute().split("\\.")[1]), attribute.getAttribute(), StringUtil.hump2underline(child.getAttribute().split("\\.")[1])));
            }

            joins.add(String.format("LEFT JOIN %s %s ON t.%s = %s.%s", attribute.getJoinTable(), attribute.getAttribute(), attribute.getField(), attribute.getAttribute(), StringUtil.hump2underline(attribute.getJoinAttribute())));
        }
    }

```

