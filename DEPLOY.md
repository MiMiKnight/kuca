kuca deploy
========

---

### **依赖发布** 
- **设置版本**
```shell
mvn versions:set "-DnewVersion=1.0.0-SNAPSHOT"
```
- **部署至本地仓库**
```shell
mvn clean install
```
- **部署至远程仓库**
```shell
mvn clean deploy
```
- **撤回版本修改**
```shell
mvn versions:revert
```

---

### **发布记录** 
- 第1次发布
> 项目初始发布
---

